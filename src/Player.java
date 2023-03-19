

    public enum Player {
    X("-fx-text-fill: deeppink;"),
    O("-fx-text-fill: darkcyan;");

    private String style;

    Player(String style) {
        this.style = style;
    }

    public String getStyle() {
        return style;
    }
}

