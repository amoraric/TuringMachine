package util;

/**
 * Interface defining the Observable part of the Observer pattern.
 * Allows an object to be observed by one or more Observer objects.
 */
public interface Observable {
    /**
     * Registers an Observer to be notified of changes.
     *
     * @param obs The Observer to be registered.
     * @return true if registration is successful, false otherwise.
     */
    boolean register(Observer obs);

    /**
     * Unregisters an Observer from receiving updates.
     *
     * @param obs The Observer to be unregistered.
     * @return true if unregistration is successful, false otherwise.
     */
    boolean unregister(Observer obs);
}
