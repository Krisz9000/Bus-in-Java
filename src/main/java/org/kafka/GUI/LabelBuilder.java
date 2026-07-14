package org.kafka.GUI;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.text.Font;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Builder Pattern implementation for creating new {@link Label}s on the fly easier with pre-set default values.
 *
 * @author Krisztián Kafka
 * @see javafx.scene.control.Label
 */
final class LabelBuilder {

    //Hashmap for looking up past builders based on their finished labels. Used for updating labels
    private static final Map<Label, LabelBuilder> builtLabels= new HashMap<>();

    private final String baseText;
    private String displayText;
    private String fontName = Font.getDefault().getName();
    private int fontSize = 16;
    private Pos alignment = Pos.CENTER;
    private double maxWidth = Double.MAX_VALUE;
    private final ArrayList<Object> extraArgs = new ArrayList<>();

    public LabelBuilder(String baseText) {
        this.baseText = baseText;
    }

    /** Looks up provided label's og builder, copies modified values from it, refreshes arg values with new ones.<br>
     * Used only for updating previously built Labels.<br>
     * Can be built immidiatelly after construction
     */
    private LabelBuilder(Label label, Object ... args) {
        LabelBuilder og = builtLabels.get(label);
        this.baseText = og.baseText;
        this.fontName = og.fontName;
        this.fontSize = og.fontSize;
        this.alignment = og.alignment;
        this.maxWidth = og.maxWidth;
        this.addExtraArgs(args);
        this.updateText();
    }

    public LabelBuilder updateText() {
        if (!extraArgs.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            for (Object o : extraArgs) {
                sb.append(' ');
                sb.append(o.toString());
            }
            this.displayText = baseText + sb.toString().stripTrailing();
        } else this.displayText = baseText;
        return this;
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

    //Add any number and type of Objects into a List connected to this Label
    public LabelBuilder addExtraArgs(Object ... args) {
        this.extraArgs.addAll(Arrays.asList(args));
        return this;
    }

    public Label build() {
        Label label = new Label(displayText);
        label.setFont(new Font(fontName, fontSize));
        label.setAlignment(alignment);
        label.setMaxWidth(maxWidth);
        label.setWrapText(true);
        builtLabels.put(label, this);
        return label;
    }

    /** Update dynamic values of Labels' texts.
     * @see LabelBuilder#LabelBuilder(Label, Object...)
     * @param label {@link Label} to be updated.
     * @param args Variables that need to be updated
     * @return Same Label with updated values.
     */
    public static Label updateLabel(Label label, Object ... args) {
        return new LabelBuilder(label, args).build();
    }
}
