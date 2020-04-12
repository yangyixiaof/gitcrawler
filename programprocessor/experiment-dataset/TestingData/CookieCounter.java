
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class CookieCounter extends HttpServlet {
  private int page_count = 0;

  public void init(ServletConfig config) throws ServletException  {
    super.init(config);
  }
  
  public void service(HttpServletRequest h_req, HttpServletResponse h_res)
       throws IOException
  {
    boolean cookie_found = false;
    Cookie this_cookie = null;
    
    h_res.setContentType("text/html; charset=GB2312");
    
    PrintWriter pw_out = h_res.getWriter();
    
    Cookie[] cookies = h_req.getCookies();
    
    if(cookies!=null){
      for(int i=0; i < cookies.length; i++) {
        this_cookie = cookies[i];
        if (this_cookie.getName().equals("CookieCount")) {
          cookie_found = true;
          break;
        }
      }
    }
    if (cookie_found == false) {
      this_cookie = new Cookie("CookieCount", "1");
      this_cookie.setMaxAge(60*1);
      h_res.addCookie(this_cookie);
    }
    
    pw_out.println("<html><head>\n" + "<title>Cookie������</title></head><body>\n" +
                "<center><h1>Cookie ������</h1></center></font>");
    page_count++;
    pw_out.println("<p>");
    pw_out.println("<font color=blue size=+1>");
    pw_out.println("<p><br><br><br>���ҳ�����Ѿ��ݷ��� " + page_count + 
                " ��.\n");
    
    if (cookie_found) {
      int cookieCount = Integer.parseInt(this_cookie.getValue());
      cookieCount++;
      this_cookie.setValue(String.valueOf(cookieCount));
      this_cookie.setMaxAge(10);
      h_res.addCookie(this_cookie);
      
      pw_out.println("<p>�������10���ڵ� " +
                  this_cookie.getValue() +
                  " �ΰݷ���һҳ\n");
      
    } else {
      pw_out.println("<p>���ڽ�10����û�аݷù���ҳ��������������֧��cookie "+
                  "�����������֧��cookie����ȷ���Ƿ���ˣ�\n");
    }
    pw_out.println("</body></html>");
    
  }
}

