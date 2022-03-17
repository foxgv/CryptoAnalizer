package ru.javarush.lisyanoy.cryptoanalizer.controllers;

import ru.javarush.lisyanoy.cryptoanalizer.commands.Action;
import ru.javarush.lisyanoy.cryptoanalizer.entity.Result;

public class MainController {

    public Result doAction(String actionName, String[] parameters) {

        Action action = Actions.find(actionName);
        Result result = action.execute(parameters);
        return result;
    }
}
