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

@WebServlet("/add")
public class AddUser extends HttpServlet {
 
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
 
        response.setContentType("text/html;charset=utf-8");
        PrintWriter writer = response.getWriter();
        try{
            String sql = "INSERT INTO domashkaMembers (name,age,legsize,sex,status) VALUES (?,?,?,?,?);";
            String url = "jdbc:mysql://localhost/domashka";
            String username = "macaron";
            String password = "123";
            String name = request.getParameter("name");
            String age = request.getParameter("age");
            int realage =Integer.parseInt(age);
            String legsize = request.getParameter("legsize");
            int reallegsize =Integer.parseInt(legsize);
            String sex = request.getParameter("sex");
            String status = request.getParameter("status");
          
            Class.forName("com.mysql.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(url, username, password)){
                PreparedStatement pst = conn.prepareStatement(sql);

                pst.setString(1, name);
                pst.setInt(2, realage);
                pst.setInt(3, reallegsize);
                pst.setString(4, sex);
                pst.setString(5, status);
                
               
                


                pst.executeUpdate();

                  
                writer.println("Успешно добвлен");
            }
            String path = "/addUspeh.html";
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
