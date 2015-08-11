<?php

$con=mysqli_connect("localhost","tictactoePlayer","android123456789","TicTacToe");

if (mysqli_connect_errno($con))
{
   echo "Failed to connect to MySQL: " . mysqli_connect_error();
}

 $file = $_FILES["game"];
 $id = $_POST["id"];
    $sql = "INSERT INTO Game (TabofPlayed,idPlayer) VALUES ($file,$id)";
    mysql_query($con,$sql);

mysql_close();
?>
