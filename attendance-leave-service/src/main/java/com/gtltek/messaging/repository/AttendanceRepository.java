package com.gtltek.messaging.repository;

import com.gtltek.messaging.model.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

    @Query("SELECT a FROM Attendance a WHERE a.employeeId = :employeeId AND a.absenteeDate BETWEEN :startDate AND :endDate")
    List<Attendance> findByEmployeeIdAndDateBetween(@Param("employeeId") Long employeeId, @Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    @Query("SELECT a FROM Attendance a WHERE a.forDepartment = :department AND a.absenteeDate BETWEEN :startDate AND :endDate")
    List<Attendance> findByDepartmentAndDateBetween(@Param("department") String department, @Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    @Query("SELECT a FROM Attendance a WHERE a.employeeId = :employeeId AND a.absenteeDate = CURRENT_DATE")
    Optional<Attendance> findTodayAttendanceByEmployeeId(@Param("employeeId") Long employeeId);

    @Query("SELECT a FROM Attendance a WHERE a.employeeId = :employeeId AND a.absenteeDate = CURRENT_DATE")
    Optional<Attendance> findTodayAttendance(@Param("employeeId") Long employeeId);

    @Query("SELECT a FROM Attendance a WHERE a.employeeId = :employeeId AND a.absenteeDate < CURRENT_DATE")
    List<Attendance> findAbsenteeismByEmployeeId(@Param("employeeId") Long employeeId);

    @Query("SELECT a FROM Attendance a WHERE a.absenteeDate BETWEEN :startDate AND :endDate")
    List<Attendance> findByDateBetween(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

//    @Query("SELECT a FROM Attendance a WHERE a.employeeId = (SELECT e.id FROM Employee e WHERE e.name = :name)")
//    Attendance findByEmployeeName(@Param("name") String name);


    @Query("SELECT a FROM Attendance a WHERE a.employeeId = :employeeId AND a.startTime BETWEEN :startDateTime AND :endDateTime")
    List<Attendance> findByEmployeeIdAndStartTimeBetween(@Param("employeeId") Long employeeId, @Param("startDateTime") LocalDateTime startDateTime, @Param("endDateTime") LocalDateTime endDateTime);
}
