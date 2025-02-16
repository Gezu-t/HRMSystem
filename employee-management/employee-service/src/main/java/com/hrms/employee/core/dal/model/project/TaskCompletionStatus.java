package com.hrms.employee.core.dal.model.project;

/**
 * Represents the completion status of a task or activity.
 */
public enum TaskCompletionStatus {

    /**
     * Indicates the task or activity is not complete.
     */
    Not_Complete("Not Complete"),

    /**
     * Indicates the task or activity has been completed successfully (passed).
     */
    Pass("Pass"),

    /**
     * Indicates the task or activity has not been completed successfully (failed).
     */
    Fail("Fail");

    private String displayName;

    /**
     * Constructs an instance of the enumeration with the given display name.
     *
     * @param displayName the human-readable display name for the enumeration value
     */
    TaskCompletionStatus(String displayName) {
        this.displayName = displayName;
    }

    /**
     * Returns the human-readable display name for the enumeration value.
     *
     * @return the display name
     */
    public String getDisplayName() {
        return displayName;
    }
}

