package test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import core.CostruttoreAlbero;
import core.TreeNode;
import data.Dato;
import data.RecordAddestramento;

public class MainTest {
    public static void main(String[] args) {
        System.out.println("--- ADDESTRAMENTO ALBERO DECISIONALE ---");

        // 1. Prepariamo il dataset di addestramento
        List<RecordAddestramento> dataset = new ArrayList<>();

        // Caso 1: Ha riposato bene e fatto mobilità -> Ottima sessione (True)
        dataset.add(creaRecord(true, true, true));
        // Caso 2: Ha riposato bene ma niente mobilità -> Sessione comunque buona grazie all'energia (True)
        dataset.add(creaRecord(true, false, true));
        // Caso 3: Ha dormito poco, anche se ha fatto mobilità -> Sessione fiacca, manca la forza (False)
        dataset.add(creaRecord(false, true, false));
        // Caso 4: Poco sonno e zero mobilità -> Pessima sessione, rischio infortunio (False)
        dataset.add(creaRecord(false, false, false));

        List<String> attributi = Arrays.asList("Ha_Dormito_Bene", "Ha_Fatto_Stretching");

        // 2. Addestriamo l'albero
        TreeNode albero = CostruttoreAlbero.addestra(dataset, attributi);
        System.out.println("Albero costruito con successo!\n");

        // 3. Facciamo una predizione su un dato nuovo 
        System.out.println("--- FASE DI PREDIZIONE ---");
        
        // Immaginiamo di arrivare in palestra dopo aver dormito solo 4 ore, ma avendo fatto un bel riscaldamento.
        Map<String, Boolean> featureNuovaSituazione = new HashMap<>();
        featureNuovaSituazione.put("Ha_Dormito_Bene", false);
        featureNuovaSituazione.put("Ha_Fatto_Stretching", true);
        Dato nuovaSituazione = new Dato(featureNuovaSituazione);

        boolean predizione = albero.predict(nuovaSituazione);
        
        System.out.println("Condizioni: Ha_Dormito_Bene=false, Ha_Fatto_Stretching=true");
        System.out.println("L'albero predice un'ottima sessione? " + predizione);
    }

    // Metodo di utility per creare i record 
    private static RecordAddestramento creaRecord(boolean sonno, boolean stretching, boolean ottimaSessione) {
        Map<String, Boolean> features = new HashMap<>();
        features.put("Ha_Dormito_Bene", sonno);
        features.put("Ha_Fatto_Stretching", stretching);
        return new RecordAddestramento(new Dato(features), ottimaSessione);
    }
}