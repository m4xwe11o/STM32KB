**Use Case - Beschreibung**

##Name:
  - Authoren Login

##Kurzbeschreibung:
  - Beschreibung wie sich ein Author einzuloggen kann

##Vorbedingung:
  - Author hat den Use Case **Author-registrieren erfolgreich abgeschlossen**

##Nachbedingung:
  - Author sieht den Authoren View `Artikel die der Author verfasst hat`

##Fehlersituation:
  - Author gibt mehr als 3 mal das Falsche Passwort ein

##Systemzustand im Fehlerfall:
  - Author ist gesperrt und muss auf eine Freischaltung von einem Administrator warten

##Akteure:
  - Author, aktiv, primär, menschlich
  - Administrator, passiv, primär, menschlich

##Trigger:
  - APP `Login View`

##Standardablauf:
  1.  Der Benutzer öffnet die App und wählt Login `Sign in`
  2.  Der Benutzer gibt seine Username / Passwort Kombination ein
  3.  Das System checkt die Eingaben `Check gegen Datenbank`
  4.  Der Benutzer wird nach erfolgreicher Validierung auf die Authoren Seite weitergeleitet

##Alternativabläufe:
  -
