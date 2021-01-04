package com.cos.blog.web;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cos.blog.domain.user.dto.JoinReqDto;
import com.cos.blog.domain.user.dto.LoginReqDto;
import com.cos.blog.service.Userservice;

@WebServlet("/user")
public class Usercontroller extends HttpServlet {

	private void doProcess(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String cmd = request.getParameter("cmd");

		Userservice userService = new Userservice();

		if (cmd.equals("loginForm")) {
			response.sendRedirect("user/lginFrom.jsp");
		} else if (cmd.equals("login")) {
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			LoginReqDto dto = new LoginReqDto();
			dto.setUsername(username);
			dto.setPassword(password);
			userService.로그인(dto);
		} else if (cmd.equals("joinForm")) {
			response.sendRedirect("user/joinForm.jsp");
		} else if (cmd.equals("join")) {
			// 서비스 호출
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			String email = request.getParameter("email");
			String address = request.getParameter("address");
			JoinReqDto dto = new JoinReqDto();
			dto.setUsername(username);
			dto.setPassword(password);
			dto.setEmail(email);
			dto.setAddress(address);
			userService.회원가입(dto);
		}

	}

}
