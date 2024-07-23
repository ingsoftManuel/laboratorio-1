module cue.edu.co.vote {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires mysql.connector.j;
    requires static lombok;
    requires java.desktop;

    opens cue.edu.co.vote to javafx.fxml;
    exports cue.edu.co.vote.view;
}