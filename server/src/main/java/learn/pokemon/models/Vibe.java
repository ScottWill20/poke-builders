package learn.pokemon.models;

public enum Vibe {
    HARDY(1, "hardy"),
    LONELY(2, "lonely"),
    BRAVE(3, "brave"),
    ADAMANT(4, "adamant"),
    NAUGHTY(5, "naughty"),
    BOLD(6, "bold"),
    DOCILE(7, "docile"),
    RELAXED(8, "relaxed"),
    IMPISH(9, "impish"),
    LAX(10, "lax"),
    TIMID(11, "timid"),
    HASTY(12, "hasty"),
    SERIOUS(13, "serious"),
    JOLLY(14, "jolly"),
    NAIVE(15, "naive"),
    MODEST(16, "modest"),
    MILD(17, "mild"),
    QUIET(18, "quiet"),
    BASHFUL(19, "bashful"),
    RASH(20, "rash"),
    CALM(21, "calm"),
    GENTLE(22, "gentle"),
    SASSY(23, "sassy"),
    CAREFUL(24, "careful"),
    QUIRKY(25, "quirky")
    ;

    private int id;
    private String name;

    Vibe(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
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
