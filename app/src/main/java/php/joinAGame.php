<?php

$con=mysqli_connect("localhost","tictactoePlayer","android123456789","TicTacToe");

if (mysqli_connect_errno($con))
{
   echo "Failed to connect to MySQL: " . mysqli_connect_error();
}

$idPlayer1 = $_POST['idPlayer1'];
$idPlayer2 = $_POST['idPlayer2'];
$sql = "UPDATE Game SET idPlayer2=$idPlayer2 WHERE idPlayer1 = $idPlayer2";
mysqli_query($con,$sql);

mysqli_close($con);


?>
