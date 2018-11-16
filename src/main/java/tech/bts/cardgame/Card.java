package tech.bts.cardgame;

public class Card {
    private int magic;
    private int strength;
    private int intelligence;
    private int hand1;
    private int hand2;

    public Card(int magic, int strength, int intelligence) {
        this.magic = magic;
        this.strength = strength;
        this.intelligence = intelligence;
    }

    public int getMagic() {
        return magic;
    }

    public int getStrength() {
        return strength;
    }

    public int getIntelligence() {
        return intelligence;
    }

    @Override
    public String toString() {
        return "M:" + magic + ", " + "S:" + strength + ", " + "I:" + intelligence;

    }



}
