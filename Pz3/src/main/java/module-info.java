module me.croshaw.pz3 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;

    exports me.croshaw.pz3;
    opens me.croshaw.pz3 to javafx.fxml;
    exports me.croshaw.pz3.gui;
    opens me.croshaw.pz3.gui to javafx.fxml;
}