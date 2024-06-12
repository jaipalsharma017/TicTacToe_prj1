module com.example.tictactoe_prj1 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.tictactoe_prj1 to javafx.fxml;
    exports com.example.tictactoe_prj1;
}