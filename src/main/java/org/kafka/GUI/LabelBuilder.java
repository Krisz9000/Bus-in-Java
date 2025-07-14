package org.kafka.GUI;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.text.Font;

/**
 * Builder Pattern implementation for creating new {@link Label}s on the fly easier with pre-set default values.
 *
 * @author Krisztián Kafka
 * @see javafx.scene.control.Label
 */
final class LabelBuilder {

    private final String text;
    private String fontName = Font.getDefault().getName();
    private int fontSize = 16;
    private Pos alignment = Pos.CENTER;
    private double maxWidth = Double.MAX_VALUE;

    public LabelBuilder(String text) {
        this.text = text;
    }

    public LabelBuilder font(String fontName) {
        this.fontName = fontName;
        return this;
    }

    public LabelBuilder fontSize(int fontSize) {
        this.fontSize = fontSize;
        return this;
    }

    public LabelBuilder alignment(Pos alignment) {
        this.alignment = alignment;
        return this;
    }

    public LabelBuilder maxWidth(int maxWidth) {
        this.maxWidth = maxWidth;
        return this;
    }

    public Label build() {
        Label label = new Label(text);
        label.setFont(new Font(fontName, fontSize));
        label.setAlignment(alignment);
        label.setMaxWidth(maxWidth);
        label.setWrapText(true);
        return label;
    }
}
