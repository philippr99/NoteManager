<?php
  include('settings.php');

  $isLogin = $_POST['isLogin'];
  $username = $_POST['username'];
  $password = $_POST['password'];

  if(isset($isLogin) && isset($username) && isset($password))
  {
    try{
      $db = new PDO('mysql:host='.$dbHost.';dbname='.$dbName, $dbUserName, $dbPassword);
      $db->exec("CREATE TABLE IF NOT EXISTS users(name varchar(100), password varchar(100), session varchar(100))"); //delete later!
      $db->exec("CREATE TABLE IF NOT EXISTS comments(name varchar(100),uniqid varchar(100),topic varchar(100), category varchar(100),textPost text)");
      if($isLogin == 1){
          $stmt = $db->prepare("SELECT * FROM users WHERE name=:username");
          $stmt->bindParam(':username',$username);
          if($stmt->execute())
          {
            while($row = $stmt->fetch())
            {
                //found the user
                if(strcmp(md5($password), $row[1]) === 0)
                {
                    //is the real user
                    session_start();
                    $_SESSION['loggedIn'] = true;
                    $_SESSION['username'] = $username;

                    $sessionID = md5($username).uniqid();
                    $stmtSession = $db->prepare("UPDATE users SET session=:sessionID WHERE name=:username");
                    $stmtSession->bindParam(':sessionID',$sessionID);
                    $stmtSession->bindParam(':username',$username);
                    $stmtSession->execute();

                    $_SESSION['sessionID'] = $sessionID;

                    echo '{"error": "none", "action": "none" ,"token" : "'.$sessionID.'"}';
                }else
                {
                    echo '{"error": "wrong_pw", "action": "none" ,"token" : "none"}';
                }
                $db = null;
                die();
            }
            echo '{"error": "user_not_exists", "action": "none" ,"token" : "none"}';
          }
      }else {
        $stmt = $db->prepare("SELECT * FROM users WHERE name=:username");
        $stmt->bindParam(':username',$username);
        if($stmt->execute())
        {
          while($row = $stmt->fetch())
          {
            echo '{"error": "name_exists", "action": "none"}'; // answer to ajax request
            $db = null;
            die();
          }
        }

        $stmt2 = $db->prepare("INSERT INTO users(name,password) VALUES(?, ?)");
        $stmt2->execute(array($username,md5($password)));

        echo '{"error": "none", "action": "registered"}'; // answer to ajax request
      }
      //disconnecting
      $db = null;
    }catch(PDOException $e)
    {
      print "ERROR: ".$e->getMessage();
      die();
    }
  }
 ?>
