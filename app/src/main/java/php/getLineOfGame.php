<?php

$con=mysqli_connect("localhost","tictactoePlayer","android123456789","TicTacToe");

if (mysqli_connect_errno($con))
{
   echo "Failed to connect to MySQL: " . mysqli_connect_error();
}

$idPlayer1 = $_POST['idPlayer1'];

$result = mysqli_query($con,"SELECT idPlayer1,IdPlayer2 FROM Game WHERE idPlayer1 = $idPlayer1");


while($row = mysqli_fetch_array($result)){    
    $data[] = $row;
}

print(json_encode($data));
mysqli_close($con);


?>
