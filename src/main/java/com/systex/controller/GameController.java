package com.systex.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.systex.model.GuessNumberResult;
import com.systex.service.GuessGameService;

public class GameController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private GuessGameService guessGameService;

	@Override
	public void init() throws ServletException {
		// 在 init 方法中初始化 GuessGameService，設定範圍為 10，剩餘次數為 3
		guessGameService = new GuessGameService(10, 3);
	}

	// 使用 session 儲存 GuessGameService
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	    GuessGameService guessGameService = (GuessGameService) request.getSession().getAttribute("guessGameService");
	    if (guessGameService == null) {
	        guessGameService = new GuessGameService(10, 3);
	        request.getSession().setAttribute("guessGameService", guessGameService);
	    }
		RequestDispatcher dispatcher = request.getRequestDispatcher("/game/guess.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
	    // 從 session 中取得 guessGameService
	    GuessGameService guessGameService = (GuessGameService) request.getSession().getAttribute("guessGameService");
	    if (guessGameService == null) {
	        // 如果 session 中不存在 guessGameService，則重新初始化
	        guessGameService = new GuessGameService(10, 3);
	        request.getSession().setAttribute("guessGameService", guessGameService);
	    }

	    int guessNumber;
	    try {
	        guessNumber = Integer.parseInt(request.getParameter("guess"));
	    } catch (NumberFormatException e) {
	        // 當輸入不是數字時，返回錯誤訊息
	        request.setAttribute("error", "請輸入有效的數字！");
	        RequestDispatcher dispatcher = request.getRequestDispatcher("/game/guess.jsp");
	        dispatcher.forward(request, response);
	        return;
	    }

	    // 執行猜測邏輯
	    GuessNumberResult guessNumberResult = guessGameService.guess(guessNumber);

	    // 判斷猜測結果
	    if ("success".equals(guessNumberResult.getStatus())) {
	        // 猜中，導向 youWin.jsp 並清除 session
	        request.getSession().invalidate();  // 清除 session
	        response.sendRedirect("youWin.jsp");
	    } else if ("fail".equals(guessNumberResult.getStatus())) {
	        // 猜錯且次數用完，將正確答案設置到 request 中，導向 youLose.jsp 並清除 session
	        request.setAttribute("correctAnswer", guessGameService.getLuckyNumber());
	        request.getSession().invalidate();  // 清除 session
	        RequestDispatcher dispatcher = request.getRequestDispatcher("youLose.jsp");
	        dispatcher.forward(request, response);
	    } else {
	        // 繼續猜測
	        request.setAttribute("message", guessNumberResult.getMessage()); // 提示訊息
	        request.setAttribute("remain", guessGameService.getRemain());    // 剩餘次數
	        RequestDispatcher dispatcher = request.getRequestDispatcher("/game/guess.jsp");
	        dispatcher.forward(request, response);
	    }
	}
}
