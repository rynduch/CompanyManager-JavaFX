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

    TextField name_textfield = new TextField();
    TextField lastname_textfield = new TextField();
    DatePicker dob_datepicker = new DatePicker();
    TextField salary_textfield = new TextField();
    ChoiceBox<EmployeeCondition> condition_choicebox = new ChoiceBox<>();

    name_textfield.setPromptText("Name");
    lastname_textfield.setPromptText("Lastname");
    salary_textfield.setPromptText("Salary");

    name_textfield.prefWidthProperty().bind(window.widthProperty().multiply(0.59));
    lastname_textfield.prefWidthProperty().bind(window.widthProperty().multiply(0.59));
    dob_datepicker.prefWidthProperty().bind(window.widthProperty().multiply(0.59));
    salary_textfield.prefWidthProperty().bind(window.widthProperty().multiply(0.59));
    condition_choicebox.getItems().setAll(EmployeeCondition.values());
    condition_choicebox.prefWidthProperty().bind(window.widthProperty().multiply(0.59));

    // LABELS
    Label head_label = new Label("Add Employee");
    head_label.setFont(Font.font("Berlin Sans FB Demi", 20));
    head_label.setAlignment(Pos.CENTER);
    Label l_name = new Label("Name: ");
    Label l_lastname = new Label("Lastname: ");
    Label l_dob = new Label("Date of birth: ");
    Label l_salary = new Label("Salary: ");
    Label l_condition = new Label("Condition: ");
    Label invalid_data = new Label();
    invalid_data.setVisible(false);

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
      LocalDate ld_dob = dob_datepicker.getValue();
      String s_salary = salary_textfield.getText();
      EmployeeCondition ec_condition = condition_choicebox.getValue();
              if (s_name.trim().isEmpty() || s_lastname.trim().isEmpty() || ld_dob == null || s_salary.isEmpty() || ec_condition == null) {
                invalid_data.setVisible(true);
                invalid_data.setText("Please provide all required data.");
              } else {
                try {
                  double d_salary = Double.parseDouble(s_salary);
                  Employee employee = new Employee(s_name, s_lastname, d_salary, ld_dob, ec_condition);
                  all.addEmployee(employee);
                  window.close();
                } catch (NumberFormatException ex) {
                  invalid_data.setVisible(true);
                  invalid_data.setText("Salary must be a valid number.");
                }
              }
            }
    );

    HBox name_layout = new HBox();
    HBox lastname_layout = new HBox();
    HBox dob_layout = new HBox();
    HBox salary_layout = new HBox();
    HBox condition_layout = new HBox();
    name_layout.getChildren().addAll(l_name, name_textfield);
    lastname_layout.getChildren().addAll(l_lastname, lastname_textfield);
    dob_layout.getChildren().addAll(l_dob, dob_datepicker);
    salary_layout.getChildren().addAll(l_salary, salary_textfield);
    condition_layout.getChildren().addAll(l_condition, condition_choicebox);
    dob_layout.setSpacing(5);

    VBox center_layout = new VBox();
    center_layout.setPadding(new Insets(10,10,10,10));
    center_layout.getChildren().addAll(head_label, name_layout,lastname_layout, dob_layout, salary_layout, condition_layout, invalid_data, ok_button);
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
    DatePicker dob_datepicker = new DatePicker();
    TextField salary_textfield = new TextField();
    name_textfield.setPromptText(employee.name);
    lastname_textfield.setPromptText(employee.lastname);
    salary_textfield.setPromptText(String.valueOf(employee.getSalary()));
    name_textfield.prefWidthProperty().bind(window.widthProperty().multiply(0.59));
    lastname_textfield.prefWidthProperty().bind(window.widthProperty().multiply(0.59));
    dob_datepicker.prefWidthProperty().bind(window.widthProperty().multiply(0.59));
    salary_textfield.prefWidthProperty().bind(window.widthProperty().multiply(0.59));
    ChoiceBox<EmployeeCondition> condition_choicebox = new ChoiceBox<>();
    condition_choicebox.getItems().setAll(EmployeeCondition.values());
    condition_choicebox.prefWidthProperty().bind(window.widthProperty().multiply(0.59));

    // LABELS
    Label head_label = new Label("Change Employee");
    head_label.setFont(Font.font("Berlin Sans FB Demi", 20));
    head_label.setAlignment(Pos.CENTER);
    Label l_name = new Label("Name: ");
    Label l_lastname = new Label("Lastname: ");
    Label l_dob = new Label("Date of birth: ");
    Label l_salary = new Label("Salary: ");
    Label l_condition = new Label("Condition: ");
    Label invalid_data = new Label();
    invalid_data.setVisible(false);

    l_name.prefWidthProperty().bind(window.widthProperty().multiply(0.4));
    l_lastname.prefWidthProperty().bind(window.widthProperty().multiply(0.4));
    l_dob.prefWidthProperty().bind(window.widthProperty().multiply(0.4));
    l_salary.prefWidthProperty().bind(window.widthProperty().multiply(0.4));
    l_condition.prefWidthProperty().bind(window.widthProperty().multiply(0.4));

    // BUTTON
    Button ok_button = new Button("OK");
    ok_button.setOnAction(e -> {
      if(name_textfield != null && !name_textfield.getText().isEmpty()) all.work_groups.get("All").changeName(employee, name_textfield.getText());
      if(lastname_textfield != null && !lastname_textfield.getText().isEmpty()) all.work_groups.get("All").changeLastname(employee, lastname_textfield.getText());
      if(dob_datepicker != null && dob_datepicker.getValue() != null)  all.work_groups.get("All").changeDob(employee, dob_datepicker.getValue());
      if(salary_textfield != null && !salary_textfield.getText().isEmpty()) {
        double d_salary;
        try {
          d_salary = Double.parseDouble(salary_textfield.getText());
          all.work_groups.get("All").changeSalary(employee, d_salary);
          window.close();
        } catch (NumberFormatException ex) {
          invalid_data.setVisible(true);
          invalid_data.setText("Salary must be a valid number.");
        }
      }
      if(condition_choicebox.getValue() != null) all.work_groups.get("All").changeCondition(employee, condition_choicebox.getValue() );
    });

    HBox name_layout = new HBox();
    HBox lastname_layout = new HBox();
    HBox dob_layout = new HBox();
    HBox salary_layout = new HBox();
    HBox condition_layout = new HBox();
    name_layout.getChildren().addAll(l_name, name_textfield);
    lastname_layout.getChildren().addAll(l_lastname, lastname_textfield);
    dob_layout.getChildren().addAll(l_dob, dob_datepicker);
    salary_layout.getChildren().addAll(l_salary, salary_textfield);
    condition_layout.getChildren().addAll(l_condition, condition_choicebox);
    dob_layout.setSpacing(5);

    VBox center_layout = new VBox(); // 10 - odleglosci miedzy children
    center_layout.setPadding(new Insets(10,10,10,10));
    center_layout.getChildren().addAll(head_label, name_layout, lastname_layout, dob_layout, salary_layout, condition_layout,invalid_data, ok_button);
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