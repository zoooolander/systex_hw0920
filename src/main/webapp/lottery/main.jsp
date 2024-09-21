<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.LinkedList"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>樂透號碼產生器</title>
    <link href="<%=request.getContextPath()%>/style/myStyle.css" rel="stylesheet">
</head>
<body style="font-family: Arial, sans-serif; background-color: #f9f9f9; margin: 0; padding: 0; display: flex; justify-content: center; align-items: center; height: 100vh;">
    <div style="text-align: center;">
        <%-- 使用者輸入表單，提交到 /generateLottery --%>
        <form action="lotteryController.do" method="post" style="display: flex; flex-direction: column;">
            <label for="numberOfSets" style="margin-bottom: 10px; font-size: 1.1em;">請輸入需要產生幾組號碼：</label>
            <input type="text" id="numberOfSets" name="numberOfSets" required style="padding: 10px; margin-bottom: 20px; border-radius: 5px; border: 1px solid #ccc; font-size: 1em;">

            <label for="excludeNumbers" style="margin-bottom: 10px; font-size: 1.1em;">請輸入5個需要排除的數字 (範圍 1~49，以空格分隔)：</label>
            <input type="text" id="excludeNumbers" name="excludeNumbers" required style="padding: 10px; margin-bottom: 20px; border-radius: 5px; border: 1px solid #ccc; font-size: 1em;">

            <%-- Start error report --%>
            <%
            LinkedList<String> errors = (LinkedList<String>) request.getAttribute("errors");
            if (errors != null) {
            %>
                <ul style="color: red; font-size: 0.9em; list-style-position: inside; padding: 0; margin-bottom: 20px;">
                    <%
                    for (String error : errors) {
                    %>
                    <li><%= error %></li>
                    <%
                    }
                    %>
                </ul>
            <%
            }
            %>
            <%-- End error report --%>

            <input type="submit" value="產生號碼" style="padding: 10px 20px; background-color: #4CAF50; color: white; border: none; border-radius: 5px; cursor: pointer; font-size: 1.1em;">
        </form>
    </div>
</body>
</html>
