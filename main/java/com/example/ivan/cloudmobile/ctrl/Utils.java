package com.example.ivan.cloudmobile.ctrl;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

/**
 * Created by ivan on 18/04/17.
 */

public class Utils {

    private static ArrayList<String> wordArray;
    private static ArrayList<String> meaningArray;
    private static String DICTIONARY_PATH = "dictionary.txt";
    private static Context context;

    public Utils(Context parentContext) {
        context = parentContext;
        initArrays();
    }

    private static void initArrays() {
        String res[] = readFromFile(DICTIONARY_PATH).split("[)]");

        wordArray = new ArrayList<>();
        meaningArray = new ArrayList<>();

        for (int i = 0; i < res.length; i++) {
            if (i % 2 == 0)
                wordArray.add(res[i]);
            else
                meaningArray.add(res[i]);
        }

        //
        for (int i = 0; i < wordArray.size(); i++)
            System.out.println(">"+wordArray.get(i) +"<");

        for (int i = 0; i < meaningArray.size(); i++)
            System.out.println(">>"+meaningArray.get(i) +"<<");
    }

    public static int searchWordLocally(String word) {
        initArrays();
        for (int i = 0; i < wordArray.size(); i++)
            if (wordArray.get(i).equalsIgnoreCase(word))
                return i;
        return -1;
    }

    public static void insertWord(String word, String meaning) {
        writeToFile(DICTIONARY_PATH, word+")"+meaning+")");
        initArrays();
    }

    public static String getMeaning(int index) {
        initArrays();
        return meaningArray.get(index);
    }

    private static String readFromFile(String path) {
        String string = "";

        try {
            InputStream inputStream = context.openFileInput(path);

            if ( inputStream != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    stringBuilder.append(receiveString);
                }

                inputStream.close();
                string = stringBuilder.toString();
            }
        }
        catch (FileNotFoundException ignored) { /*writeToFile(path, ""); string = readFromFile(path);*/ }
        catch (IOException e) { Log.e("login activity", "Cannot read file: " + e.toString()); }

        return string;
    }

    private static void writeToFile(String path, String data) {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput(path, Context.MODE_APPEND));
            outputStreamWriter.write(data);
            outputStreamWriter.close();
        }
        catch (IOException ignored) {}
    }

    public static ArrayList<String> getWordArray() { return wordArray; }
    public static ArrayList<String> getMeaningArray() { return meaningArray; }

}
