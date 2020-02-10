import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;
import javax.servlet.ServletContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/changeLegsize")
public class LegSize extends HttpServlet {
 
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
 
        response.setContentType("text/html;charset=utf-8");
        PrintWriter writer = response.getWriter();
        try{
            String sql = "UPDATE domashkaMembers SET legsize = ? WHERE id = ?;";
            String url = "jdbc:mysql://localhost/domashka";
            String username = "macaron";
            String password = "123";
            String id = request.getParameter("id");
            int realid = Integer.parseInt(id);
            String legsize = request.getParameter("legsize");
            int reallegsize = Integer.parseInt(legsize);
          
          
            Class.forName("com.mysql.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(url, username, password)){
                PreparedStatement pst = conn.prepareStatement(sql);

                pst.setInt(1,reallegsize);
                pst.setInt(2,realid);
               
                
               
                


                pst.executeUpdate();

                  
                writer.println("Размер изменен");
            }
String path = "/legsizeUspeh.html";
            ServletContext servletContext = getServletContext();
            RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(path);
            requestDispatcher.forward(request, response);
        }
        catch(Exception ex){
            writer.println("Что то пошло не так(");
            writer.println(ex);
        }
        finally {
            writer.close();
        }
    }
}
