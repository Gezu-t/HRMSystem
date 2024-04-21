package et.hrms.controller.attendance.impl;

import et.hrms.controller.attendance.AttendanceController;
import et.hrms.dal.dto.attendance.AttendanceDTO;
import et.hrms.service.attendance.AttendanceService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/attendances")
public class AttendanceControllerImpl implements AttendanceController {

  private final AttendanceService attendanceService;

    public AttendanceControllerImpl(AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }


    @Override
  @PostMapping("/{employeeId}")
  public ResponseEntity<AttendanceDTO> createAttendance(@PathVariable("employeeId") Long employeeId,
                                                        @RequestBody AttendanceDTO attendanceDTO) {
    AttendanceDTO dto = attendanceService.saveAttendance(employeeId, attendanceDTO);
    return new ResponseEntity<>(dto, HttpStatus.CREATED);
  }

  @Override
  @GetMapping("/{attendanceId}")
  public ResponseEntity<AttendanceDTO> getAttendanceById(@PathVariable("attendanceId") Long attendanceId) {
    AttendanceDTO attendanceDTO = attendanceService.getAttendanceById(attendanceId);
    if (attendanceDTO == null) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<>(attendanceDTO, HttpStatus.OK);
  }

  @Override
  @PutMapping("/{attendanceId}")
  public ResponseEntity<AttendanceDTO> updateAttendance(@PathVariable("attendanceId") Long attendanceId,
                                                        @RequestBody AttendanceDTO attendanceDTO) {
    AttendanceDTO updatedAttendanceDTO = attendanceService.updateAttendance(attendanceId, attendanceDTO);
    if (updatedAttendanceDTO == null) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<>(updatedAttendanceDTO, HttpStatus.OK);
  }


  @Override
  @GetMapping
  public ResponseEntity<List<AttendanceDTO>> getAllAttendance(@RequestParam(defaultValue = "0") int page,
                                                              @RequestParam(defaultValue = "10") int size,
                                                              @RequestParam(defaultValue = "id, Asc") String[] sort) {
    Sort sortOrder = Sort.by(sort[0]);
    if (sort.length > 1) {
      sortOrder = sort[1].equalsIgnoreCase("desc") ? sortOrder.descending() : sortOrder.ascending();
    }
    try {
      List<AttendanceDTO> attendanceDTOS = attendanceService.getAllAttendance(page, size, sortOrder);
      return new ResponseEntity<>(attendanceDTOS, HttpStatus.OK);
    } catch (EntityNotFoundException e) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

}
