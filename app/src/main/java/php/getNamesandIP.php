<?php

$con=mysqli_connect("localhost","tictactoePlayer","android123456789","TicTacToe");

if (mysqli_connect_errno($con))
{
   echo "Failed to connect to MySQL: " . mysqli_connect_error();
}
$result = mysqli_query($con,"SELECT idPlayers,name FROM Game G INNER JOIN Players P ON P.idPlayers = G.idPlayer1 AND G.idPlayer2 is null");


while($row = mysqli_fetch_array($result)){    
    $data[] = $row;
}

print(json_encode($data));
mysql_free_result($result);
mysqli_close($con);


?>
