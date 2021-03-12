package com.advanced.java.demo.application.com.advanced.java.demo.application;

import com.advanced.java.concurrency.com.advanced.java.concurrency.Lesson5Concurrent;
import com.advanced.java.database.com.advanced.java.database.Lesson7Database;
import com.advanced.java.iostream.com.advanced.java.iostream.Lesson1IOStream;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

public class DemoDriver {

    public static void runConcurrencyDemo() throws InterruptedException {
        Lesson5Concurrent lesson5Concurrent = new Lesson5Concurrent(3);
        lesson5Concurrent.setMode(Lesson5Concurrent.MODE.ATOMIC);
        lesson5Concurrent.processFiles("resources/Data4.txt", "resources/Data5.txt");
        System.out.println(lesson5Concurrent);
    }

    public static void runDatabaseDemo() throws SQLException, IOException {
        Lesson7Database lesson7Database = new Lesson7Database("resources/Lessons.sql");
        lesson7Database.connectToTheDatabase();
        lesson7Database.populateTheTable();
        lesson7Database.dumpTheDatabase();
    }

    public static void runIOStreamDemo() {
        Lesson1IOStream lesson1IOStream = new Lesson1IOStream();
        System.out.println("\nNot yet ready for prime time.\n");
    }

    public static void main(String[] arguments) throws InterruptedException, IOException, SQLException {
        Scanner keyboardInput = new Scanner(System.in);

        System.out.println("This is the demo driver.\n");
        int choice = 0;
        do {
            System.out.println("\n1) Concurrency demo\n" +
                    "2) Database demo\n" +
                    "3) I/O Stream demo\n" +
                    "4) Networking demo\n" +
                    "5) Regular expressions demo\n" +
                    "6) Streams\n" +
                    "7) XML demo");
            System.out.print("\nEnter the number of the demo to run, or 0 to exit: ");
            choice = Integer.parseInt(keyboardInput.next());
            switch (choice) {
                case 1:
                    runConcurrencyDemo();
                    break;
                case 2:
                    runDatabaseDemo();
                    break;
                case 3:
                    runIOStreamDemo();
                    break;
                default:
                    choice = 0;
                    break;
            }
        } while (choice != 0);
    }
}

