<?php 



$con=mysqli_connect("localhost","tictactoePlayer","android123456789","TicTacToe");

if (mysqli_connect_errno($con))
{
   echo "Failed to connect to MySQL: " . mysqli_connect_error();
}
$id =  $_GET['id'];
mysqli_query($con,"DELETE FROM Players WHERE idPlayers = $id");



mysql_close($con);



?>
