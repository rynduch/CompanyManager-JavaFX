package com.example.companymanager;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;
import java.util.*;

public class ClassEmployee {
  String employee_group_name;
  ArrayList<Employee> employee_list;
  int max_employees;

  public ClassEmployee(String n, int m) {
    this.employee_group_name = n;
    this.employee_list = new ArrayList<>();
    this.max_employees = m;
  }

  public void addEmployee(Employee e_employee) {
    employee_list.add(e_employee);
  }

  public void removeEmployee(Employee e_employee) {
    Iterator<Employee> iterator = employee_list.iterator();
    while (iterator.hasNext()) {
      Employee e = iterator.next();
      if (e.compare_full_name(e_employee) == 0) {
        iterator.remove();
        return;
      }
    }
  }

  public void changeCondition(Employee e_employee, EmployeeCondition e_condition) {
    for (Employee e : employee_list) {
      if (e.compare_full_name(e_employee) == 0) {
        e.condition = e_condition;
        return;
      }
    }
  }

  public void changeName(Employee e_employee, String n) {
    for (Employee e : employee_list) {
      if (e.compare_full_name(e_employee) == 0) {
        e.setName(n);
        return;
      }
    }
  }

  public void changeLastname(Employee e_employee, String l) {
    for (Employee e : employee_list) {
      if (e.compare_full_name(e_employee) == 0) {
        e.setLastname(l);
        return;
      }
    }
  }

  public void changeSalary(Employee e_employee, double s) {
    for (Employee e : employee_list) {
      if (e.compare_full_name(e_employee) == 0) {
        e.setSalary(s);
        return;
      }
    }
  }

  public void changeDob(Employee e_employee, LocalDate d) {
    for (Employee e : employee_list) {
      if (e.compare_full_name(e_employee) == 0) {
        e.setDob(d);
        return;
      }
    }
  }

  public ObservableList<Employee> searchPartial(String part_of_name) {
    ObservableList<Employee> found = FXCollections.observableArrayList();
    String lc_part_of_name = part_of_name.toLowerCase();
    for (Employee e : employee_list) {
      if (e.name.toLowerCase().contains(lc_part_of_name) || e.lastname.toLowerCase().contains(lc_part_of_name)) {
        found.add(e);
      }
    }
    return found;
  }

  public ObservableList<Employee> sortByName() {
    ObservableList<Employee> sorted = FXCollections.observableArrayList();
    ArrayList<Employee> pom;
    pom = employee_list;
    pom.sort(Comparator.comparing(Employee::getLastname));
    sorted.addAll(pom);
    return sorted;
  }

  public double getPercentage() {
    return Math.round((double) employee_list.size() / (double) max_employees * 100);
  }

  public ObservableList<Employee> getObsEmpoyeeList() {
    ObservableList<Employee> obs_list = FXCollections.observableArrayList();
    obs_list.addAll(employee_list);
    return obs_list;
  }
}