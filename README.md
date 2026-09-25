# Decision Tree Classifier in Java

Un'implementazione da zero di un albero decisionale, sviluppato per un esercizio pratico dell'università, per la classificazione binaria, scritto in Java puro. Il progetto ha uno scopo didattico e illustra i meccanismi interni della costruzione di un modello predittivo basato sulla Teoria dell'Informazione, senza l'utilizzo di librerie di Machine Learning esterne.

## Concetti Teorici

Il motore dell'albero costruisce la sua struttura decisionale basandosi su due concetti matematici:

* **Entropia**: Definisce il grado di impurità o incertezza in un set di dati. Un insieme in cui tutti gli elementi appartengono alla stessa classe ha entropia 0 (certezza assoluta), mentre un set diviso esattamente a metà tra due classi ha entropia 1 (caos massimo).
* **Information Gain (Guadagno Informativo)**: Misura l'effettiva riduzione di entropia che si ottiene suddividendo il dataset in base a un determinato attributo. Durante la fase di addestramento (costruzione top-down), l'algoritmo valuta iterativamente ogni feature disponibile e seleziona come nodo di split l'attributo che massimizza questo guadagno.

## Architettura del Progetto

Il codice è strutturato seguendo i principi della programmazione orientata agli oggetti e sfrutta il polimorfismo per la navigazione dei nodi. I file sono divisi in tre package logici:

* **core**: Contiene il motore matematico e la struttura dell'albero.
  * `TreeNode`: Interfaccia implementata da tutti i nodi.
  * `InternalNode` e `LeafNode`: Rappresentano rispettivamente i bivi decisionali (test sugli attributi) e i risultati finali (la classe predetta).
  * `TreeMath`: Classe utility con metodi puri per i calcoli logaritmici e statistici.
  * `CostruttoreAlbero`: Implementa l'algoritmo ricorsivo per la creazione dell'albero ottimale.

* **data**: Contiene le strutture dati.
  * `Dato`: Mappa chiave-valore delle feature in ingresso per un singolo record.
  * `RecordAddestramento`: Associa un oggetto Dato al suo target reale (la risposta esatta usata in fase di training).

* **test**: Contiene il punto di ingresso.
  * `MainTest`: Script eseguibile che istanzia un dataset fittizio, addestra il modello e verifica una predizione su dati non visti.

## Come Eseguire

Il progetto non ha dipendenze esterne. Può essere eseguito direttamente tramite qualsiasi IDE (VS Code, IntelliJ, Eclipse) impostando la cartella `src` come source root.

Per l'esecuzione da riga di comando (posizionandosi nella root del progetto):
1. Compilare i file: `javac -d bin src/*/*.java`
2. Eseguire il main: `java -cp bin test.MainTest`