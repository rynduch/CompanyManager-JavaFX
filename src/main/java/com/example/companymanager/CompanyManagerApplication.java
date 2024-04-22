package com.example.companymanager;

import javafx.application.Application;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;

import java.time.LocalDate;


public class CompanyManagerApplication extends Application {
  ObservableList<Employee> workers = FXCollections.observableArrayList(
          new Employee("Jan", "Kowalski", 8000, LocalDate.of(1998, 1, 25), EmployeeCondition.OBECNY),
          new Employee("Dariusz", "Makowski", 7000, LocalDate.of(1980, 10, 21), EmployeeCondition.NIEOBECNY),
          new Employee("Juliusz", "Kaczmarczyk", 9500, LocalDate.of(1990, 8, 15), EmployeeCondition.DELEGACJA),
          new Employee("Izabela", "Walczak", 6000, LocalDate.of(1974, 1, 30), EmployeeCondition.CHORY),
          new Employee("Bartłomiej", "Andrzejewski", 7000, LocalDate.of(1981, 6, 13), EmployeeCondition.NIEOBECNY),
          new Employee("Maciej", "Woźniak", 8000, LocalDate.of(1975, 6, 4), EmployeeCondition.OBECNY),
          new Employee("Cezary", "Pawlak", 5000, LocalDate.of(1962, 6, 3), EmployeeCondition.OBECNY),
          new Employee("Robert", "Szymczak", 7500, LocalDate.of(1998, 4, 4), EmployeeCondition.DELEGACJA),
          new Employee("Zofia", "Głowacka", 8500, LocalDate.of(1998, 6, 22), EmployeeCondition.OBECNY),
          new Employee("Ewelina", "Ziółkowska", 9000, LocalDate.of(1992, 7, 22), EmployeeCondition.OBECNY)
  );
  ObservableList<ClassEmployee> groups = FXCollections.observableArrayList(
          new ClassEmployee("All", 100),
          new ClassEmployee("Programmers", 5),
          new ClassEmployee("Managers", 5),
          new ClassEmployee("Testers", 3)
  );
  ClassContainer container = new ClassContainer();
  Stage window;
  Scene menu, employees_scene, groups_scene;
  private TableView employee_table = new TableView();
  private TableView groups_table = new TableView();
  private Integer current_g_table = 0;
  private Integer current_e_table = 0;

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) throws Exception {

    for (Employee worker : workers) {
      groups.get(0).addEmployee(worker);
    }
    groups.get(1).addEmployee(workers.get(1));
    groups.get(1).addEmployee(workers.get(3));
    groups.get(1).addEmployee(workers.get(5));
    groups.get(1).addEmployee(workers.get(7));
    groups.get(2).addEmployee(workers.get(2));
    groups.get(2).addEmployee(workers.get(4));
    groups.get(2).addEmployee(workers.get(9));
    groups.get(3).addEmployee(workers.get(8));
    groups.get(3).addEmployee(workers.get(0));

    window = primaryStage;

    for (ClassEmployee group : groups) {
      container.addClass(group);
    }

    // LABELS
    Label menu_label = new Label("Company Manager");
    Label employees_label = new Label("Employees");
    Label groups_label = new Label("Groups");
    Label search_e_label = new Label("Search: ");
    menu_label.setAlignment(Pos.CENTER);
    menu_label.setFont(Font.font("Berlin Sans FB Demi", 30));
    employees_label.setFont(Font.font("Berlin Sans FB Demi", 20));
    groups_label.setFont(Font.font("Berlin Sans FB Demi", 20));
    search_e_label.setMaxWidth(100);
    search_e_label.setAlignment(Pos.CENTER);
    //VBox na label
    VBox all_employees_g_label = new VBox();

    // BUTTONS
    // employees_button
    Button employees_button = new Button("Employees");
    employees_button.setOnAction(e -> window.setScene(employees_scene));
    employees_button.setPrefWidth(100);
    // groups_button
    Button groups_button = new Button("Groups");
    groups_button.setOnAction(e -> window.setScene(groups_scene));
    groups_button.setPrefWidth(100);
    // back_e_button
    Button back_e_button = new Button("Back");
    back_e_button.setOnAction(e -> {
      employee_table.setItems(container.work_groups.get("All").getObsEmpoyeeList());
      groups_table.setItems(container.getObsClassEmployeeList());
      groups_table.refresh();
      window.setScene(menu);
    });
    back_e_button.setMaxWidth(Double.MAX_VALUE);
    // back_g_button
    Button back_g_button = new Button("Back");
    back_g_button.setOnAction(e -> {
      groups_table.setItems(container.getObsClassEmployeeList());
      groups_table.refresh();
      window.setScene(menu);
    });
    back_g_button.setMaxWidth(Double.MAX_VALUE);
    // add_e_button
    Button add_e_button = new Button("Add");
    add_e_button.setOnAction(e -> EmployeeButtonsFunctions.AddEmployeeToAll(container.work_groups.get("All")));
    add_e_button.setMaxWidth(Double.MAX_VALUE);
    // add_g_button
    Button add_g_button = new Button("Add");
    add_g_button.setOnAction(e -> GroupButtonsFunctions.AddGroup(container));
    add_g_button.setMaxWidth(Double.MAX_VALUE);
    // remove_e_button
    Button remove_e_button = new Button("Remove");
    remove_e_button.setOnAction(e -> {
      Employee selectedEmployee = (Employee) employee_table.getSelectionModel().getSelectedItem();
      if (selectedEmployee != null) {
        //employee_table.refresh();
        EmployeeButtonsFunctions.RemoveEmployee(selectedEmployee, container);
      }
    });
    remove_e_button.setMaxWidth(Double.MAX_VALUE);
    // remove_g_button
    Button remove_g_button = new Button("Remove");
    remove_g_button.setOnAction(e -> {
      ClassEmployee selectedClassEmployee = (ClassEmployee) groups_table.getSelectionModel().getSelectedItem();
      if (selectedClassEmployee != null) {
        //groups_table.refresh();
        GroupButtonsFunctions.RemoveGroup(selectedClassEmployee, container);
      }
    });
    remove_g_button.setMaxWidth(Double.MAX_VALUE);
    // find_empty_g_button
    Button find_empty_g_button = new Button("Find Empty");
    find_empty_g_button.setOnAction(e -> {
      if (current_g_table != 1) {
        ObservableList<ClassEmployee> empty_groups = container.findEmpty();
        groups_table.setItems(empty_groups);
        current_g_table = 1;
      } else {
        groups_table.setItems(container.getObsClassEmployeeList());
        current_g_table = 0;
      }
    });
    find_empty_g_button.setMaxWidth(Double.MAX_VALUE);
    // sort_e_by_lastname_button
    Button sort_e_by_lastname_button = new Button("Sort by lastname");
    sort_e_by_lastname_button.setOnAction(e -> {
      if (current_e_table != 1) {
        ObservableList<Employee> sorted_e = container.work_groups.get("All").sortByName();
        employee_table.setItems(sorted_e);
        current_e_table = 1;
      } else {
        employee_table.setItems(container.work_groups.get("All").getObsEmpoyeeList());
        current_e_table = 0;
      }
    });
    sort_e_by_lastname_button.setMaxWidth(Double.MAX_VALUE);
    // sort_g_by_percentage_button
    Button sort_g_by_percentage_button = new Button("Sort by percentage");
    sort_g_by_percentage_button.setOnAction(e -> {
      if (current_g_table != 2) {
        ObservableList<ClassEmployee> sorted_groups = container.sortByPercentage();
        groups_table.setItems(sorted_groups);
        current_g_table = 2;
      } else {
        groups_table.setItems(container.getObsClassEmployeeList());
        current_g_table = 0;
      }
    });
    sort_g_by_percentage_button.setMaxWidth(Double.MAX_VALUE);
    // refresh_e_button
    Button refresh_e_button = new Button("Refresh");
    refresh_e_button.setOnAction(e -> {
      ObservableList<Employee> refreshed = container.work_groups.get("All").getObsEmpoyeeList();
      employee_table.setItems(refreshed);
      employee_table.refresh();
    });
    refresh_e_button.setMaxWidth(Double.MAX_VALUE);
    // refresh_g_button
    Button refresh_g_button = new Button("Refresh");
    refresh_g_button.setOnAction(e -> {
      groups_table.setItems(container.getObsClassEmployeeList());
      groups_table.refresh();
    });
    refresh_g_button.setMaxWidth(Double.MAX_VALUE);
    // change_e_button
    Button change_e_button = new Button("Change");
    change_e_button.setOnAction(e -> {
      Employee selectedClassEmployee = (Employee) employee_table.getSelectionModel().getSelectedItem();
      if (selectedClassEmployee != null) {
        EmployeeButtonsFunctions.ChangeEmployee(selectedClassEmployee, container);
      }
    });
    change_e_button.setMaxWidth(Double.MAX_VALUE);

    // POLE EDYCYJNE
    TextField search_e_textfield = new TextField();
    search_e_textfield.setPromptText("Name/Lastname");
    search_e_textfield.setMaxWidth(Double.MAX_VALUE);
    search_e_label.prefHeightProperty().bind(search_e_textfield.heightProperty());
    search_e_textfield.setOnAction(e -> {
      String searched_part = search_e_textfield.getText();
      employee_table.setItems(container.work_groups.get("All").searchPartial(searched_part));
    });

    // TABELA EMPLOYEES
    employee_table.setEditable(true);
    TableColumn<Employee, String> c_name = new TableColumn<>("Name");
    TableColumn<Employee, String> c_lastname = new TableColumn<>("Lastname");
    TableColumn<Employee, LocalDate> c_date_of_birth = new TableColumn<>("Date of Birth");
    TableColumn c_salary = new TableColumn("Salary");
    TableColumn<Employee, EmployeeCondition> c_condition = new TableColumn<>("Condition");
    c_name.prefWidthProperty().bind(employee_table.widthProperty().multiply(0.2));
    c_lastname.prefWidthProperty().bind(employee_table.widthProperty().multiply(0.2));
    c_date_of_birth.prefWidthProperty().bind(employee_table.widthProperty().multiply(0.19));
    c_salary.prefWidthProperty().bind(employee_table.widthProperty().multiply(0.2));
    c_condition.prefWidthProperty().bind(employee_table.widthProperty().multiply(0.2));
    c_name.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().name));
    c_lastname.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().lastname));
    c_date_of_birth.setCellValueFactory(cellData -> new SimpleObjectProperty(cellData.getValue().dob));
    c_salary.setCellValueFactory(new PropertyValueFactory<Employee, Double>("salary"));
    c_condition.setCellValueFactory(cellData -> new SimpleObjectProperty(cellData.getValue().condition));
    employee_table.setItems(container.work_groups.get("All").getObsEmpoyeeList());
    employee_table.getColumns().addAll(c_name, c_lastname, c_date_of_birth, c_salary, c_condition);

    // TABELA GROUPS
    groups_table.setEditable(true);
    TableColumn<ClassEmployee, String> c_grname = new TableColumn<>("Name");
    TableColumn<ClassEmployee, Integer> c_max = new TableColumn<>("Max");
    TableColumn<ClassEmployee, Double> c_percentage = new TableColumn<>("Percentage");
    c_grname.prefWidthProperty().bind(groups_table.widthProperty().multiply(0.5));
    c_max.prefWidthProperty().bind(groups_table.widthProperty().multiply(0.25));
    c_percentage.prefWidthProperty().bind(groups_table.widthProperty().multiply(0.24));
    c_grname.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().employee_group_name));
    c_max.setCellValueFactory(cellData -> new SimpleObjectProperty(cellData.getValue().max_employees));
    c_percentage.setCellValueFactory(new PropertyValueFactory<ClassEmployee, Double>("percentage"));
    groups_table.setItems(container.getObsClassEmployeeList());
    groups_table.getColumns().addAll(c_grname, c_max, c_percentage);
    groups_table.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
      all_employees_g_label.getChildren().clear();
      Label employees_g_label = new Label("Employees:");
      employees_g_label.setFont(Font.font("Berlin Sans FB Demi", 15));
      all_employees_g_label.getChildren().add(employees_g_label);
      ClassEmployee selectedClassEmployee = (ClassEmployee) groups_table.getSelectionModel().getSelectedItem();
      if (selectedClassEmployee != null) {
        for (Employee employee : selectedClassEmployee.employee_list) {
          Label full_name = new Label(employee.getFullName());
          all_employees_g_label.getChildren().add(full_name);
        }
      }
    });

    // LAYOUTS
    // menu_layout - children dodawane sa w pionie
    VBox menu_layout = new VBox();
    menu_layout.setSpacing(10);
    menu_layout.getChildren().addAll(menu_label, employees_button, groups_button);
    menu_layout.setAlignment(Pos.CENTER);
    // emplyees_layout
    VBox employee_buttons_layout = new VBox();
    employee_buttons_layout.setSpacing(10); // odleglosc miedzy elementami
    employee_buttons_layout.getChildren().addAll(add_e_button, remove_e_button, change_e_button);
    employee_buttons_layout.setMinWidth(150);
    employee_buttons_layout.setAlignment(Pos.CENTER);
    HBox search_employee_layout = new HBox();
    search_employee_layout.setSpacing(10);
    search_employee_layout.getChildren().addAll(search_e_label, search_e_textfield);
    search_employee_layout.setAlignment(Pos.TOP_RIGHT);
    VBox search_refresh_layout = new VBox();
    search_refresh_layout.setSpacing(10);
    search_refresh_layout.getChildren().setAll(search_employee_layout, refresh_e_button, sort_e_by_lastname_button);
    VBox center_employee_layout = new VBox();
    center_employee_layout.setSpacing(10);
    center_employee_layout.getChildren().addAll(employee_table);
    center_employee_layout.setAlignment(Pos.CENTER);
    BorderPane right_employees_layout = new BorderPane();
    right_employees_layout.setPadding(new Insets(20, 10, 10, 20));
    right_employees_layout.setCenter(employee_buttons_layout);
    right_employees_layout.setTop(search_refresh_layout);
    right_employees_layout.setBottom(back_e_button);
    BorderPane employees_layout = new BorderPane();
    employees_layout.setPadding(new Insets(10, 10, 10, 10));
    employees_layout.setTop(employees_label);
    employees_layout.setCenter(center_employee_layout);
    employees_layout.setRight(right_employees_layout);
    // groups_layout
    VBox groups_buttons_layout = new VBox();
    groups_buttons_layout.setSpacing(10);
    groups_buttons_layout.getChildren().addAll(add_g_button, remove_g_button, find_empty_g_button, back_g_button);
    groups_buttons_layout.setMinWidth(250);
    groups_buttons_layout.setAlignment(Pos.CENTER);
    VBox refresh_sort_layout = new VBox();
    refresh_sort_layout.setSpacing(10);
    refresh_sort_layout.getChildren().addAll(refresh_g_button, sort_g_by_percentage_button);
    refresh_sort_layout.setPadding(new Insets(0, 10, 10, 0));
    BorderPane right_groups_borderpane = new BorderPane();
    right_groups_borderpane.setBottom(groups_buttons_layout);
    right_groups_borderpane.setTop(refresh_sort_layout);
    right_groups_borderpane.setCenter(all_employees_g_label);
    right_groups_borderpane.setPadding(new Insets(20, 10, 10, 20));
    BorderPane groups_layout = new BorderPane();
    groups_layout.setPadding(new Insets(10, 10, 10, 10));
    groups_layout.setTop(groups_label);
    groups_layout.setCenter(groups_table);
    groups_layout.setRight(right_groups_borderpane);

    // SCENES
    // Zawartosc strony
    menu = new Scene(menu_layout, 700, 500);
    employees_scene = new Scene(employees_layout, 700, 500);
    //employees_scene = new Scene(new Group());
    //((Group) employees_scene.getRoot()).getChildren().addAll(employees_buttons);
    groups_scene = new Scene(groups_layout, 700, 500);

    // Pierwsze okno po uruchomieniu programu
    window.setScene(menu);
    window.setTitle("Company Manager");
    window.show();
    window.setMinWidth(700);
    window.setMinHeight(500);

  }
}