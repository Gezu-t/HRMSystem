import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { EmployeeDTO } from '../dtos/employee.dto';

@Injectable({
  providedIn: 'root'
})
export class EmployeeService {
  private employeeUrl = '/api/employee';

  constructor(private http: HttpClient) { }

  getEmployee(): Observable<EmployeeDTO> {
    return this.http.get<EmployeeDTO>(this.employeeUrl);
  }

  createEmployee(employee: EmployeeDTO): Observable<EmployeeDTO> {
    return this.http.post<EmployeeDTO>(this.employeeUrl, employee);
  }

  updateEmployee(employee: EmployeeDTO): Observable<EmployeeDTO> {
    return this.http.put<EmployeeDTO>(`${this.employeeUrl}/${employee.employeeId}`, employee);
  }

  deleteEmployee(employeeId: number): Observable<EmployeeDTO> {
    return this.http.delete<EmployeeDTO>(`${this.employeeUrl}/${employeeId}`);
  }
}
