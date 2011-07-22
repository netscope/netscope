package trace;

import extend.wireless.JxBaseNode2;
import kernel.*;

public class JxDbTrace implements JiBaseTrace {

	private String m_rootdir = "/dev/netscope/data/";
	private JxDbConnection m_db = null;
	
	JxDbTrace()
	{
		m_db = new JxDbConnection();
	}
	
	public boolean init()
	{
		// add timestamp into the dbname
		String dbname = m_rootdir + "trace" + "201004120345" + ".trace";
		m_db.openDatabase( dbname);
		
		// Create tables in the database
		
		return false;
	}
	
    public void update()
	{
	}
    
    public void show()
	{
	}
	
	public boolean onNodeChanged( JxBaseNode2 node )
	{
		return true;
	}
	
	/*
	public boolean onEdgeChanged( JxBaseEdge edge )
	{
		return false;
	}
	*/
	
	/*
	public boolean onPacketChanged( JxBasePacket packet )
	{
		return true;
	}
	*/
        
        
      public void opendatabase(String database)                       //打开数据库
      { /*
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
      	   */
      }
      
      
      public void createnodetable()
      { /* 
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
    	  */
       }
    
      
      public void insertnodetable(int TIME,int NODEID,int QUELENGTH)
      { /*  
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
    	  */
       }
      
      
      public void createlinktable()
      {  /* 
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
    	  */
       }

      
     public void insertlinktable(int TIME,String LINKID,int QUELENGTH)
   {   /*
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
	  */
   }
     public void CloseDatabase() {  
    	 /*//关闭数据库
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
         */
     }	
}
