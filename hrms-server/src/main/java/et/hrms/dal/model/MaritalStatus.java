package et.hrms.dal.model;

public enum MaritalStatus {
    /**
     * Indicates no information is available for the marital status.
     */
    None("Not specified"),

    /**
     * Indicates the individual is not married.
     */
    Single("Single"),

    /**
     * Indicates the individual is married.
     */
    Married("Married"),

    /**
     * Indicates the individual is divorced.
     */
    Divorced("Divorced"),

    /**
     * Indicates the individual's spouse has passed away.
     */
    Widowed("Widowed");

    private String displayName;

    /**
     * Constructs an instance of the enumeration with the given display name.
     *
     * @param displayName the human-readable display name for the enumeration value
     */
    MaritalStatus(String displayName) {
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
