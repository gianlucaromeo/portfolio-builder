# Portfolio Builder
- [Gianluca Romeo](https://github.com/gianlucaromeo)
- [Cristian Domenico Dramisino](https://github.com/cristiandrami)
- [Luigi Montalbano](https://github.com/Luigim2000)

> Portfolio Builder permette la creazione di un Portfolio personale attraverso l'uso di una Dashboard privata.

Se il progetto è stato avviato correttamente, è possibile accedere al Sistema tramite credenziali di utenti test all'indirizzo `localhost:8080/dashboard/login`:

Utenti:
```
username: gianluca; password: gianluca;
username: cristian; password: cristian;
username: luigi; password: luigi;
username: user1; password: user1;
username: user2; password: user2;
```

Moderatori:
```
username: moderator1; password: moderator1;
username: moderator2; password: moderator2;
username: moderator3; password: moderator3;
username: moderator4; password: moderator4;
```


Dopo aver importato il dump del Database, modificare `URL`, `USER`, `PASSWORD` dalla classe `DBSettings` nel package `it.unical.demacs.informatica.digitales.app.database`:

```
public class DBSettings {

	public final static String URL = "jdbc:postgresql://localhost:5432/web-project";
	public final static String USER = "postgres";
	public final static String PASSWORD = "postgres";
	
}
```
