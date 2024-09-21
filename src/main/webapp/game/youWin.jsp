<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-Hant">
<head>
    <meta charset="UTF-8">
    <title>猜數字 - 勝利</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            height: 100vh;
            margin: 0;
            text-align: center;
        }
        h1 {
            color: #000; 
            margin-bottom: 20px;
        }
        .button-container {
            display: flex;
            gap: 20px; /* 按鈕之間的間距 */
        }
        a {
            text-decoration: none;
            color: white;
            background-color: #4CAF50;
            padding: 10px 20px;
            border-radius: 5px;
            transition: background-color 0.3s;
        }
        a:hover {
            background-color: #388E3C;
        }
    </style>
</head>
<body>
    <h1>恭喜你！你猜對了 🎉</h1>
    
    <div class="button-container">
        <a href="guess.jsp">再玩一次</a>
        <a href="../index.jsp">返回首頁</a>
    </div>
</body>
</html>
