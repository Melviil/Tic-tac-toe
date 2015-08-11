<?php

$con=mysqli_connect("localhost","tictactoePlayer","android123456789","TicTacToe");

if (mysqli_connect_errno($con))
{
   echo "Failed to connect to MySQL: " . mysqli_connect_error();
}
    $id1 = $_POST['id1'];
    $id2 = $_POST['id2'];
    $sql = "SELECT TabofPlayed FROM Game WHERE $id1 = idPlayer1 AND $id2 = idPlayer2 ";
    $result = mysql_query($con,$sql);

    print($result);

mysql_close();
?>
