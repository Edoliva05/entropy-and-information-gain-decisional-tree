package data;

public class RecordAddestramento {
    private final Dato input;
    private final boolean target; // true per classe positiva, false per negativa

    public RecordAddestramento(Dato input, boolean target) {
        this.input = input;
        this.target = target;
    }

    public Dato getInput() {
        return input;
    }

    public boolean getTarget() {
        return target;
    }
}