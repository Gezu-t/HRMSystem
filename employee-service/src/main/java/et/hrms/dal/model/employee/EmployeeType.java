package et.hrms.dal.model.employee;

public enum EmployeeType {
  FULL_TIME("Full-time employee"),
  PART_TIME("Part-time employee"),
  CONTRACTOR("Contractor"),
  INTERN("Intern");

  private final String description;

  EmployeeType(String description) {
    this.description = description;
  }

  public String getDescription() {
    return description;
  }

  @Override
  public String toString() {
    return name() + " (" + description + ")";
  }
}
