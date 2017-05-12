
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

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
public class CookieCounter extends HttpServlet {
	private int pageCount = 0;

	/**
	 * <br>
	 * ����˵������ʼ�� <br>
	 * ���������ServletConfig config ���������ö��� <br>
	 * �������ͣ�
	 */

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}

	public void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
		boolean cookieFound = false;
		// wrong because of not generalizing.
		Cookie thisCookie = null;
		
		response.setContentType("text/html; charset=GB2312");
		PrintWriter out = response.getWriter();
		
		Cookie[] cookies = request.getCookies();
		// wrong because of not generalizing.
		if (cookies != null) {
			// wrong because of not generalizing.
			for (int i = 0; i < cookies.length; i++) {
				thisCookie = cookies[i];
				if (thisCookie.getName().equals("CookieCount")) {
					cookieFound = true;
					break;
				}
			}
		}
		if (cookieFound) {
			int cookieCount = Integer.parseInt(thisCookie.getValue());
			// wrong because of not generalizing.
			cookieCount++;
			thisCookie.setValue(String.valueOf(cookieCount));
			thisCookie.setMaxAge(10);
			response.addCookie(thisCookie);
			
			out.println("<p>�������10���ڵ� " + thisCookie.getValue() + " �ΰݷ���һҳ\n");

		} else {
			out.println("<p>���ڽ�10����û�аݷù���ҳ��������������֧��cookie " + "�����������֧��cookie����ȷ���Ƿ���ˣ�\n");
		}
		// wrong because of not generalizing.
		out.println("</body></html>");

	}
}
