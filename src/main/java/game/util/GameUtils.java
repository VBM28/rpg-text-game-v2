package game.util;


import game.characters.Monster;
import game.characters.Player;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

import static game.util.GameConstants.MONSTERS_LIST;
import static game.util.GameConstants.WEAPONS_LIST;

public class GameUtils {

    public static Player gameOpening(Scanner scanner) {
        System.out.println("Welcome to the game. Let's create a character.\nChoose your character's name:\n");
        return new Player(scanner.nextLine());
    }
    public static void gameOpening(Player player) {
        System.out.println("Welcome to the Dungeon Stream. Let's start the party!\uD83D\uDD25 \n");
    }

    public static void weaponList(Player player, Scanner scanner) {
        //printing the list of available weapons using the for method
        System.out.println("Available weapons:\n");
        for (int j = 0; j < WEAPONS_LIST.length; j++) {
            System.out.println(j + 1 + ". " + WEAPONS_LIST[j]);
        }
        // selecting weapon based on user's input or setting the default one if the choice is outside the list
        System.out.println("\nSelect option: ");
        int choice;
        //in case the input isn't formed only from numbers, it'll throw the exception
        try{
             choice = scanner.nextInt();
        } catch (InputMismatchException e){
            System.out.println("Ooops. Made a typo there. Then we'll use the sword.\n");
            choice = 2;
        }
        if (choice <= WEAPONS_LIST.length && choice > 0) {
            player.setWeapon(WEAPONS_LIST[choice - 1]);
        } else {
            System.out.println("Invalid choice. Default weapon will be used.");
            player.setWeapon(WEAPONS_LIST[(new Random()).nextInt(WEAPONS_LIST.length - 1)]);
        }
    }

    public static void startPlayingGame(Player player, boolean gameOngoing, Scanner scanner){
        player.setLifePoints(1f);
        int choice;
        int numberOfWaves = new Random().nextInt(10) + 5;
        int currentWave = 0;
        System.out.println(player);
        Monster monster = generateNewMonster();

        while(gameOngoing) {
            if (!characterStillAlive(monster.getLifePoints())) {
                ++currentWave;
                if (currentWave > numberOfWaves) {
                    System.out.println("You have completed all " + numberOfWaves + " waves. You have cleared the game!");
                    return;
                }
                monster = generateNewMonster();
            }

            System.out.println("Wave " + currentWave + " out of " + numberOfWaves);
            System.out.println(monster);
            boolean monsterStillAlive = true;

            while(monsterStillAlive) {
                menuList();
                choice = scanner.nextInt();
                switch(choice) {
                    case 1:
                        attack(player, monster);
                        if (characterStillAlive(player.getLifePoints()) && characterStillAlive(monster.getLifePoints())) {
                            System.out.println("Remaining player HP: " + player.getLifePoints() + "\t Monster HP: " + monster.getLifePoints());
                        } else {
                            if (!characterStillAlive(player.getLifePoints())) {
                                System.out.println(player.getName() + " died. Game over!\n");
                                return;
                            }
                            if (!characterStillAlive(monster.getLifePoints())) {
                                System.out.println(monster.getName() + " defeated. Remaining HP: " + player.getLifePoints() + "\n");
                            }
                        }
                        break;
                    case 2:
                        player.usePotion();
                        break;
                    case 3:
                        System.out.println(player);
                        break;
                    case 4:
                        System.out.println("You have quit the game. Thank you for playing.");
                        gameOngoing = false;
                        break;
                    default:
                        System.out.println("Invalid option.");
                        break;
                }

                if (!characterStillAlive(monster.getLifePoints())) {
                    monsterStillAlive = false;
                }
            }
        }
    }

    private static boolean characterStillAlive(Float lifePoints) {
        return lifePoints > 0;
    }

    private static void attack(Player player, Monster monster) {
        player.takeDamage(monster.getAttackPoints());
        monster.takeDamage(player.getAttackPoints());
    }

    private static Monster generateNewMonster() {
        int randomMonster = (new Random()).nextInt(MONSTERS_LIST.length - 1);
        Monster monster = new Monster();
        monster.setName(MONSTERS_LIST[randomMonster]);
        System.out.println("You have encountered a " + monster.getName());
        return monster;
    }

    private static void menuList() {
        System.out.println("\nWhat do you do?\n1. Attack.\t2. Drink Health Potion.\t3. See player details. \t4. Quit.");
    }

}
