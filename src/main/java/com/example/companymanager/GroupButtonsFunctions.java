package com.example.companymanager;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class GroupButtonsFunctions {
  public static void AddGroup(ClassContainer container){
    Stage window = new Stage();
    window.initModality(Modality.APPLICATION_MODAL);

    window.setTitle("Add Group");
    window.setMinHeight(250);
    window.setMinWidth(350);

    window.setTitle("Add Group");
    window.setMinWidth(250);

    TextField group_name_textfield = new TextField();
    TextField max_textfield = new TextField();
    group_name_textfield.setPromptText("Name");
    max_textfield.setPromptText("Max employee");
    group_name_textfield.prefWidthProperty().bind(window.widthProperty().multiply(0.59));
    max_textfield.prefWidthProperty().bind(window.widthProperty().multiply(0.59));

    Label head_label = new Label("Add Group");
    head_label.setAlignment(Pos.CENTER);
    head_label.setFont(Font.font("Berlin Sans FB Demi", 20));
    Label l_group_name = new Label("Name: ");
    Label l_max_number = new Label("Max: ");
    Label invalid_data = new Label();
    invalid_data.setVisible(false);

    l_group_name.prefWidthProperty().bind(window.widthProperty().multiply(0.4));
    l_max_number.prefWidthProperty().bind(window.widthProperty().multiply(0.4));

    Button ok_button = new Button("OK");
    ok_button.setOnAction(e -> {
      String g_name = group_name_textfield.getText();
      String s_max = max_textfield.getText();
      if (g_name.trim().isEmpty() || s_max.trim().isEmpty()) {
        invalid_data.setVisible(true);
        invalid_data.setText("Please provide all required data.");
      } else {
        try {
          int i_max = Integer.parseInt(s_max);
          ClassEmployee clas_employee = new ClassEmployee(g_name,i_max);
          container.addClass(clas_employee);
          window.close();
        } catch (NumberFormatException ex) {
          invalid_data.setVisible(true);
          invalid_data.setText("Max must be a valid number.");
        }
      }
    });

    HBox group_name_layout = new HBox();
    HBox max_layout = new HBox();
    group_name_layout.getChildren().addAll(l_group_name, group_name_textfield);
    max_layout.getChildren().addAll(l_max_number, max_textfield);

    VBox center_layout = new VBox();
    center_layout.setPadding(new Insets(10,10,10,10));
    center_layout.getChildren().addAll(head_label, group_name_layout,max_layout, invalid_data, ok_button );
    center_layout.setAlignment(Pos.CENTER);
    center_layout.setSpacing(10);

    Scene scene = new Scene(center_layout);
    window.setScene(scene);
    window.showAndWait();

  }
  public static void RemoveGroup(ClassEmployee class_employee, ClassContainer container){
    container.work_groups.remove(class_employee.employee_group_name);
  }
}
