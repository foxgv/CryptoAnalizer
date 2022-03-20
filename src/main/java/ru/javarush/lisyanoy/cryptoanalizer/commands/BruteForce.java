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

public class BruteForce implements Action {
    @Override
    public Result execute(String[] parameters) {

        int count = 0;
        String controlText = " да ";

        Scanner scanner1 = new Scanner(Constants.TXT_FOLDER + parameters[0]);
        String line1 = scanner1.nextLine();
        Path fileIncrypt = Paths.get(line1);
        Scanner scanner2 = new Scanner(Constants.TXT_FOLDER + parameters[1]);
        String line2 = scanner2.nextLine();
        Path fileBruteForce = Paths.get(line2);

        try {
            List<String> listFileIncrypt = Files.readAllLines(fileIncrypt, UTF_8);
            StringBuilder stringBuilder2 = new StringBuilder(String.valueOf(listFileIncrypt));
            StringBuilder stringBuilder3 = new StringBuilder();
            searchKey(count, controlText, stringBuilder2, stringBuilder3);

            Files.writeString(fileBruteForce,stringBuilder3);

        } catch (IOException e) {
            throw new AppException("reading error");
        }

        return new Result("BruteForce complete", ResultCode.OK);
    }

    private void searchKey(int count, String controlText, StringBuilder stringBuilder2, StringBuilder stringBuilder3) {
        do {
            for (int i = 0; i < stringBuilder2.length(); i++) {
                char ch2 = stringBuilder2.charAt(i);
                if (Constants.alphabetList.contains(ch2)) {
                    if ((Constants.alphabetList.indexOf(stringBuilder2.charAt(i)) - count) > -1) {
                        stringBuilder3.append(Constants.alphabetList.get(
                                (Constants.alphabetList.indexOf(ch2)) - count));
                    } else {
                        stringBuilder3.append(Constants.alphabetList.get(
                                ((Constants.alphabetList.indexOf(ch2)) - count) +
                                        Constants.alphabetList.size()));
                    }
                }
            }

            if(stringBuilder3.indexOf(controlText) > 0) {
                System.out.println("Ключ равен: " + count);
                break;
            }
            count++;
            stringBuilder3.delete(0, stringBuilder3.length());
        } while (stringBuilder3.isEmpty());
    }
}
