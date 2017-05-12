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
 * Title: servlet��ȡcookie
 * </p>
 * <p>
 * Description: ���servlet��ʾ���������ͻ�ȡcookie������cookie������
 * </p>
 * <p>
 * Copyright: Copyright (c) 2003
 * </p>
 * <p>
 * Filename: CookieCounter.java
 * </p>
 * 
 * @author �Ž�
 * @version 1.0
 */
// ����̳�HttpServlet��
@SuppressWarnings("serial")
public class CookieCounter extends HttpServlet {
	private int pageCount = 0;

	/**
	 * <br>
	 * ����˵������ʼ�� <br>
	 * ���������ServletConfig config ���������ö��� <br>
	 * �������ͣ�
	 */

	public void init(ServletConfig config) throws ServletException {
		// right 1
		super.init(config);
	}

	/**
	 * <br>
	 * ����˵����ʵ��service���� <br>
	 * ���������HttpServletRequest req �ͻ�������� <br>
	 * ���������HttpServletResponse res ������Ӧ����� <br>
	 * �������ͣ�
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
		out.println("<html><head>\n" + "<title>Cookie������</title></head><body>\n"
				+ "<center><h1>Cookie ������</h1></center></font>");
		pageCount++;
		// right 1
		out.println("<p>");
		// right 2
		out.println("<font color=blue size=+1>");
		// right 1
		out.println("<p><br><br><br>���ҳ�����Ѿ��ݷ��� " + pageCount + " ��.\n");
		
		if (cookieFound) {
			// right 1
			int cookieCount = Integer.parseInt(thisCookie.getValue());
			// right 4
			cookieCount++;
			thisCookie.setValue(String.valueOf(cookieCount));
			thisCookie.setMaxAge(10);
			res.addCookie(thisCookie);
			
			// right 1
			out.println("<p>�������10���ڵ� " + thisCookie.getValue() + " �ΰݷ���һҳ\n");

		} else {
			// right 1
			out.println("<p>���ڽ�10����û�аݷù���ҳ��������������֧��cookie " + "�����������֧��cookie����ȷ���Ƿ���ˣ�\n");
		}
		// right 1
		out.println("</body></html>");

	}
}
