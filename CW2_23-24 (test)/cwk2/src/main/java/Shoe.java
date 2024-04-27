import java.util.*;
// TODO: Implement the Shoe class in this file
public class Shoe extends CardCollection {
    public Shoe(int decks) {
        super();
        if (decks != 6 && decks != 8) {
            throw new CardException("Invalid number of decks. Must be 6 or 8.");
        }
        for (int i = 0; i < decks; i++) {
            for (Card.Suit suit : Card.Suit.values()) {
                for (Card.Rank rank : Card.Rank.values()) {
                    cards.add(new BaccaratCard(rank, suit));
                }
            }
        }
        
    }

   public Card deal() {
        if (size() == 0) {
            throw new CardException("Cannot deal from an empty shoe.");
        }
        return cards.remove(0);
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }
}