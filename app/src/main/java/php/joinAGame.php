<?php

$con=mysqli_connect("localhost","tictactoePlayer","android123456789","TicTacToe");

if (mysqli_connect_errno($con))
{
   echo "Failed to connect to MySQL: " . mysqli_connect_error();
}

$idPlayer1 = $_POST['idPlayer1'];
$namePlayer2 = $_POST['name'];
//$idPlayer1 = $_GET['idPlayer1'];
//$namePlayer2 = $_GET['name'];
echo "idPlayer1 = $idPlayer1";
echo "name Player2 = $namePlayer2";

$result = mysqli_query($con,"SELECT idPlayers FROM Players WHERE name=\"$namePlayer2\"");
while($row = mysqli_fetch_array($result)){
    $data[]=$row[0];
}

$idPlayer2 = $data[0];
echo "idPlayer2 = $idPlayer2";
$sql = "UPDATE Game SET idPlayer2=$idPlayer2 WHERE idPlayer1=$idPlayer1";
mysqli_query($con,$sql);

mysqli_close($con);


?>
