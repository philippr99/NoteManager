<?php
  include('../extensions/secureLib.php');
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
    <link rel="stylesheet" href="../css/addPostSite.css">
    <link rel="icon" href="../assets/logo/logo-transparent.png">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=yes"/>
    <title>NoteManager</title>
  </head>
  <body>
    <div id="header">
      <p class="tags" id="first"><?php secureEcho('@'.$_SESSION['username']);?></p>
      <a class="tags" id="last" href="../backend/logout.php">Logout</a>
      <a class="tags" id="last" href="../sites/home.php">Back</a>
    </div>

    <div id="formCenter">
      <form method="post" id="postForm" action="../backend/addPost.php">
        <div class="inputField">
          <p>Topic</p>
          <input autocomplete="off" autocorrect="off" autocapitalize="off" spellcheck="false" type="text" name="topic">
        </div>
        <div class="inputField">
          <p>Category</p>
          <input autocomplete="off" autocorrect="off" autocapitalize="off" spellcheck="false" type="text" name="category">
        </div>
          <p>Note</p>
          <textarea autocomplete="off" autocorrect="off" autocapitalize="off" spellcheck="false" name="postText" form="postForm">Type in your details</textarea>
          <br></br>
          <input type="submit" value="Submit">
      </form>
    </div>

    <!-- Scripts -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
    <script src="../js/home.js"></script>
    <script src="../js/sweetalert.min.js"></script>
    <script src="../js/swal-forms.js"></script>
  </body>
 </html>
