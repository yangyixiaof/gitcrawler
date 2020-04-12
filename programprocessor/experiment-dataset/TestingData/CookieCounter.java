
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
/**
 * <p>Title: servlet读取cookie</p>
 * <p>Description: 这个servlet演示怎样创建和获取cookie并设置cookie的期限</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Filename: CookieCounter.java</p>
 * @author 杜江
 * @version 1.0
 */
//必须继承HttpServlet类
public class CookieCounter extends HttpServlet {
  private int page_count = 0;

/**
 *<br>方法说明：初始化
 *<br>输入参数：ServletConfig config 服务器配置对象
 *<br>返回类型：
 */

  public void init(ServletConfig config) throws ServletException  {
    super.init(config);
  }
/**
 *<br>方法说明：实现service方法
 *<br>输入参数：HttpServletRequest req 客户请求对象
 *<br>输入参数：HttpServletResponse res 服务器应答对象
 *<br>返回类型：
 */
  public void service(HttpServletRequest h_req, HttpServletResponse h_res)
       throws IOException
  {
    boolean cookie_found = false;
    Cookie this_cookie = null;
    
    // 设置内容类型
    h_res.setContentType("text/html; charset=GB2312");
    // 调用getWriter()
    PrintWriter pw_out = h_res.getWriter();
    
    // 从请求获取coolies
    Cookie[] cookies = h_req.getCookies();
    
    if(cookies!=null){
      for(int i=0; i < cookies.length; i++) {
        this_cookie = cookies[i];
        //检查是否存在CookieCount数据
        if (this_cookie.getName().equals("CookieCount")) {
          cookie_found = true;
          break;
        }
      }
    }
    if (cookie_found == false) {
      // 创建新的Cookie并设置它的存活期
      this_cookie = new Cookie("CookieCount", "1");
      this_cookie.setMaxAge(60*1);
      // 在response对象中加入cookie
      h_res.addCookie(this_cookie);
    }
    //输出页面
    pw_out.println("<html><head>\n" + "<title>Cookie计数器</title></head><body>\n" +
                "<center><h1>Cookie 计数器</h1></center></font>");
    page_count++;
    pw_out.println("<p>");
    pw_out.println("<font color=blue size=+1>");
    pw_out.println("<p><br><br><br>这个页面您已经拜访了 " + page_count + 
                " 次.\n");
    
    // 显示客户端详细信息,是否存在计数器cookie
    if (cookie_found) {
      int cookieCount = Integer.parseInt(this_cookie.getValue());
      cookieCount++;
      // 设置cookie的新值, 加到相应对象中
      this_cookie.setValue(String.valueOf(cookieCount));
      this_cookie.setMaxAge(10);
      h_res.addCookie(this_cookie);
      
      pw_out.println("<p>这是你近10秒内第 " +
                  this_cookie.getValue() +
                  " 次拜访这一页\n");
      
    } else {
      pw_out.println("<p>你在近10秒内没有拜访过此页或者你的浏览器不支持cookie "+
                  "如果你的浏览器支持cookie，请确认是否打开了！\n");
    }
    pw_out.println("</body></html>");
    
  }
}

