package et.hrms.dal.model;

public enum LeaveRequestStates {
    SUBMITTED("Submitted"),
    PENDING_APPROVAL("Pending Approval"),
    APPROVED("Approved"),
    REJECTED("Rejected"),
    PENDING_DISCUSSION("Pending Discussion"),
    CANCELLED("Cancelled");

    private final String displayName;

    LeaveRequestStates(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return this.displayName;
    }

    // Optional: Method to get enum from displayName
    public static LeaveRequestStates fromDisplayName(String displayName) {
        for (LeaveRequestStates state : LeaveRequestStates.values()) {
            if (state.displayName.equalsIgnoreCase(displayName)) {
                return state;
            }
        }
        throw new IllegalArgumentException("Unknown display name: " + displayName);
    }
}
