package ru.javarush.lisyanoy.cryptoanalizer;

import ru.javarush.lisyanoy.cryptoanalizer.constants.Constants;
import ru.javarush.lisyanoy.cryptoanalizer.entity.Result;

import java.util.Arrays;

public class ConsoleRunner {
    public static void main(String[] args) {
//        System.out.println("Hello maven!");
//        System.out.println(Arrays.toString(args));

        Application application = new Application();
        Result result = application.run(args);
        System.out.println(result);

    }
}
