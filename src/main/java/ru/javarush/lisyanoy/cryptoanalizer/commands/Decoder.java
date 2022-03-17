package ru.javarush.lisyanoy.cryptoanalizer.commands;

import ru.javarush.lisyanoy.cryptoanalizer.constants.Constants;
import ru.javarush.lisyanoy.cryptoanalizer.entity.Result;
import ru.javarush.lisyanoy.cryptoanalizer.entity.ResultCode;
import ru.javarush.lisyanoy.cryptoanalizer.exception.AppException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

import static java.nio.charset.StandardCharsets.UTF_8;

public class Decoder implements Action {
    @Override
    public Result execute(String[] parameters) {
        //TODO something do

        Scanner scannerOut = new Scanner(Constants.TXT_FOLDER + parameters[0]);
        String lineOut = scannerOut.nextLine();
        Path fileIncrypt = Paths.get(lineOut);
        Scanner scannerOut1 = new Scanner(Constants.TXT_FOLDER + parameters[1]);
        String lineOut1 = scannerOut1.nextLine();
        Path fileDecrypt = Paths.get(lineOut1);

        int key = Integer.parseInt(parameters[2]);

        try {
            List<String> listFileSource = Files.readAllLines(fileIncrypt, UTF_8);
            StringBuilder stringBuilder2 = new StringBuilder(String.valueOf(listFileSource));
            StringBuilder stringBuilder3 = new StringBuilder();
            for (int i = 0; i < stringBuilder2.length(); i++) {
                char ch2 = stringBuilder2.charAt(i);
                if (Constants.alphabetList.contains(ch2)) {
                    if ((Constants.alphabetList.indexOf(stringBuilder2.charAt(i)) - key) > -1) {
                        stringBuilder3.append(Constants.alphabetList.get((Constants.alphabetList.indexOf(ch2)) - key));
                    } else {
                        stringBuilder3.append(Constants.alphabetList.get(((Constants.alphabetList.indexOf(ch2)) - key) + Constants.alphabetList.size()));
                    }
                }
            }
            Files.writeString(fileDecrypt,stringBuilder3);

        } catch (IOException e) {
            throw new AppException("reading error");
        }

        return new Result("decode all right", ResultCode.OK);
    }
}
