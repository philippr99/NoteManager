<?php
  include('../extensions/secureLib.php');
  session_start();
  if(!isset($_SESSION['loggedIn']))
  {
    header('Location: ../index.php');
  }

   ini_set('display_errors', 'On'); //display error
 ?>

 <!doctype html5>
 <html>
  <head>
    <!--CSS Sheets -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <link rel="stylesheet" href="../css/home.css">
    <link rel="stylesheet" type="text/css" href="../css/sweetalert.css">
    <link rel="stylesheet" href="../css/swal-forms.css">
    <link rel="icon" href="../assets/logo/logo-transparent.png">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=yes"/>
    <title>NoteManager</title>
  </head>
  <body>

    <div id="header">
      <p class="tags" id="first"><?php secureEcho('Welcome @'.$_SESSION['username']);?></p>
      <a class="tags" id="middle" href="../sites/addPostSite.php">Add a note!</a>
      <a class="tags" id="last" href="../backend/logout.php">Logout</a>
    </div>
    <div id="center">
      <?php
          require('../backend/settings.php');
          $db = new PDO('mysql:host='.$dbHost.';dbname='.$dbName, $dbUserName, $dbPassword);
          $getNoteData = $db->prepare("SELECT * FROM comments WHERE name=:username");
          $getNoteData->bindParam(':username',$_SESSION['username']);
          //      $db->exec("CREATE TABLE IF NOT EXISTS comments(name varchar(100),uniqid varchar(100),topic varchar(100), category varchar(100),textPost text)");
          if($getNoteData->execute())
          {
            while($row = $getNoteData->fetch())
            {
              echo '<div class="note"><span onclick="deletePostEvent(this)" class="glyphicon glyphicon-remove removePost"><p class="hash">'.secureString($row[1]).'</p></span><h1>'.secureString($row[2]).'</h1><p>'.secureString($row[4]).'</p></div>';
            }
          }
          $db = null;

       ?>
    </div>

    <!-- Scripts -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
    <script src="../js/home.js"></script>
    <script src="../js/sweetalert.min.js"></script>
    <script src="../js/swal-forms.js"></script>
  </body>
 </html>
