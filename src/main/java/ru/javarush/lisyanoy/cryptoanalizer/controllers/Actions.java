package ru.javarush.lisyanoy.cryptoanalizer.controllers;

import ru.javarush.lisyanoy.cryptoanalizer.commands.Action;
import ru.javarush.lisyanoy.cryptoanalizer.commands.Decoder;
import ru.javarush.lisyanoy.cryptoanalizer.commands.Encoder;
import ru.javarush.lisyanoy.cryptoanalizer.exception.AppException;

import java.util.Locale;

public enum Actions {
    ENCODER(new Encoder()),
    DECODER(new Decoder());

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
