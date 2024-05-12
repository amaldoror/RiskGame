package game;

import java.util.*;

public class Deck {
    private Card[] cards;
    private int top;

    public Deck() {
        this.cards = new Card[52];
        this.top = 0;
        generateNewDeck();
        shuffle();
    }

    public Deck(Card[] cards) {
        this.cards = cards;
        this.top = 0;
    }

    private Deck generateNewDeck() {
        int index = 0;
        for (CardType cardType: CardType.values()) {
            for (CardRank cardRank : CardRank.values()) {
                this.cards[index] = new Card("Card" + index, cardType, cardRank);
                index++;
            }
        }
        top = this.cards.length;
        return this;
    }

    public void addCard(Card card) {
        if (top < cards.length) {
            cards[top++] = card;
        } else {
            System.out.println("Deck is full. Cannot add more cards.");
        }
    }

    public Card drawSpecificCard(Card card) {
        for (int i = 0; i < top; i++) {
            if (cards[i].equals(card)) {
                Card drawnCard = cards[i];
                cards[i] = cards[--top];
                cards[top] = null;
                return drawnCard;
            }
        }
        return null;
    }

    public Card drawTopCard(){
        return this.cards[top-1];
    }

    public List<Card> dealHand(int numberOfCards) {
        List<Card> hand = new ArrayList<>();
        for (int i = 0; i < numberOfCards; i++) {
            if (top > 0) {
                hand.add(getRandomCard());
            } else {
                break; // Deck is empty
            }
        }
        return hand;
    }

    public boolean hasCard(Card card) {
        for (int i = 0; i < top; i++) {
            if (cards[i].equals(card)) {
                return true;
            }
        }
        return false;
    }

    public void clearDeck() {
        Arrays.fill(cards, null);
        top = 0;
    }

    public Card getRandomCard() {
        if (top == 0) {
            shuffle();
        }
        return cards[--top];
    }

    public void shuffle() {
        Random rand = new Random();
        for (int i = cards.length - 1; i > 0; i--) {
            int randomIndex = rand.nextInt(i + 1);
            Card temp = cards[i];
            cards[i] = cards[randomIndex];
            cards[randomIndex] = temp;
        }
        top = cards.length;
    }

    public void discard(Card card) {
        for (int i = 0; i < cards.length; i++) {
            if (cards[i].equals(card)) {
                for (int j = i; j < cards.length - 1; j++) {
                    cards[j] = cards[j + 1];
                }
                cards[cards.length - 1] = null;
                top--;
                return;
            }
        }
    }

    public int getRemainingCards() {
        return top;
    }

    public boolean isEmpty() {
        return top == 0;
    }
}
