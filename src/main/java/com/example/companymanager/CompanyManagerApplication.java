package com.example.companymanager;

import javafx.application.Application; // Aplication - klasa bazowa, kazdy program JavaFX musi ja miec
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene; // Scene - zawartosc okna
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.MapValueFactory;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.stage.Stage; // Stage - okno

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import javax.security.auth.callback.Callback;
import java.time.LocalDate;
import java.util.Map;


public class CompanyManagerApplication extends Application{
    ObservableList<Employee> workers = FXCollections.observableArrayList(
            new Employee("Jan", "Kowalski", 8000, LocalDate.of(1998, 1, 25), EmployeeCondition.OBECNY),
            new Employee("Kim", "Kardashian", 7000, LocalDate.of(1980, 10, 21), EmployeeCondition.NIEOBECNY),
            new Employee("Jennifer", "Lawrence", 9500, LocalDate.of(1990, 8, 15), EmployeeCondition.DELEGACJA),
            new Employee("Christian", "Bale", 6000, LocalDate.of(1974 , 1, 30), EmployeeCondition.CHORY),
            new Employee("Chris", "Evans", 7000, LocalDate.of(1981, 6, 13), EmployeeCondition.NIEOBECNY),
            new Employee("Angelina", "Jolie", 8000, LocalDate.of(1975, 6, 4), EmployeeCondition.OBECNY),
            new Employee("Tom", "Cruise", 5000, LocalDate.of(1962, 6, 3), EmployeeCondition.OBECNY),
            new Employee("Robert", "Downey Jr.", 7500, LocalDate.of(1998, 4, 4), EmployeeCondition.DELEGACJA),
            new Employee("Meryl", "Streep", 8500, LocalDate.of(1998, 6, 22), EmployeeCondition.OBECNY),
            new Employee("Selena", "Gomez", 9000, LocalDate.of(1992, 7, 22), EmployeeCondition.OBECNY)
    );
    ObservableList<ClassEmployee> groups = FXCollections.observableArrayList(
            new ClassEmployee("All", 100),
            new ClassEmployee("Programmers", 5),
            new ClassEmployee("Testers", 3)
    );
    ClassContainer container = new ClassContainer();
    Stage window;
    Scene menu, employees_scene, groups_scene;
    private TableView employee_table = new TableView();
    private TableView groups_table = new TableView();

    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception{

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

        window = primaryStage;

        // LABELS
        Label menu_label = new Label ("Company Manager");
        Label employees_label = new Label ("Employees");
        Label groups_label = new Label ("Groups");
        menu_label.setAlignment(Pos.CENTER);
        menu_label.setFont(Font.font("Berlin Sans FB Demi", 30));
        employees_label.setFont(Font.font("Berlin Sans FB Demi", 20));
        groups_label.setFont(Font.font("Berlin Sans FB Demi", 20));
        //VBox na label
        VBox all_employees_g_label = new VBox();

        // BUTTONS
        // employees_button
        Button employees_button = new Button("Employees");
        employees_button.setOnAction(e->window.setScene(employees_scene));
        employees_button.setPrefWidth(100);
        // groups_button
        Button groups_button = new Button("Groups");
        groups_button.setOnAction(e->window.setScene(groups_scene));
        groups_button.setPrefWidth(100);
        // back_e_button
        Button back_e_button = new Button("Back");
        back_e_button.setOnAction(e->window.setScene(menu));
        back_e_button.setMaxWidth(Double.MAX_VALUE);
        // back_g_button
        Button back_g_button = new Button("Back");
        back_g_button.setOnAction(e->window.setScene(menu));
        back_g_button.setMaxWidth(Double.MAX_VALUE);
        // add_e_button
        Button add_e_button = new Button("Add employee");
        add_e_button.setOnAction(e-> EmployeeWindow.AddEmployeeToAll(workers));
        add_e_button.setMaxWidth(Double.MAX_VALUE);
        // add_g_button
        Button add_g_button = new Button("Add");
        add_g_button.setOnAction(e-> GroupWindow.AddGroup());
        add_g_button.setMaxWidth(Double.MAX_VALUE);
        // remove_e_button
        Button remove_e_button = new Button("Remove employee");
        remove_e_button.setOnAction(e -> {
            Employee selectedEmployee = (Employee) employee_table.getSelectionModel().getSelectedItem();
            if (selectedEmployee != null) {
                employee_table.refresh();
                EmployeeWindow.RemoveEmployee(selectedEmployee,workers,groups);
                //employee_tableview.getItems().remove(selectedEmployee);
            }
        });
        remove_e_button.setMaxWidth(Double.MAX_VALUE);

        // TABELA EMPLOYEES
        employee_table.setEditable(true);
        TableColumn<Employee, String> c_name = new TableColumn<>("Name");
        TableColumn<Employee, String> c_lastname = new TableColumn<>("Lastname");
        TableColumn<Employee, LocalDate>  c_date_of_birth = new TableColumn<>("Date of Birth");
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
        c_salary.setCellValueFactory( new PropertyValueFactory<Employee, Double >("salary"));
        c_condition.setCellValueFactory(cellData -> new SimpleObjectProperty(cellData.getValue().condition));
        employee_table.setItems(workers);
        employee_table.getColumns().addAll(c_name, c_lastname, c_date_of_birth,c_salary,c_condition);


        // TABELA GROUPS
        groups_table.setEditable(true);
        TableColumn<ClassEmployee, String> c_grname = new TableColumn<>("Name");
        TableColumn<ClassEmployee, Double> c_percentage = new TableColumn<>("Percentage");
        c_grname.prefWidthProperty().bind(groups_table.widthProperty().multiply(0.70));
        c_percentage.prefWidthProperty().bind(groups_table.widthProperty().multiply(0.29));
        c_grname.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().employee_group_name));
        c_percentage.setCellValueFactory( new PropertyValueFactory<ClassEmployee, Double >("percentage"));
        groups_table.setItems(groups);
        groups_table.getColumns().addAll(c_grname,c_percentage);
        groups_table.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            all_employees_g_label.getChildren().clear();
                Label employees_g_label = new Label ("Employees:");
                employees_g_label.setFont(Font.font("Berlin Sans FB Demi", 15));
                all_employees_g_label.getChildren().add(employees_g_label);
                ClassEmployee selectedClassEmployee = (ClassEmployee) groups_table.getSelectionModel().getSelectedItem();
                if (selectedClassEmployee != null) {
                    for(Employee employee: selectedClassEmployee.employee_list){
                        Label full_name = new Label (employee.getFullName());
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
        employee_buttons_layout.setPadding(new Insets(20,10,10,20)); // marginesy
        employee_buttons_layout.getChildren().addAll(add_e_button,remove_e_button, back_e_button);
        employee_buttons_layout.setMinWidth(150);
        employee_buttons_layout.setAlignment(Pos.CENTER);
        BorderPane right_employees_borderpane = new BorderPane();
        right_employees_borderpane.setBottom(employee_buttons_layout);
        BorderPane employees_layout = new BorderPane();
        employees_layout.setPadding(new Insets(10,10,10,10));
        employees_layout.setTop(employees_label);
        employees_layout.setCenter(employee_table);
        employees_layout.setRight(right_employees_borderpane);
        // groups_layout
        VBox groups_buttons_layout = new VBox();
        groups_buttons_layout.setSpacing(10);
        groups_buttons_layout.getChildren().addAll(add_g_button, back_g_button);
        groups_buttons_layout.setMinWidth(250);
        groups_buttons_layout.setAlignment(Pos.CENTER);
        BorderPane right_groups_borderpane = new BorderPane();
        right_groups_borderpane.setBottom(groups_buttons_layout);
        right_groups_borderpane.setTop(all_employees_g_label);
        right_groups_borderpane.setTop(all_employees_g_label);
        right_groups_borderpane.setPadding(new Insets(20,10,10,20));
        BorderPane groups_layout = new BorderPane();
        groups_layout.setPadding(new Insets(10,10,10,10));
        groups_layout.setTop(groups_label);
        groups_layout.setCenter(groups_table);
        groups_layout.setRight(right_groups_borderpane);

        // SCENES
        // Zawartosc strony
        menu = new Scene(menu_layout, 600,500);
        employees_scene = new Scene(employees_layout, 600,500);
        //employees_scene = new Scene(new Group());
        //((Group) employees_scene.getRoot()).getChildren().addAll(employees_buttons);
        groups_scene = new Scene (groups_layout, 600, 500);

        // Pierwsze okno po uruchomieniu programu
        window.setScene(menu);
        window.setTitle("Company Manager");
        window.show();
        window.setMinWidth(600);
        window.setMinHeight(500);

    }
}