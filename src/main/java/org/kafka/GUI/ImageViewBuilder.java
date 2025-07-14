package org.kafka.GUI;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Builder Pattern implementation for creating new {@link ImageView}s on the fly easier with pre-set default values.
 *
 * @author Krisztián Kafka
 * @see javafx.scene.image.ImageView
 */
public class ImageViewBuilder {
    private double fitHeight = 150;
    private double fitWidth = 0;
    private boolean preserveRatio = true;
    private boolean smooth = true;
    private boolean cache = true;
    private final Image image;
    private boolean disable = true;

    public ImageViewBuilder(Image image) {
        this.image = image;
    }

    public ImageViewBuilder fitHeight(double fitHeight) {
        this.fitHeight = fitHeight;
        return this;
    }

    public ImageViewBuilder fitWidth(double fitWidth) {
        this.fitWidth = fitWidth;
        return this;
    }

    public ImageViewBuilder preserveRatio(boolean preserveRatio) {
        this.preserveRatio = preserveRatio;
        return this;
    }

    public ImageViewBuilder smooth(boolean smooth) {
        this.smooth = smooth;
        return this;
    }

    public ImageViewBuilder cache(boolean cache) {
        this.cache = cache;
        return this;
    }

    public ImageViewBuilder disable(boolean disable) {
        this.disable = disable;
        return this;
    }

    public ImageView build() {
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(fitHeight);
        imageView.setFitWidth(fitWidth);
        imageView.setPreserveRatio(preserveRatio);
        imageView.setSmooth(smooth);
        imageView.setCache(cache);
        imageView.setDisable(disable);
        return imageView;
    }
}
