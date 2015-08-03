package dao;
 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
 
public class Database{


public Connection getConnection() throws Exception
{
try
{
String connectionURL = "jdbc:mysql://localhost:3306/test";
Connection connection = null;
Class.forName("com.mysql.jdbc.Driver").newInstance();
connection = DriverManager.getConnection(connectionURL, "user", "");
return connection;
} catch (Exception e)
{
throw e;
}
 
}
 
}
