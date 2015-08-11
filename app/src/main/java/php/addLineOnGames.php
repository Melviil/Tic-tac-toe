<?php


$con=mysqli_connect("localhost","tictactoePlayer","android123456789","TicTacToe");

if (mysqli_connect_errno($con))
{
   echo "Failed to connect to MySQL: " . mysqli_connect_error();
}


$IP = $_SERVER['REMOTE_ADDR'];
$name = $_GET['name'];


mysqli_query($con,"INSERT INTO Players (IP,name) VALUES(\"$IP\",\"$name\")");


mysql_close($con);

?>
