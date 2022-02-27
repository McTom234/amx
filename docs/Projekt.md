# Airline Manager X
###### Informatikprojekt von Jonas Bellman und Jannis Lübke

## Beschreibung
Der Airline Manager X ist ein management Programm für Airlines. Diese verwaltet
Flüge, Flugzeuge und Routen Mithilfe einer grafischen Oberfläche basierend 
auf Java Swing. Das Programm speichert die eingegebenen Daten im Json Format unter
Nutzung der Bibliothek Gson.

Die Datenbank wird vom Programm verwaltet und kann vom Nutzer ausgewählt werden.
So ist es möglich, dass der Nutzer mehrere Datenbanken anlegt 

Flugzeuge sind in ihre Typen inklusive ihrer technischen Daten unterteilt. Diese
Typen können vom Nutzer zur Laufzeit definiert werden unter Abfrage der benötigten
Daten. Weiter lassen sich Flugzeuge Routen zuweisen und somit Flüge planen.
Unterstützt werden diese Operationen durch die Programmoberfläche, welche eine
interaktive Eingabe bietet.

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
Flugzeuge werden von Herstellern entwickelt und haben in ihrer Zulassung bestimmte
technische Daten. Diese technischen Daten werden sind für alle gebauten Flugzeuge
dieses Typen gültig. Die Klasse `AircraftType` ist mit einer Zulassung vergleichbar.
Der Nutzer kann solche Datentypen definieren und dann Flugzeuge, die mit einer solchen
Zulassung in Besitz hat hinzufügen. Für diese speziellen Flugzeuge sind Werte, wie z. B.
die Flugstunden, die spezielle und eindeutige Registrierung oder der aktuelle Ort, wo sich
das Flugzeug befindet. Ein Flugzeug kann als Version für Passagiere oder für Fracht
ausgebaut werden. Ein Flugzeug mit Passagierkonfiguration kann Passagiere transportieren
und enthält eine maximale Passagieranzahl und die Anzahl, die an Kabinenpersonal
gebraucht wird. Flugzeuge mit Frachtkonfiguration haben dahingegen Eigenschaften, auf
welcher Seite sie beladen werden und wie viele Frachtcontainer beladen werden können.