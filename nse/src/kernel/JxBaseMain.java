package kernel;

/*
 * @modified by zhangwei on 2011.09.05
 * - Revision
 */
public class JxBaseMain {
	
	/**
	 * Demostrate how to assemble the kernel component into an full-functional 
	 * simulator.
	 *  
	 * @param args
	 */
	public static void main(String []args)
	{
		JxBaseMain test=new JxBaseMain();
		test.test1();
		test.test2();
		test.test3();
	} 

	void test1()
	{
		JxBaseApplication simu = new JxBaseApplication();
		
		simu.setNodes(new JxBaseNodeCollection(simu, 10)); 
		simu.setRelations(new JxBaseRelationCollection(simu, simu.getNodes()));
		simu.setInteraction(new JxBaseInteraction(simu));
		simu.setTrace(new JxBaseTrace(simu, "/temp/expr/20110722-124512-01"));
		
		simu.execute(10000);
	}

	/**
	 * Reload the initial parameters from saved network trace.
	 */
	void test2() 
	{   
		JxBaseApplication simu = new JxBaseApplication();
        
		simu.setTrace( new JxBaseTrace(simu, "/temp/expr/"));
		
		simu.load( simu.getNodes() );
		simu.load( simu.getRelations() );
		simu.setInteraction( new JxBaseInteraction(simu) );
		simu.execute( 100000 );
       
		/** restore */
		simu.restore( "/temp/expr/20110722-124512-01" );
	}
	
	void test3()
	{
		String traceclass, nodesclass, relationsclass, interactionclass;
		
		traceclass = "nse.kernel.JxBaseTrace";
		nodesclass = "nse.kernel.JxBaseNodesCollection";
		relationsclass = "nse.kernel.JxBaseRelationsCollection";
		interactionclass = "nse.kernel.JiBaseInteraction";
		
		JxBaseApplication simu = new JxBaseApplication();
		simu.open( nodesclass, relationsclass, interactionclass, traceclass );
		simu.execute(10000);
	}
	
}
