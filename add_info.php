<?php

require "init.php";

$name = $_POST["name"];
$email = $_POST["email"];
$mobile = $_POST["mobile"];

//query

$sql = "insert into user_info values('$name','$email', '$mobile');";
$var = mysqli_query($con, $sql);

if($var)
{
	echo"<br><h3>Single Row Insertion Sucess</h3>";

}

else 
{
	echo"Error in insertion! ".mysqli_error($con);

}





?>