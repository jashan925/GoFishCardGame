package ca.sheridancollege.project;

import java.util.ArrayList;
import java.util.Collections;

public class GroupOfCards {
    private final ArrayList<Card> cards;

    public GroupOfCards() {
        cards = new ArrayList<>();
    }

    public void initializeStandardDeck() {
        String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};
        for (String suit : suits) {
            for (int rank = 1; rank <= 13; rank++) {
                cards.add(new PlayingCard(rank, suit));
            }
        }
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public Card drawCard() {
        return cards.isEmpty() ? null : cards.remove(0);
    }

    public boolean isEmpty() {
        return cards.isEmpty();
    }
}
