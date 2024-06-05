package homework4;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        String csvContent = "friend1;friend2;friendship_strength\n" +
                "Alice;Bob;5\n" +
                "Alice;Charlie;2\n" +

                "Bob;Eve;1\n" +
                "Bob;Charlie;30\n" +
                "Bob;Alice;50\n" +

                "Charlie;Eve;5\n" +
                "Charlie;Bob;30\n" +
                "Charlie;David;2\n" +
                "Charlie;Alice;50\n";

        BufferedInputStream bis = new BufferedInputStream(new FileInputStream("social_network.csv"));
        System.out.println("Loading in the social network...");
        SocialNetwork ewg = new SocialNetwork(new Scanner(csvContent));
//        SocialNetwork ewg = new SocialNetwork(new Scanner(bis));
        System.out.println(
                "Network successfully loaded.\n" +
                        "Total users: " + ewg.V() + '\n' +
                        "Total friendships: " + ewg.E()
        );
        System.out.println("==============================\n");

        System.out.print("Enter the name recommend for, or -1 to exit: ");
        Scanner scanner = new Scanner(System.in);
        String userName = scanner.nextLine();
        while (!userName.equals("-1")) {
            ArrayList<FriendshipRecommendation> recommendFriends = ewg.recommendFriends(userName);
            printResult(recommendFriends);

            System.out.print("Enter the name recommend for, or -1 to exit: ");
            userName = scanner.nextLine();
        }
        System.out.println("Thank you for using our friendship recommender algorithm.");
    }

    private static void printResult(ArrayList<FriendshipRecommendation> rfs) {
        if (rfs.isEmpty()) {
            System.out.println("The user you are looking for does not exist in the network.\n");
            return;
        }
        double result = 0;
        for (FriendshipRecommendation fr : rfs) {
            result += fr.getRecommendationStrength();
        }

        System.out.println("Total recommendations: " + result);
        System.out.println("Top 10 recommendations based on friendship strength: ");
        for (int i = 0; i < 10; i++) {
            if (i == rfs.size()) break;
            System.out.println(rfs.get(i));
        }
        System.out.println();
    }
}