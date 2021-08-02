package game.characters;

import java.util.Random;

public class Player extends Character {
    //fields only for Player class
    private String weapon;
    private Integer healthPotion;

    // constructors
    public Player(String name, Float lifePoints, Float attackPoints) {
        super(name, lifePoints, attackPoints);
        this.healthPotion = new Random().nextInt(10);
    }

    //randomly generating the HP and attackPoints
    public Player(String name) {
        super(name, new Random().nextFloat() * 100F, new Random().nextFloat() * 45F);
        this.healthPotion = new Random().nextInt(10);
    }

    //getters&setters
    public String getWeapon() {
        return weapon;
    }

    public void setWeapon(String weapon) {
        this.weapon = weapon;
    }

    //overriding toString method from the Character class to have all the details of the player
    @Override
    public String toString() {
        return super.toString() + ", weapon: " + this.weapon + ", remaining health potions: " + this.healthPotion;
    }

    //methods
    public void usePotion() {
        //checking if there are health potions left
        if (this.healthPotion - 1 > 0) {
            //updating the life points and number of potions
            //in case the use of potion exceeds the maximum health value, set life points to 100
            if (this.getLifePoints() + 50 > 100) {
                this.setLifePoints(100f);
            } else {
                this.setLifePoints(this.getLifePoints() + 50);
            }
            this.healthPotion--;
            System.out.println("Health potion used. " + this.healthPotion
                    + " potions remaining. Current HP: " + this.getLifePoints());
        } else {
            System.out.println("You are don't have any health potions left. HP: " + this.getLifePoints());
        }
    }
}
