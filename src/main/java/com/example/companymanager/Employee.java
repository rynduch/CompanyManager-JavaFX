package com.example.companymanager;

import java.time.LocalDate;

public class Employee implements Comparable<Employee> {
  String name;
  String lastname;
  EmployeeCondition condition;
  LocalDate dob;
  private double salary;
  public Employee(String e_name, String e_lastname, double e_salary, LocalDate e_dob,EmployeeCondition e_condition ){
    this.name = e_name;
    this.lastname = e_lastname;
    this.condition = e_condition;
    this.dob = e_dob;
    this.salary = e_salary;
  }
  public void print(){
    System.out.print("\n- Dane pracownika");
    System.out.print("\n\tImie: " + name);
    System.out.print("\n\tNazwisko: " + lastname);
    System.out.print("\n\tStan: " + condition );
    System.out.print("\n\tData urodzenia: " + dob);
    System.out.print("\n\tWynagrodzenie: " + salary + "\n");
  }
  @Override
  public int compareTo(Employee e_employee) { // zwraca 0 gdy takie same
    return this.lastname.compareTo(e_employee.lastname);
    //interface Comparable jest częścią standardowej biblioteki języka Java
  }
  public int compare_full_name(Employee e_employee) {
    if (this.compareTo(e_employee) != 0) { //jesli nazwiska sa rozne
      return 1;
    } else {
      if(this.name.equals(e_employee.name)){ //jesli imiona sa takie same
        return 0;
      }
      else{
        return 1;
      }
    }
  }
  public void print_name(){
    System.out.print(this.name + " " + this.lastname);
  }
  public double getSalary(){
    return this.salary;
  }
  public void setSalary(double s){
    this.salary = s;
  }
  public String getFullName(){
    String full_name = name + " " + lastname;
    return full_name;
  }
  public String getLastname(){
    return this.lastname;
  }
  public void setName(String n){
    this.name = n;
  }
  public void setLastname(String l){
    this.lastname = l;
  }
  public void setDayDob(int d){
    this.dob.withDayOfMonth(d);
  }
  public void setMonthDob(int m){
    this.dob.withMonth(m);
  }
  public void setYearDob(int y){
    this.dob.withYear(y);
  }

  public void setCondition(EmployeeCondition ec){
    this.condition = ec;
  }
}
