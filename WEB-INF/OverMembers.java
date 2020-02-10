import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/showover")
public class OverMembers extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=utf-8");

        PrintWriter writer = response.getWriter();
        try {
            String sql = "select * from domashkaMembers where age >  21;";
            String url = "jdbc:mysql://localhost/domashka";
            String username = "macaron";
            String password = "123";
            ArrayList <String> list = new ArrayList<>();


            Class.forName("com.mysql.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(url, username, password)) {
                PreparedStatement pst = conn.prepareStatement(sql);
                ResultSet rs = pst.executeQuery();

                while (rs.next()) {
                    list.add("   Id:" + rs.getInt("id") + "   Имя:" + rs.getString("name") + "    Возраст:" + rs.getInt("age") + "    Размер ноги:" + rs.getInt("legsize") + "     Пол:"+ rs.getString("sex") + "     Статус:" + rs.getString("status"));

                }
                writer.println("Старше 21 года: ");
                for (String listok : list)
                    writer.println(listok);

            }
        } catch (Exception ex) {
            writer.println("Что то пошло не так");
            writer.println(ex);
        } finally {
            writer.close();
        }
    }
}
