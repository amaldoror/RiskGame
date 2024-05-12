package game;

public class Card {

    private final CardRank cardRank;
    private final String countryName;
    private final CardType cardType;

    public Card(String countryName, CardType cardType, CardRank cardRank) {
        this.cardRank = cardRank;
        this.countryName = countryName;
        this.cardType = cardType;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass()!= obj.getClass()) {
            return false;
        }
        Card card = (Card) obj;
        return cardType == card.cardType && cardRank == card.cardRank;
    }


    public String getName() {
        return countryName;
    }


    public CardRank getCardRank() {
        return cardRank;
    }

    public CardType getCardType() {
        return cardType;
    }

    @Override
    public String toString() {
        return    "Name: " + getName() + "\n"
                + "Rank: " + getCardRank() + "\n"
                + "Type: " + getCardType() + "\n";

    }
}