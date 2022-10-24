package learn.pokemon.models;

public enum Type {
    NORMAL(1, "normal"),
    FIRE(2, "fire"),
    WATER(3, "water"),
    GRASS(4, "grass"),
    ELECTRIC(5, "electric"),
    ICE(6, "ice"),
    FIGHTING(7, "fighting"),
    POISON(8, "poison"),
    GROUND(9, "ground"),
    FLYING(10, "flying"),
    PSYCHIC(11, "psychic"),
    BUG(12, "bug"),
    ROCK(13, "rock"),
    GHOST(14, "ghost"),
    DARK(15, "dark"),
    DRAGON(16, "dragon"),
    STEEL(17, "steel"),
    FAIRY(18, "fairy");

    private int id;
    private String name;

    Type(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
