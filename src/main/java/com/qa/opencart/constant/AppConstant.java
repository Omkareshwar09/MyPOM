package com.qa.opencart.constant;

import java.util.Arrays;
import java.util.List;

public class AppConstant
{
	public static final int DEFAULT_MEDIUM_TIME_OUT = 10;
	public static final int DEFAULT_SHORT_TIME_OUT = 5;
	public static final int DEFAULT_LONG_TIME_OUT = 15;
	public static final int DEFAULT_LONGEST_TIME_OUT = 20;
	
	public static final String PROPERTY_FILE_PATH= "./src/test/resources/config/config.properties";
	public static final String PROPERTY_FILE_PATH_DEV = "./src/test/resources/config/dev.config.properties";
	public static final String PROPERTY_FILE_PATH_QA = "./src/test/resources/config/qa.config.properties";
	public static final String PROPERTY_FILE_PATH_STAGE = "./src/test/resources/config/stage.config.properties";
	
	public static final String LOGIN_PAGE_TITLE_VALUE = "Account Login";
	public static final String LOGIN_PAGE_URL_FRACTION_VALUE = "route=account/login";

	public static final String ACCOUNT_PAGE_TITLE_VALUE = "My Account";
	public static final String ACCOUNT_PAGE_URL_FRACTION_VALUE = "route=account/account";
	public static final int ACCOUNT_PAGE_HEADERS_COUNT = 4;
	
	public static final List<String> EXPECTED_ACCOUNT_PAGE_HEADERS_LIST = Arrays.asList(
																						"My Account","My Orders","My Affiliate Account", "Newsletter");
	
	public static final String USER_REG_SUCCESS_MESSG = "Your Account Has Been Created";
	
	//*********************** Excel Sheet Name ********************************************************************
	public static final String REGISTER_SHEET_NAME = "register"; 
	public static final String PRODUCT_SHEET_NAME = "product"; 
	
	
	
	
}
