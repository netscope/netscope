package extend.scalefree;
import kernel.*;
public class JxScaleFreeMain 
{
	JxScaleFreeApplication m_app = new JxScaleFreeApplication(); 
	JxBaseTrace m_trace = new JxBaseTrace();
	
	int m_nodeCount = 0; 
	int m_relationCount = 0;
	
	String m_beginTime=null;
	String m_endTime=null;
	
	
	public void init()
	{
		m_app.open();
		
		m_beginTime=m_trace.getSystemTime();
		System.out.println("begin at:"+m_beginTime);
	}
	
	public void run()
	{	  	    
	    m_relationCount=m_nodeCount-1;
	  //m_app.generateRelations(m_relationCount); 
	    m_app.saveRelations(); //this method can be insert into generateRelations  
	  //m_app.run1(10000);  
    }
	
	public void run1()  //current experiments
	{   
		m_nodeCount=1000;
		
		m_app.generateNodes(m_nodeCount);
		
		//m_relationCount=5964;
		m_app.generateTopo1(999);
	    //m_app.generateTopo3(2991);
	    //m_app.generateTopo5(4975);
	    //m_app.generateTopo7(6951);
	    // m_app.generateTopo9(8919);
		
        //m_app.interact1(1000);//the interact of the neighbor node
       
        //m_app.interact(1000); the random packet exchange
	}
	
	public void close()
	{                 
	    m_app.close();
	    
	    m_endTime=m_trace.getSystemTime();
	    
	    System.out.println("end at:"+m_endTime);
	    
	    int beginTime = Integer.parseInt(m_beginTime);
	    int endTime = Integer.parseInt(m_endTime);
	    int totalTime=endTime-beginTime;
	    
	    System.out.println("total time is:"+Integer.toString(totalTime));
	}
	
    public static void main(String []args)
    {	
    	JxScaleFreeMain m=new JxScaleFreeMain();
    	
    	m.init();
    	m.run1();
    	m.close();
    }        
}
