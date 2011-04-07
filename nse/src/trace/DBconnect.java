package nse.cellular;
import  java.sql.Connection;
import  java.sql.DriverManager;
import  java.sql.SQLException;
import  java.sql.Statement;
//import  java.sql.ResultSet;
public class DBconnect {
        Connection con=null;
        Statement sta=null;
   //   ResultSet res=null;
        
        
      public void opendatabase(String database)                       //打开数据库
      { 
    	System.out.println("dbconnect.createnodetable() is ok");
    	try{
    		 if (con != null) 
    		 {
                 con.close();
             }
    		Class.forName("org.hsqldb.jdbc.JDBCDriver");                    //添加驱动 
    		String url="jdbc:hsqldb:file"+ database +":shutdown=true"; //
    		con=DriverManager.getConnection(url,"sa","");
    		System.out.println("Connection Successful!");
    		sta=con.createStatement();
    	   } catch(SQLException e)
    	   { 
    		 con=null;
    	     sta=null;
      	   } catch(ClassNotFoundException e)
      	   {
      		 con=null;
      		 sta=null;
      	   }
      	  
      }
      
      
      public void createnodetable()
      {  
    	  try
    	  { 
    		System.out.println("dbconnect.createnodetable() is ok");
    	    assert((con!=null)&&!(con.isClosed()));
    	    assert(sta!=null);
    	    String sql="create table noderecord(time int,nodeid int,queuelength int)";
    	    sta.executeUpdate(sql);
    	  }catch(Exception e)	
    	  {
    		  e.printStackTrace();
    	  }
       }
    
      
      public void insertnodetable(int TIME,int NODEID,int QUELENGTH)
      {   
    	  try
    	  { System.out.println("dbconnect.insertnodetable() is ok");
    	    assert((con!=null)&&!(con.isClosed()));
    	    assert(sta!=null);
    	    String sql="insert into noderecord(time int,nodeid int,queuelength int)";
    	    sta.executeUpdate(sql);
      	  } catch(Exception e)	
    	  {
      		 e.printStackTrace();
    	  }
       }
      
      
      public void createlinktable()
      {   
    	  try
    	  {System.out.println("dbconnect.createlinktable() is ok");
    	    assert((con!=null)&&!(con.isClosed()));
    	    assert(sta!=null);
    	    String sql="create table linkrecord(time int,linkid String ,queuelength int)";
    	    sta.executeUpdate(sql);
    	  }catch(Exception e)	
    	  {
    		e.printStackTrace();
    	  }
       }

      
     public void insertlinktable(int TIME,String LINKID,int QUELENGTH)
   {   
    try
	  {
	    assert((con!=null)&&!(con.isClosed()));
	    assert(sta!=null);
	    String sql="insert into linkrecord(time int,linkid string,queuelength int)";
	    sta.executeUpdate(sql);
	  }catch(Exception e)	
	  {
		  e.printStackTrace();
	  }
   }
     public void CloseDatabase() {                                       //关闭数据库
         try {
            
             if (sta != null) {
                 sta.executeUpdate("SHUTDOWN");                          //SHUTDOWN用法
                 sta.close();
             }
             if (con != null) {
                 con.close();
             }
         } catch (SQLException e) {
             sta = null;
             con = null;
             
         }
     }
}     
      
	


