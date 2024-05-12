package game;

import java.util.*;

public class Board {
    private Map<Country, List<Country>> adjacencyMap;
    private List<Country> countryList;

    public Board() {
        adjacencyMap = new HashMap<>();
        countryList = new ArrayList<>();
    }

    public void initializeBoard() {
        // Create countries and add them to the countryList
        Country usa = new Country("USA", CountryType.NORTH_AMERICA);
        Country canada = new Country("Canada", CountryType.NORTH_AMERICA);
        Country mexico = new Country("Mexico", CountryType.NORTH_AMERICA);
        Country brazil = new Country("Brazil", CountryType.SOUTH_AMERICA);
        // Add more countries...

        countryList.add(usa);
        countryList.add(canada);
        countryList.add(mexico);
        countryList.add(brazil);
        // Add more countries to the countryList...

        // Add neighbors for each country
        adjacencyMap.put(usa, Arrays.asList(canada, mexico)); // USA borders Canada and Mexico
        adjacencyMap.put(canada, Arrays.asList(usa)); // Canada only borders USA
        adjacencyMap.put(mexico, Arrays.asList(usa)); // Mexico only borders USA
        adjacencyMap.put(brazil, Arrays.asList()); // Brazil has no neighbors
        // Add neighbors for more countries...
    }

    public List<Country> getCountryList() {
        return countryList;
    }

    public Map<Country, List<Country>> getAdjacencyMap() {
        return adjacencyMap;
    }

    public Country getCountryByName(String name) {
        for (Country country : countryList) {
            if (country.getName().equals(name)) {
                return country;
            }
        }
        return null;
    }

    public List<Country> getNeighbors(Country country) {
        return adjacencyMap.get(country);
    }

    public boolean isNeighbor(Country country1, Country country2) {
        List<Country> neighbors = adjacencyMap.get(country1);
        return neighbors!= null && neighbors.contains(country2);
    }

    public void addCountry(Country country) {
        countryList.add(country);
    }

    public void addNeighbor(Country country, Country neighbor) {
        adjacencyMap.computeIfAbsent(country, k -> new ArrayList<>()).add(neighbor);
    }

    public void removeCountry(Country country) {
        countryList.remove(country);
        adjacencyMap.remove(country);
        for (List<Country> neighbors : adjacencyMap.values()) {
            neighbors.remove(country);
        }
    }

    public void removeNeighbor(Country country, Country neighbor) {
        adjacencyMap.computeIfPresent(country, (k, v) -> {
            v.remove(neighbor);
            return v;
        });
    }
}