# Portfolio Builder
- [Gianluca Romeo](https://github.com/gianlucaromeo)
- [Cristian Domenico Dramisino](https://github.com/cristiandrami)
- [Luigi Montalbano](https://github.com/Luigim2000)

> Portfolio Builder permette la creazione di un Portfolio personale attraverso l'uso di una Dashboard privata.


Dopo aver importato il dump del Database, modificare `URL`, `USER`, `PASSWORD` dalla classe `DBSettings` nel package `it.unical.demacs.informatica.digitales.app.database`:

```
public class DBSettings {

	public final static String URL = "jdbc:postgresql://localhost:5432/web-project";
	public final static String USER = "postgres";
	public final static String PASSWORD = "postgres";
	
}
```

Per avviare l'applicazione è sufficiente eseguire il main della classe `DigitalESApplication` nel package `it.unical.demacs.informatica.digitales.app.`.
> Se l'applicazione non viene eseguita, provare ad eseguire il main come Spring Boot App.

Se il progetto è stato avviato correttamente, è possibile accedere alla Home Page del Sistema all'indirizzo `localhost:8080/home`, oppure tramite credenziali di utenti test all'indirizzo `localhost:8080/dashboard/login`:

Utenti (per testare il progetto):
```
username: gianluca; password: gianluca;
username: cristian; password: cristian;
username: luigi; password: luigi;
username: user1; password: user1;
username: user2; password: user2;
```

Moderatori (per testare il progetto):
```
username: moderator1; password: moderator1;
username: moderator2; password: moderator2;
username: moderator3; password: moderator3;
username: moderator4; password: moderator4;
```

#Come è strutturato il progetto

`it.unical.demacs.informatica.digitales.app`:
- Contiene una classe per l'avvio dell'applicazione e una per la creazione di dati per testare le funzionalità dei moderatori.

`it.unical.demacs.informatica.digitales.app.beans`
- Contiene tutti i Beans del progetto: `User`, `Project`, `Post`, `BannedUser`, `RemovedProject`, ...

`it.unical.demacs.informatica.digitales.app.beans.validation`
- Contiene Beans utili per mandare risposte al Client circa la validità dei valori inseriti dall'Utente. Questi Bean offrono la possibilità di sapere quanti e quali campi non rispettano il formato desiderato.

`it.unical.demacs.informatica.digitales.app.dao`
- Contiene classi utili per la manipolazione dei dati e la connessione con il database.

`it.unical.demacs.informatica.digitales.app.dashboard`
- Contiene tutti i Controller per la gestione grafica dell'interfaccia della Dashboard privata dell'utente.

`it.unical.demacs.informatica.digitales.app.dashboard.rest`
- Contiene tutti i Rest Controller per la gestione delle funzionalità del Sistema come il Login, la Registrazione, il Recupero Password, .. .

`it.unical.demacs.informatica.digitales.app.dashboard.rest.moderator`
- Contiene tutti i Rest Controller per la gestione della comunicazione Client-Server circa le azioni del Moderatore sulla propria Dashboard.

`it.unical.demacs.informatica.digitales.app.dashboard.rest.user`
- Contiene tutti i Rest Controller per la gestione della comunicazione Client-Server circa le azioni dell'Utente sulla propria Dashboard.

`it.unical.demacs.informatica.digitales.app.database`
- Contiene classi di Utilità per gestire la connessione al Database.

`it.unical.demacs.informatica.digitales.app.database.protocol`
- Contiente gli esiti delle risposte delle richieste durante la comunicazione Client-Server.

`it.unical.demacs.informatica.digitales.app.portfolio`
- Contiene un Controller che si occupa di gestire l'interfaccia grafica del Portoflio generato per l'Utente. All'interno del package sono presenti classi di utilità che si occupano di selezionare quali dati debbano essere visibili, in base alla pagina su cui si trova un utente che visita il Portfolio.

`it.unical.demacs.informatica.digitales.app.settings`
- Contiene collezioni di costanti all'interno di classi di utilità per una migliore gestione del codice.

`it.unical.demacs.informatica.digitales.app.validator`
- Contiene le classi che si occupano di convalidare i dati inseriti dall'Utente tramite regex e altri controlli. 














