package org.kafka.GUI;

import org.junit.Test;

import static org.junit.Assert.fail;

public class JavaFXAvailabilityTest {
    @Test
    public void testJavaFXModulesAvailability() {
        try {
            Class.forName("javafx.application.Application");
            Class.forName("javafx.fxml.FXMLLoader");
            Class.forName("javafx.scene.Scene");
        } catch (ClassNotFoundException e) {
            fail("JavaFXAvailabilityTest failed\nJavaFX classes not found: " + e.getMessage());
        }
    }
}
