<?php

$con=mysqli_connect("localhost","tictactoePlayer","android123456789","TicTacToe");

if (mysqli_connect_errno($con))
{
   echo "Failed to connect to MySQL: " . mysqli_connect_error();
}

    $idPlayer1 = $_POST["idPlayer1"];
    $idPlayer2 = $_POST["idPlayer2"];
    $data = $_POST['data'];

    $sql = "UPDATE Game SET TabofPlayed=\"$data\" WHERE idPlayer1 = $idPlayer1 AND idPlayer2 = $idPlayer2";
    mysqli_query($con,$sql);

mysql_close($con);
?>
