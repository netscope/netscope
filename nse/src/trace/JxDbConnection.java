package trace;
import  java.sql.Connection;
import  java.sql.DriverManager;
import  java.sql.SQLException;
import  java.sql.Statement;
import  java.sql.ResultSet;

public class JxDbConnection {

	private String m_database;
	private Connection m_connection = null;
	private Statement m_statement = null;
	
	// Open the database at "%APPLICATIOIN_ROOT%/data/" by default. If the database
	// already exists there, then open it directly. If it doesn't exist, then create
	// it and open it.
	public boolean openDatabase( String database )
	{
		m_database = "D:\\dev\\netscope\\data";
		m_database += "database";
		return false;
	}
	
	public void closeDatabase()
	{
		//m_connection.Close();
	}
	
	public boolean startTransaction()
	{
		return false;
	}
	
	public void commit()
	{
	}
	
	public void rollback()
	{
	}
	
	// Execute an sql statement. This statement doesn't return dataset, such as 
	// "CREATE TABLE" and "INSERT INTO".
	public boolean execute( String sql )
	{
		return false;
	}
	
	// Execute an sql statement. This statement returns an dataset, such as "SELECT".
	public ResultSet select( String sql )
	{
		return null;
		
	}
}  


