package ca.sheridancollege.project;

import java.util.ArrayList;
import java.util.Iterator;

public class GoFishPlayer extends Player {


    public GoFishPlayer(String name) {
        super(name);
    }


    public boolean hasRank(int rank) {
        for (Card card : getHand()) {
            if (card instanceof PlayingCard) {
                PlayingCard playingCard = (PlayingCard) card;
                if (playingCard.getRank() == rank) {
                    return true;
                }
            }
        }
        return false;
    }

  
    public ArrayList<Card> giveCard(int rank) {
        ArrayList<Card> cardsToGive = new ArrayList<>();
        Iterator<Card> iterator = getHand().iterator();  

        while (iterator.hasNext()) {
            Card card = iterator.next();
            if (card instanceof PlayingCard) {
                PlayingCard playingCard = (PlayingCard) card;
                if (playingCard.getRank() == rank) {
                    iterator.remove();  
                    cardsToGive.add(card);  
                }
            }
        }

        return cardsToGive;
    }


    public Card drawFromDeck(GroupOfCards deck) {
        Card drawnCard = deck.drawCard();
        if (drawnCard != null) {
            addCard(drawnCard);
        }
        return drawnCard;
    }

  
    public void collectCards(ArrayList<Card> cards) {
        for (Card card : cards) {
            addCard(card); 
        }
    }

    public void checkForBooks() {
        ArrayList<PlayingCard> hand = new ArrayList<>();
        for (Card card : getHand()) {
            if (card instanceof PlayingCard) {
                hand.add((PlayingCard) card);
            }
        }

       
        for (int rank = 1; rank <= 13; rank++) {
            int count = 0;
            for (PlayingCard card : hand) {
                if (card.getRank() == rank) {
                    count++;
                }
            }

            
            if (count == 4) {
                
                ArrayList<PlayingCard> toRemove = new ArrayList<>();
                for (PlayingCard card : hand) {
                    if (card.getRank() == rank) {
                        toRemove.add(card);
                    }
                }
                hand.removeAll(toRemove);
                System.out.println(getName() + " has collected a book of " + rank);
            }
        }
    }

    
    public int getBooks() {
        int books = 0;
        ArrayList<PlayingCard> hand = new ArrayList<>();
        for (Card card : getHand()) {
            if (card instanceof PlayingCard) {
                hand.add((PlayingCard) card);
            }
        }

        
        for (int rank = 1; rank <= 13; rank++) {
            int count = 0;
            for (PlayingCard card : hand) {
                if (card.getRank() == rank) {
                    count++;
                }
            }
            if (count == 4) {
                books++;
            }
        }
        return books;
    }

   
    @Override
    public void play() {
        
    }

    
    public void drawCards(GroupOfCards deck, int cardsPerPlayer) {
        for (int i = 0; i < cardsPerPlayer; i++) {
            Card card = deck.drawCard();
            if (card != null) {
                addCard(card);
            }
        }
    }
}
