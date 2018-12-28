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
        echo "POST資料:";
        showDebug($_POST);
        echo "FILE資料:";
        showDebug($_FILES);

        $conn = mysqli_connect("127.0.0.1", "root", "", "nutc_homework");
        if($conn) echo $conn ? "連接成功<br>":"連接失敗<br>";
        
        $file_path = "upload/";
        if(isset($_POST["post"])){
            if($_FILES["up_image"]["error"] == 0 ){
                echo "上傳資料狀態:upload ok<br>";
                echo "檔案名稱: " . $_FILES["up_image"]["name"]."<br>";
                echo "檔案類型: " . $_FILES["up_image"]["type"]."<br>";
                echo "檔案大小: " . ($_FILES["up_image"]["size"] / 1024)." Kb<br>";
                echo "暫存名稱: " . $_FILES["up_image"]["tmp_name"]."<br>";
                $savePath = $file_path . $_FILES["up_image"]["name"];

                // 在最後上傳之前，檢查目錄下是否有相同檔案
                if(file_exists($savePath)){
                    echo "<font color='red'>伺服器已擁有相同檔名，請更換檔名稱</font>";
                }else{
                    $sql = "INSERT INTO upload_image (name, class, img) VALUE('%s','%s','%s')";
                    $sql = sprintf($sql, $_POST["up_name"],$_POST["up_class"], $_FILES["up_image"]["name"]);
                    $result = mysqli_query($conn, $sql);
        
                    echo $sql;
                    showDebug($result ? "新增成功<br>":"新增失敗<br>");
                    move_uploaded_file($_FILES["up_image"]["tmp_name"], $file_path . $_FILES["up_image"]["name"]);
                }
            }else{
                echo "upload error";
            }
        }

        if(isset($_POST["delete"])){
            $sql = "DELETE FROM upload_image WHERE img ='".$_POST["delete"]."'";
            showDebug($sql);
            $result = mysqli_query($conn, $sql);
            showDebug($result ? "刪除成功<br>":"刪除失敗<br>");
        }
    ?>
    
    <div style="width: 80%; margin:0 auto;">
        <form action="index.php" method="post" class="form-inline" enctype="multipart/form-data">
            名稱 <input type="text" name="up_name" id="input" class="form-control" placeholder="name">
            類別 <input type="text" name="up_class" id="input" class="form-control" placeholder="class">
            圖片 <input type="file" name="up_image" id="fileUpload" class="form-control" style="display:inline-block">
            
            <button type="submit" name="post" value="Send" class="btn btn-success">上傳</button>

            
            <select name="select">
                <?php
                $defaultSelect = "ALL";
                $selected = "ALL";// 預設選擇為全部
                echo "<option selected>".$selected."</option>";

                if(isset($_POST["select"])){
                    $selected = $_POST["select"];
                }
                // 取出資料的類別(不重複)
                $result = mysqli_query($conn, "SELECT class FROM upload_image group by class");
                while ($row = mysqli_fetch_assoc($result)) {
                    $toSelect = $selected == $row["class"] ? "selected":"";// 綁定選擇
                    echo "<option ".$toSelect.">".$row["class"]."</option>";
                }
                ?>
            </select>
            
            <button name="selectBtn" value="select" type=button" class="btn btn-info">選擇</button>
            

            <table class="table table-bordered table-hover">
                <thead>
                    <tr id="coltitle">
                        <th>功能</th>
                        <th width=30%>圖片</th>
                        <th width=20%>名稱</th>
                        <th width=20%>類別</th>
                        <th width=30%>時間</th>
                    </tr>
                </thead>
                <tbody>
                <?php
                $result = mysqli_query($conn, "SELECT * FROM upload_image");
                while ($row = mysqli_fetch_assoc($result)) {                    
                    // 不是選擇全部 && 不是指定的類別，跳過這筆資料
                    if($selected != $defaultSelect && $row["class"] != $selected) continue;

                    echo "<tr>";

                    echo "<td align='center'>".
                    "<button name='delete' value='".$row["img"]."' type='submit' class='btn btn-danger'>刪除</button>"
                    ."</td>";
    
                    echo "<td>".
                    "<image width=100% src='".$file_path.$row["img"]."'>"
                    ."</td>";

                    echo "<td>".
                    $row["name"]
                    ."</td>";

                    echo "<td>".
                    $row["class"]
                    ."</td>";

                    echo "<td>".
                    $row["time"]
                    ."</td>";

                    echo "</tr>";
                }
                ?>
                </tbody>
            </table>     
        </form>    
    </div>
    
</body>
</html>