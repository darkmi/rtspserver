package com.darkmi.rtsp.server.web.web.account;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserAction {

	@RequestMapping("/user.do")
	public void test(HttpServletResponse response) throws IOException {
		response.getWriter().write("Hi, u guy.");
	}
}
