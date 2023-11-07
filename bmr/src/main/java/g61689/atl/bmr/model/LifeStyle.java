package g61689.atl.bmr.model;

/**
 * Enumeration representing different activity levels for the BMR Calculator.
 */
public enum LifeStyle {
    SEDENTARY("Sedentary"),
    A_LITTLE_ACTIVE("A little active"),
    ACTIVE("Active"),
    VERY_ACTIVE("Very active"),
    EXTREMELY_ACTIVE("Extremely active");

    private final String label;

    /**
     * Constructs an ActivityLevel with a descriptive label.
     *
     * @param label A string representing the label for the activity level.
     */
    LifeStyle(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return label;
    }
}
