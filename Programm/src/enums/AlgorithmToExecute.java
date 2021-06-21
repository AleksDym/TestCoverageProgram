package enums;

//welches Algorithmus will der Benutzen anwenden
public enum AlgorithmToExecute {

    MCDC("mcdc"), MMBUE("mmbue");

    private final String name;

    AlgorithmToExecute(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
