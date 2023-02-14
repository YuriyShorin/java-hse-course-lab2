module com.example.letterscounter {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.letterscounter to javafx.fxml;
    exports com.example.letterscounter;
}