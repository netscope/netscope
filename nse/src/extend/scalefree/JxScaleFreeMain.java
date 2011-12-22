package extend.scalefree;

public class JxScaleFreeMain 
{
	JxScaleFreeApplication m_app=new JxScaleFreeApplication(); 
	int m_nodeCount=0; 
	int m_relationCount=0;
	
	public void init()
	{
		m_app.open();
	}
	
	public void run()
	{	  	    
	    m_relationCount=m_nodeCount-1;
	   // m_app.generateRelations(m_relationCount); 
	    m_app.saveRelations(); //this method can be insert into generateRelations  
	  //m_app.run1(10000);
    }
	
	public void run1()  //current experiments
	{   
		m_nodeCount=10000;
		m_app.generateNodes(m_nodeCount);
		m_relationCount=59964;
		m_app.generateTopo(m_relationCount);
	    m_app.interact(5000000);
	}
	
	public void close()
	{
	    m_app.close();
	    System.out.println("success!");
	}
	
    public static void main(String []args)
    {	
    	JxScaleFreeMain m=new JxScaleFreeMain();
    	
    	m.init();
    	m.run1();
    	m.close();
    }        
}
