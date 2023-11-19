package com.example.companymanager;

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
        e.set_salary(e.getSalary()+e_raise);
        return;
      }
    }
    System.out.println("Nie znaleziono pracownika w " + this.employee_group_name + ".");
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
    System.out.println("Nie znaleziono pracownika w " + this.employee_group_name + ".");
  }
  public List<Employee> search(String e_lastname) {
    List<Employee> found = new ArrayList<>();
    Comparator<Employee> comparator = Comparator.comparing(e -> e.lastname);
    for (Employee e : employee_list) {
      if (comparator.compare(e, new Employee("", e_lastname, 0, null, null)) == 0) {
        found.add(e);
      }
    }
    if (found.isEmpty()) {
      System.out.println("Nie znaleziono pracownika w " + this.employee_group_name + " o nazwisku " + e_lastname + ".");
    }
    return found;
  }
  public List<Employee> searchPartial(String part_of_name) {
    List<Employee> found = new ArrayList<>();
    int i = 0;
    for (Employee e : employee_list) {
      if (e.name.contains(part_of_name) || e.lastname.contains(part_of_name)) {
        found.add(e);
        i = 1;
      }
    }
    if (i == 0)
      System.out.println("Nie znaleziono pracownika w " + this.employee_group_name + " zawierajacego " + part_of_name + ".");
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
  public List<Employee> sortByName() {
    List<Employee> sorted = new ArrayList<>(employee_list);
    sorted.sort(Comparator.comparing(e -> e.name));
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
    return (double)employee_list.size() / max_employees * 100;
  }
}