package g61689.atl.ascii.view;

import g61689.atl.ascii.model.*;

/**
 * The view class.
 */
public class View {
    private final AsciiPaint asciiPaint;

    /**
     * The view's constructor.
     *
     * @param asciiPaint the facade of the model
     */
    public View(AsciiPaint asciiPaint) {
        this.asciiPaint = asciiPaint;
    }

    /**
     * Outputs the drawing.
     */
    public void displayAsciiArt() {
        String asciiArt = asciiPaint.asAscii();
        System.out.println("ASCII illustration :");
        System.out.println(asciiArt);
    }

    /**
     * Outputs the shape's list.
     */
    public void displayAsciiArtList() {

    }
}