package com.gtltek.employee.core.dal.model;

/**
 * Represents the gender status of an individual.
 */

public enum GenderType {

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

    /**
     * -- GETTER --
     *  Returns the human-readable display name for the enumeration value.
     *
     * @return the display name
     */
    private String displayName;

    /**
     * Constructs an instance of the enumeration with the given display name.
     *
     * @param displayName the human-readable display name for the enumeration value
     */
    GenderType(String displayName) {
        this.displayName = displayName;
    }

}