import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.util.*;

public class updateCart extends HttpServlet
{
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException   
	{
		response.setContentType("text/html");
        PrintWriter out = response.getWriter();
		String name = request.getParameter("name");
		String qty = request.getParameter("qty");
		String item = request.getParameter("item");
		String price = request.getParameter("price");
		Cookie cookies[] = request.getCookies(); 
		String cname = "";
		String cvalue = "";
		String[] items;
		String[] itemDetails;
		int flag = 0;
		int count = 0;
		int total = 0;
		int quan,pri;
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
					items = cvalue.split("-");
					for(int j=0;i<cvalue.length();i++)
						if(cvalue.charAt(i)=='-') count++;
					count++;
					for(int j=0;j<count;j++){
						if(items[j].contains(item)){
							itemDetails = items[j].split("\\+");
							cvalue = cvalue.replaceFirst(itemDetails[2],qty);
						}
					}
					
					c1.setValue(cvalue);
					response.addCookie(c1);
					items = cvalue.split("-");
					for(int j=0;j<count;j++){
						itemDetails = items[j].split("\\+");
						quan = Integer.parseInt(itemDetails[2]);
						pri = Integer.parseInt(itemDetails[3]);
						total += (quan*pri);
					}
					
					flag=1;
					break;	
				}		
			}
	    }
		out.println(total);
	}
}