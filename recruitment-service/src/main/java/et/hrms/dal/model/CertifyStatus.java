package et.hrms.dal.model;

public enum CertifyStatus {
    APPROVED("Approved"),
    PENDING("Pending"),
    REJECTED("Rejected");

    private final String description;

    CertifyStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }
}
