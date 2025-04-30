* Employee Work Time Tracker *
  --------------------------
  
A simple web-based application to track employee working hours based on login and logout timestamps.

💡 Project Overview
-----------------------

This project records the total time an employee spends on work in a day. When an employee logs in using their email ID, the system stores the login time. On logout or browser/tab close, the logout time is captured automatically using JavaScript and the working duration is calculated.

The project is designed with basic UI — just a username input field and a submit button. It integrates with Google Sheets using an embedded iframe to simulate working on a project.

🚀 Features
-------------

Capture and store login time.

Automatically detect logout (even on tab/browser close) and store logout time.

Calculate session-based working hours.

Store data in MySQL for historical reference.

Simple and minimal interface for user interaction.

📂 Project Structure
---------------------

LoginServlet.java: Handles login and records login time.

LogoutServlet.java: Automatically detects logout, calculates session time, and updates database.

home.jsp: Embedded Google Sheet page that triggers logout time storage on close.

login.jsp: Basic login form.

DBUtil.java: Database connection utility.

🛠️ Technologies Used
----------------------

Backend: Java, JDBC, Servlets, JSP, Tomcat
Frontend: HTML, JSP, JavaScript
Database: MySQL
IDE: Eclipse

📁 Database Table (user_activity)

id	username	login_time	logout_time	total_working_hours
📌 How It Works
User logs in using their email → login time is stored.

User works on the embedded Google Sheet.

When the user closes the browser or logs out → logout time is stored and session hours are calculated.

Total working hours are updated in the database.

🔮 Future Development
----------------------

Add user authentication with password protection and role-based access (admin/employee).

Enable project-based tracking and reporting.

Generate reports (PDF/Excel) of user working hours.

Create an admin dashboard for viewing and managing user logs.

Implement idle time detection with JavaScript for accurate tracking.

Add email notifications for daily summaries or alerts.

Redesign the frontend using Bootstrap/Tailwind for better UI.

Integrate a mobile app for Android/iOS tracking.

Visualize data using charts and analytics dashboards.

Host the application on cloud platforms for production access.

🔗 GitHub Repository :
https://github.com/Manohar27m/EmployeeWorkTimeTracker
