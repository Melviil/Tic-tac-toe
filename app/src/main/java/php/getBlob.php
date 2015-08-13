<?php

$con=mysqli_connect("localhost","tictactoePlayer","android123456789","TicTacToe");

if (mysqli_connect_errno($con))
{
   echo "Failed to connect to MySQL: " . mysqli_connect_error();
}
    $id1 = $_POST['idPlayer1'];
    $id2 = $_POST['idPlayer2'];
    $sql = "SELECT TabofPlayed FROM Game WHERE $id1 = idPlayer1 AND $id2 = idPlayer2 ";
    $result = mysqli_query($con,$sql);
    
while($row = mysqli_fetch_array($result)){    
    $data[] = $row[0];
}
    $value = $data[0];
    print($value);

mysql_close($con);
?>
