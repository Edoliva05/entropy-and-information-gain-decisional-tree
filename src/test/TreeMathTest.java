import core.TreeMath;

package test;

public class TreeMathTest {
    public static void main(String[] args) {

        System.out.println("--- TEST MATEMATICA ALBERO ---");

        //Calcoliamo entropia di partenza con 29 positivi e 35 negativi
        double entropiaPadre = TreeMath.calcolaEntropia(29, 35);
        System.out.println("Entropia Radice (atteso 0.994...): " + entropiaPadre);

        //Calcoliamo guadagno informativo di A
        double gainA = TreeMath.calcolaGain(entropiaPadre, 21, 5, 8, 30);
        System.out.println("Gain Attributo A (atteso 0.288...): " + gainA);

        //Calcoliamo guadagno informativo di B
        double gainB = TreeMath.calcolaGain(entropiaPadre, 18, 33, 11, 2);
        System.out.println("Gain Attributo B (atteso 0.122...): " + gainB);

    }
}
