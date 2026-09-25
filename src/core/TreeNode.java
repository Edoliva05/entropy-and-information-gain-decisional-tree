package core;
import data.Dato;

public interface TreeNode {
    // Ogni nodo deve saper fare una predizione dato un input
    boolean predict(Dato input);
}
