# ProgettoSistemi
Applicazione Client-Server per la Consultazione Remota di un File CSV

Descrizione del Progetto

Il progetto sviluppa un’applicazione client-server in Java, progettata per consentire la consultazione remota di un file CSV contenente informazioni sui rifugi alpini della provincia di Bolzano. Il server si occupa di leggere il file CSV e di organizzarlo in una struttura dati interna, gestendo le richieste provenienti dal client attraverso connessioni socket TCP. Il protocollo di comunicazione è semplice e consente al client di richiedere righe specifiche del file, con il server che risponde inviando i dati corrispondenti o messaggi di errore se le richieste non sono valide.

Struttura del Progetto

Il progetto si articola in più componenti principali, tutti localizzati all’interno di una struttura di cartelle organizzata. La parte server è costituita dal file Server.java, che implementa la logica del server, e dal file CSVReader.java, che si occupa della lettura e gestione dei dati provenienti dal file CSV. Per la parte client, c'è il file Client.java, che gestisce la comunicazione e l’interazione con il server. Il file CSV con i dati sui rifugi alpini è salvato sotto il nome Provincia-Autonoma-di-Bolzano-Elenco-dei-rifugi-alpini.csv e si trova nella cartella dei dati. Il progetto è accompagnato dal documento README.md, che descrive l’implementazione.

Funzionamento del Server

Il server è progettato per gestire le richieste del client in modo efficiente. All’avvio, il server legge il file CSV e lo carica in una struttura dati interna, come una lista di oggetti o una mappa, per permettere un rapido accesso ai dati. Una volta che il file è stato caricato, il server apre una connessione socket in ascolto delle richieste provenienti dal client. Ogni richiesta del client viene gestita in modo indipendente tramite l'uso di thread, consentendo così al server di supportare la comunicazione con più client contemporaneamente. Il server risponde alle richieste inviando la riga del file CSV richiesta dal client, oppure restituisce un messaggio di errore nel caso in cui la richiesta non sia valida o la riga richiesta non esista.

Funzionamento del Client

Il client si connette al server utilizzando una connessione socket TCP. Una volta stabilita la connessione, il client invia richieste al server seguendo un protocollo definito, come ad esempio una richiesta per ottenere una riga specifica del file CSV. Ogni richiesta inviata dal client è formulata in modo che il server possa comprenderla facilmente e restituire una risposta adeguata. Quando il server invia la risposta, che può essere o i dati richiesti o un messaggio di errore, il client li riceve e li visualizza per l'utente. In questo modo, l’interazione con il server è semplice e mirata a permettere all'utente di ottenere informazioni specifiche sui rifugi alpini.

Protocollo di Comunicazione

Il protocollo di comunicazione tra il client e il server è strutturato in modo semplice ma preciso. Le richieste del client sono inviate come comandi di testo, come nel caso di GET_ROW n, dove n rappresenta il numero della riga richiesta dal file CSV. Se la richiesta è valida, il server risponde con i dati relativi alla riga richiesta, come, ad esempio, "Rifugio XYZ, 2500m, Bolzano". Se la richiesta non è valida, ad esempio se il numero della riga è fuori dal range o il formato della richiesta è errato, il server invia un messaggio di errore come "ERROR: Invalid row". Questo protocollo è pensato per essere semplice, consentendo al client di comunicare in modo diretto e chiaro con il server.

Requisiti Tecnici

Il progetto è sviluppato in Java e fa uso di socket TCP per stabilire la comunicazione tra il client e il server. La gestione delle connessioni è implementata con threading, per consentire al server di rispondere simultaneamente a più client senza bloccare il servizio. Inoltre, il server è in grado di gestire situazioni di errore, restituendo messaggi di errore quando le richieste non sono valide. La gestione degli errori è una componente fondamentale per garantire un’esperienza utente fluida e senza intoppi.

Autore

Jacopo Rigo 
jacopo.rigo@itiszuccante.edu.it