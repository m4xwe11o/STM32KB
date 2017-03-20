**Use Case - Author registrieren**

##Name:
  - Athor registrieren

##Kurzbeschreibung:
  - Ein Anwender kann sich für die STM32 KB (Knowledge Base) registrieren

##Vorbedingung:
  -

##Nachbedingung:
  - Benutzer muss von Administrator freigeschalten werden um die App als Author verwenden zu können
  
##Fehlersituation:
  - Benutzer beantwortet die Fragen nach zu Abschluss der Registrierung falsch

##Systemzustand im Fehlerfall:
  - Benutzer kann nicht freigeschalten werden. Administrator wird informiert.

##Akteure:
  - Author, primär, aktiv, menschlich
  - Admin, primär, passiv
  - System, primär, passiv

##Trigger:
  - App Interface

##Standardablauf:
  1.  Der Benutzer öffnet die App und wällt Registrierung (Sign up) aus.
  2.  Der Benutzer befüllt die Eingabe Felder mit seinen Daten
  3.  Dem Benutzer muss den Hinweis akzeptieren um fortfahren zu können.
    1.  Der Hinweis beinhaltet Informationen darüber das, erst nach einer Fraischaltung
        durch einen Administrator der Author berechtigt ist Artikel zu verfassen
  4.  Der Benutzer muss 4 Fragen beantworten um die Registrierung vorerst abschließen zu können
    1.  Dem Benutzer wird nicht  nicht das korrekte Ergebnis der Fragen angezeigt
  5.  Der Benutzer wartet auf eine Freischaltung eines Administrators

##Alternativabläufe:
  - Der Benutzer bricht die Registrierung ab und verwendet die App als Leser (Lesender Benutzer)
