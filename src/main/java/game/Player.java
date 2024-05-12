package game;

import java.util.*;

public class Player {
    private String name;
    private List<Country> countries;
    private int armies;

    public Player(String name, Board board, Deck deck) {
        this.name = name;
        countries = new ArrayList<>();
        armies = 0;
    }

    public void attackCountry(Country attackingCountry, Country defendingCountry, int numDiceAttack, int numDiceDefense) {
        Random random = new Random();
        List<Integer> attackRolls = new ArrayList<>();
        List<Integer> defenseRolls = new ArrayList<>();

        for (int i = 0; i < numDiceAttack; i++) {
            attackRolls.add(random.nextInt(6) + 1); // Simuliere Würfelwurf für Angriff
        }
        for (int i = 0; i < numDiceDefense; i++) {
            defenseRolls.add(random.nextInt(6) + 1); // Simuliere Würfelwurf für Verteidigung
        }

        // Sortiere die Würfelwürfe absteigend
        attackRolls.sort(Comparator.reverseOrder());
        defenseRolls.sort(Comparator.reverseOrder());

        // Vergleiche die Würfelwürfe und entferne entsprechende Armeen
        for (int i = 0; i < Math.min(numDiceAttack, numDiceDefense); i++) {
            if (attackRolls.get(i) > defenseRolls.get(i)) {
                // Der Angreifer hat gewonnen
                defendingCountry.removeArmy();
            } else {
                // Der Verteidiger hat gewonnen
                attackingCountry.removeArmy();
            }
        }
    }

    public Card drawCard(Deck deck) {
        return deck.drawTopCard();
    }

    public String getName() {
        return name;
    }

    public ArrayList<Country> getCountries() {
        return (ArrayList<Country>) countries;
    }

    public void addCountry(Country country) {
        countries.add(country);
    }

    public void removeCountry(Country attackCountry) {

    }
}
