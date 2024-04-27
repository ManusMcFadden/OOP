// TODO: Implement the BaccaratHand class in the file
public class BaccaratHand extends CardCollection {
    public BaccaratHand() {
        super();
    }

    @Override
    public void add(Card card) {
        if (size() < 3) {
            super.add(card);
        }  
    }

    @Override
    public int value() {
        int sum = super.value();
        return sum % 10;
    }

    public boolean isNatural() {
        return (size() == 2) && (value() == 8 || value() == 9);
    }

    public String toString() {
        String myString = "";
        for (int i = 0; i < size(); i++) {
            myString += cards.get(i).toString();
            myString += " ";
        }
        return myString.trim();
    }
}


