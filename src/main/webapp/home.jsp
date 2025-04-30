<%
    String username = (String) session.getAttribute("username");
    if (username == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>Google Sheet</title>
</head>
<body>
    <iframe src="https://docs.google.com/spreadsheets/d/1m-srzhgDWWHRNOd7lszYGvke34xijiqmdU2zZ8mKWx4/edit?gid=0#gid=0"
            width="100%" height="800px"></iframe>

    <!-- DEBUGGING PURPOSE ONLY -->
    
    <script>
    window.addEventListener("beforeunload", function () {
        const data = new Blob(
            ["username=" + encodeURIComponent("<%= username %>")],
            { type: 'application/x-www-form-urlencoded' }
        );
        navigator.sendBeacon("<%= request.getContextPath() %>/logout-time", data);
    });
</script>
    
</body>
</html>
