<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.List, java.util.Set, java.util.ArrayList, java.util.Collections" %>
<!DOCTYPE html>
<html lang="zh-Hant">
<head>
    <meta charset="UTF-8">
    <title>樂透號碼結果</title>
    <link href="<%=request.getContextPath()%>/style/myStyle.css" rel="stylesheet">
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 20px;
            display: flex;
            flex-direction: column;
            align-items: center;
        }
        h1 {
            color: #333;
            margin-bottom: 20px;
        }
        p {
            margin: 10px 0;
        }
        .number {
            background-color: white;
            border: 1px solid #ccc;
            border-radius: 5px;
            padding: 10px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
        }
        a {
            text-decoration: none;
            color: white;
            background-color: #4CAF50;
            padding: 10px 15px;
            border-radius: 5px;
            margin: 5px;
            transition: background-color 0.3s;
        }
        a:hover {
            background-color: #388E3C;
        }
    </style>
</head>
<body>
    <h1>樂透號碼結果</h1>
    <% 
    List<Set<Integer>> lotteryNumbers = (List<Set<Integer>>) request.getAttribute("lotteryNumbers");
    int numberOfSets = (Integer) request.getAttribute("numberOfSets");

    if (lotteryNumbers != null) {
        for (int i = 0; i < numberOfSets; i++) {
            List<Integer> sortedNumbers = new ArrayList<>(lotteryNumbers.get(i));
            Collections.sort(sortedNumbers);
            String numbersString = String.join(", ", sortedNumbers.stream().map(String::valueOf).toArray(String[]::new));
            out.println("<p>第 " + (i + 1) + " 組號碼：<span class='number'>" + numbersString + "</span></p>");
        }
    } else {
        out.println("<p>無結果。</p>");
    }
    %>

    <div style="margin-top: 20px;">
    <a href="main.jsp">重新產生號碼</a>
    <a href="../index.jsp">返回首頁</a>
</div>
</body>
</html>
