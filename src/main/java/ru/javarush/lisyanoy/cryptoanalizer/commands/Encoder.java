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

public class Encoder implements Action {
    @Override
    public Result execute(String[] parameters) {
        Scanner scannerIn = new Scanner(Constants.TXT_FOLDER + parameters[0]);
        String lineIn = scannerIn.nextLine();
        Path fileTxt = Paths.get(lineIn);
        Scanner scannerOut = new Scanner(Constants.TXT_FOLDER + parameters[1]);
        String lineOut = scannerOut.nextLine();
        Path fileIncrypt = Paths.get(lineOut);

        int key = Integer.parseInt(parameters[2]);

        try {
            List<String> listFileSource = Files.readAllLines(fileTxt, UTF_8);
            StringBuilder stringBuilder1 = new StringBuilder(String.valueOf(listFileSource));
            StringBuilder stringBuilder2 = new StringBuilder();
            for (int i = 0; i < stringBuilder1.length(); i++) {
                char ch1 = stringBuilder1.charAt(i);
                if (Constants.alphabetList.contains(ch1)) {
                    if ((Constants.alphabetList.indexOf(stringBuilder1.charAt(i)) + key) <
                            Constants.alphabetList.size())
                    {
                        stringBuilder2.append(Constants.alphabetList.get((
                                Constants.alphabetList.indexOf(ch1)) + key));
                    } else {
                        stringBuilder2.append(Constants.alphabetList.get((
                                (Constants.alphabetList.indexOf(ch1)) + key) -
                                Constants.alphabetList.size()));
                    }
                }
            }
            Files.writeString(fileIncrypt,stringBuilder2);

        } catch (IOException e) {
           throw new AppException("reading error");
        }

        return new Result("encode all right", ResultCode.OK);
    }
}
