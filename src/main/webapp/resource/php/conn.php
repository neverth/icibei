<?php
$servername = "47.112.25.38";
$username = "aicibei";
$password = "Xzc4X6YSRH";

$conn = new mysqli($servername, $username, $password, "aicibei");
$conn->set_charset("utf8");
if ($conn->connect_error) {
    echo "<script>console.log('conn.php 数据库连接失败')</script>";

}
else{
    echo "<script>console.log('conn.php 数据库连接成功')</script>";
}
?>
