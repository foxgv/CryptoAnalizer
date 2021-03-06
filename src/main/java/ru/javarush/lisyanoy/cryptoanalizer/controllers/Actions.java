package ru.javarush.lisyanoy.cryptoanalizer.controllers;

import ru.javarush.lisyanoy.cryptoanalizer.commands.*;
import ru.javarush.lisyanoy.cryptoanalizer.exception.AppException;

public enum Actions {
    ENCODE(new Encoder()),
    DECODE(new Decoder()),
    BRUTEFORCE(new BruteForce()),
    ANALYZE(new StatAnalysis());

    private final Action action;


    Actions(Action action) {
        this.action = action;
    }

    public static Action find(String actionName) {
        try {
            Actions valueEnum = Actions.valueOf(actionName.toUpperCase());
            Action action = valueEnum.action;
            return action;
        } catch (IllegalArgumentException e) {
            throw new AppException("not found " + actionName, e);
        }
    }
}
