package com.progtech.progtech2024.helper;

import android.content.Context;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

public class BankActionLogger {
    public static void WriteInLogFile(Context context, String msgLine) {
        String filename = "Log.txt";

        // Get internal storage directory
        File file = new File(context.getFilesDir(), filename);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            writer.write(DateFormatter.DateToString(new Date()) + " " + msgLine + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
