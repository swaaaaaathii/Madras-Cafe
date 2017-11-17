import bean.food;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

public class itemcontroller extends HttpServlet
{
  public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException
  {
      response.setContentType("text/html");
      PrintWriter out = response.getWriter();
      String it = request.getParameter("item");
      String c = request.getParameter("category");
      fooddao f = new fooddao();
      String itemDetails = f.retrieveItemDetails(c,it);
	  int count = 0;
	  String[] details;
	  String[] subs;
	  for(int i=0;i<itemDetails.length();i++)
		  if(itemDetails.charAt(i)=='+') count++;
	  
	  count++;
      if(itemDetails!=null)
      {
		subs = itemDetails.split("\\+");
		for(int i=0;i<count;i++){
			details=subs[i].split("-");
			out.println("<div id = \""+i+"\">");
			out.println("<h2>"+details[0]+"</h2>");
			out.println("<h3>"+details[1]+"</h3>");
			out.println("<h3>Rs.<div id=\""+i+"price\" class=\"price\">"+details[2]+"</div></h3>");
			out.println("<input type=\"button\" value=\"-\" onclick=\"dec("+i+")\"><input value=\"0\" type = \"text\" id=\""+i+"qty\" class=\"qty\"><input type=\"button\" value=\"+\" onclick=\"inc("+i+")\"><input type=\"button\" id=\""+details[0]+"\" onclick=\"addToCart(this,"+i+")\"value=\"Add to Cart\"></div><br>");
		}
      }
      else
      {
        out.println("Unavailable");
      }
    out.close();
  }
}
      