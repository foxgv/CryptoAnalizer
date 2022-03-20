package ru.javarush.lisyanoy.cryptoanalizer.commands;

import ru.javarush.lisyanoy.cryptoanalizer.constants.Constants;
import ru.javarush.lisyanoy.cryptoanalizer.entity.Result;
import ru.javarush.lisyanoy.cryptoanalizer.entity.ResultCode;
import ru.javarush.lisyanoy.cryptoanalizer.exception.AppException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.TreeMap;

public class StatAnalysis implements Action {
    @Override
    public Result execute(String[] parameters) {

        Scanner scannerIn1 = new Scanner(Constants.TXT_FOLDER + parameters[0]);
        String lineIn1 = scannerIn1.nextLine();
        Path fileDict = Paths.get(lineIn1);
        Scanner scannerIn2 = new Scanner(Constants.TXT_FOLDER + parameters[1]);
        String lineIn2 = scannerIn2.nextLine();
        Path fileIncrypt = Paths.get(lineIn2);
        Scanner scannerOut = new Scanner(Constants.TXT_FOLDER + parameters[2]);
        String lineOut = scannerOut.nextLine();
        Path fileDecrypt = Paths.get(lineOut);

        Object[] chars = Constants.lowAlphabetList.toArray();

        TreeMap<Double, Character> mapDict = new TreeMap<>();
        TreeMap<Double, Character> mapIncrypt = new TreeMap<>();


        try {
            List<String> stringsDict = Files.readAllLines(fileDict);
            StringBuilder stringBuilder1 = new StringBuilder(String.valueOf(stringsDict).toLowerCase());
            characterStatistics(Constants.lowAlphabetList, chars, stringBuilder1, mapDict);

            List<String> stringsIncrypt = Files.readAllLines(fileIncrypt);
            StringBuilder stringBuilder2 = new StringBuilder(String.valueOf(stringsIncrypt).toLowerCase());
            characterStatistics(Constants.lowAlphabetList, chars, stringBuilder2, mapIncrypt);

            Character char1 = mapDict.get(mapDict.lastKey());
            Character char2 = mapIncrypt.get(mapIncrypt.lastKey());

            int key = getKey(Constants.lowAlphabetList, char1, char2);
            System.out.println("Key = " + key);

            StringBuilder stringBuilder3 = getStringBuilder(Constants.lowAlphabetList, stringBuilder2, key);
            Files.writeString(fileDecrypt,stringBuilder3);

        } catch (IOException e) {
            throw new AppException("reading error");
        }

        return new Result("statistical analysis all right", ResultCode.OK);
    }

    private static void characterStatistics(ArrayList<Character> lowAlphabetList, Object[] chars, StringBuilder stringBuilder, TreeMap<Double, Character> treeMap) {
        int count = 0;
        for (int i = 0; i < lowAlphabetList.size(); i++) {
            char ch = (char) chars[i];
            for (int j = 0; j < stringBuilder.length(); j++) {
                if (stringBuilder.charAt(j) == ch) {
                    count++;
                }
            }
            double result = ((double) count / (double) stringBuilder.length()) * 100;
            treeMap.put(result, ch);
            count = 0;
        }
    }

    private static int getKey(ArrayList<Character> lowAlphabetList, Character char1, Character char2) {
        int key = 0;
        while (lowAlphabetList.indexOf(char1) != lowAlphabetList.indexOf(char2)){
            if ((lowAlphabetList.indexOf(char1) + 1) < lowAlphabetList.size()) {
                char1 = lowAlphabetList.get(lowAlphabetList.indexOf(char1) + 1);
            }  else {
                char1 = lowAlphabetList.get((lowAlphabetList.indexOf(char1) + 1) - lowAlphabetList.size());
            }
            key++;
        }
        return key;
    }

    private static StringBuilder getStringBuilder(ArrayList<Character> lowAlphabetList, StringBuilder stringBuilder2, int key) {
        StringBuilder stringBuilder3 = new StringBuilder();
        for (int i = 0; i < stringBuilder2.length(); i++) {
            char ch = stringBuilder2.charAt(i);
            if (lowAlphabetList.contains(ch)) {
                if ((lowAlphabetList.indexOf(stringBuilder2.charAt(i)) - key) > -1) {
                    stringBuilder3.append(lowAlphabetList.get((lowAlphabetList.indexOf(ch)) - key));
                } else {
                    stringBuilder3.append(lowAlphabetList.get(((lowAlphabetList.indexOf(ch)) - key) + lowAlphabetList.size()));
                }
            }
        }
        return stringBuilder3;
    }
}
