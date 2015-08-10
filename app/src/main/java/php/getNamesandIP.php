<?php

$con=mysqli_connect("localhost","tictactoePlayer","android123456789","TicTacToe");

if (mysqli_connect_errno($con))
{
   echo "Failed to connect to MySQL: " . mysqli_connect_error();
}
$result = mysqli_query($con,"SELECT * FROM Players");


while($row = mysqli_fetch_array($result)){    
    $data[] = $row;
}

print(json_encode($data));
mysql_free_result($result);
mysqli_close($con);


?>