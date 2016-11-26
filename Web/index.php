 <!doctype html5>
<html>
  <head>
    <!--CSS Sheets -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <link rel="stylesheet" href="./css/global.css">
    <link rel="stylesheet" type="text/css" href="./css/sweetalert.css">
    <link rel="stylesheet" href="./css/swal-forms.css">
    <title>Todolist - TaskManager</title>
  </head>
  <body>
    <div id="mainButtons">
      <button class="cButton btn btn-default" onclick="onRegisterClick()">Register</button>
      <button class="cButton btn btn-default" onclick="onLoginClick()">Login</button>
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
          echo '<script>swal("Hallo,","You are now registered!","success");</script>';
        else if(strcmp($action,'acc_exists') == 0)
          echo '<script>swal("ERROR","the name is already in use!","error");</script>';
      }
     ?>
  </body>
</html>
