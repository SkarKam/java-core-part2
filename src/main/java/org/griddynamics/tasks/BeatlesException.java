package org.griddynamics.tasks;

public class BeatlesException extends Exception{
    public BeatlesException(String message) {
        super("Beatles " + message);
    }
}
