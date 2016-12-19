<?php

$host = "localhost";
$user = "id282865_root";
$password = "root@321";
$db = "id282865_mysql";

$con = mysqli_connect($host, $user , $password , $db);

if(!$con)
{
	die("Error in Connection! ".mysql_connect_error());
}

else 
{
	echo "<br><h3>Connection Success.</h3>";
	
}


?>