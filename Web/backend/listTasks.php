<?php
include('settings.php');

if(isset($_POST['sessionID']))
{

  $sessionID = $_POST['sessionID'];
  $db = new PDO('mysql:host='.$dbHost.';dbname='.$dbName, $dbUserName, $dbPassword);

  $getUsername = $db->prepare("SELECT name FROM users WHERE session=:sessionID");
  $getUsername->bindParam(':sessionID',$sessionID);
  if($getUsername->execute())
  {
    while($row = $getUsername->fetch())
    {
        $username = $row[0];
        

        $db = null;
        die();
    }
    echo '{"result":"error","reason":"invalid sessionID"}';
    $db = null;
    die();

}


echo '{"result":"there is no sessionID"}';
die();

?>
