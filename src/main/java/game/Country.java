package game;

import netscape.javascript.JSObject;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;


import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonWriter;
import javax.json.stream.JsonGenerator;

public class Country {
    private String name;
    private CountryType type;
    private Player owner;
    private int armies;
    private List<Country> neighbors;        // Liste der Nachbarländer

    public Country(String name, CountryType type) {
        this.name = name;
        this.type = type;
        this.armies = 1;                    // Jedes Land hat zu Beginn eine Armee
    }

    public void removeArmy() {
        armies--;
    }
    public void addArmy() {
        armies++;
    }

    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public CountryType getType() {
        return this.type;
    }
    public void setType(CountryType type) {
        this.type = type;
    }
    public Player getOwner() {
        return this.owner;
    }
    public void setOwner(Player owner) {
        this.owner = owner;
    }
    public int getArmies() {
        return this.armies;
    }
    public void setArmies(int armies) {
        this.armies = armies;
    }

    public String toString() {
        return      "Country: " + this.getName()    + "\n"
                +   "Type: "    + this.getType()    + "\n"
                +   "Armies: "  + this.getArmies()  + "\n";
    }

    public void addNeighbor(Country neighbor) {
        neighbors.add(neighbor);
    }

    // JSON serialization and deserialization

    public void writeToJson(String filename) {
        try (Writer writer = new FileWriter(filename)) {
            JsonGenerator generator = Json.createGenerator(writer);
            generator.writeStartObject()
                    .write("name", name)
                    .write("type", this.type.toString())
                    .write("armies", this.armies);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Country readFromJson(String filename) {
        try (JsonReader reader = Json.createReader(Files.newBufferedReader(Paths.get(filename)))) {
            JsonObject jsonObject = reader.readObject();
            Country country = new Country(jsonObject.getString("name"), CountryType.valueOf(jsonObject.getString("type")));
            country.setArmies(jsonObject.getInt("armies"));
            return country;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    // Weitere Methoden für Länder, wie das Hinzufügen von Nachbarn, Angriffe usw.
}
