package extend.lattice;
import kernel.JxBaseTrace;

/**
 * @author lipengfei
 *
 */
public class Jxlatticemain {
	
	Jxlatticeapp m_app = new Jxlatticeapp(); 
	JxBaseTrace m_trace = new JxBaseTrace();
	
	int m_nodeCount = 1000; 
	
	String m_beginTime=null;
	String m_endTime=null;
	
	public void init()
	{
		m_app.open();
		
		System.out.println("begin at:"+m_trace.getSystemTime());
	}
	
	public void run1()  
	{   	
		System.out.println("load is 10");
		
		this.init();
		
		m_app.generateNodes(m_nodeCount,10);
		m_app.generateTopo(m_nodeCount,0.5);
		
		m_app.interact(10000);
		
		m_app.clearNodes();
		m_app.clearRelations();
		
		this.close();
	}
	
	public void run3()
	{
		System.out.println("load is 30");
		
		this.init();
		
		m_app.generateNodes(m_nodeCount,30);
		m_app.generateTopo(m_nodeCount,0.5);
		
		m_app.interact(10000);
		
		m_app.clearNodes();
		m_app.clearRelations();
		
		this.close();
	}
	
	public void run5()
	{
		System.out.println("load is 50");
		
		this.init();
		
		m_app.generateNodes(m_nodeCount,50);
		m_app.generateTopo(m_nodeCount,0.5);
		
		m_app.interact(10000);
		
		m_app.clearNodes();
		m_app.clearRelations();
		
		this.close();
	}
	
	public void run7()
	{
		System.out.println("load is 70");
		
		this.init();
		
		m_app.generateNodes(m_nodeCount,70);
		m_app.generateTopo(m_nodeCount,0.5);
		
		m_app.interact(10000);
		
		m_app.clearNodes();
		m_app.clearRelations();
		
		this.close();
	}
	
	public void run9()
	{
		System.out.println("load is 90");
		
		this.init();
		
		m_app.generateNodes(m_nodeCount,90);
		m_app.generateTopo(m_nodeCount,0.5);
		
		m_app.interact(10000);	
		
		this.close();
	}
	
	public void close()
	{                 
	    m_app.close();
	    
	    m_app.clearNodes();
		m_app.clearRelations();
	    
	    System.out.println("end at:"+m_trace.getSystemTime());
	}
	
    public static void main(String []args)
    {	
    	Jxlatticemain m=new Jxlatticemain();
    	
    	//m.run1();
    	//m.run3();
    	//m.run5();
    	//m.run7();
    	m.run9();
    } 
}
