package com.eazybytes.accounts.service.impl;

import java.util.Optional;
import java.util.Random;

import com.eazybytes.accounts.constants.AccountsConstants;
import com.eazybytes.accounts.dto.AccountsDto;
import com.eazybytes.accounts.dto.CustomerDto;
import com.eazybytes.accounts.entity.Accounts;
import com.eazybytes.accounts.entity.Customer;
import com.eazybytes.accounts.exception.CustomerAlreadyExistsException;
import com.eazybytes.accounts.exception.ResourceNotFoundException;
import com.eazybytes.accounts.mapper.AccountsMapper;
import com.eazybytes.accounts.mapper.CustomerMapper;
import com.eazybytes.accounts.repository.AccountsRepository;
import com.eazybytes.accounts.repository.CustomerRepository;
import com.eazybytes.accounts.service.IAccountsService;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements IAccountsService {
	
	private final AccountsRepository accountsRepository;
	private final CustomerRepository customerRepository;
	

	@Override
	public void creatAccount(CustomerDto customerDto) {
		
		Customer customer = CustomerMapper.mapToCustomer(customerDto, new Customer());
		Optional<Customer> optional = this.customerRepository.findByMobileNumber(customerDto.getMobileNumber());
		if(optional.isPresent()) {
			throw new CustomerAlreadyExistsException(
					new StringBuilder("Customer with mobile number [").append(customerDto.getMobileNumber())
						.append("] already registered").toString());
		}
		
		this.customerRepository.save(customer);
		this.accountsRepository.save(this.createNewAccount(customer));
	}
	
	@Override
	public CustomerDto getAccount(String mobileNumber) {
		
		Customer customer = this.customerRepository.findByMobileNumber(mobileNumber)
				.orElseThrow(() -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber));
		
		Accounts accounts = this.accountsRepository.findByCustomerId(customer.getCustomerId())
				.orElseThrow(() -> new ResourceNotFoundException("Accounts", "customerId", customer.getCustomerId().toString()));
	
		CustomerDto customerDto = CustomerMapper.mapToCustomerDto(customer, new CustomerDto());
		customerDto.setAccountsDto(AccountsMapper.mapToAccountsDto(accounts, new AccountsDto()));
		
		return customerDto;
	}
	
	@Override
	public boolean updateAccount(CustomerDto customerDto) {
		
		boolean isUpdated = false;
        AccountsDto accountsDto = customerDto.getAccountsDto();
        if(accountsDto != null) {
            Accounts accounts = this.accountsRepository.findById(accountsDto.getAccountNumber())
            		.orElseThrow(() -> new ResourceNotFoundException("Accounts", "accountNumber", accountsDto.getAccountNumber().toString()));
            AccountsMapper.mapToAccounts(accountsDto, accounts);
            accounts = this.accountsRepository.save(accounts);

            Long customerId = accounts.getCustomerId();
            Customer customer = this.customerRepository.findById(customerId)
            		.orElseThrow(() -> new ResourceNotFoundException("Customer", "customerId", customerId.toString()));
            CustomerMapper.mapToCustomer(customerDto, customer);
            this.customerRepository.save(customer);
            
            isUpdated = true;
        }
        
        return  isUpdated;
	}
	
    @Override
    public boolean deleteAccount(String mobileNumber) {
        
    	Customer customer = this.customerRepository.findByMobileNumber(mobileNumber)
    			.orElseThrow(() -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
        );
    	
        this.accountsRepository.deleteByCustomerId(customer.getCustomerId());
        this.customerRepository.deleteById(customer.getCustomerId());
        
        return true;
    }
	
	/**
	 * 
	 * @param customer - Customer object
	 * @return new account details
	 */
	private Accounts createNewAccount(Customer customer) {
        
		Accounts newAccount = new Accounts();
        newAccount.setCustomerId(customer.getCustomerId());
        long randomAccNumber = 1000000000L + new Random().nextInt(900000000);

        newAccount.setAccountNumber(randomAccNumber);
        newAccount.setAccountType(AccountsConstants.SAVINGS);
        newAccount.setBranchAddress(AccountsConstants.ADDRESS);
        
        return newAccount;
    }

}