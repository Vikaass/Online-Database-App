<?php

$host = "localhost";
$user = "id282865_root";
$password = "root@321";
$db = "id282865_mysql";

$sql = "select * from user_info;";

$con = mysqli_connect($host, $user, $password , $db);

$result = mysqli_query($con , $sql);

$response = array ();

while($row = mysqli_fetch_array($result))
{
	//create the name value pair 
	array_push ($response, array("name" => $row[0], "email" => $row[1], "mobile" => $row[2]));
}


echo json_encode(array("server_response"=> $response));
mysqli_close($con);

?>