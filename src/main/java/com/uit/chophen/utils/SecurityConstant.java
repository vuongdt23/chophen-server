package com.uit.chophen.utils;

public class SecurityConstant {
    public static final long EXPIRATION_TIME= 432_000_000; //5 days in ms
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String JWT_TOKEN_HEADER= "Jwt-Token";
    public static final String TOKEN_CANNOT_BE_VERIFIED = "Token cannot be verified";
    public static final String GET_ARRAYS_LLC = "Get arrays, llc";
    public static final String GET_ARRAYS_ADMINISTRATION= "User management portal";
    public static final String AUTHORITIES = "Authorities";
    public static final String FORBIDDEN_MESSAGE = "Login required";
    public static final String ACCESS_DENIED_MESSAGE = "Insufficient permissions";
    public static final String OPTIONS_HTTP_METHOD = "OPTIONS";
    public static final String[] PUBLIC_URLS = {"/user/login","/user/register","/user/resetpassword/**"};

}
