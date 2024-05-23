package org.example.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;

public class UserInteraction {

    //Look for user input within x seconds
    public static boolean userCall(long seconds) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        long startTime = System.currentTimeMillis();
        while ((System.currentTimeMillis() - startTime) < seconds * 1000 && !in.ready()) {
            TimeUnit.MILLISECONDS.sleep(1);
        }

        return in.ready();
    }
}
