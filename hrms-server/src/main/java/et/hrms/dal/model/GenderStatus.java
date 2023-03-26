package et.hrms.dal.model;

/**
 * Represents the gender status of an individual.
 */
public enum GenderStatus {

    /**
     * Indicates no information is available for the gender status.
     */
    None("Not specified"),

    /**
     * Indicates the individual identifies as male.
     */
    Male("Male"),

    /**
     * Indicates the individual identifies as female.
     */
    Female("Female"),

    /**
     * Indicates the individual has a non-specific or non-binary gender identity.
     */
    NonSpecific("Non-specific");

    private String displayName;

    /**
     * Constructs an instance of the enumeration with the given display name.
     *
     * @param displayName the human-readable display name for the enumeration value
     */
    GenderStatus(String displayName) {
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