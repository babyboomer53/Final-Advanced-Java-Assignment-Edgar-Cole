package com.advanced.java.demo.application.com.advanced.java.demo.application;

import com.advanced.java.concurrency.com.advanced.java.concurrency.Lesson5Concurrent;
import com.advanced.java.database.com.advanced.java.database.Lesson7Database;
import com.advanced.java.iostream.com.advanced.java.iostream.Lesson1IOStream;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

public class DemoDriver {

    public static void runConcurrencyDemo() throws InterruptedException {

        Scanner userInput = new Scanner(System.in);
        int numberOfThreads = 3;
        Lesson5Concurrent.MODE mode = Lesson5Concurrent.MODE.NOLOCKING;
        int choice = 0;
        do {
            System.out.print("\nC O N C U R R E N T   P R O C E S S I N G   D E M O\n");
            System.out.print("Enter the number of concurrent threads to run, or 0 to exit: ");
            numberOfThreads = Integer.parseInt(userInput.next());
            if (numberOfThreads == 0) continue; // Jump to the while condition…
            System.out.println("\n1) Reentrant\n" +
                    "2) Atomic\n" +
                    "3) No locking\n");
            System.out.print("\nEnter the number of the locking mode to use: ");
            choice = Integer.parseInt(userInput.next());
            switch (choice) {
                case 1:
                    mode = Lesson5Concurrent.MODE.REENTRANT;
                    break;
                case 2:
                    mode = Lesson5Concurrent.MODE.ATOMIC;
                    break;
                case 3:
                    mode = Lesson5Concurrent.MODE.NOLOCKING;
                    break;
                default:
                    choice = 0;
                    break;
            }
            Lesson5Concurrent lesson5Concurrent = new Lesson5Concurrent(numberOfThreads, mode);
            System.out.println(lesson5Concurrent);
            lesson5Concurrent.processFiles("resources/Data4.txt", "resources/Data5.txt");
        } while (numberOfThreads != 0);
    }

    public static void runDatabaseDemo() throws SQLException, IOException {
        System.out.print("\nD A T A B A S E   D E M O   W I T H   J D B C   A N D   M y S Q L\n");
        Lesson7Database lesson7Database = new Lesson7Database("resources/Lessons.sql");
        lesson7Database.connectToTheDatabase();
        lesson7Database.populateTheTable();
        lesson7Database.dumpTheDatabase();
    }

    public static void runIOStreamDemo() {
        System.out.print("\nI / O   S T R E A M   D E M O\n");
        Lesson1IOStream lesson1IOStream = new Lesson1IOStream();
        System.out.println("Not yet ready for prime time.");
    }

    public static void runNetworkingDemo(){
        System.out.println("\nN E T W O R K I N G   D E M O\n");
        System.out.println("Not yet ready for prime time.");
    }

    public static void runRegularExpressionsDemo(){
        System.out.print("\nR E G U L A R   E X P R E S S I O N S   D E M O\n");
    }

    public static void main(String[] arguments) throws InterruptedException, IOException, SQLException {
        Scanner keyboardInput = new Scanner(System.in);

        int choice = 0;
        do {
            System.out.print("\nW E L C O M E   TO   T H E   D E M O   D R I V E R !\n");
            System.out.println("\nSELECT A DEMO…\n");
            System.out.println("1) Concurrency demo\n" +
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
                case 4:
                    runNetworkingDemo();
                    break;
                case 5:
                    runRegularExpressionsDemo();
                    break;
                default:
                    choice = 0;
                    break;
            }
        } while (choice != 0);
    }
}

