# Sistema di Gestione Referti Medici 🏥
**Modellazione dati - Progetto 3a - Master di II Livello FULL STACK WEB DEVELOPMENT**

Questo repository contiene lo sviluppo del sistema gestionale dedicato alla digitalizzazione, archiviazione e consultazione di referti medici e documenti allegati. Il progetto copre l'intero ciclo di vita del dato: dall'analisi dei requisiti alla realizzazione dell'interfaccia software.

---

## 👥 Team di Progetto
* **Federica Aprile**
* **Cosimo Bifano**
* **Erika Cavigliano**
* **Maria Vittoria Cavigliano**
* **Giacomo	Gigliotti**
* **Tommaso	Muraca**
* **Francesco Primerano**

---

## 📂 Struttura del Progetto
La repository è organizzata secondo le fasi di sviluppo previste dal corso:

* **Progetto Modellazione Dati.pdf**: Documentazione tecnica sulla tipologia di dati e specifiche del dominio medico trattato.
* **/DB/ER_schema.pdf**: Diagrammi ed elaborati grafici **Entity-Relationship (E/R)** per la modellazione logica del database.
* **/DB/schema_and_data.sql**: Script SQL (`.sql`) per la creazione dello schema, delle tabelle e dei vincoli d'integrità su MySQL.
* **/GestionaleReferti/src/**: Codice sorgente dell'applicativo gestionale sviluppato in **Java**.

---

## 🛠️ Caratteristiche del Sistema

### 1. Modellazione Dati
Il database è stato progettato su **MySQL** per garantire coerenza e sicurezza dei dati. Il modello E/R definisce le relazioni tra pazienti, medici e prestazioni sanitarie, ottimizzando le performance delle query di ricerca.

### 2. Software Gestionale (Java)
L'applicazione permette un'interazione fluida con il database attraverso le seguenti funzionalità principali:
* **Visualizzazione Referti:** Interfaccia dedicata alla lettura dei dati clinici.
* **Filtri Avanzati:** Possibilità di filtrare i risultati per **Paziente** o per **Medico**.
* **Gestione Documentale:** Inserimento di nuovi referti e supporto per il caricamento di **documenti allegati**.

---

## 🚀 Guida Rapida

### Prerequisiti
* Java Development Kit (JDK) 8 o superiore.
* MySQL Server attivo.

### Configurazione
1.  **Database:** Importare ed eseguire gli script contenuti nella cartella `/DB/schema_and_data.sql` sul proprio DBMS MySQL.
2.  **Connessione:** Verificare i parametri di connessione JDBC (Host, Porta, User, Password) all'interno del codice Java nel file `/GestionaleReferti/src/App.java`
3.  **Build:** Compilare ed eseguire il progetto tramite il proprio IDE di riferimento (es. IntelliJ, Eclipse o VS Code) presente nella cartella `/GestionaleReferti/`.

---

## 📝 Finalità Accademica
Il progetto costituisce il materiale d'esame **Modellazione dati** per il **Master di II Livello FULL STACK WEB DEVELOPMENT**, focalizzandosi sulle best practice di progettazione database e sviluppo software orientato agli oggetti.
