package util;

public interface Observable {
    boolean register(Observer obs);

    boolean unregister(Observer obs);
}
