

    public enum Winner {
    NONE(""),
    X("-fx-color: deeppink;"),
    O("-fx-color: darkcyan"),
    TIE("-fx-color: midnightblue;");

    private String style;

    Winner(String style) {
        this.style = style;
    }

    public String getStyle() {
        return style;
    }
}

