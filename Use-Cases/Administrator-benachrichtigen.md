**Use Case - Administrator beachrichtigen**

##Name:
  - Administrator beachrichtigen

##Kurzbeschreibung:
  - Benachrichtigung an den Administrator das eine Freischaltung für einen Author aussteht

##Vorbedingung:
  - Benutzer muss den Use Case **Author-registrieren** abgeschlossen haben

##Nachbedingung:
  - Benutzer hat nun die Möglichkeit die App als Author zu verwenden

##Fehlersituation:
  - Administrator führt Freischaltung nicht durch

##Systemzustand im Fehlerfall:
  - Benachrichtigung an den Benutzer

##Akteure:
  - Administrator, primär, aktiv, menschlich
  - Benutzer, primär, passiv, menschlich

##Trigger:
  - Benutzer durch Use Case **Author-registrieren**

##Standardablauf:
  1.  Der Administrator erhält eine Email in der Freizuschaltenden Benutzer hinterlegt ist
  2.  Der Administrator öffnet die App im Adminitratorview
  3.  Der Administrator wählt den Freizuschaltenden Benutzer aus
  4.  Der Administrator vergibt dem Benutzer die Authoren Berechtigung

##Alternativabläufe:
  - 
