package net.swanndolia;

import java.util.Scanner;

import static net.swanndolia.DataBase.*;

public class IHM {
    private static final Scanner scanner = new Scanner(System.in);

    public static String requestInputFromUser(String message) {
        sendMessageToUser(message);
        return scanner.nextLine();
    }

    public static FoodHabits requestUserChoiceFromEnum(String s) {
        FoodHabits[] foodHabits = FoodHabits.values();
        for (int i = 0; i < foodHabits.length; i++) {
            sendMessageToUser(i + ": " + foodHabits[i].name());
        }
        return foodHabits[Integer.parseInt(scanner.nextLine())];
    }

    public static void sendMessageToUser(String message) {
        System.out.println(message);
    }

    public static void openMenu() {
        sendMessageToUser("1. Ajouter un animal");
        sendMessageToUser("2. Rechercher un animal par ID");
        sendMessageToUser("3. Rechercher un animal par Nom");
        sendMessageToUser("4. Rechercher un animal par habitude alimentaire");
        sendMessageToUser("0. Quitter");

        switch (Integer.parseInt(requestInputFromUser("Que voulez vous faire ?"))) {
            case 1 -> addAnimal();
            case 2 -> sendMessageToUser(
                    String.valueOf(searchAnimalByID(Integer.parseInt(requestInputFromUser("Entrez l'id de l'animal que vous cherchez")))));
            case 3 -> sendMessageToUser(String.valueOf(searchAnimalByName(requestInputFromUser("Entrez le nom de l'animal que vous cherchez"))));
            case 4 -> sendMessageToUser(String.valueOf(searchAnimalByFoodHabits(
                    requestUserChoiceFromEnum("Séléctionnez le numéro correspondant au régime alimentaire de l'animal que vous cherchez"))));
            case 0 -> closeEntityManager();
        }
        openMenu();
    }
}
