module com.example.java_term_project {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


//    opens com.example.java_term_project to javafx.fxml;
//    exports com.example.java_term_project;
    exports Controller;
    opens Controller;
    opens User to javafx.fxml;
    exports User;
    opens Obj to javafx.base;
    exports RestaurantController;
    opens RestaurantController;
    opens Restaurant to javafx.fxml;
    exports Restaurant;
    opens Server to javafx.fxml;
    exports Server;
}