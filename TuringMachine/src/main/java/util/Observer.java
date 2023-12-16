package util;

/**
 * Interface defining the Observer part of the Observer pattern.
 * Allows an object to receive updates from Observable objects.
 */
public interface Observer {
    /**
     * Called when the Observable object notifies its observers of a change.
     *
     * @param observable The Observable object that has been updated.
     */
    void update(Observable observable);
}
