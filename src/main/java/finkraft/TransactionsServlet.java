package finkraft;
import java.io.*;
import java.sql.*;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/transactions")
public class TransactionsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String DB_URL = "jdbc:mysql://localhost:3306/finkraft";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "bhargavi@123";

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
            String sql = "SELECT * FROM transactions";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            List<String> transactions = new ArrayList<>();
            while (rs.next()) {
                transactions.add(rs.getString("transaction_id") + ", " + rs.getString("customer_name") + ", "
                        + rs.getString("transaction_date") + ", " + rs.getBigDecimal("amount") + ", "
                        + rs.getString("status") + ", " + rs.getString("invoice_url"));
            }
            response.getWriter().println(transactions);
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to fetch transactions");
        } finally {
            try {
                if (rs != null)
                    rs.close();
                if (pstmt != null)
                    pstmt.close();
                if (conn != null)
                    conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // Implement PUT and DELETE methods similarly
}
