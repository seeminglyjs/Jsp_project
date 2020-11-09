package com.seeminglyjs.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/pro")
public class profile extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html charset=UTF-8;" );
		
		String cnt_ = request.getParameter("cnt");
		int cnt = 100;
		if(cnt_ != null && !cnt_.equals("")) {
			cnt = Integer.parseInt(cnt_);
		}
		
		PrintWriter out = response.getWriter();
		
		for(int i = 0; i < cnt; i++) {
			out.print("안녕하세요<br>");
		}
		
//		Scanner scan = new Scanner(System.in);
//		String input = request.getParameter(scan.next());
//		PrintWriter out = response.getWriter();
//		response.sendRedirect("경력.html");
	
	}
}
