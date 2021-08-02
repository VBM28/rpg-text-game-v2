package game;

import game.characters.Player;

import java.util.Scanner;

import static game.util.GameUtils.*;

public class GameMain {
    //using the scanner for user input
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Player player = gameOpening(scanner);
        weaponList(player, scanner);
        gameOpening(player);
        boolean gameOngoing = true;
        startPlayingGame(player, gameOngoing,scanner);
    }

}
