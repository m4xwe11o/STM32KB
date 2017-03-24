**MYSQL auf Raspberry**

#Raspberry updaten und MYSQL DB installieren
  - sudo apt-get update
  - sudo apt-get install mysql-server mysql-client php5-mysql

#MYSQL Datenbank einrichten
  pi@Webserver-clock:~$ mysql -u root -p
  Enter password:
  mysql: Unknown OS character set 'ISO-8859-15'.
  mysql: Switching to the default character set 'latin1'.
  Welcome to the MySQL monitor.  Commands end with ; or \g.
  Your MySQL connection id is 37
  Server version: 5.5.52-0+deb8u1 (Raspbian)

  Copyright (c) 2000, 2016, Oracle and/or its affiliates. All rights reserved.

  Oracle is a registered trademark of Oracle Corporation and/or its
  affiliates. Other names may be trademarks of their respective
  owners.

  Type 'help;' or '\h' for help. Type '\c' to clear the current input statement.

  `Anlegen eine Datenbank`
  mysql> CREATE DATABASE Kontaktdaten;
  Query OK, 1 row affected (0.00 sec)

  `Anzeigen der Datenbanken`
  mysql> SHOW DATABASES;
  +--------------------+
  | Database           |
  +--------------------+
  | information_schema |
  | Kontaktdaten       |
  | mysql              |
  | nft                |
  | performance_schema |
  +--------------------+
  5 rows in set (0.02 sec)

`Verwenden einer Datenbanl`
mysql>  USE Kontaktdaten
Database changed

`Anlegen einer Tabelle`
mysql> CREATE TABLE Persoenlich(Nummer INT NOT NULL AUTO_INCREMENT,Vorname VARCHAR(25),Nachname VARCHAR(25),Stadt VARCHAR(25),Geburtstag DATE,PRIMARY KEY (Nummer));
Query OK, 0 rows affected (0.08 sec)

`Schreiben in die Tabelle`
mysql> INSERT INTO Persoenlich(Vorname, Nachname, Stadt, Geburtstag) VALUES ("Hans","Peter","Muenster","1978-12-01");
Query OK, 1 row affected (0.06 sec)

`Werte in Tabelle aus bestimmter Spalte anzeigen`
mysql> SELECT Vorname FROM Persoenlich;
+---------+
| Vorname |
+---------+
| Hans    |
+---------+
1 row in set (0.01 sec)

`Alles anzeigen`
mysql> SELECT * FROM Persoenlich;
+--------+---------+----------+----------+------------+
| Nummer | Vorname | Nachname | Stadt    | Geburtstag |
+--------+---------+----------+----------+------------+
|      1 | Hans    | Peter    | Muenster | 1978-12-01 |
+--------+---------+----------+----------+------------+
1 row in set (0.00 sec)

mysql> exit
Bye
