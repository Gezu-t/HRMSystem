package com.hrms.employee.core.dal.constants;


public class HRMSConstants {

    // Date and Time Formats
    public static final String DATE_FORMAT = "yyyy-MM-dd";
    public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String TIME_FORMAT = "HH:mm:ss";

    // Timezone
    public static final String DEFAULT_TIMEZONE = "UTC";

    // Leave and Vacation Policies
    public static final int MAX_VACATION_DAYS = 30;
    public static final int MAX_SICK_LEAVE_DAYS = 15;
    public static final int MAX_MATERNITY_LEAVE_DAYS = 180;
    public static final int MAX_PATERNITY_LEAVE_DAYS = 30;

    // Employee Constants
    public static final int MAX_PROBATION_PERIOD_DAYS = 90;
    public static final int NOTICE_PERIOD_DAYS = 30;

    // Salary and Benefits
    public static final double MINIMUM_WAGE = 1000.00; // Example value
    public static final double MAX_BONUS_PERCENTAGE = 20.0; // 20% of annual salary

    // Performance Review Constants
    public static final int PERFORMANCE_REVIEW_CYCLE_DAYS = 365; // Annual review
    public static final double PERFORMANCE_THRESHOLD = 3.0; // On a scale of 1 to 5

    // Miscellaneous
    public static final String DEFAULT_CURRENCY = "USD";
    public static final int PASSWORD_EXPIRY_DAYS = 90;
    public static final int MAX_LOGIN_ATTEMPTS = 5;

    // Email Templates
    public static final String WELCOME_EMAIL_TEMPLATE = "Welcome to the company, {name}!";
    public static final String LEAVE_APPROVAL_EMAIL_TEMPLATE = "Your leave request from {startDate} to {endDate} has been approved.";
    public static final String PASSWORD_RESET_EMAIL_TEMPLATE = "Please reset your password using this link: {resetLink}";

    // Roles and Permissions
    public static final String ROLE_ADMIN = "ADMIN";
    public static final String ROLE_MANAGER = "MANAGER";
    public static final String ROLE_EMPLOYEE = "EMPLOYEE";

    // API Endpoints (Example)
    public static final String API_BASE_URL = "https://api.hrms.com/v1";
    public static final String API_LEAVE_REQUEST_ENDPOINT = API_BASE_URL + "/leave/request";
    public static final String API_EMPLOYEE_DETAILS_ENDPOINT = API_BASE_URL + "/employee/details";

}