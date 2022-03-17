package ru.javarush.lisyanoy.cryptoanalizer.commands;

import ru.javarush.lisyanoy.cryptoanalizer.entity.Result;
import ru.javarush.lisyanoy.cryptoanalizer.entity.ResultCode;

public class BrudeForce implements Action {
    @Override
    public Result execute(String[] parameters) {
        //TODO something do
        return new Result("BrudeForce complete", ResultCode.OK);
    }
}
