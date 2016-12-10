<?php
  session_start();
  if(isset($_SESSION['loggedIn']))
  {
    //echo $_SESSION['loggedIn'];
    //echo 'logged in';
    header('Location: ./sites/home.php');
  }

 ?>

 <!doctype html5>
<html>
  <head>
    <!--CSS Sheets -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <link rel="stylesheet" href="./css/global.css">
    <link rel="stylesheet" type="text/css" href="./css/sweetalert.css">
    <link rel="stylesheet" href="./css/swal-forms.css">
    <link rel="icon" href="./assets/logo/logo-transparent.png">
     <link href="https://fonts.googleapis.com/css?family=Roboto" rel="stylesheet"> 
    <title>NoteManager</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=yes"/>
  </head>
  <body>
    <div id="mainButtons">
      <button class="cButton btn btn-default" onclick="onRegisterClick()">Register</button>
      <button class="cButton btn btn-default" onclick="onLoginClick()">Login</button>
    </div>

    <div id="downloadProgramm">
      <img id="logo" src="./assets/logo/logo-transparent.png" alt="notemanager logo" width="64" height="64">
      <a href="download/desktopb2.0.jar">Download TaskManager for Desktop b2.0</a>
    </div>

    <!-- Scripts -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
    <script src="./js/global.js"></script>
    <script src="./js/sweetalert.min.js"></script>
    <script src="./js/swal-forms.js"></script>

    <!-- if redirected from ajax -> show success or error message -->
    <?php
      $action = $_GET['action'];
      if(isset($action))
      {
        if(strcmp($action,'registered') === 0)
          echo '<script>swal("Hello,","You are now registered!","success");</script>';
        else if(strcmp($action,'acc_exists') === 0)
          echo '<script>swal("ERROR","the name is already in use!","error");</script>';
        else if(strcmp($action,'wrong_pw') === 0)
          echo '<script>swal("ERROR","Wrong password","error");</script>';
        else if(strcmp($action,'user_not_exists') === 0)
          echo '<script>swal("ERROR","User doesn\'t exist!","error");</script>';
      }
     ?>
  </body>
</html>
