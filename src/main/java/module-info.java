module com.dobreagenty.aasd_javafx_demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires jade;
    requires org.json;

    opens com.dobreagenty.aasd_javafx_demo to javafx.fxml;
    exports com.dobreagenty.aasd_javafx_demo;
    exports com.dobreagenty.agents;
    exports com.dobreagenty.behaviours;
    exports com.dobreagenty.misc;
    exports com.dobreagenty.payloads;
}