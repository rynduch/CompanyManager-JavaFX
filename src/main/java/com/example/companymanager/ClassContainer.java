package com.example.companymanager;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.*;

public class ClassContainer {
  public Map<String, ClassEmployee> work_groups;

  public ClassContainer() {
    this.work_groups = new HashMap<>();
  }

  public void addClass(ClassEmployee ce) {
    this.work_groups.put(ce.employee_group_name, ce);
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

  public ObservableList<ClassEmployee> sortByPercentage() {
    ObservableList<ClassEmployee> sorted = FXCollections.observableArrayList();
    for (Map.Entry<String, ClassEmployee> group : this.work_groups.entrySet()) {
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
}