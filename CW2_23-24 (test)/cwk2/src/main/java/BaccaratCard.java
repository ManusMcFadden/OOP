// TODO: Implement the BaccaratCard class in this file
public class BaccaratCard extends Card {
    private Card c;
    public BaccaratCard(Rank r, Suit s) {
        super(r, s);
    }
    @Override
    public int value() {
        int v = super.value();
        if (v == 10) {
            v = 0;
        }
        return v;
    }
}
