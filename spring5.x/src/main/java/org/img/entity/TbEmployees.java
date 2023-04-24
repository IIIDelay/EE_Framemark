package org.img.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TbEmployees {
    private String employeeId;
    private String constraint;
    private String primary;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String hireDate;
    private String jobId;
    private Double salary;
    private String commissionPct;
    private String managerId;
    private String departmentId;
    private String logInfo;
}
