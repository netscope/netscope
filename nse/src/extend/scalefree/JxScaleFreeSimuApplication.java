/**
 * 
 */
package extend.scalefree;

import kernel.basetrace.*;
import java.util.*;
import kernel.*;

  public class JxScaleFreeSimuApplication 
  {  
	    
	    JxBaseEngine  m_engine = new JxBaseEngine();
	    JxBaseTraceLoader m_traceLoader = new JxBaseTraceLoader();
	    
	    
	    JxBaseRelationCollection  m_relations =  new JxBaseRelationCollection();
		JxBaseNodeCollection  m_nodes = new JxBaseNodeCollection();
		
		
	   // JxBaseNodeCollection  m_leftnodes = new JxBaseNodeCollection();
	   // JxBaseNodeCollection  m_addednodes = new JxBaseNodeCollection();
		
		ArrayList<JiBaseNode>  leftnodes=new ArrayList();
		ArrayList<JiBaseNode>  addednodes=new ArrayList();

		Random m_random = m_engine.getRandom();
	    
		
		JiBaseNode m_nodeFrom = new JxBaseNode();
		JiBaseNode m_nodeTo = new JxBaseNode();
		
		
		String m_datadir = null;
		
		public void init()
		{  
		   m_engine.setInteraction(new JxBaseInteraction(m_engine));
		   m_engine.setTrace(new JxBaseTrace(m_engine));    
		}
		
		public void open()
		{
	       m_engine.open(); 
		}
			
		public void close()
		{
		   m_engine.close();	
		}
		
	    public void generateNodes(int nodeCount)
	    {
	    	for(int i=0;i<nodeCount;i++)
	    	{
	           int loc_x = m_random.nextInt(100); 
	           int loc_y = m_random.nextInt(100);
	         
	           JiBaseNode node=new JxBaseNode(i,loc_x,loc_y,50,100);
	         
	           m_engine.addNode(node);  
	           m_engine.save(node);
	           
	           leftnodes.add(node);
	    	} 
	    	print1();
	    }
	    
	    public void generateRelations(int relationCount)
	    { 
	    	 m_relations.generate(relationCount);
	    	 m_relations.randomize(); 
	    	 m_engine.setRelations(m_relations); 
	    	 
	    	 m_leftnodes = (JxBaseNodeCollection)JxBaseEngine.getNodes();
	    	 print1();
	    	 
	    	 m_leftnodes.randomize();
	    	 print1();
	    	 
		     for(int i=0;i<m_relations.count();i++)
			 { 
		    	JxBaseRelation relation=(JxBaseRelation)JxBaseEngine.getRelations().get(i);	
		    	 
		    	  if(i==0)
		          {
			    	    m_nodeFrom = m_leftnodes.get(0);
			    	    m_nodeTo=m_leftnodes.get(1);
			    	  
			    	    relation.setNodeFrom( m_nodeFrom );
			    	    relation.setNodeTo( m_nodeTo );
			    	  
			    	    print();
			    	    m_leftnodes.remove( m_nodeFrom );
			    	    m_leftnodes.remove( m_nodeTo );
			    	    print();
			    	    
			    	    m_addednodes.add( m_nodeFrom ); 
			    	    m_addednodes.add( m_nodeTo );   
			    	    print();
	              }
	    	      else 
	    	      { 
			    	    m_nodeFrom  = m_leftnodes.get(0);
			    	    m_nodeTo  = selectnodeto();
			    	  
			    	    relation.setNodeFrom(m_nodeFrom);
			    	    relation.setNodeTo(m_nodeTo);
			    	  
			     	    m_addednodes.add( m_nodeFrom ); 
			     	    m_addednodes.add( m_nodeTo ); 
			     	  
			     	   // print();
			     	    m_leftnodes.remove(m_nodeFrom);
			     	   // print();
		         } 
			    	  m_engine.saveRelation(relation);  
		    }     
	    } 
	    
	    JiBaseNode selectnodeto() 
	    {    	
			int p = m_random.nextInt(m_addednodes.count()); 
			return m_addednodes.get(p);  
	    }
	    
	    public void print()
	    {
		   System.out.println(JxBaseEngine.getNodes().count());
	    }
	    
	    public void print1()
	    {  
	       System.out.println(" attention please£¡");
		   System.out.println(JxBaseEngine.getNodes());
	    }
	   
	   
	    public void run()
	    {           
		     m_engine.execute(2);		    	   
	    }
	    
        public static void main(String []args)
        {	
    	    JxScaleFreeSimuApplication app=new JxScaleFreeSimuApplication(); 	    
    	    
    	    app.init();
    	    app.open();
            
    	    app.generateNodes(10);
    	    app.generateRelations(9);
    	    
    	    app.run();
    	    app.print();
    	    
    	    app.m_traceLoader.loadnodes();
    	    app.m_traceLoader.loadrelations();
    	    
            System.out.println("sucess!");
	    }     	   		
 }
	

