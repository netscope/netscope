package kernel;

public class JxBaseTest {
	void test1()
	{
	JxBaseEngine engine = new JxBaseEngine();
	engine.setTrace( new JxBaseTrace(engine, "/temp/expr/") )
	engine.setNodes( new JxBaseNodeCollection(engine, 10000) );
	engine.setRelations( new JxBaseRelationCollection(engine, engine) );
	engine.setInteraction( new JxBaseInteraction(engine) );
	engine.execute( 100000 );
	}

	void test2()
	{
		JxBaseEngine engine = new JxBaseEngine();
		engine.setTrace( new JxBaseTrace(engine, "/temp/expr/") )
		trace.load( engine.getNodes() );
		trace.load( engine.getRelations() );
		engine.setInteraction( new JxBaseInteraction(engine) );
		engine.execute( 100000 );
	}
}
