package com.exceltracker;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/logout-time")
public class LogoutServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        String username = (String) request.getSession().getAttribute("username");

        if (username == null) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            System.out.println("Username is null, cannot proceed.");
            return;
        }

        Timestamp logoutTime = new Timestamp(System.currentTimeMillis());

        try (Connection conn = DBUtil.getConnection()) {

            // Step 1: Update logout_time for the latest session
            String updateLogoutSql = "UPDATE user_activity SET logout_time = ? " +
                    "WHERE username = ?";
            PreparedStatement updateLogoutStmt = conn.prepareStatement(updateLogoutSql);
            updateLogoutStmt.setTimestamp(1, logoutTime);
            updateLogoutStmt.setString(2, username);
            int rows = updateLogoutStmt.executeUpdate();
//            System.out.println(rows);
            

            // Step 2: Fetch login and logout times for that session
            String fetchTimesSql = "SELECT id, login_time, logout_time FROM user_activity " +
                    "WHERE username = ? ORDER BY login_time DESC LIMIT 1";
            PreparedStatement fetchTimesStmt = conn.prepareStatement(fetchTimesSql);
            fetchTimesStmt.setString(1, username);
            ResultSet rs = fetchTimesStmt.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("id");
                Timestamp loginTime = rs.getTimestamp("login_time");
                Timestamp logout = rs.getTimestamp("logout_time");

                if (loginTime != null && logout != null) {
                    long millis = logout.getTime() - loginTime.getTime();
                    double sessionHours = millis / 1000.0 / 3600.0;
                    double totalHours = 0.0;
                    double updatedTotal = totalHours + sessionHours;
                    updatedTotal = Math.round(updatedTotal * 10000.0) / 10000.0;
                    

                    // Step 3: Update working_hours and total_working_hours
                    String updateHoursSql = "UPDATE user_activity SET total_working_hours = ? WHERE id = ?";
                    PreparedStatement updateHoursStmt = conn.prepareStatement(updateHoursSql);
                    updateHoursStmt.setDouble(1, updatedTotal);
                    updateHoursStmt.setInt(2, id);
                    updateHoursStmt.executeUpdate();

//                    System.out.printf("Session duration: %.2f hrs, Total updated: %.2f",
//                            sessionHours, updatedTotal);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
