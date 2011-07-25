package kernel;

public class JxBaseTest {

	/**
	 * Create a network simulator from scratch.
	 */
	void test1()
	{
		JxBaseEngine engine = new JxBaseEngine();
		
		engine.setNodes(new JxBaseNodeCollection(engine, 10000));
		engine.setRelations(new JxBaseRelationCollection(engine, engine.getNodes()));
		engine.setInteraction(new JxBaseInteraction(engine));
		engine.setTrace(new JxBaseTrace(engine, "/temp/expr/20110722-124512-01"));
		engine.execute(10000);
	}

	/**
	 * Reload the initial parameters from saved network trace.
	 */
	void test2() 
	{
		JxBaseEngine engine = new JxBaseEngine();

		engine.setTrace( new JxBaseTrace(engine, "/temp/expr/"));
		engine.m_trace.load( engine.getNodes() );
		trace.load( engine.getRelations() );
		engine.setInteraction( new JxBaseInteraction(engine) );
		engine.execute( 100000 );

		engine.restore( "/temp/expr/20110722-124512-01" );
		engine.setInteraction(new JxBaseInteraction(engine));
		engine.execute(10000);
	}
}
