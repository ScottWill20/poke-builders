package learn.pokemon.models;

public enum Vibe {
    HARDY("hardy"),
    LONELY( "lonely"),
    BRAVE( "brave"),
    ADAMANT( "adamant"),
    NAUGHTY( "naughty"),
    BOLD( "bold"),
    DOCILE( "docile"),
    RELAXED( "relaxed"),
    IMPISH( "impish"),
    LAX( "lax"),
    TIMID( "timid"),
    HASTY( "hasty"),
    SERIOUS( "serious"),
    JOLLY( "jolly"),
    NAIVE( "naive"),
    MODEST( "modest"),
    MILD( "mild"),
    QUIET( "quiet"),
    BASHFUL( "bashful"),
    RASH( "rash"),
    CALM( "calm"),
    GENTLE( "gentle"),
    SASSY( "sassy"),
    CAREFUL( "careful"),
    QUIRKY( "quirky")
    ;

    private String name;

    Vibe(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static Vibe getVibeByName(String name) {
        for (Vibe vibe : Vibe.values()) {
            if (vibe.getName().equalsIgnoreCase(name)) {
                return vibe;
            }
        }
        String message = String.format("%s vibe does not exist.", name);
        throw new RuntimeException(message);
    }
}
