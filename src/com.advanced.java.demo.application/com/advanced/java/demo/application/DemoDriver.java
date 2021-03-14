package com.advanced.java.demo.application.com.advanced.java.demo.application;

import com.advanced.java.concurrency.com.advanced.java.concurrency.Lesson5Concurrent;
import com.advanced.java.database.com.advanced.java.database.Lesson7Database;
import com.advanced.java.iostream.com.advanced.java.iostream.Lesson1IOStream;
import com.advanced.java.networking.com.advanced.java.networking.Lesson6NetworkingClient;
import com.advanced.java.networking.com.advanced.java.networking.Lesson6NetworkingServer;
import com.advanced.java.regex.com.advanced.java.regex.Lesson2RegEx;
import com.advanced.java.streams.com.advanced.java.streams.Lesson3Streams;
import com.advanced.java.xml.com.advanced.java.xml.Lesson4XML;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

public class DemoDriver {

    public static void runConcurrencyDemo() throws InterruptedException {

        Scanner userInput = new Scanner(System.in);
        int numberOfThreads = 8;
        Lesson5Concurrent.MODE mode = Lesson5Concurrent.MODE.NOLOCKING;
        int choice = 0;
        do {
            System.out.print("\nC O N C U R R E N T   P R O C E S S I N G   D E M O\n");
            System.out.print("\nEnter the number of concurrent threads to run, or 0 to return to the main menu: ");
            numberOfThreads = Integer.parseInt(userInput.next());
            if (numberOfThreads == 0) continue; // Jump to the loop condition…
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
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nD A T A B A S E   D E M O   W I T H   J D B C   A N D   M y S Q L\n");
        Lesson7Database lesson7Database = new Lesson7Database("resources/Lessons.sql");
        lesson7Database.connectToTheDatabase();
        lesson7Database.populateTheTable();
        lesson7Database.dumpTheDatabase();
        System.out.print("\nPress ENTER to return to the main menu: ");
        while (!scanner.hasNextLine()) {
            // Do nothing…
        }
    }

    public static void runIOStreamDemo() throws IOException {
        Scanner scanner = new Scanner(System.in);
        var filename = "resources/Employee.dat";
        int method = 0;
        int sampleSize = 10_000;
        do {
            System.out.print("\nI / O   S T R E A M   D E M O\n");
            System.out.println("\nSELECT AN I/O METHOD…\n");
            System.out.println("1) Object stream\n" +
                    "2) Binary stream\n" +
                    "3) Text stream\n");
            System.out.print("Enter the number of the I/O method to use, or 0 to return to the main menu: ");
            method = Integer.parseInt(scanner.next());
            if (method == 0) continue;
            System.out.println();
            switch (method) {
                case 1:
                    Lesson1IOStream.writeObjectStream(filename, sampleSize);
                    Lesson1IOStream.readObjectStream(filename);
                    break;
                case 2:
                    Lesson1IOStream.writeBinaryStream(filename, sampleSize);
                    Lesson1IOStream.readBinaryStream(filename);
                    break;
                case 3:
                    Lesson1IOStream.writeTextStream(filename, sampleSize);
                    Lesson1IOStream.readTextStream(filename);
                    break;
                default:
                    method = 0;
                    break;
            }
        } while (method >= 1);
    }

    public static void runNetworkingDemo() throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nN E T W O R K I N G   D E M O\n");
        Runnable runnable = new Lesson6NetworkingServer(1953);
        var thread = new Thread(runnable);
        thread.start();
        Lesson6NetworkingClient lesson6NetworkingClient = new Lesson6NetworkingClient("127.0.0.1", 1953);
        lesson6NetworkingClient.runSocketTest();
        thread.interrupt();
        System.out.print("\nPress ENTER to return to the main menu: ");
        while (!scanner.hasNextLine()) {
            // Do nothing…
        }
    }

    public static void runRegularExpressionsDemo() {
        Scanner scanner = new Scanner(System.in);
        String[] filename = {"resources/neighbor-dump.txt"};
        System.out.print("\nR E G U L A R   E X P R E S S I O N S   D E M O\n");
        //Lesson2RegEx lesson2RegEx = new Lesson2RegEx();
        Lesson2RegEx.main(filename);
        System.out.print("\nPress ENTER to return to the main menu: ");
        while (!scanner.hasNextLine()) {
            // Wait…
        }
    }

    public static void runXMLDemo() throws IOException, SAXException, ParserConfigurationException, XPathExpressionException {
        Scanner scanner = new Scanner(System.in);
        int parser = 0;
        System.out.print("\nX M L   D E M O\n");
        do {
            System.out.println("\nSELECT A PARSER…\n");
            System.out.println("1) DOM parser\n" +
                    "2) SAX parser\n" +
                    "3) XPATH\n");
            System.out.print("Enter the number of the parser to use, or 0 to return to the main menu: ");
            parser = Integer.parseInt(scanner.next());
            if (parser == 0) continue;
            System.out.println();
            Lesson4XML lesson4XML = new Lesson4XML("resources/JobResult_UCSDExt.xml");
            switch (parser) {
                case 1:
                    System.out.println("\nResults of XML parsing using DOM parser:");
                    lesson4XML.useDOMtoParseXMLFile();
                    break;
                case 2:
                    System.out.println("Results of XML parsing using SAX parser:");
                    lesson4XML.useSAXtoParseXMLFile();
                    break;
                case 3:
                    System.out.println("Results of XML parsing using XPATH:");
                    lesson4XML.useXPATHtoParseXMLFile();
                    break;
                default:
                    parser = 0;
                    break;
            }
        } while (parser >= 1);
    }

    public static void runStreamsDemo() throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nS T R E A M S   D E M O\n");
        String[] fileName = new String[1];
        fileName[0] = "resources/JobResult_124432.txt";
        Lesson3Streams.main(fileName);
        System.out.print("\nPress ENTER to return to the main menu…");
        while (!scanner.hasNextLine()) {
            // Wait…
        }
    }

    public static void main(String[] arguments) throws InterruptedException, IOException, SQLException, XPathExpressionException, SAXException, ParserConfigurationException {
        Scanner keyboardInput = new Scanner(System.in);
        int demo = 0;
        System.out.print("\nW E L C O M E   TO   T H E   D E M O   D R I V E R !\n");
        do {
            System.out.println("\nSELECT A DEMO…\n");
            System.out.println("1) I/O Stream demo\n" +
                    "2) Regular Expressions demo\n" +
                    "3) Streams demo\n" +
                    "4) XML demo\n" +
                    "5) Concurrency demo\n" +
                    "6) Networking demo\n" +
                    "7) Database demo");
            System.out.print("\nEnter the number of the demo to run, or 0 to exit: ");
            demo = Integer.parseInt(keyboardInput.next());
            switch (demo) {
                case 1:
                    runIOStreamDemo();
                    break;
                case 2:
                    runRegularExpressionsDemo();
                    break;
                case 3:
                    runStreamsDemo();
                    break;
                case 4:
                    runXMLDemo();
                    break;
                case 5:
                    runConcurrencyDemo();
                    break;
                case 6:
                    runNetworkingDemo();
                    break;
                case 7:
                    runDatabaseDemo();
                    break;
                default:
                    demo = 0;
                    break;
            }
        } while (demo != 0);
    }
}
