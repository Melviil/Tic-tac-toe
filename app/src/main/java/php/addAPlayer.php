<?php 

$con=mysqli_connect("localhost","tictactoePlayer","android123456789","TicTacToe");

if (mysqli_connect_errno($con))
{
   echo "Failed to connect to MySQL: " . mysqli_connect_error();
}
$name = $_POST['name'];

$sql = "INSERT INTO Players (name) VALUES(\"$name\")";
mysqli_query($con,$sql);

mysql_close($con);

?>
