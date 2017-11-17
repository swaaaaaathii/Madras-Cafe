import bean.food;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

public class foodcontroller extends HttpServlet
{
  public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException
  {
      response.setContentType("text/html");
      PrintWriter out = response.getWriter();
      String c = request.getParameter("category");
      fooddao f = new fooddao();
      String categorylist = f.retrieveCategory(c);
	  int count = f.getCount(c);
	  String[] categories;
      if(categorylist!=null)
      {
        categories = categorylist.split("\\+");
		for(int i=0;i<count;i++){
			out.println("<input type=\"button\" onclick=\"getDetails(this)\" value=\""+categories[i]+"\" class=\"button3\"></input><br>");
		}
      }
      else
      {
        out.println("Unavailable");
      }
    out.close();
  }
}
      