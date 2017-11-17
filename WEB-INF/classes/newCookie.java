import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.util.*;

public class newCookie extends HttpServlet
{
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException   
	{
		response.setContentType("text/html");
        PrintWriter out = response.getWriter();
		String name = request.getParameter("name");
		Cookie cookies[] = request.getCookies(); 
		String cname = "";
		int flag = 0;
	    if(cookies!=null)
	    {
			for(int i = 0; i < cookies.length; i++)     
			{
				String c_name = cookies[i].getName();
				if(c_name.equals(name))
				{  
					Cookie c1 = cookies[i];
					response.addCookie(c1);
					flag=1;
					cname = c1.getName();
					break;	
				}		
			}
	    }
		if(flag==0){
				Cookie c2 = new Cookie(name,"empty");
				response.addCookie(c2);
				cname = c2.getName();
		}
		out.println(cname);
	}
}