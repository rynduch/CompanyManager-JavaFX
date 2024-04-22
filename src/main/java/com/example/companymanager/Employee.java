package com.example.companymanager;

import java.time.LocalDate;

public class Employee implements Comparable<Employee> {
  String name;
  String lastname;
  EmployeeCondition condition;
  LocalDate dob;
  private double salary;

  public Employee(String e_name, String e_lastname, double e_salary, LocalDate e_dob, EmployeeCondition e_condition) {
    this.name = e_name;
    this.lastname = e_lastname;
    this.condition = e_condition;
    this.dob = e_dob;
    this.salary = e_salary;
  }

  @Override
  public int compareTo(Employee e_employee) {
    return this.lastname.compareTo(e_employee.lastname);
  }

  public int compare_full_name(Employee e_employee) {
    if (this.compareTo(e_employee) != 0) {
      return 1;
    } else {
      if (this.name.equals(e_employee.name)) {
        return 0;
      } else {
        return 1;
      }
    }
  }

  public double getSalary() {
    return this.salary;
  }

  public void setSalary(double s) {
    this.salary = s;
  }

  public String getFullName() {
    return name + " " + lastname;
  }

  public String getLastname() {
    return this.lastname;
  }

  public void setName(String n) {
    this.name = n;
  }

  public void setLastname(String l) {
    this.lastname = l;
  }

  public void setDob(LocalDate d) {
    this.dob = d;
  }

}
