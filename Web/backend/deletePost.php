<?php
  require('./settings.php');
  ini_set('display_errors', 'On'); //display error
  session_start();
  $noteID = $_POST['noteID'];
  if(!isset($noteID)) die('{"result" : "error"}');
  $db = new PDO('mysql:host='.$dbHost.';dbname='.$dbName, $dbUserName, $dbPassword);
  if(isset($_POST['sessionID']))
  {
    $sessionID = $_POST['sessionID'];
    //getting username
    $getUsername = $db->prepare("SELECT name FROM users WHERE session=:sessionID");
    $getUsername->bindParam(':sessionID',$sessionID);
    if($getUsername->execute())
    {
      while($row = $getUsername->fetch())
      {
          $username = $row[0];
          $stmt = $db->prepare("DELETE FROM comments WHERE name=:username AND uniqid=:noteID");
          $stmt->bindParam(':username',$username);
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
        $stmt = $db->prepare("DELETE FROM comments WHERE name=:username AND uniqid=:noteID");
        $stmt->bindParam(':username',$username);
        $stmt->bindParam(':noteID',$noteID);
        $stmt->execute();
        echo '{"result" : "success"}';
  }
  $db = null;
  die();
  ?>
