package ru.javarush.lisyanoy.cryptoanalizer.commands;

import ru.javarush.lisyanoy.cryptoanalizer.entity.Result;

public interface Action {

    Result execute (String[] parameters);

}
