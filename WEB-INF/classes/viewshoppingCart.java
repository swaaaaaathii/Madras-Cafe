import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.util.*;

public class viewShoppingCart extends HttpServlet
{
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException   
	{
		response.setContentType("text/html");
        PrintWriter out = response.getWriter();
		String name = request.getParameter("name");
		Cookie cookies[] = request.getCookies(); 
		String cname = "";
		String cvalue = "";
		String[] items;
		String[] itemDetails;
		int qty,price,total=0;
		int count=0;
		int flag = 0;
		String message = "Cart unavailable";
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
					if(cvalue.contentEquals("empty")){
						flag=0;
						message = "Cart is empty";
						break;
					}
					items = cvalue.split("-");
					for(int j=0;i<cvalue.length();i++)
						if(cvalue.charAt(i)=='-') count++;
					count++;
					out.println("<div align=\"center\" id='cart'>");
					for(int j=0;j<count;j++){
						itemDetails = items[j].split("\\+");
						qty = Integer.parseInt(itemDetails[2]);
						price = Integer.parseInt(itemDetails[3]);
						total += (qty*price);
						out.println("<div id=\""+j+"\">");
						out.println("<h3>Item : "+itemDetails[1]+"</h3>");
						out.println("<h4>Category : "+itemDetails[0]+"</h4>");
						out.println("<h4>Quantity : <input type=\"button\" value=\"-\" onclick=\"dec("+j+")\"><input type=\"text\" value=\""+itemDetails[2]+"\" id=\""+j+"qty\" class='qty'><input type=\"button\" value=\"+\" onclick=\"inc("+j+")\"><input type=\"button\" id='"+itemDetails[1]+"' onclick=\"update(this,"+j+")\" value=\"update\"><input type=\"button\" id='"+itemDetails[1]+"del' onclick=\"del(this)\" value=\"x\"></h4>");
						out.println("<h4>Price : Rs.<div id='"+j+"price' class='price'>"+itemDetails[3]+"</div></h4>");
						out.println("</div>");
					}
					out.println("<div id='total'>Total Amount : Rs."+total+"</div></div>");
					flag=1;
					break;	
				}	
			}
	    }
		if(flag==0){
			out.println(message);
		}
	}
}