<?php
  $sessionPost = $_POST['sessionID'];
  $topic = $_POST['topic'];
  $postText = $_POST['postText'];
  $category = $_POST['category'];
  require("./settings.php");

  session_start();
  if(!(isset($topic) && isset($postText) && isset($category)))die("bad arguments!"); // some fields are missing
  $db = new PDO('mysql:host='.$dbHost.';dbname='.$dbName, $dbUserName, $dbPassword);
  //webbrowser
  if(isset($_SESSION['username']) && isset($_SESSION['sessionID']))
  {
          $insertPost = $db->prepare("INSERT INTO comments(name,uniqid,topic,category,textPost) VALUES(:username,:uniqid,:topic,:category,:textPost)");
          $insertPost->bindParam(':username',$_SESSION['username']);
          $id = md5($topic).uniqid();
          $insertPost->bindParam(':uniqid', $id);
          $insertPost->bindParam(':topic', $topic);
          $insertPost->bindParam(':category',$category);
          $insertPost->bindParam(':textPost',$postText);
          $insertPost->execute();

          $db = null;
          header("Location: ../sites/home.php");
          die();

  }else if(isset($sessionPost)) //used as api
  {
    $getSession = $db->prepare("SELECT name FROM users WHERE session=:sessionID");
    $getSession->bindParam(':sessionID',$sessionPost);
    if($getSession->execute())
    {
      while($row = $getSession->fetch())
      {
          $insertPost = $db->prepare("INSERT INTO comments(name,uniqid,topic,category,textPost) VALUES(:username,:uni:topic,:category,:textPost)");
          $insertPost->bindParam(':username',$row[0]);
          $insertPost->bindParam(':uniqid',md5($topic).uniqid());
          $insertPost->bindParam(':topic', $topic);
          $insertPost->bindParam(':category',$category);
          $insertPost->bindParam(':textPost',$postText);
          $insertPost->execute();

          echo '{"result" : "success"}';
          $db = null;
          die();
      }
    }
    echo '{"result" : "error"}';
  }

 ?>
