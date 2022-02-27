# Airline Manager X
###### Informatikprojekt von Jonas Bellman und Jannis Lübke

## Beschreibung
Der Airline Manager X ist ein Management-Programm für Airlines. Diese verwaltet
Flüge, Flugzeuge und Routen mithilfe einer grafischen Oberfläche basierend 
auf Java Swing. Das Programm speichert die eingegebenen Daten im JSON-Format unter
Nutzung der Bibliothek Gson.

Die Datenbank wird vom Programm verwaltet und kann vom Nutzer ausgewählt werden.
So ist es möglich, dass der Nutzer mehrere Datenbanken anlegt.

Flugzeuge sind in ihre Typen inklusive ihrer technischen Daten unterteilt. Diese
Typen können vom Nutzer zur Laufzeit definiert werden. Dazu werden die benötigten
Daten in einem Pop-up-Fenster erfasst. Weiter lassen sich Flugzeuge Routen zuweisen
und somit Flüge planen. Unterstützt werden diese Operationen durch die
Programmoberfläche, welche eine interaktive Eingabe bietet.

## Vollständigkeit
Datenstrukturen und Funktionen für das Laden und Speichern des Datensatzes sowie die
grafische Oberfläche sind implementiert. Zusätzlich können Flugzeuge und Helikopter
bereits über die Oberfläche verwaltet werden.

Eigenschaften und Daten in den jeweiligen Klassen sind exemplarisch für ihre echten
Vorbilder. Zusätzlich werden Eingabedaten grob auf ihre Sinnhaftigkeit geprüft.

Das Verwalten von Routen, Personen und Flügen ist bis jetzt noch nicht implementiert,
die Grundstruktur ist aber vorhanden.

Als vollständig und funktionsfähig werden alle Klassen in
`de.humboldtgym.amx.models.aircraft` sowie die Klasse 
`de.humboldtgym.amx.models.Airline` eingestuft. Ein 
[exportiertes UML Diagramm](./models.svg) dieser
Klassenstrukturen befindet sich neben dieser Datei als `models.svg`. 
Nicht finalisierte Klassen und Pakete sind eingeklappt und nicht vollständig angegeben.

## Datenstrukturen
### Flugzeuge
Alle fliegende Objekte haben gemeinsame Eigenschaften, wie z.B. eine eindeutige
Registrierung, eine Spannweite der Flügel oder die Fähigkeit zu fliegen (Red Bull...).
Grob kann man fliegende Objekte in Flugzeuge und Hubschrauber unterteilen. Da es aber
Hubschrauber und Flugzeuge unter anderem als Fracht- und Passagiervariante ausgebaut
sind, kann man hier eine weitere Unterteilung vornehmen.