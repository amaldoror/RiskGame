package game;

import java.io.Console;
import java.util.*;


public class RiskGame {
    public Map<String, Player> players;
    public final Board board;
    private Deck deck;
    private Random random;
    private Console console;
    private Scanner scanner;

    public RiskGame(int numPlayers) {
        players = new HashMap<>();
        board   = new Board();
        deck    = new Deck();
        random  = new Random();
        console = System.console();
        scanner = new Scanner(System.in);

        createPlayers(numPlayers);
        board.initializeBoard();
        startGame();
    }

    private void createPlayers(int numPlayers) {
        for (int i = 1; i <= numPlayers; i++) {
            players.put("Player " + i, new Player("Player " + i, board, deck));
        }
    }

    public void startGame() {
        // Distribute initial countries to players
        List<Country> countries = board.getCountryList();
        for (Player player : players.values()) {
            Country country = countries.get(random.nextInt(countries.size()));
            player.addCountry(country);
            countries.remove(country);
        }

        // Start the game loop
        while (true) {
            for (Player player : players.values()) {
                takeTurn(player);
            }
        }
    }

    private void takeTurn(Player player) {
        System.out.println("It's " + player.getName() + "'s turn.");

        // Draw a card
        Card card = player.drawCard(deck);
        System.out.println("You drew a " + card.getName() + " card.");

        // Attack a country
        Country attackCountry = chooseCountryToAttack(player);
        if (attackCountry!= null) {
            Player defender = getOwner(attackCountry);
            if (defender!= null) {
                int attackerDice = rollDice(3);
                int defenderDice = rollDice(2);
                System.out.println("Attacker rolled " + attackerDice + " and defender rolled " + defenderDice);
                if (attackerDice > defenderDice) {
                    System.out.println("Attacker wins!");
                    defender.removeCountry(attackCountry);
                    player.addCountry(attackCountry);
                } else {
                    System.out.println("Defender wins!");
                }
            } else {
                System.out.println("You can't attack a country that no one owns.");
            }
        }

        // Fortify a country
        Country fortifyCountry = chooseCountryToFortify(player);
        if (fortifyCountry!= null) {
            System.out.println("You fortified " + fortifyCountry.getName());
        }

        System.out.println();
    }

    private Country chooseCountryToAttack(Player player) {
        ArrayList<Country> countries = player.getCountries();
        if (countries.isEmpty()) {
            return null;
        }
        System.out.println("Choose a country to attack:");
        for (int i = 0; i < countries.size(); i++) {
            System.out.println((i + 1) + ". " + countries.get(i).getName());
        }
        int choice = readInt("Enter the number of the country: ");
        return countries.get(choice - 1);
    }

    private Country chooseCountryToFortify(Player player) {
        List<Country> countries = player.getCountries();
        if (countries.isEmpty()) {
            return null;
        }
        System.out.println("Choose a country to fortify:");
        for (int i = 0; i < countries.size(); i++) {
            System.out.println((i + 1) + ". " + countries.get(i).getName());
        }
        int choice = readInt("Enter the number of the country: ");
        return countries.get(choice - 1);
    }

    public Player getOwner(Country country) {
        for (Player player : players.values()) {
            if (player.getCountries().contains(country)) {
                return player;
            }
        }
        return null;
    }

    private int rollDice(int numDice) {
        int total = 0;
        for (int i = 0; i < numDice; i++) {
            total += random.nextInt(6) + 1;
        }
        return total;
    }

    private int readInt(String prompt) {
        System.out.print(prompt);
        return scanner.nextInt();
    }

    public static void main(String[] args) {
        RiskGame game = new RiskGame(2);
        game.startGame();
    }
}