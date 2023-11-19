module com.example.companymanager {
  requires javafx.controls;
  requires javafx.fxml;


  opens com.example.companymanager to javafx.fxml;
  exports com.example.companymanager;
}