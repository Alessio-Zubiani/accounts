package com.eazybytes.accounts.dto;

import java.util.List;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Component
@ConfigurationProperties(prefix = "accounts")
public class AccountsContactInfoDto {
	
	private String message;
	private Map<String, String> contactDetails;
	private List<String> onCallSupport;

}
