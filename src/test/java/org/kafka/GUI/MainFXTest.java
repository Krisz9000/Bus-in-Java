package org.kafka.GUI;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;

public class MainFXTest {
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
