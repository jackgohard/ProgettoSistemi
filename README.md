# ProgettoSistemi

Questo progetto permette di consultare un archivio di rifugi alpini della Provincia Autonoma di Bolzano tramite una connessione client-server.

## Avvio del server

Compilare ed eseguire il server

Successivamente per interagire con il server tramite Telnet scrivere il seguente comando sul terminale:

telnet 127.0.0.1 1300

Dopo la connessione, sarà possibile inviare comandi testuali direttamente al server.

## Comandi disponibili

Comandi disponibili:
    - AIUTO: mostra questo messaggio
    - MOSTRA_TUTTO: elenca tutti i rifugi
    - MOSTRA_RIGA <n>: mostra il rifugio alla riga n
    - MOSTRA_COMUNE <nome_comune>: elenca i rifugi nel comune specificato
    - MOSTRA_ALTITUDINE <min> <max>: elenca i rifugi con altitudine nel range indicato
    - ESCI: termina la connessione

Autore

Jacopo Rigo 
jacopo.rigo@itiszuccante.edu.it