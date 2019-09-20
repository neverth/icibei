<?php
/**
 * Created by IntelliJ IDEA.
 * User: LIYANG
 * Date: 2019/3/5
 * Time: 10:44
 */


require('conn.php');//返回conn数据库对象

$script_l = '<script>';
$script_r = '</script>';
$script = '';
$sql = "SELECT * FROM `wordlist`";

$result = $conn->query($sql);

for ($i = 0; $i < $result->num_rows; $i++){
    $row[$i] = $result->fetch_assoc();
}

echo $script_l;
foreach ($row as $key=>$value){
    $script = "wordList.push({word:'" . $value['english'] . "',chinese:'" . $value['chinese'] . "'});";
    echo $script;
}
echo "console.log('getlist.php单词导入成功')";
echo $script_r;
?>
