<?php
    function showDebug($obj) {
        echo "<pre>";
        print_r ($obj);
        echo "</pre>";
    }

    function sqlQuery($query){
        $conn = mysqli_connect("127.0.0.1", "root", "", "nutc_homework");
        // showDebug($conn);
        return mysqli_query($conn, $query);
    }
?>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Document</title>
    <style>
        *{
            font-family: "微軟正黑體", serif;
            font-size:16px;
        }
        table {
            border-collapse: collapse;
        }
        #post{
            margin:0 0 30px 0;
        }
        #coltitle{
            text-align: center;
        }
    </style>
</head>
<body>
    <?php
        showDebug($_POST);
        if(isset($_POST["post"])){
            if($_POST)
            $sql = "INSERT INTO post (post_name,post_comment) VALUE('%s','%s')";
            $sql = sprintf($sql, $_POST["post_name"],$_POST["post_comment"]);
            // showDebug($sql);
            $result = sqlQuery($sql);

            echo $result?"留言成功":"留言失敗";
        }

        if(isset($_POST["delete_comment"])){
            $sql = "DELETE FROM post WHERE post_id=%s";
            $sql = sprintf($sql, $_POST["delete_comment"]);
            $result = sqlQuery($sql);
            echo $result?"刪除成功":"刪除失敗";
        }

        if(isset($_POST["delete_checks"]) && isset($_POST["checks"])){
            $checks = $_POST["checks"];
            $sql = "DELETE FROM post WHERE ";
            $arr = array();
            foreach ($checks as $index => $id) {
                array_push($arr, "post_id=".$id);
            }
            $sql.=join(" OR ",$arr);
            // showDebug($sql);
            $result = sqlQuery($sql);
            echo $result?"刪除成功":"刪除失敗";
        }
    ?>

    <form id="post" action="" method="post">
        ID <input type="text" name="post_name" placeholder="Your name" required>
        Content <input type="text" name="post_comment" placeholder="Type something" required>
        <input type="submit" name="post" value="Send">
    </form>
    
    <form action="" method="post">
        <button type="button" name="update_checks" value="編輯勾選" onclick="btnClick_update_checks(this)">編輯勾選</button>
        <input type="submit" name="delete_checks" value="刪除勾選">

        <table id="comment_list" border="solid black">
            <tr id="coltitle">
                <td>勾選</td>
                <td>功能</td>
                <td>時間</td>
                <td width="100px">名稱</td>
                <td width="300px">內容</td>
            </tr>
            <?php
            $result = sqlQuery("SELECT * FROM post");
            while ($row = mysqli_fetch_assoc($result)) {
                
                // showDebug($row);
                echo "<tr id=postid_".$row["post_id"].">";
                echo "<td>"."<input type='checkbox' name='checks[]' value=".$row["post_id"].">"."</td>";

                echo 
                "<td>".
                "<button type='button' name='update_comment' value=".$row["post_id"].">編輯</button>".
                "<button type='submit' name='delete_comment' value=".$row["post_id"].">刪除</button>"
                ."</td>";

                echo "<td>".$row["post_time"]."</td>";
                echo "<td>".$row["post_name"]."</td>";
                echo "<td>".$row["post_comment"]."</td>";
                echo "</tr>";
            }
            ?>
        </table>    
    </form>
    
</body>
</html>