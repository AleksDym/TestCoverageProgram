package enums;

//von welchem Format ist die Input Datei

public enum FileFormat {
    CSV("csv"), //CSV haben wir keine Zeit zu implementieren, sorry
    MD("md");

    private final String fileEnding;

    FileFormat(String fileEnding) {
        this.fileEnding = fileEnding;
    }

    public String getFileEnding() {
        return fileEnding;
    }
}
