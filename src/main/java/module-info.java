module anderson.c482 {
    requires javafx.controls;
    requires javafx.fxml;


    opens anderson.c482 to javafx.fxml;
    exports anderson.c482;
    exports anderson.c482.controller;
    opens anderson.c482.controller to javafx.fxml;
    exports anderson.c482.Model;
    opens anderson.c482.Model to javafx.fxml;
}