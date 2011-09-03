
package extend.scalefree;

import kernel.*;

  /**
   * Can JxScaleFreeSimulation directly extends the JxBaseEngine? It's much easier than this,I think?;
   * @author LiPengFei
   *
   */
  public class JxScaleFreeSimuApplication  
  { 
	    
	    JxScaleFreeRelationCollection  m_relations = new JxScaleFreeRelationCollection();
		JxScaleFreeNodeCollection  m_nodes = new JxScaleFreeNodeCollection();
	    
		
		JxScaleFreeRelation  m_relation = new JxScaleFreeRelation();
		JxScaleFreeNode  m_node = new JxScaleFreeNode();
		JxScaleFreeTrace  m_trace = new JxScaleFreeTrace(); 
		JxScaleFreeInteraction m_interaction = new JxScaleFreeInteraction();
		
		
	    JxBaseEngine  m_engine = new JxBaseEngine();
	    
	    
		public void open()
		{
	       m_trace.open(); 
		}	
		public void close()
		{
		   m_trace.close();	
		}
	  
		
	    public void run(int expertime)
	    {           
		     for(int i=0;i<expertime;i++)
		     {
		    	 for(int j=0;j<m_relations.count();i++)
		    	 {
		    		 m_interaction.interact(i, m_relations.get(j), m_trace);	
		    	 }    
		     }	    	   
	    }
	    
	    
	    public void generateNodes(int count)
	    {
	    	m_nodes.generate(count);
	    	setNodes(m_nodes);
	    }    
	    public void generateRelations(int count)
	    {
	    	m_relations.generate(count);
	    	setRelations(m_relations);
	    }
	    
	    
	    public void saveNodes()
	    {
	    	m_trace.save(m_nodes);
	    }
	    public void saveRelations()
	    {
	    	m_trace.save(m_relations);
	    }
	    
	    
        public static void main(String []args)
        {	
    	    JxScaleFreeSimuApplication app=new JxScaleFreeSimuApplication(); 	    
    	    
    	    app.open();
    	    app.generateNodes(10);
    	    app.generateRelations(9);
    	   
    	    app.saveNodes();
    	    app.saveRelations();
    	    app.run(10);
    	    app.close();
    	    	   	   
    	    System.out.println("sucess!");
	    }  
        
        
        public void setNodes(JxScaleFreeNodeCollection nodes)
        {
        	m_nodes = nodes;
        }
		public  JxScaleFreeNodeCollection getNodes()
		{
			return m_nodes;
		}
		
		
		public void setRelations(JxScaleFreeRelationCollection relations)
		{ 
			m_relations = relations;
		}
		
		public JxScaleFreeRelationCollection getRelations()
		{
			return m_relations;
		}	
	 
 }
	

