package com.uit.chophen.utils;

public class SecurityConstant {
    public static final long EXPIRATION_TIME= 432_000_000; //5 days in ms
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String JWT_TOKEN_HEADER= "Jwt-Token";
    public static final String TOKEN_CANNOT_BE_VERIFIED = "Token cannot be verified";
    public static final String GET_ARRAYS_LLC = "UIT, HCMVNU";
    public static final String GET_ARRAYS_ADMINISTRATION= "SE330";
    public static final String AUTHORITIES = "Authorities";
    public static final String FORBIDDEN_MESSAGE = "Login required";
    public static final String ACCESS_DENIED_MESSAGE = "Insufficient permissions";
    public static final String OPTIONS_HTTP_METHOD = "OPTIONS";
    public static final String[] PUBLIC_URLS = {"/uni/**", "/user/login","/user/home/**", "/user/signUp","/user/resetPassword/**", "/listings/getListings", "/listings/search", "/listingCategories/**", "/listings/getPosterProfile/**", };

    
    //User Service 
    public static final String USERNAME_ALREADY_EXISTS = "Username already exists";
    public static final String EMAIL_ALREADY_EXISTS = "Email already exists";
    public static final String NO_USER_FOUND_BY_USERNAME = "No user found by username: ";
    public static final String FOUND_USER_BY_USERNAME = "Returning found user by username: ";
    public static final String NO_USER_FOUND_BY_EMAIL = "No user found for email: ";
    public static final String EMAIL_SENT = "An email with a new password was sent to: ";

}
