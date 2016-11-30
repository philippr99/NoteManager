<?php
  include('settings.php');
  session_start();
  $sessionID = $_POST['sessionID'];
  $noteID = $_POST['noteID'];
  if(!isset($noteID) die("No noteID!");

  $db = new PDO('mysql:host='.$dbHost.';dbname='.$dbName, $dbUserName, $dbPassword);

  if(isset($sessionID))
  {
    //getting username
    $getUsername = $db->prepare("SELECT name FROM users WHERE session=:sessionID");
    $getUsername->bindParam(':sessionID',$sessionID);
    if($getUsername->execute())
    {
      while($row = $getUsername->fetch())
      {
          $username = $row[0];
          $username = $_SESSION['username'];
          $stmt = $db->prepare("DELETE * FROM comments WHERE name=:username AND uniqid=:noteID");
          $stmt->bindParam(':name',$username);
          $stmt->bindParam(':noteID',$noteID);
          $stmt->execute();

          echo '{"result" : "success"}';
          $db = null;
          die();
      }

          echo '{"result" : "error"}';
    }


  }else if(isset($_SESSION['sessionID']))
  {
        $username = $_SESSION['username'];
        $stmt = $db->prepare("DELETE * FROM comments WHERE name=:username AND uniqid=:noteID");
        $stmt->bindParam(':name',$username);
        $stmt->bindParam(':noteID',$noteID);
        $stmt->execute();
  }

  $db = null;
  die();

  ?>
