package com.example.companymanager;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.*;

public class ClassEmployee{
  String employee_group_name;
  ArrayList<Employee> employee_list;
  int max_employees;
  public ClassEmployee(String n, int m) {
    this.employee_group_name = n;
    this.employee_list = new ArrayList<>();
    this.max_employees = m;
  }
  public boolean classEmployeeIsFull(){
    if (employee_list.size() >= max_employees) {
      return true;
    }
    else {
      return false;
    }
  }
  public void addEmployee(Employee e_employee) {

//    for (Employee e : employee_list) {
//      if (e.compare_full_name(e_employee) == 0) {
//        System.out.println("Pracownik o takim imieniu i nazwisku już istnieje.");
//        return;
//      }
//    }
      employee_list.add(e_employee);
  }

  public void addSalary(Employee e_employee, double e_raise) {
    for (Employee e : employee_list) {
      if (e.compare_full_name(e_employee) == 0) {
        e.setSalary(e.getSalary()+e_raise);
        return;
      }
    }
    //System.out.println("Nie znaleziono pracownika w " + this.employee_group_name + ".");
  }
  public void removeEmployee(Employee e_employee){
    Iterator<Employee> iterator = employee_list.iterator();
    while (iterator.hasNext()) {
      Employee e = iterator.next();
      if (e.compare_full_name(e_employee) == 0) {
        iterator.remove();
        e_employee = null;
        return;
      }
    }
    //System.out.println("Nie znaleziono pracownika w " + this.employee_group_name + ".");
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
  public void changeDayDob(Employee e_employee, int d) {
    for (Employee e : employee_list) {
      if (e.compare_full_name(e_employee) == 0) {
        e.setDayDob(d);
        return;
      }
    }
  }
  public void changeMonthDob(Employee e_employee, int m) {
    for (Employee e : employee_list) {
      if (e.compare_full_name(e_employee) == 0) {
        e.setMonthDob(m);
        return;
      }
    }
  }
  public void changeYearDob(Employee e_employee, int y) {
    for (Employee e : employee_list) {
      if (e.compare_full_name(e_employee) == 0) {
        e.setYearDob(y);
        return;
      }
    }
  }
  public ObservableList<Employee> searchPartial(String part_of_name) {
    ObservableList<Employee> found = FXCollections.observableArrayList();
    String lc_part_of_name = part_of_name.toLowerCase();
    for (Employee e : employee_list) {
      if(e.name.toLowerCase().contains(lc_part_of_name) || e.lastname.toLowerCase().contains(lc_part_of_name))  {
        found.add(e);
      }
    }
    return found;
  }
  public int countByCondition(EmployeeCondition condition) {
    int i = 0;
    for (Employee e : employee_list) {
      if (e.condition == condition) {
        i++;
      }
    }
    return i;
  }
  public void summary() {
    System.out.println("PRACOWNICY - grupa " + this.employee_group_name);
    for (Employee e : employee_list) {
      e.print_name();
      System.out.print("\n");
    }
  }
  public ObservableList<Employee> sortByName() {
    ObservableList<Employee> sorted = FXCollections.observableArrayList();
    ArrayList<Employee> pom = new ArrayList<>();
    pom = employee_list;
    pom.sort(Comparator.comparing(Employee::getLastname));
    for (Employee e : pom){
      sorted.add(e);
    }
    return sorted;
  }
  public List<Employee> sortBySalary() {
    List<Employee> sortedList = new ArrayList<>(employee_list);
    sortedList.sort(Comparator.comparing(Employee::getSalary).reversed());
    return sortedList;
  }
  public Employee max() {
    return Collections.max(employee_list, Comparator.comparing(Employee::getSalary));
  }
  public double getPercentage(){
    return (double)employee_list.size() / (double) max_employees * 100;
  }
  public ObservableList<Employee> getObsEmpoyeeList() {
    ObservableList<Employee> obs_list = FXCollections.observableArrayList();
    for (Employee e : employee_list){
      obs_list.add(e);
    }
    return obs_list;
  }
}