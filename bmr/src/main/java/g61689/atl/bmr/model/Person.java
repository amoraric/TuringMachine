package g61689.atl.bmr.model;

import g61689.atl.util.Observable;
import g61689.atl.util.Observer;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Person implements Observable {
    private static final double[] multipliers = {1.2, 1.375, 1.55, 1.725, 1.9};
    private String selectedGender;
    private double height;
    private double weight;
    private int age;
    private LifeStyle style;

    private final List<Observer> observers = new ArrayList<>();

    /**
     * Default constructor.
     */
    public Person() {

    }

    /**
     * Calculates the BMR based on user inputs.
     *
     * @return The calculated BMR value
     */
    public double calculateBmr() {
        if (Objects.equals(selectedGender, "Female")) {
            return (9.6 * weight + 1.8 * height - 4.7 * age + 655);
        } else {
            return (13.7 * weight + 5 * height - 6.8 * age + 66);
        }
    }

    /**
     * Calculates the Calories based on user inputs.
     *
     * @return The calculated Calories value
     */
    public double calculateCalories() {
        return calculateBmr() * multipliers[style.ordinal()];
    }

    /**
     * Sets the attributes of the person that are required for the calculations.
     *
     * @param selectedGender male / female
     * @param height height
     * @param weight weight
     * @param age age
     * @param style lifestyle
     */
    public void set(String selectedGender, double height, double weight, int age, LifeStyle style) {
        this.selectedGender = selectedGender;
        this.height = height;
        this.weight = weight;
        this.age = age;
        this.style = style;
        notifyObservers();
    }

    @Override
    public boolean register(Observer obs) {
        if (!observers.contains(obs)) {
            observers.add(obs);
            return true;
        }
        return false;
    }

    @Override
    public boolean unregister(Observer obs) {
        if (observers.contains(obs)) {
            observers.remove(obs);
            return true;
        }
        return false;
    }

    @Override
    public void notifyObservers() {
        for (Observer obs : observers) {
            obs.update();
        }
    }
}
