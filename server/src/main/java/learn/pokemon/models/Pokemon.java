package learn.pokemon.models;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;

public class Pokemon {
    private int id;
    private String name;
    private int height;
    private int weight;
    private LocalDate birthday;
    private ArrayList<Type> types;
    private Vibe vibe;
    private User user;
    private ArrayList<Move> moves;
    private Ability ability;

    public Pokemon() {
    }

    public Pokemon(int id, String name, int height, int weight, LocalDate birthday, ArrayList<Type> types,
                   Vibe vibe, User user, ArrayList<Move> moves, Ability ability) {
        this.id = id;
        this.name = name;
        this.height = height;
        this.weight = weight;
        this.birthday = birthday;
        this.types = types;
        this.vibe = vibe;
        this.user = user;
        this.moves = moves;
        this.ability = ability;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public ArrayList<Type> getTypes() {
        return types;
    }

    public void setTypes(ArrayList<Type> types) {
        this.types = types;
    }

    public Vibe getVibe() {
        return vibe;
    }

    public void setVibe(Vibe vibe) {
        this.vibe = vibe;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ArrayList<Move> getMoves() {
        return moves;
    }

    public void setMoves(ArrayList<Move> moves) {
        this.moves = moves;
    }

    public Ability getAbility() {
        return ability;
    }

    public void setAbility(Ability ability) {
        this.ability = ability;
    }
}
