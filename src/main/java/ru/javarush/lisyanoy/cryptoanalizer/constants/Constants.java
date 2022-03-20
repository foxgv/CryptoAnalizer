package ru.javarush.lisyanoy.cryptoanalizer.constants;

import java.io.File;
import java.util.ArrayList;

public class Constants {

    public static final String TXT_FOLDER = System.getProperty("user.dir") +
            File.separator +
            "text" +
            File.separator;

    private final static ArrayList<Character> arrayList1;

    static {
        arrayList1 = new ArrayList<>();
        for (char i = 'А'; i <= 'Я'; i++) {
            arrayList1.add(i);
        }
        arrayList1.add(6, 'Ё');
    }

    private final static ArrayList<Character> arrayList2;

    static {
        arrayList2 = new ArrayList<>();
        for (char i = 'а'; i <= 'я'; i++) {
            arrayList2.add(i);
        }
        arrayList2.add(6, 'ё');
    }

    private final static ArrayList<Character> arrayList3;

    static {
        arrayList3 = new ArrayList<>();
        arrayList3.add(0, '.');
        arrayList3.add(1, ',');
        arrayList3.add(2, '\"');
        arrayList3.add(3, ':');
        arrayList3.add(4, '-');
        arrayList3.add(5, '!');
        arrayList3.add(6, '?');
        arrayList3.add(7, ' ');
    }

    public final static ArrayList<Character> alphabetList = new ArrayList<>();

    static {
        alphabetList.addAll(arrayList1);
        alphabetList.addAll(arrayList2);
        alphabetList.addAll(arrayList3);
    }

    public final static ArrayList<Character> lowAlphabetList = new ArrayList<>();

    static {
        lowAlphabetList.addAll(arrayList2);
        lowAlphabetList.addAll(arrayList3);
    }
}
