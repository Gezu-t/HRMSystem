package dal.model.employee;

import java.util.HashMap;
import java.util.Map;

public enum EmployeeType {
  FULL_TIME("Full-time employee"),
  PART_TIME("Part-time employee"),
  CONTRACTOR("Contractor"),
  INTERN("Intern");

  private final String description;
  private static final Map<String, EmployeeType> DESCRIPTION_MAP = new HashMap<>();

  static {
    for (EmployeeType type : EmployeeType.values()) {
      DESCRIPTION_MAP.put(type.getDescription(), type);
    }
  }

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

  public static EmployeeType fromDescription(String description) {
    return DESCRIPTION_MAP.get(description);
  }
}
