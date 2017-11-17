import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.util.*;

public class viewCart extends HttpServlet
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
		String message = "Cart Unavailable";
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
						flag = 0;
						message = "Cart is empty";
						break;
					}
					items = cvalue.split("-");
					for(int j=0;i<cvalue.length();i++)
						if(cvalue.charAt(i)=='-') count++;
					count++;
					for(int j=0;j<count;j++){
						itemDetails = items[j].split("\\+");
						qty = Integer.parseInt(itemDetails[2]);
						price = Integer.parseInt(itemDetails[3]);
						total += (qty*price);
						int k=j+1;
						out.println("<h2>Item no."+k+"</h2>");
						out.println("<h3>Category : "+itemDetails[0]+"</h3>");
						out.println("<h3>Item name : "+itemDetails[1]+"</h3>");
						out.println("<h3>Quantity : "+itemDetails[2]+"</h3>");
						out.println("<h3>Price(per unit) : "+itemDetails[3]+"</h3><hr>");
					}
					out.println("<h2>Total Amount : "+total+"</h2>");
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