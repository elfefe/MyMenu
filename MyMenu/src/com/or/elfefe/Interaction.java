package com.or.elfefe;

import java.util.Scanner;

public class Interaction {
    static Scanner sc = new Scanner(System.in);
    /**
     * Display a question about a category in the standard input, get response and display it
     * @param category the category of the question
     * @param responses available responses
     */
    public static int askSomething(String category, String[] responses) {
        System.out.println("Choix " + category);
        for (int i = 1; i <= responses.length; i++)
            System.out.println(i + " - " + responses[i - 1]);
        System.out.println("Que souhaitez-vous comme " + category + "?");
        int nbResponse;
        boolean responseIsGood;
        do {
            nbResponse = sc.nextInt();
            responseIsGood = (nbResponse >= 1 && nbResponse <= responses.length);
            if (responseIsGood)
                System.out.println("Vous avez choisi comme " + category + " : " + responses[nbResponse - 1]);
            else {
                boolean isVowel = "aeiouy".contains(Character.toString(category.charAt(0)));
                if (isVowel)
                    System.out.println("Vous n'avez pas choisi d'" + category + " parmi les choix proposés");
                else
                    System.out.println("Vous n'avez pas choisi de " + category + " parmi les choix proposés");
            }
        } while (!responseIsGood);
        return nbResponse;
    }
}
