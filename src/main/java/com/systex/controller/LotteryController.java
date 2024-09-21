package com.systex.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import com.systex.service.LotteryService;

/**
 * Servlet implementation class LotteryController
 */
public class LotteryController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private LotteryService lotteryService = new LotteryService();   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LotteryController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		request.setCharacterEncoding("UTF-8");
		RequestDispatcher view;
		LinkedList<String> errorMsgs = new LinkedList<>();
		request.setAttribute("errors", errorMsgs);
		
		// 1. 從表單取得使用者的輸入
        String numberOfSetsStr = request.getParameter("numberOfSets");
        String excludeNumbersStr = request.getParameter("excludeNumbers");

        try {
            // 2. 轉換組數並計算所需產生的總數字數
            int numberOfSets = Integer.parseInt(numberOfSetsStr);
            int totalNumbers = numberOfSets * 6;

            // 3. 解析並檢查使用者輸入的排除數字
            Set<Integer> excludeNumbers = new HashSet<>();
            String[] inputNumbers = excludeNumbersStr.split(" ");
            
            // 驗證排除的數字
            if (inputNumbers.length != 5) {
                errorMsgs.add("請輸入正好 5 個數字！");
            } else {
                for (String numStr : inputNumbers) {
                    try {
                        int num = Integer.parseInt(numStr);
                        if (num < 1 || num > 49 || excludeNumbers.contains(num)) {
                            errorMsgs.add("數字需介於 1~49 且不能重複！");
                        } else {
                            excludeNumbers.add(num);
                        }
                    } catch (NumberFormatException e) {
                        errorMsgs.add("請輸入有效的整數！");
                    }
                }
            }

            // 如果有錯誤，返回原頁面
            if (!errorMsgs.isEmpty()) {
                view = request.getRequestDispatcher("main.jsp"); // 返回原頁面
                view.forward(request, response);
                return;
            }

            // 4. 調用 service 層生成樂透號碼
            List<Set<Integer>> lotteryNumbers = lotteryService.generateLotteryNumbers(excludeNumbers, totalNumbers);

            // 5. 將生成的號碼存入 request 中，並傳遞給 JSP 顯示結果
            request.setAttribute("lotteryNumbers", lotteryNumbers);
            request.setAttribute("numberOfSets", numberOfSets);

            // 6. 轉發到 JSP 顯示結果
            request.getRequestDispatcher("result.jsp").forward(request, response);

        } catch (NumberFormatException e) {
            // 7. 處理輸入錯誤
            response.getWriter().println("請輸入有效的數字！");
        }
    }
}
