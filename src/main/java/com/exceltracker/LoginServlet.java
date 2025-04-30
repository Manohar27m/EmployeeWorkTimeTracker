package com.exceltracker;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        LocalDateTime loginTime = LocalDateTime.now();

        try (Connection conn = DBUtil.getConnection()) {
            String sql = "INSERT INTO user_activity (username, login_time) VALUES (?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setTimestamp(2, Timestamp.valueOf(loginTime));
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        request.getSession().setAttribute("username", username);

        response.sendRedirect("home.jsp");
   

    }
}