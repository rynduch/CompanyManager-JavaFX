package com.example.companymanager;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.*;

public class ClassContainer {
  public Map<String, ClassEmployee> work_groups;

  public ClassContainer() {
    this.work_groups = new HashMap<>();
  }
  public void addClass(String gr_name, double max) {
    ClassEmployee group = new ClassEmployee(gr_name,(int)max);
    this.work_groups.put(gr_name, group);
  }
  public void addClass(ClassEmployee ce) {
    this.work_groups.put(ce.employee_group_name, ce);
  }

  public void removeClass(String name) {
    this.work_groups.remove(name);
  }
  public ObservableList<ClassEmployee> findEmpty() {
    ObservableList<ClassEmployee> list = FXCollections.observableArrayList();
    for (Map.Entry<String, ClassEmployee> group : this.work_groups.entrySet()) {
      if (group.getValue().getPercentage() == 0.0) {
        list.add(group.getValue());
      }
    }
    return list;
  }
  public void summary() {
    for (Map.Entry<String, ClassEmployee> e : this.work_groups.entrySet()) {
      double percentage = ((double) e.getValue().employee_list.size() / e.getValue().max_employees) * 100;
      System.out.println("Nazwa grupy: " + e.getKey());
      System.out.println("Procentowe zapełnienie: " + percentage + "%");
    }
  }
  public ObservableList<ClassEmployee> sortByPercentage() {
    ObservableList<ClassEmployee> sorted = FXCollections.observableArrayList();
    for (Map.Entry<String, ClassEmployee> group : this.work_groups.entrySet()){
      sorted.add(group.getValue());
    }
    sorted.sort(Comparator.comparing(ClassEmployee::getPercentage).reversed());
    return sorted;
  }
  public ObservableList<ClassEmployee> getObsClassEmployeeList() {
    ObservableList<ClassEmployee> obs_list = FXCollections.observableArrayList();
    for (Map.Entry<String, ClassEmployee> group : this.work_groups.entrySet()) {
      obs_list.add(group.getValue());
    }
    return obs_list;
  }
//  public double getPercentage(String key){
//    double percentage = ((double) work_groups.get(key).employee_list.size() / work_groups.get(key).max_employees) * 100;
//    return percentage;
//  }
}