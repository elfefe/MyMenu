package com.or.elfefe;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.InputMismatchException;

import static java.nio.file.StandardOpenOption.APPEND;

public class Order {
        /**
         * Display all available menus in the restaurant.
         */
        public void displayAvailableMenu() {
            System.out.println("Choix menu");
            System.out.println("1 - poulet");
            System.out.println("2 - boeuf");
            System.out.println("3 - végétarien");
            System.out.println("Que souhaitez-vous comme menu ?");
        }

        /**
         * Display a selected menu.
         * @param nbMenu The selected menu.
         */
        public void displaySelectedMenu(int nbMenu) {
            switch (nbMenu) {
                case 1:
                    System.out.println("Vous avez choisi comme menu : poulet");
                    break;
                case 2:
                    System.out.println("Vous avez choisi comme menu : boeuf");
                    break;
                case 3:
                    System.out.println("Vous avez choisi comme menu : végétarien");
                    break;
                default:
                    System.out.println("Vous n'avez pas choisi de menu parmi les choix proposés");
                    break;
            }
        }

    /**
     * Display all available sides depending on all sides enable or not.
     * All sides = vegetables, frites and rice
     * No all sides = rice or not
     * @param allSideEnable enable display for all side or not
     */
    public void displayAvailableSide(boolean allSideEnable) {
        System.out.println("Choix accompagnement");
        if (allSideEnable) {
            System.out.println("1 - légumes frais");
            System.out.println("2 - frites");
            System.out.println("3 - riz");
        } else {
            System.out.println("1 - riz");
            System.out.println("2 - pas de riz");
        }
        System.out.println("Que souhaitez-vous comme accompagnement ?");
    }

    /**
     * Display all available drinks in the restaurant
     */
    public void displayAvailableDrink() {
        System.out.println("Choix boisson");
        System.out.println("1 - eau plate");
        System.out.println("2 - eau gazeuse");
        System.out.println("3 - soda");
        System.out.println("Que souhaitez-vous comme boisson ?");
    }

        public String run_menu(){
            int nbMenu = askMenu();
            int nbSide = -1;
            int nbDrink = -1;
            switch (nbMenu) {
                case 1:
                    nbSide = askSide(true);
                    nbDrink = askDrink();
                    break;
                case 2:
                    nbSide = askSide(true);
                    break;
                case 3:
                    nbSide = askSide(false);
                    nbDrink = askDrink();
                    break;
            }
            return nbMenu + "," + nbSide + "," + nbDrink + "%n";
        }
    /**
     * Run asking process for several menus.
     */
    public void runMenus() throws IOException {
        Path orderPath = Paths.get("order.csv");
        System.out.println("Combien souhaitez vous commander de menu ?");
        int menuQuantity = -1;
        boolean responseIsGood;
        do {
            try {
                menuQuantity = Interaction.sc.nextInt();
                responseIsGood = true;
            } catch (InputMismatchException e) {
                Interaction.sc.next();
                System.out.println("Vous devez saisir un nombre, correspondant au nombre de menus souhaités");
                responseIsGood = false;
            }
        } while (!responseIsGood);
        Interaction.orderSummary = "Résumé de votre commande :%n";
        for (int i = 0; i < menuQuantity; i++) {
            Interaction.orderSummary += "Menu " + (i + 1) + ":%n";
            String orderLine = run_menu();
            try {
                Files.write(orderPath, String.format(orderLine).getBytes(), APPEND);
            } catch (IOException e) {
                System.out.println("Ooops une erreur est survenue. Merci de réessayer plus tard");
                return;
            }        }
        System.out.println("");
        System.out.println(String.format(Interaction.orderSummary));
    }
    /**
     * Display a selected side depending on all sides enable or not.
     * All sides = vegetables, frites and rice
     * No all sides = rice or not
     * @param nbSide The selected Side
     * @param allSidesEnable  enable display for all side or not
     */
    public void displaySelectedSide(int nbSide, boolean allSidesEnable) {
        if (allSidesEnable) {
            switch (nbSide) {
                case 1:
                    System.out.println("Vous avez choisi comme accompagnement : légumes frais");
                    break;
                case 2:
                    System.out.println("Vous avez choisi comme accompagnement : frites");
                    break;
                case 3:
                    System.out.println("Vous avez choisi comme accompagnement : riz");
                    break;
                default:
                    System.out.println("Vous n'avez pas choisi d'accompagnement parmi les choix proposés");
                    break;
            }
        } else {
            switch (nbSide) {
                case 1:
                    System.out.println("Vous avez choisi comme accompagnement : riz");
                    break;
                case 2:
                    System.out.println("Vous avez choisi comme accompagnement : pas de riz");
                    break;
                default:
                    System.out.println("Vous n'avez pas choisi d'accompagnement parmi les choix proposés");
                    break;
            }
        }
    }
    /**
     * Display a selected drink.
     * @param nbDrink The selected drink.
     */
    public void displaySelectedDrink(int nbDrink) {
        switch (nbDrink) {
            case 1:
                System.out.println("Vous avez choisi comme boisson : eau plate");
                break;
            case 2:
                System.out.println("Vous avez choisi comme boisson : eau gazeuse");
                break;
            case 3:
                System.out.println("Vous avez choisi comme boisson : soda");
                break;
            default:
                System.out.println("Vous n'avez pas choisi de boisson parmi les choix proposés");
                break;
        }
    }
    /**
     * Display a question about menu in the standard input, get response and display it
     */
    public int askMenu() {
        String[] menus = {"poulet", "boeuf", "végétarien"};
        return Interaction.askSomething("menu", menus);
    }

    public int askSide(boolean allSidesEnable) {
        if (allSidesEnable) {
            String[] responsesAllSide = {"légumes frais", "frites", "riz"};
            return Interaction.askSomething("accompagnement", responsesAllSide);
        } else {
            String[] responsesOnlyRice = {"riz", "pas de riz"};
            return Interaction.askSomething("accompagnement", responsesOnlyRice);
        }
    }

    /**
     * Display a question about drink in the standard input, get response and display it
     * @return chosen value
     */
    public int askDrink() {
        String[] responsesDrink = {"eau plate", "eau gazeuse", "soda"};
        return Interaction.askSomething("boisson", responsesDrink);
    }
}