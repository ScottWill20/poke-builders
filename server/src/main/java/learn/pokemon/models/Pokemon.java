package learn.pokemon.models;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Pokemon {
    private int id;
    private String name;
    private String description;
    private String url;
    private double height;
    private double weight;
    private LocalDate birthday;
    private Type type;
    private Vibe vibe;
    private User user;
    private List<Move> moves;
    private Ability ability;
    private boolean isPrivate;

    public Pokemon() {
    }

    public Pokemon(int id, String name, String description, String url, int height, int weight, LocalDate birthday, Type type,
                   Vibe vibe, User user, ArrayList<Move> moves, Ability ability, boolean isPrivate) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.url = url;
        this.height = height;
        this.weight = weight;
        this.birthday = birthday;
        this.type = type;
        this.vibe = vibe;
        this.user = user;
        this.moves = moves;
        this.ability = ability;
        this.isPrivate = isPrivate;
    }

    public Pokemon(int id, String name, String description, String url, int height, int weight, String type,
    String vibe, User user, ArrayList<Move> moves, Ability ability, boolean isPrivate) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.url = url;
        this.height = height;
        this.weight = weight;
        this.birthday = LocalDate.now();
        this.type = Type.getTypeByName(type);
        this.vibe = Vibe.getVibeByName(vibe);
        this.user = user;
        this.moves = moves;
        this.ability = ability;
        this.isPrivate = isPrivate;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
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

    public List<Move> getMoves() {
        return moves;
    }

    public void setMoves(List<Move> moves) {
        this.moves = moves;
    }

    public Ability getAbility() {
        return ability;
    }

    public void setAbility(Ability ability) {
        this.ability = ability;
    }

    public boolean isPrivate() {
        return isPrivate;
    }

    public void setPrivate(boolean aPrivate) {
        isPrivate = aPrivate;
    }
}
