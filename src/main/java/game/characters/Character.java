package game.characters;

public class Character {
    //fields
    private String name;
    private Float lifePoints;
    private Float attackPoints;

    //constructor
    public Character(String name, Float lifePoints, Float attackPoints) {
        this.name = name;
        this.lifePoints = lifePoints;
        this.attackPoints = attackPoints;
    }

    //getters&setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getLifePoints() {
        return lifePoints;
    }

    public void setLifePoints(Float lifePoints) {
        this.lifePoints = lifePoints;
    }

    public Float getAttackPoints() {
        return attackPoints;
    }

    public void setAttackPoints(Float attackPoints) {
        this.attackPoints = attackPoints;
    }

    //overriding the toString() method to print more clear information
    @Override
    public String toString() {
        return name + " current status: " +
                " lifePoints=" + lifePoints +
                ", attackPoints=" + attackPoints;
    }

    //methods
    public boolean takeDamage(float damage) {
        float value = this.getLifePoints() - damage;
        if (value <= 0) {
            this.setLifePoints(0F);
            return false;
        } else {
            this.setLifePoints(value);
            return true;
        }
    }
}
