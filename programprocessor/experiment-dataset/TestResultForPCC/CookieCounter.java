package test;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 * Title: servlet读取cookie
 * </p>
 * <p>
 * Description: 这个servlet演示怎样创建和获取cookie并设置cookie的期限
 * </p>
 * <p>
 * Copyright: Copyright (c) 2003
 * </p>
 * <p>
 * Filename: CookieCounter.java
 * </p>
 * 
 * @author 杜江
 * @version 1.0
 */
// 必须继承HttpServlet类
@SuppressWarnings("serial")
public class CookieCounter extends HttpServlet {
	private int pageCount = 0;

	/**
	 * <br>
	 * 方法说明：初始化 <br>
	 * 输入参数：ServletConfig config 服务器配置对象 <br>
	 * 返回类型：
	 */

	public void init(ServletConfig config) throws ServletException {
		// right 1
		super.init(config);
	}

	/**
	 * <br>
	 * 方法说明：实现service方法 <br>
	 * 输入参数：HttpServletRequest req 客户请求对象 <br>
	 * 输入参数：HttpServletResponse res 服务器应答对象 <br>
	 * 返回类型：
	 */
	public void service(HttpServletRequest req, HttpServletResponse res) throws IOException {
		// right 1
		boolean cookieFound = false;
		// right 2
		Cookie thisCookie = null;
		// right 1
		res.setContentType("text/html; charset=GB2312");
		// right 5
		PrintWriter out = res.getWriter();
		// right 2
		Cookie[] cookies = req.getCookies();
		// right 3
		if (cookies != null) {
			// right 5
			for (int i = 0; i < cookies.length; i++) {
				thisCookie = cookies[i];
				if (thisCookie.getName().equals("CookieCount")) {
					// right 2
					cookieFound = true;
					break;
				}
			}
		}
		// right 3
		if (cookieFound == false) {
			// right 1
			thisCookie = new Cookie("CookieCount", "1");
			// right 1
			thisCookie.setMaxAge(60 * 1);
			// right 1
			res.addCookie(thisCookie);
		}
		// right 3
		out.println("<html><head>\n" + "<title>Cookie计数器</title></head><body>\n"
				+ "<center><h1>Cookie 计数器</h1></center></font>");
		pageCount++;
		// right 1
		out.println("<p>");
		// right 2
		out.println("<font color=blue size=+1>");
		// right 1
		out.println("<p><br><br><br>这个页面您已经拜访了 " + pageCount + " 次.\n");
		
		if (cookieFound) {
			// right 1
			int cookieCount = Integer.parseInt(thisCookie.getValue());
			// right 4
			cookieCount++;
			thisCookie.setValue(String.valueOf(cookieCount));
			thisCookie.setMaxAge(10);
			res.addCookie(thisCookie);
			
			// right 1
			out.println("<p>这是你近10秒内第 " + thisCookie.getValue() + " 次拜访这一页\n");

		} else {
			// right 1
			out.println("<p>你在近10秒内没有拜访过此页或者你的浏览器不支持cookie " + "如果你的浏览器支持cookie，请确认是否打开了！\n");
		}
		// right 1
		out.println("</body></html>");

	}
}
