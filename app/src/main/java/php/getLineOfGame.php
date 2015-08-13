<?php

$con=mysqli_connect("localhost","tictactoePlayer","android123456789","TicTacToe");

if (mysqli_connect_errno($con))
{
   echo "Failed to connect to MySQL: " . mysqli_connect_error();
}

$nameP1 = $_POST['namep1'];

$result = mysqli_query($con,"SELECT idPlayers FROM Players WHERE name=\"$nameP1\"");
while($row = mysqli_fetch_array($result)){
    $data1[]=$row[0];
}
$idPlayer1 = $data1[0];

$result = mysqli_query($con,"SELECT idPlayer1,idPlayer2,name FROM Game INNER JOIN Players on idPlayers = idPlayer2 WHERE idPlayer1=$idPlayer1");


while($row = mysqli_fetch_array($result)){    
    $data2[] = $row;
}

print(json_encode($data2));
mysqli_close($con);


?>
