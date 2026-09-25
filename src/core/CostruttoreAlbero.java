package core;
import java.util.ArrayList;
import java.util.List;

import data.RecordAddestramento;

public class CostruttoreAlbero {

    public static TreeNode addestra(List<RecordAddestramento> dataset, List<String> attributiDisponibili) {
        
        // --- 1. CASI BASE (Condizioni di stop della ricorsione) ---
        
        // Contiamo quanti target positivi e negativi ci sono nel dataset attuale
        int positivi = 0;
        int negativi = 0;
        for (RecordAddestramento rec : dataset) {
            if (rec.getTarget()) positivi++;
            else negativi++;
        }
        
        // Se i dati sono completamente "puri" (entropia 0), creiamo subito una foglia
        if (negativi == 0) return new LeafNode(true);
        if (positivi == 0) return new LeafNode(false);
        
        // Se non abbiamo più attributi da testare, creiamo una foglia con la maggioranza
        if (attributiDisponibili.isEmpty()) {
            return new LeafNode(positivi >= negativi);
        }

        // --- 2. RICERCA DEL MIGLIOR ATTRIBUTO ---
        
        double entropiaAttuale = TreeMath.calcolaEntropia(positivi, negativi);
        String migliorAttributo = null;
        double maxGain = -1.0;
        
        // Variabili per salvare come il miglior attributo divide i dati
        List<RecordAddestramento> miglioriDatiVero = new ArrayList<>();
        List<RecordAddestramento> miglioriDatiFalso = new ArrayList<>();

        for (String attributo : attributiDisponibili) {
            List<RecordAddestramento> datiVero = new ArrayList<>();
            List<RecordAddestramento> datiFalso = new ArrayList<>();
            int posV = 0, negV = 0, posF = 0, negF = 0;

            // Simuliamo la divisione dei dati con questo attributo
            for (RecordAddestramento rec : dataset) {
                if (rec.getInput().getFeatureValue(attributo)) {
                    datiVero.add(rec);
                    if (rec.getTarget()) posV++; else negV++;
                } else {
                    datiFalso.add(rec);
                    if (rec.getTarget()) posF++; else negF++;
                }
            }

            double gain = TreeMath.calcolaGain(entropiaAttuale, posV, negV, posF, negF);
            
            // Se questo attributo è il migliore finora, ce lo salviamo
            if (gain > maxGain) {
                maxGain = gain;
                migliorAttributo = attributo;
                miglioriDatiVero = datiVero;
                miglioriDatiFalso = datiFalso;
            }
        }

        // --- 3. COSTRUZIONE RICORSIVA DEI NODI FIGLI ---
        
        // Prepariamo la nuova lista di attributi togliendo quello che stiamo per usare
        List<String> nuoviAttributi = new ArrayList<>(attributiDisponibili);
        nuoviAttributi.remove(migliorAttributo);

        // Chiamata ricorsiva: addestriamo il ramo vero e il ramo falso
        TreeNode ramoVero = addestra(miglioriDatiVero, nuoviAttributi);
        TreeNode ramoFalso = addestra(miglioriDatiFalso, nuoviAttributi);

        // Restituiamo il nodo interno assemblato
        return new InternalNode(migliorAttributo, ramoVero, ramoFalso);
    }
}