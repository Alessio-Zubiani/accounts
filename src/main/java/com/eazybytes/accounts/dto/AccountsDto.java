package com.eazybytes.accounts.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(
        name = "Accounts",
        description = "Schema to hold Account information"
)
@Data
public class AccountsDto {
	
	@Schema(
            description = "Account Number of Eazy Bank account", example = "3454433243"
    )
	@NotEmpty(message = "AccountNumber cannot be null or empty")
	@Pattern(regexp = "(^$|[0-9]{10})", message = "AccountNumber must be 10 digits")
	private Long accountNumber;
	
	 @Schema(
            description = "Account type of Eazy Bank account", example = "Savings"
    )
	@NotEmpty(message = "AccountType cannot be null or empty")
	private String accountType;
	
	 @Schema(
            description = "Eazy Bank branch address", example = "123 NewYork"
    )
	@NotEmpty(message = "BranchAddress cannot be null or empty")
	private String branchAddress;

}
