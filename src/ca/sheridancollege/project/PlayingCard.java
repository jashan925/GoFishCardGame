package ca.sheridancollege.project;

public class PlayingCard extends Card {
    private final int rank; // Represents the rank (1 for Ace, 2-10, 11 for Jack, 12 for Queen, 13 for King)
    private final String suit; // Represents the suit ("Hearts", "Diamonds", "Clubs", "Spades")

    public PlayingCard(int rank, String suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public int getRank() {
        return rank;
    }

    public String getSuit() {
        return suit;
    }

    @Override
public String toString() {
    String rankName;
    switch (rank) {
        case 1: rankName = "Ace"; break;
        case 11: rankName = "Jack"; break;
        case 12: rankName = "Queen"; break;
        case 13: rankName = "King"; break;
        default: rankName = String.valueOf(rank); break;
    }
    return rankName + " of " + suit;
}

}
