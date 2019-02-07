package tech.bts.cardgame;

import tech.bts.cardgame.util.ListOperations;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class CodeSmellsPractice {

    public static void main(String[] args) {

        final ListOperations listOperations = new ListOperations(2);
        listOperations.setStart(1);
        listOperations.setEnd(15);
        System.out.println(buildList(listOperations));
        System.out.println();

        final GreetingOptions options = new GreetingOptions("Susan");
        options.setName("Susan");
        options.setTitle("Ms");
        options.setFormal(true);
        options.setAskQuestion(true);

        System.out.println( buildGreeting (options) );
        System.out.println();

        final Client client = new Client();
        client.setName("Peter");
        client.setCountry("England");
        displayClientData(client);
        System.out.println();


        readAndDisplayClient();
    }

    /** Builds a list of numbers from start to end, with given increment*/
    public static List<Integer> buildList(ListOperations listOperations) {

        final List<Integer> result  = new ArrayList<>();

        for (int i = listOperations.getStart(); i <= listOperations.getEnd(); i += listOperations.getIncrement() ) {
            result .add(i);
        }
        if (listOperations.getEnd() >= 15) {
            Collections.reverse(result);
        }

        return result ;
    }

    public static String buildGreeting(GreetingOptions options) {

        final StringBuilder result = new StringBuilder();

        addGreeting(options, result);
        addQuestion(options, result);

        return result.toString();
    }

    public static void addQuestion(GreetingOptions options, StringBuilder result) {
        if (options.isAskQuestion()) {
            if (options.isFormal()) {
                result.append(" What can I do for you?");
            } else {
                result.append(" What's up?");
            }
        }
    }

    public static void addGreeting(GreetingOptions options, StringBuilder result) {
        result.append(options.isFormal() ? "Hello " : "Hi ");

        if (options.getTitle() != null) {
            result.append(options.getTitle()).append(" ");
        }

        result.append(options.getName());

        result.append(options.isExclamation() ? "!" : ".");
    }

    /** Returns the text if it's not empty, otherwise returns "unknown*/
    public static String getDisplayText (String text) {

        if (text != null && !text.isEmpty()) {
            return text;
        } else {
            return "(unknown)";
        }
    }

    public static void displayClientData(Client client) {

        System.out.println("--- Client data ---");
        System.out.println("Name: " + getDisplayText(client.getName()));
        System.out.println("Surname: " + getDisplayText(client.getSurname()));
        System.out.println("Email: " + getDisplayText(client.getEmail()));
        System.out.println("Country: " + getDisplayText(client.getCountry()));
    }


    public static void readAndDisplayClient() {

        final Client client = readClient();
        displayClientData(client);
    }

    public static Client readClient() {
        final Scanner scanner = new Scanner(System.in);
        final Client client = new Client();

        System.out.println("Please enter client data -->");
        System.out.print("Name: ");
        client.setName( scanner.nextLine() );
        System.out.print("Surname: ");
        client.setSurname( scanner.nextLine() );
        System.out.print("Email: ");
        client.setEmail( scanner.nextLine() );
        System.out.print("Country: ");
        client.setCountry( scanner.nextLine() );
        return client;
    }
}