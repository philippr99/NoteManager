<?php
  function secureString($string)
  {
    return htmlspecialchars($string,ENT_QUOTES,'UTF-8');
  }

  function secureEcho($string)
  {
    echo secureString($string);
  }
?>
