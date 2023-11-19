package com.example.companymanager;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class GroupWindow {
  public static void AddGroup(){
    Stage window = new Stage();
    window.initModality(Modality.APPLICATION_MODAL);

    window.setTitle("Add Group");
    window.setMinWidth(250);

    Label head_text = new Label();
    Label group_name = new Label();
    Label max_number = new Label();

    head_text.setText("Add Group");
    group_name.setText("Name: ");
    max_number.setText("Max employees: ");

    Button ok_button = new Button("OK");
    ok_button.setOnAction(e -> window.close());

    VBox layout = new VBox(10); // 10 - odleglosci miedzy children
    layout.getChildren().addAll(head_text, group_name, max_number, ok_button);
    layout.setAlignment(Pos.CENTER);

    Scene scene = new Scene(layout);
    window.setScene(scene);
    window.showAndWait();

  }
}
