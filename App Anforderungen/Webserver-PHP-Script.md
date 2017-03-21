**Script auf Webserver mit DB**

<?php
        //Datenbankverbindung aufbauen
        $connection = mysqli_connect("`IP OF HOST`","`DB USER`","`very secret passwort`","`<Enter DATABASE>`");
        if (mysqli_connect_errno()) {
                echo mysql_errno($connection) . ": " . mysql_error($connection). "\n";
                die();
        }
        echo "Connected sucesfully to DB! <br>";

        getData($connection);

        //Liefert alle Namen zurück.
        function getData($connection){
                $sqlStmt = "SELECT * FROM <TABLE>;";
                $result =  mysqli_query($connection,$sqlStmt);
                $data = array();
                if ($result = $connection->query($sqlStmt)) {
                        while ($row = $result->fetch_assoc()) {
                                $id = $row["`<SPECIFY COLUMN>`"];
                                $de_name = $row["`<SPECIFY COLUMN>`"];
                                array_push($data,array("ID"=> $id,"de_name"=>$de_name));
                        }
                // Das Objekt wieder freigeben.
                $result->free();
                }

                closeConnection($connection);

                // Das Array durchlaufen und nur die deutschen Namen ausgeben.
                foreach ($data as $d){
                        echo $d["ID"];
                        //each $d["de_name"];
                        echo "<br>";
                }
        }

        //Verbindung schlieszen.
        function closeConnection($connection){
                mysqli_close($connection);
        }
?>
