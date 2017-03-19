**Use Case - Administrator beachrichtigen**

##Name:
  - Administrator beachrichtigen
  //ich wuerde diesen Use Case eher als Freischaltung beantragen nennen - mit benachrichtigen assoziere ich eher eine Nachricht schreiben

##Kurzbeschreibung:
  - Benachrichtigung an den Administrator das eine Freischaltung für einen Author aussteht

##Vorbedingung:
  - Benutzer muss den Use Case **Author-registrieren** abgeschlossen haben

##Nachbedingung:
  - Benutzer hat nun die Möglichkeit die App als Author zu verwenden

##Fehlersituation:
  - Administrator führt Freischaltung nicht durch
  //Fehlersituation bezieht sich auf Systemfehler bei der use case beschreibung
  //Administrator fuehrt Freischaltung nicht durch ist somit nur ein Zweig in einem if - falls Anforderungen nicht erfuellt werden, wird nicht freigeschalten
  //Bsp beim Bankomat waere: es gibt keine 10Euro Scheine keine Fehlersituation, da dies im Funktionsablauf vorkommen kann - jedoch waere eine fehlgeschlagene Authentication aufgrund eines Serverausfalls eine Fehlersituation
  //Fehlersituation waere bei unserem Use-Case bspw dass die Benachrichtigung gerade nicht an den Administrators geschickt werden kann aufgrund fehlender Netzwerkverbindung

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
