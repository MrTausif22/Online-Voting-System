# Online-Voting-System
<!DOCTYPE html> 
<html> 
<head> 
    <title>Login</title> 
</head> 
<body> 
    <h2>Login</h2> 
    <form action=”LoginServlet” method=”post”> 
        <label>Username:</label> 
        <input type=”text” name=”username” required><br> 
        <label>Password:</label> 
        <input type=”password” name=”password” required><br> 
        <button type=”submit”>Login</button> 
    </form> 
    <p style=”color:red;”><%= request.getParameter(“error”) %></p> 
</body> 
</html> 

Import java.io.*; 
Import java.sql.*; 
Import javax.servlet.*; 
Import javax.servlet.http.*; 
 
Public class LoginServlet extends HttpServlet { 
    Protected void doPost(HttpServletRequest request, HttpServletResponse response) throws 
ServletException, IOException { 
        String username = request.getParameter(“username”); 
        String password = request.getParameter(“password”); 
  Try { 
            // Database connection 
            Class.forName(“com.mysql.cj.jdbc.Driver”); 
            Connection conn = DriverManager.getConnection(“jdbc:mysql://localhost:3306/OnlineVoting”, 
“root”, “password”); 

   String query = “SELECT * FROM Users WHERE username = ? AND password = ?”; 
            PreparedStatement ps = conn.prepareStatement(query); 
            Ps.setString(1, username); 
            Ps.setString(2, password); 
            ResultSet rs = ps.executeQuery();
             If (rs.next()) {
             HttpSession session = request.getSession(); 
                Session.setAttribute(“username”, username); 
                Response.sendRedirect(“vote.jsp”); 
            } else { 
                Response.sendRedirect(“login.jsp?error=Invalid username or password”); 
            }
              Conn.close(); 
        } catch (Exception e) { 
            e.printStackTrace(); 
        } 
    } 
}

Import java.io.*; 
Import java.sql.*; 
Import javax.servlet.*; 
Import javax.servlet.http.*; 
Public class VoteServlet extends HttpServlet { 
    Protected void doPost(HttpServletRequest request, HttpServletResponse response) throws 
ServletException, IOException { 
        String candidateId = request.getParameter(“candidateId”); 
        HttpSession session = request.getSession(); 
        String username = (String) session.getAttribute(“username”); 
  Try { 
            // Database connection 
            Class.forName(“com.mysql.cj.jdbc.Driver”);
          Connection conn = DriverManager.getConnection(“jdbc:mysql://      Ps.setString(2, password);             
ResultSet rs = ps.executeQuery();              If (rs.next()) {                 HttpSession session = 
request.getSession();                 Session.setAttribute(“username”, username);                 
Response.sendRedirect(“vote.jsp”);             } else {                 
Response.sendRedirect(“login.jsp?error=Invalid username or password”);             }              Conn.close();         
} catch (Exception e) {             e.printStackTrace();         }     } }   Import java.io.*; Import java.sql.*; Iemport 
javax.servlet.*; Import javax.servlet.http.*;  Public class VoteServlet extends HttpServlet {     Protected 
void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, 
IOException {         String candidateId = request.getParameter(“candidateId”);         HttpSession session = 
request.getSession();         String username = (String) session.getAttribute(“username”);          Try {             
// Database connection             Clas nt(updateUserQuery); 

   userPs.setString(1, username); 
            userPs.executeUpdate();
              response.sendRedirect(“vote.jsp?success=Vote submitted successfully!”); 
            conn.close(); 
        } catch (Exception e) { 
        e.printStackTrace(); 
} 
} 
}
 Users Table 
CREATE TABLE Users ( 
user_id INT AUTO_INCREMENT PRIMARY KEY, 
username VARCHAR(50) UNIQUE NOT NULL, 
password VARCHAR(100) NOT NULL, 
has_voted BOOLEAN DEFAULT FALSE 
); 

- Candidates Table 
CREATE TABLE Candidates ( 
candidate_id INT AUTO_INCREMENT PRIMARY KEY, 
name VARCHAR(50) NOT NULL, 
votes INT DEFAULT 0 
); 
 Votes Table (Optional, for audit trail) 
CREATE TABLE Votes ( 
vote_id INT AUTO_INCREMENT PRIMARY KEY, 
user_id INT NOT NULL, 
candidate_id INT NOT NULL,
 vote_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP, 
    FOREIGN KEY (user_id) REFERENCES Users(user_id), 
    FOREIGN KEY (candidate_id) REFERENCES Candidates(candidate_id) 
); 
<!DOCTYPE html> 
<html> 
<head> 
    <title>Vote</title> 
</head> 
<body> 
    <h2>Vote for Your Candidate</h2> 
    <form action="VoteServlet" method="post"> 
        <% 
            try { 
                Class.forName("com.mysql.cj.jdbc.Driver"); 
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/OnlineVoting", 
"root", "password")
 String query = "SELECT * FROM Candidates"; 
                PreparedStatement ps = conn.prepareStatement(query); 
                ResultSet rs = ps.executeQuery(); 
 while (rs.next()) { 
        %> 
                    <input type="radio" name="candidateId" value="<%= rs.getInt("candidate_id") %>"> 
                    <%= rs.getString("name") %><br> 
        <% 
                } 

                conn.close(); 
} catch (Exception e) { 
e.printStackTrace(); 
} 
%> 
<button type="submit">Submit Vote</button> 
</form> 
<p style="color:red;"><%= request.getParameter("error") %></p> 
<p style="color:green;"><%= request.getParameter("success") %></p> 
</body> 
</html>


               
