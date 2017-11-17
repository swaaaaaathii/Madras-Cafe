import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.util.*;

public class cart extends HttpServlet
{
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException   
	{
		response.setContentType("text/html");
        PrintWriter out = response.getWriter();
		String name = request.getParameter("name");
		String qty = request.getParameter("qty");
		String item = request.getParameter("item");
		String price = request.getParameter("price");
		String category = request.getParameter("category");
		Cookie cookies[] = request.getCookies(); 
		String cname = "";
		String cvalue = "";
		int flag = 0;
	    if(cookies!=null)
	    {
			for(int i = 0; i < cookies.length; i++)     
			{
				String c_name = cookies[i].getName();
				if(c_name.equals(name))
				{  
					Cookie c1 = cookies[i];
					cname = c1.getName();
					cvalue = c1.getValue();
					if(cvalue.contains(item)){
						out.println("Item already exists in cart. Go to view cart to edit quantity");
						return;
					}
					if(cvalue.contentEquals("empty"))
						cvalue = category+"+"+item+"+"+qty+"+"+price;
					else
						cvalue = cvalue + "-" + category+"+"+item+"+"+qty+"+"+price;
					c1.setValue(cvalue);
					response.addCookie(c1);
					flag=1;
					break;	
				}		
			}
	    }
		if(flag==0){
				cvalue = category+"+"+item+"+"+qty;
				Cookie c2 = new Cookie(name,cvalue);
				response.addCookie(c2);
		}
		out.println("Item added to cart");
	}
}