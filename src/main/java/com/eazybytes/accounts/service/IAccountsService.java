package com.eazybytes.accounts.service;

import com.eazybytes.accounts.dto.CustomerDto;

public interface IAccountsService {
	
	/**
	 * 
	 * @param customerDto - CustomerDto object
	 */
	void creatAccount(CustomerDto customerDto);
	
	/**
	 * 
	 * @param mobileNumber - String object
	 * @return Accounts details based on a given mobileNumber
	 */
	CustomerDto getAccount(String mobileNumber);
	
	/**
	 * 
	 * @param customerDto - CustomerDto object
	 * @return boolean indicating if the update of Account details is successful or not
	 */
	boolean updateAccount(CustomerDto customerDto);
	
	/**
    *
    * @param mobileNumber - Input Mobile Number
    * @return boolean indicating if the delete of Account details is successful or not
    */
	boolean deleteAccount(String mobileNumber);

}
