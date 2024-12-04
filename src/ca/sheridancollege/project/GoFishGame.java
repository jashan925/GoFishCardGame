package ca.sheridancollege.project;

import java.util.ArrayList;
import java.util.Scanner;

public class GoFishGame extends Game<GoFishPlayer> {
    private final GroupOfCards deck;
    private final int rounds;

    public GoFishGame(String name, int rounds) {
        super(name);
        this.rounds = rounds;
        this.deck = new GroupOfCards();
        this.deck.initializeStandardDeck();
        this.deck.shuffle();
    }

    @Override
    public void play() {
        // Ask for the number of players
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of players: ");
        int numPlayers = scanner.nextInt();

        // Create players and assign them names: "Player 1", "Player 2", etc.
        ArrayList<GoFishPlayer> players = new ArrayList<>();
        for (int i = 0; i < numPlayers; i++) {
            players.add(new GoFishPlayer("Player " + (i + 1)));
        }

        // Set players to the game
        setPlayers(players);

        // Deal cards
        int cardsPerPlayer = (numPlayers <= 3) ? 7 : 5;
        for (GoFishPlayer player : players) {
            player.drawCards(deck, cardsPerPlayer);
        }

        // Game Rounds
        for (int round = 1; round <= rounds; round++) {
            System.out.println("\n=== Round " + round + " ===");

            for (GoFishPlayer currentPlayer : players) {
                System.out.println("\n" + currentPlayer.getName() + "'s turn!");
                if (currentPlayer.getHand().isEmpty()) {
                    System.out.println(currentPlayer.getName() + " has no cards. Turn skipped.");
                    continue;
                }

                System.out.println("Your hand: " + currentPlayer.getHand());
                System.out.println("Choose an opponent:");
                for (int i = 0; i < numPlayers; i++) {
                    if (!players.get(i).equals(currentPlayer)) {
                        System.out.println(i + ": " + players.get(i).getName());
                    }
                }

                int opponentIndex;
                do {
                    System.out.print("Enter opponent index: ");
                    opponentIndex = scanner.nextInt();
                } while (opponentIndex < 0 || opponentIndex >= numPlayers || players.get(opponentIndex).equals(currentPlayer));

                GoFishPlayer opponent = players.get(opponentIndex);

                int rank;
                // Prompt for rank and validate it
                while (true) {
                    System.out.print("Ask for a rank (1-13): ");
                    rank = scanner.nextInt();
                    if (rank >= 1 && rank <= 13) {
                        break; // Exit loop if the rank is valid
                    } else {
                        System.out.println("Invalid choice. Please enter a rank between 1 and 13.");
                    }
                }

                if (opponent.hasRank(rank)) {
                    System.out.println(opponent.getName() + " has the card(s).");
                    currentPlayer.collectCards(opponent.giveCard(rank)); // Collect the cards
                } else {
                    System.out.println(opponent.getName() + " says 'Go Fish!'");
                    Card drawnCard = currentPlayer.drawFromDeck(deck);
                    if (drawnCard != null) {
                        currentPlayer.addCard(drawnCard);
                        System.out.println("You drew: " + drawnCard);
                    } else {
                        System.out.println("The deck is empty.");
                    }
                }

                // Check for books at the end of the round
                currentPlayer.checkForBooks();
            }
        }

        // At the end of the game, determine the winner and display scores
        determineWinnerAndScores(players);
    }

    private void determineWinnerAndScores(ArrayList<GoFishPlayer> players) {
        // Determine the player with the most books
        GoFishPlayer winner = null;
        int maxBooks = 0;

        for (GoFishPlayer player : players) {
            int books = player.getBooks();
            System.out.println(player.getName() + " has " + books + " books.");
            if (books > maxBooks) {
                maxBooks = books;
                winner = player;
            }
        }

        // Declare the winner
        if (winner != null) {
            System.out.println("\nThe winner is " + winner.getName() + " with " + maxBooks + " books!");
        } else {
            System.out.println("\nNo winner, no books collected.");
        }
    }

    @Override
    public void declareWinner() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
