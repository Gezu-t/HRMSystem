package et.hrms.dal.model;

public enum GenderType {
    MALE("Male"),
    FEMALE("Female"),
    NON_BINARY("Non-binary"),
    OTHER("Other");

    private final String description;

    private GenderType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
