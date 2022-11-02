package learn.pokemon.models;

public enum Type {
    NORMAL("normal"),
    FIRE( "fire"),
    WATER( "water"),
    GRASS( "grass"),
    ELECTRIC( "electric"),
    ICE( "ice"),
    FIGHTING( "fighting"),
    POISON( "poison"),
    GROUND( "ground"),
    FLYING( "flying"),
    PSYCHIC( "psychic"),
    BUG( "bug"),
    ROCK( "rock"),
    GHOST( "ghost"),
    DARK( "dark"),
    DRAGON( "dragon"),
    STEEL( "steel"),
    FAIRY( "fairy");

    private String name;

    Type(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static Type getTypeByName(String name) {
        for (Type type : Type.values()) {
            if (type.getName().equalsIgnoreCase(name)) {
                return type;
            }
        }
        String message = String.format("%s type does not exist.", name);
        throw new RuntimeException(message);
    }
}
