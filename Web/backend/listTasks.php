<?php
include('settings.php');

if (isset($_POST['sessionID'])) {

    $sessionID = $_POST['sessionID'];
    $db = new PDO('mysql:host=' . $dbHost . ';dbname=' . $dbName, $dbUserName, $dbPassword);

    $getUsername = $db->prepare("SELECT name FROM users WHERE session=:sessionID");
    $getUsername->bindParam(':sessionID', $sessionID);
    if ($getUsername->execute()) {
        while ($row = $getUsername->fetch()) {
            $jsonResult = '';
            $jsonResult=   $jsonResult.'[';
            $username    = $row[0];
            $getNoteData = $db->prepare("SELECT * FROM comments WHERE name=:username");
            $getNoteData->bindParam(':username', $username);
            if ($getNoteData->execute()) {
                while ($row = $getNoteData->fetch()) {
                    //name varchar(100),uniqid varchar(100),topic varchar(100), category varchar(100),textPost text)");
                      $jsonResult=   $jsonResult.'{"sessionID":"' . $row[1] . '","topic":"' . $row[2] . '", "category":"' . $row[3] . '", "post":"' . $row[4] . '"},';
                }
            }
              $jsonResult= substr_replace($jsonResult,"",strlen($jsonResult)-1).']';

              echo $jsonResult;
            $db = null;
            die();
        }
        echo '{"result":"error","reason":"invalid sessionID"}';
        $db = null;
        die();
    }
  }
    echo '{"result":"there is no sessionID"}';
    die();
?>
