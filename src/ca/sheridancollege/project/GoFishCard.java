package ca.sheridancollege.project;

public class GoFishCard extends Card {
    private String rank;
    private String suit;

    public GoFishCard(String rank, String suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public String getRank() {
        return rank;
    }

    public String getSuit() {
        return suit;
    }

    @Override
    public String toString() {
        return rank + " of " + suit;
    }
}
