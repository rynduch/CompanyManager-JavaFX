package com.example.companymanager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
  public List<String> findEmpty() {
    List<String> found = new ArrayList<>();
    for (Map.Entry<String, ClassEmployee> entry : this.work_groups.entrySet()) {
      if (entry.getValue().employee_list.isEmpty()) {
        found.add(entry.getKey());
      }
    }
    return found;
  }
  public void summary() {
    for (Map.Entry<String, ClassEmployee> e : this.work_groups.entrySet()) {
      double percentage = ((double) e.getValue().employee_list.size() / e.getValue().max_employees) * 100;
      System.out.println("Nazwa grupy: " + e.getKey());
      System.out.println("Procentowe zapełnienie: " + percentage + "%");
    }
  }
//  public double getPercentage(String key){
//    double percentage = ((double) work_groups.get(key).employee_list.size() / work_groups.get(key).max_employees) * 100;
//    return percentage;
//  }
}