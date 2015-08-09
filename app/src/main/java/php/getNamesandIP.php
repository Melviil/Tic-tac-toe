<?php

$con=mysqli_connect("localhost","tictactoePlayer","android123456789","TicTacToe");

if (mysqli_connect_errno($con))
{
   echo "Failed to connect to MySQL: " . mysqli_connect_error();
}
$result = mysqli_query($con,"SELECT * FROM Players");
$row = mysqli_fetch_array($result);
$data = $row[1];

if($data){
    echo $data;
}
mysqli_close($con);


?>
