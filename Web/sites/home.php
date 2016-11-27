<?php
  session_start();
  if(!isset($_SESSION['loggedIn']))
  {
    header('Location: ../index.php');
  }

 ?>

 <!doctype html5>
 <html>
  <head>
    <!--CSS Sheets -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <link rel="stylesheet" href="../css/home.css">
    <link rel="stylesheet" type="text/css" href="../css/sweetalert.css">
    <link rel="stylesheet" href="../css/swal-forms.css">
    <title>Manage your Tasks</title>
  </head>
  <body>

    <div id="header">
      <p><?php echo 'Welcome @'.$_SESSION['username'];?></p>
      <a href="../sites/addPostSite.php">Add a note!</a>
      <a href="../backend/logout.php">Logout</a>
    </div>
    <div id="center">
    </div>

    <!-- Scripts -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
    <script src="../js/home.js"></script>
    <script src="../js/sweetalert.min.js"></script>
    <script src="../js/swal-forms.js"></script>
  </body>
 </html>
