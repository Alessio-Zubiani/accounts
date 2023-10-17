package com.eazybytes.accounts.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(
		name = "Customer",
		description = "Schema to hold Customer and Account information"
)
@Data
public class CustomerDto {
	
	@Schema(
            description = "Name of the customer", example = "Eazy Bytes"
    )
	@NotEmpty(message = "Name cannot be null or empty")
	@Size(min = 5, max = 30, message = "The length of the customer name should be between 5 and 30")
	private String name;
	
	@Schema(
            description = "Email address of the customer", example = "tutor@eazybytes.com"
    )
	@NotEmpty(message = "Email address cannot be null or empty")
	@Email(message = "Email address should be of valid value")
	private String email;
	
	@Schema(
            description = "Mobile Number of the customer", example = "9345432123"
    )
	@Pattern(regexp = "(^$|[0-9]{10})", message = "MobileNumber must be 10 digits")
	private String mobileNumber;
	
	@Schema(
            description = "Account details of the Customer"
    )
	private AccountsDto accountsDto;

}
