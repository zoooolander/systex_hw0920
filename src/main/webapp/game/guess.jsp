<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>猜數字</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f9f9f9; 
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        .container {
            text-align: center;
            background: white;
            border-radius: 10px;
            box-shadow: 0 4px 20px rgba(0, 0, 0, 0.1);
            padding: 20px;
            width: 300px; /* 設定固定寬度 */
        }
        h2 {
            color: #333;
            margin-bottom: 20px;
        }
        label {
            margin-bottom: 10px;
            font-size: 1.1em;
            color: #555; /* 深灰色 */
        }
        input[type="text"] {
            padding: 10px;
            margin-bottom: 20px;
            border-radius: 5px;
            border: 1px solid #ccc;
            font-size: 1em;
            width: 100%; /* 佔滿寬度 */
            box-sizing: border-box; /* 確保邊框不影響寬度 */
        }
        input[type="submit"] {
            padding: 10px 20px;
            background-color: #4CAF50; /* 綠色 */
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-size: 1.1em;
            transition: background-color 0.3s; /* 加入過渡效果 */
        }
        input[type="submit"]:hover {
            background-color: #45a049; /* 更深的綠色 */
        }
        .message {
            color: red;
            font-size: 0.9em;
            margin: 10px 0;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>請輸入 1 到 10 之間的數字：</h2>
        <form action="gameController.do" method="post" style="display: flex; flex-direction: column;">
            <label>你的猜測：</label>
            <input type="text" name="guess" required>

            <%-- 顯示提示訊息 --%>
            <p class="message">
                <%= request.getAttribute("message") != null ? request.getAttribute("message") : "" %>
            </p>
            <p>剩餘次數：<%= request.getAttribute("remain") != null ? request.getAttribute("remain") : 3 %></p>

            <input type="submit" value="提交">
        </form>
    </div>
</body>
</html>
