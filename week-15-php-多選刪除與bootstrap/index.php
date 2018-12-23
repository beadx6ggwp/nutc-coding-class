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
    
    <!-- Latest compiled and minified CSS & JS -->
    <link rel="stylesheet" media="screen" href="//netdna.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
    <script src="//code.jquery.com/jquery.js"></script>
    <script src="//netdna.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
    
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

        $update_id = "";
        if(isset($_POST["update_comment"])){
            $update_id = $_POST["update_comment"];
        }

        if(isset($_POST["save_update"])){
            $sql = "UPDATE post SET post_name=%s, post_comment=%s WHERE post_id=%s";
            $sql = sprintf($sql, $_POST["save_name"], $_POST["save_comment"], $_POST["save_update"]);
            $result = sqlQuery($sql);
            echo $result?"更新成功":"更新失敗";
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
    
    <div style="width: 800px; margin:0 auto;">
        <form action="index.php" method="post" class="form-inline">
            ID <input type="text" name="post_name" id="input" class="form-control" placeholder="Your name">
            Content <input type="text" name="post_comment" id="input" class="form-control" placeholder="Type something">
            
            <button type="submit" name="post" value="Send" class="btn btn-success">留言</button>

            <br><br>
            <button type="button" name="update_checks" value="編輯勾選" class="btn btn-success">編輯勾選</button>
            
            <button type="submit" name="delete_checks" class="btn btn-danger">刪除勾選</button>

            <table class="table table-bordered table-hover">
                <thead>
                    <tr id="coltitle">
                        <th width="50px">勾選</th>
                        <th>功能</th>
                        <th>時間</th>
                        <th width=20%>名稱</th>
                        <th width=30%>內容</th>
                    </tr>
                </thead>
                <tbody>
                <?php
                $result = sqlQuery("SELECT * FROM post");
                while ($row = mysqli_fetch_assoc($result)) {
                    echo "<tr id=postid_".$row["post_id"].">";
                    
                    echo "<td>"."<input type='checkbox' name='checks[]' value=".$row["post_id"].">"."</td>";

                    if($update_id == $row["post_id"]){
                        echo 
                        "<td>".
                        "<button type='submit' class='btn btn-primary btn-xs' name='save_update' value=".$row["post_id"].">儲存</button>".
                        "<button type='submit' class='btn btn-danger btn-xs'>取消</button>"
                        ."</td>";

                        echo "<td>".$row["post_time"]."</td>";
                       
                        echo 
                        "<td>".
                        "<input type='text' name='save_name' class='form-control' value=".$row["post_name"].">"
                        ."</td>";
                        echo 
                        "<td>".
                        "<input type='text' name='save_comment' class='form-control' value=".$row["post_comment"].">"
                        ."</td>";
                    }
                    else{
                        echo 
                        "<td>".
                        "<button type='submit' class='btn btn-success btn-xs' name='update_comment' value=".$row["post_id"].">編輯</button>".
                        "<button type='submit' class='btn btn-danger btn-xs' name='delete_comment' value=".$row["post_id"].">刪除</button>"
                        ."</td>";

                        echo "<td>".$row["post_time"]."</td>";
                        echo "<td>".$row["post_name"]."</td>";
                        echo "<td>".$row["post_comment"]."</td>";
                    }                        

                    echo "</tr>";
                }
                ?>
                </tbody>
            </table>     
        </form>    
    </div>
    
</body>
</html>