package core;

public class TreeMath {

    public static double calcolaEntropia(int positivi, int negativi){
        int totElem = positivi + negativi;

        //Entropia minima, non c'è incertezza
        if(totElem == 0){
            return 0.0;
        }

        //Calcolo probabilità
        double pPos = (double) positivi/totElem;
        double pNeg = (double) negativi/totElem;

        double entropia = 0.0;

        if(pPos > 0){
            entropia -= pPos * (Math.log(pPos)/Math.log(2));
        }

        if(pNeg > 0){
            entropia -= pNeg * (Math.log(pNeg)/Math.log(2));
        }

        return entropia;
    }

    public static double calcolaGain(double entropiaPadre, int posT, int negT, int posF, int negF){
        // Quanti elementi finiscono nel ramo Vero e quanti nel ramo Falso?
        int totT = posT + negT;
        int totF = posF + negF;
        int totPadre = totT + totF;

        if(totPadre == 0){
            return 0.0;
        }

        double entropiaT = calcolaEntropia(posT, negT);
        double entropiaF = calcolaEntropia(posF, negF);

        //Calcolo del peso di ciascun ramo rispetto al totale
        double pesoT = (double) totT / totPadre;
        double pesoF = (double) totF / totPadre;

        // Il Guadagno è l'entropia iniziale meno la somma delle entropie dei figli ponderate per il loro peso
        double gain = entropiaPadre - (pesoT * entropiaT) - (pesoF * entropiaF);

        return gain;
    }
}
