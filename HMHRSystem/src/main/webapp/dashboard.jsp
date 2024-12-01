<%@ page session="true" language="java" %>
<%
    String username = (String) session.getAttribute("username");
    if (username == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dashboard</title>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f7fc;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        .container {
            width: 100%;
            max-width: 800px;
            background-color: #fff;
            padding: 30px;
            border-radius: 8px;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
        }
        h2 {
            font-size: 24px;
            color: #333;
            text-align: center;
            margin-bottom: 30px;
        }
        ul {
            list-style: none;
            padding: 0;
            margin: 0;
            display: grid;
            grid-template-columns: 1fr 1fr;
            gap: 20px;
        }
        li {
            background-color: #4CAF50;
            color: white;
            border-radius: 5px;
            text-align: center;
            transition: background-color 0.3s ease;
        }
        li:hover {
            background-color: #45a049;
        }
        a {
            display: block;
            padding: 15px;
            text-decoration: none;
            color: white;
            font-size: 18px;
        }
        a:hover {
            color: #f4f4f9;
        }
        .logout {
            grid-column: span 2;
            background-color: #f44336;
        }
        .logout:hover {
            background-color: #e53935;
        }
        @media (max-width: 600px) {
            ul {
                grid-template-columns: 1fr;
            }
        }
    </style>
</head>
<body>

    <div class="container">
        <h2>Welcome, <%= username %></h2>

        <ul>
            <li><a href="search.jsp">Search</a></li>
            <li><a href="shop.jsp">Shop</a></li>
            <li><a href="SendMessage.jsp">Messages</a></li>
            <li><a href="connections.jsp">Connections</a></li>
            <li class="logout"><a href="LogoutServlet">Logout</a></li>
        </ul>
    </div>

</body>
</html>
