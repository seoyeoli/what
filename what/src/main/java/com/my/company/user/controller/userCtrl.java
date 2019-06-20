package com.my.company.user.controller;

import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.my.company.common.controller.SHA256Password;
import com.my.company.common.service.commonService;
import com.my.company.user.service.userService;
import com.my.company.user.vo.memberVO;
import com.my.company.user.vo.userVO;

@Controller
public class userCtrl {
	
	private static final Logger logger = LoggerFactory.getLogger(userCtrl.class);
	
	@Resource(name="userservice")
	private userService userservice;
	
	@Resource(name="commonservice")
	private commonService commonservice;
	
	
	@RequestMapping("/user/signin.do")
	public ModelAndView signin(HttpServletRequest request, HttpServletResponse response) throws Exception{
		ModelAndView mv = new ModelAndView("/user/signin");
		return mv;
	}
	
	
	@RequestMapping("/user/loginPage.do")
	public ModelAndView loginPage(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView("/user/loginPage");
		String url = request.getParameter("url") == null ? "/common/main.do" : request.getParameter("url");
		mv.addObject("url", url);
		return mv;
	}
	
	@RequestMapping("/user/login.do")
	public void login(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		HttpSession session = request.getSession(true);
		String user_id = request.getParameter("user_id");
		String user_pw = request.getParameter("user_pw");
		String loc = request.getParameter("loc");
		String url = request.getParameter("url");
		logger.debug("\t url ==> "+url);
		
		logger.debug("\n\n입력값\n"+user_id+"\n"+user_pw+"\n\n");
		SHA256Password ecd= new SHA256Password(); 
		String ecdpw = ecd.LockPassword(user_pw);
		
		userVO uservo = new userVO();
		uservo.setUser_id(user_id);
		uservo.setUser_pw(ecdpw);
		
		try {
			logger.debug("\n\n검색값\n"+uservo.toString()+"\n");
			if(userservice.loginUser(uservo)){
				uservo = userservice.selectUser(uservo);
				
				//회원가입이 되어있으면 회원권 조회함.
				memberVO membervo = new memberVO();
				membervo.setUser_num(uservo.getUser_num());
				
				
				logger.debug(uservo.toString());
				request.setAttribute("uservo", uservo);
				session.setAttribute("user_id", uservo.getUser_id());
				session.setAttribute("user_name", uservo.getUser_name());
				session.setAttribute("uservo", uservo);
				response.sendRedirect(url);
			}else {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				
				out.println("<script>alert('아이디랑 비밀번호를 다시 확인해주세요.'); location.href='"+loc+"' </script>");
				return;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@RequestMapping("/user/logout.do")
	public void logout(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession(true);
		session.invalidate(); //session clear
		
		//한글 깨짐
		//request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<script>alert('로그아웃되었습니다.'); location.href='/common/main.do';</script>");
	}
	
	
	
	@RequestMapping("/user/userInfo.do")
	public ModelAndView userInfo(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String user_id = request.getParameter("user_id");
		userVO uservo = new userVO();
		uservo.setUser_id(user_id);
		uservo = userservice.selectUser(uservo);
		logger.debug(uservo.toString());
		
		ModelAndView mv = new ModelAndView("/user/userInfo");
		mv.addObject("uservo", uservo);
		
		return mv;
	}
	
}
