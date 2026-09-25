package core;
import data.Dato;

public class InternalNode implements TreeNode{
    private final TreeNode trueBranch;
    private final TreeNode falseBranch;
    private final String attributeToTest;

    public InternalNode(String attributeToTest, TreeNode falseBranch, TreeNode trueBranch){
        this.attributeToTest = attributeToTest;
        this.trueBranch = trueBranch;
        this.falseBranch = falseBranch;
    }

    @Override
    public boolean predict(Dato input) {
        // Legge il valore dell'attributo dal dato in ingresso e sceglie il ramo
        boolean attributeValue = input.getFeatureValue(this.attributeToTest);
        if (attributeValue) {
            return trueBranch.predict(input);
        } else {
            return falseBranch.predict(input);
        }
    }

}
