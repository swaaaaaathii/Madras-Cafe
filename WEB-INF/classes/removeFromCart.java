import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.util.*;

public class removeFromCart extends HttpServlet
{
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException   
	{
		response.setContentType("text/html");
        PrintWriter out = response.getWriter();
		String name = request.getParameter("name");
		String item = request.getParameter("item");
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
					List<String> list = new ArrayList(Arrays.asList(items));
					for(int j=0;i<cvalue.length();i++)
						if(cvalue.charAt(i)=='-') count++;
					count++;
					for(int j=0;j<count;j++){
						if(items[j].contains(item)){
							list.remove(items[j]);
							count--;
						}
					}
					items = list.toArray(items);
					cvalue = "";
					if(count!=0){
						for(int a=0;a<count;a++){
							if(a!=count-1)
								cvalue += items[a]+"-";
							else
								cvalue += items[a];
						}
					}
					else
						cvalue = "empty";
					c1.setValue(cvalue);
					response.addCookie(c1);
					flag=1;
					break;	
				}		
			}
	    }
	}
}