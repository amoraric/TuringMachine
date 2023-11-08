package g61689.atl.util;

public interface Observable {
    boolean register(Observer obs);

    boolean unregister(Observer obs);

    void notifyObservers();
}
