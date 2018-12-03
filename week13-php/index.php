<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Document</title>

    <style>
        table {
            border-collapse: collapse;
            text-align: center;
        }
        tr,
        td {
            border: 2px solid gray;
            width: 160px;
            font-family:"consolas";
        }
    </style>
</head>
<body>

    <table>
    <tr>
        <td colspan = "3">1410732014 許展維</td>
    </tr>
    <?php
        for ($i=1; $i <= 9; $i++) {
            if(($i - 1) % 3 == 0) 
                echo("<tr>");

            echo("<td>");
            for ($j=1; $j <= 9; $j++)
                echo(sprintf("%d x %d = %d<br>", $i, $j, $i * $j));
            echo("</td>");
            
            if(($i - 1) % 3 == 2) 
                echo("</tr>");
        }
    ?>
    </table>
</body>
</html>