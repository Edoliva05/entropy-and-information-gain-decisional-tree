package core;
import data.Dato;

public class LeafNode implements TreeNode{
    private final boolean prediction;

    public LeafNode(boolean prediction){
        this.prediction = prediction;
    }

    @Override
    public boolean predict(Dato input) {
        // La foglia non valuta nulla, restituisce solo la sua etichetta
        return this.prediction;
    }
}
