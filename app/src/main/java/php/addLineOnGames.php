<?php


$con=mysqli_connect("localhost","tictactoePlayer","android123456789","TicTacToe");

if (mysqli_connect_errno($con))
{
   echo "Failed to connect to MySQL: " . mysqli_connect_error();
}


$name = $_POST['name'];



$result = mysqli_query($con,"SELECT idPlayers FROM Players WHERE name=\"$name\"");
//echo "Result = $result";
while($row = mysqli_fetch_array($result)){
    $data[]=$row[0];
}

$value = $data[0];
mysqli_query($con,"INSERT INTO Game (idPlayer1) VALUES ($value)");


mysql_close($con);


?>
