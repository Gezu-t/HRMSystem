package com.gtltek.messaging.model;

public enum LeaveRequestEvents {
  SUBMITTED("Submitted", "The leave request has been submitted and is awaiting review."),
  APPROVE("Approve", "The leave request has been approved."),
  REJECT("Reject", "The leave request has been rejected."),
  PENDING_DISCUSSION("Pending Discussion", "The leave request is pending discussion."),
  CANCEL("Cancel", "The leave request has been cancelled.");

  private final String label;
  private final String description;

  LeaveRequestEvents(String label, String description) {
    this.label = label;
    this.description = description;
  }

  public String getLabel() {
    return label;
  }

  public String getDescription() {
    return description;
  }
}
