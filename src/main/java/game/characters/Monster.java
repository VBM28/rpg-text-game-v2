package game.characters;

import java.util.Random;

import static game.util.GameConstants.MONSTERS_LIST;


public class Monster extends Character {
    //randomly generating the HP and attackPoints
    public Monster() {
        super(MONSTERS_LIST[(new Random()).nextInt(MONSTERS_LIST.length - 1)],
                new Random().nextFloat() * 5F,
                new Random().nextFloat() * 2F);
    }
}
