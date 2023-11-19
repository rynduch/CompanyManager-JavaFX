package com.example.companymanager;
import javafx.geometry.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.control.*;

import java.time.LocalDate;

public class EmployeeButtonsFunctions {
  public static void AddEmployeeToAll(ClassEmployee all){
    Stage window = new Stage();
    window.initModality(Modality.APPLICATION_MODAL);

    window.setTitle("Add Employee");
    window.setMinHeight(300);
    window.setMinWidth(350);

    //TEXTFIELD
    TextField name_textfield = new TextField();
    TextField lastname_textfield = new TextField();
    TextField day_dob_textfield = new TextField();
    TextField month_dob_textfield = new TextField();
    TextField year_dob_textfield = new TextField();
    TextField salary_textfield = new TextField();
    name_textfield.setPromptText("Name");
    lastname_textfield.setPromptText("Lastname");
    day_dob_textfield.setPromptText("Day");
    month_dob_textfield.setPromptText("Month");
    year_dob_textfield.setPromptText("Year");
    salary_textfield.setPromptText("Salary");
    name_textfield.prefWidthProperty().bind(window.widthProperty().multiply(0.59));
    lastname_textfield.prefWidthProperty().bind(window.widthProperty().multiply(0.59));
    day_dob_textfield.prefWidthProperty().bind(name_textfield.widthProperty().multiply(0.33));
    month_dob_textfield.prefWidthProperty().bind(name_textfield.widthProperty().multiply(0.33));
    year_dob_textfield.prefWidthProperty().bind(name_textfield.widthProperty().multiply(0.33));
    salary_textfield.prefWidthProperty().bind(window.widthProperty().multiply(0.59));
    ChoiceBox<EmployeeCondition> condition_choicebox = new ChoiceBox<>();
    condition_choicebox.getItems().setAll(EmployeeCondition.values());
    condition_choicebox.prefWidthProperty().bind(window.widthProperty().multiply(0.59));

    // LABELS
    Label head_label = new Label();
    head_label.setAlignment(Pos.CENTER);
    Label l_name = new Label();
    Label l_lastname = new Label();
    Label l_dob = new Label();
    Label l_salary = new Label();
    Label l_condition = new Label();

    head_label.setText("Add Employee");
    head_label.setFont(Font.font("Berlin Sans FB Demi", 20));
    l_name.setText("Name: ");
    l_lastname.setText("Lastname: ");
    l_dob.setText("Date of birth: ");
    l_salary.setText("Salary: ");
    l_condition.setText("Condition: ");
    l_name.prefWidthProperty().bind(window.widthProperty().multiply(0.4));
    l_lastname.prefWidthProperty().bind(window.widthProperty().multiply(0.4));
    l_dob.prefWidthProperty().bind(window.widthProperty().multiply(0.4));
    l_salary.prefWidthProperty().bind(window.widthProperty().multiply(0.4));
    l_condition.prefWidthProperty().bind(window.widthProperty().multiply(0.4));

    // BUTTON
    Button ok_button = new Button("OK");
    ok_button.setOnAction(e -> {
      String s_name = name_textfield.getText();
      String s_lastname = lastname_textfield.getText();
      int day = Integer.parseInt(day_dob_textfield.getText());
      int month = Integer.parseInt(month_dob_textfield.getText());
      int year = Integer.parseInt(year_dob_textfield.getText());
      LocalDate ld_dob = LocalDate.of(year, month, day);
      double d_salary = Double.parseDouble(salary_textfield.getText());
      EmployeeCondition ec_condition = condition_choicebox.getValue();
      Employee employee = new Employee(s_name,s_lastname, d_salary, ld_dob, ec_condition);
      all.addEmployee(employee);
      window.close();}
    );

    HBox name_layout = new HBox();
    HBox lastname_layout = new HBox();
    HBox dob_layout = new HBox();
    HBox salary_layout = new HBox();
    HBox condition_layout = new HBox();
    name_layout.getChildren().addAll(l_name, name_textfield);
    lastname_layout.getChildren().addAll(l_lastname, lastname_textfield);
    dob_layout.getChildren().addAll(l_dob,day_dob_textfield, month_dob_textfield,year_dob_textfield);
    salary_layout.getChildren().addAll(l_salary, salary_textfield);
    condition_layout.getChildren().addAll(l_condition, condition_choicebox);
    dob_layout.setSpacing(5);

    VBox center_layout = new VBox(); // 10 - odleglosci miedzy children
    center_layout.setPadding(new Insets(10,10,10,10));
    center_layout.getChildren().addAll(head_label, name_layout,lastname_layout, dob_layout, salary_layout, condition_layout, ok_button);
    center_layout.setAlignment(Pos.CENTER);
    center_layout.setSpacing(10);

    BorderPane add_employees_layout = new BorderPane();
    add_employees_layout.setPadding(new Insets(10,10,10,10));
    add_employees_layout.setCenter(center_layout);

    Scene scene = new Scene(add_employees_layout);
    window.setScene(scene);
    window.showAndWait();
  }
  public static void RemoveEmployee(Employee employee, ClassContainer class_container){
    if ( employee != null) {
      for(ClassEmployee gr: class_container.getObsClassEmployeeList()){
        gr.removeEmployee(employee);
      }
    }
  }
  public static void ChangeEmployee(Employee employee, ClassContainer all){
    Stage window = new Stage();
    window.initModality(Modality.APPLICATION_MODAL);

    window.setTitle("Change Employee");
    window.setMinHeight(300);
    window.setMinWidth(350);

    //TEXTFIELD
    TextField name_textfield = new TextField();
    TextField lastname_textfield = new TextField();
    TextField day_dob_textfield = new TextField();
    TextField month_dob_textfield = new TextField();
    TextField year_dob_textfield = new TextField();
    TextField salary_textfield = new TextField();
    name_textfield.setPromptText(employee.name);
    lastname_textfield.setPromptText(employee.lastname);
    day_dob_textfield.setPromptText(String.valueOf(employee.dob.getDayOfMonth()));
    month_dob_textfield.setPromptText(String.valueOf(employee.dob.getMonth()));
    year_dob_textfield.setPromptText(String.valueOf(employee.dob.getYear()));
    salary_textfield.setPromptText(String.valueOf(employee.getSalary()));
    name_textfield.prefWidthProperty().bind(window.widthProperty().multiply(0.59));
    lastname_textfield.prefWidthProperty().bind(window.widthProperty().multiply(0.59));
    day_dob_textfield.prefWidthProperty().bind(name_textfield.widthProperty().multiply(0.33));
    month_dob_textfield.prefWidthProperty().bind(name_textfield.widthProperty().multiply(0.33));
    year_dob_textfield.prefWidthProperty().bind(name_textfield.widthProperty().multiply(0.33));
    salary_textfield.prefWidthProperty().bind(window.widthProperty().multiply(0.59));
    ChoiceBox<EmployeeCondition> condition_choicebox = new ChoiceBox<>();
    condition_choicebox.getItems().setAll(EmployeeCondition.values());
    condition_choicebox.prefWidthProperty().bind(window.widthProperty().multiply(0.59));

    // LABELS
    Label head_label = new Label();
    head_label.setAlignment(Pos.CENTER);
    Label l_name = new Label();
    Label l_lastname = new Label();
    Label l_dob = new Label();
    Label l_salary = new Label();
    Label l_condition = new Label();

    head_label.setText("Change Employee");
    head_label.setFont(Font.font("Berlin Sans FB Demi", 20));
    l_name.setText("Name: ");
    l_lastname.setText("Lastname: ");
    l_dob.setText("Date of birth: ");
    l_salary.setText("Salary: ");
    l_condition.setText("Condition: ");
    l_name.prefWidthProperty().bind(window.widthProperty().multiply(0.4));
    l_lastname.prefWidthProperty().bind(window.widthProperty().multiply(0.4));
    l_dob.prefWidthProperty().bind(window.widthProperty().multiply(0.4));
    l_salary.prefWidthProperty().bind(window.widthProperty().multiply(0.4));
    l_condition.prefWidthProperty().bind(window.widthProperty().multiply(0.4));

    // BUTTON
    Button ok_button = new Button("OK");
    ok_button.setOnAction(e -> {
      //all.work_groups.get("All").employee_list.remove(employee);
      if(name_textfield != null && !name_textfield.getText().isEmpty()) all.work_groups.get("All").changeName(employee, name_textfield.getText());
      if(lastname_textfield != null && !lastname_textfield.getText().isEmpty()) all.work_groups.get("All").changeLastname(employee, lastname_textfield.getText());
      if(day_dob_textfield != null && !day_dob_textfield.getText().isEmpty()) all.work_groups.get("All").changeDayDob(employee, Integer.parseInt(day_dob_textfield.getText()));
      if(month_dob_textfield != null && !month_dob_textfield.getText().isEmpty()) all.work_groups.get("All").changeMonthDob(employee, Integer.parseInt(month_dob_textfield.getText()));
      if(year_dob_textfield != null && !year_dob_textfield.getText().isEmpty()) all.work_groups.get("All").changeYearDob(employee, Integer.parseInt(year_dob_textfield.getText()));
      if(salary_textfield != null && !salary_textfield.getText().isEmpty()) all.work_groups.get("All").changeSalary(employee, Double.parseDouble(salary_textfield.getText()));
      if(condition_choicebox.getValue() != null) all.work_groups.get("All").changeCondition(employee, condition_choicebox.getValue() );

      //all.work_groups.get("All").employee_list.add(employee);
      window.close();}
    );

    HBox name_layout = new HBox();
    HBox lastname_layout = new HBox();
    HBox dob_layout = new HBox();
    HBox salary_layout = new HBox();
    HBox condition_layout = new HBox();
    name_layout.getChildren().addAll(l_name, name_textfield);
    lastname_layout.getChildren().addAll(l_lastname, lastname_textfield);
    dob_layout.getChildren().addAll(l_dob,day_dob_textfield, month_dob_textfield,year_dob_textfield);
    salary_layout.getChildren().addAll(l_salary, salary_textfield);
    condition_layout.getChildren().addAll(l_condition, condition_choicebox);
    dob_layout.setSpacing(5);

    VBox center_layout = new VBox(); // 10 - odleglosci miedzy children
    center_layout.setPadding(new Insets(10,10,10,10));
    center_layout.getChildren().addAll(head_label, name_layout,lastname_layout, dob_layout, salary_layout, condition_layout, ok_button);
    center_layout.setAlignment(Pos.CENTER);
    center_layout.setSpacing(10);

    BorderPane add_employees_layout = new BorderPane();
    add_employees_layout.setPadding(new Insets(10,10,10,10));
    add_employees_layout.setCenter(center_layout);

    Scene scene = new Scene(add_employees_layout);
    window.setScene(scene);
    window.showAndWait();
  }
}