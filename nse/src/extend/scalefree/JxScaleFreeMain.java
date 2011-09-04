package extend.scalefree;

public class JxScaleFreeMain {
	
    public static void main(String []args)
    {	
	    JxScaleFreeApplication app=new JxScaleFreeApplication(); 	    
	    
	    app.init();
	    app.open();
        
	   // app.generateNodes(10);
	    //app.generateRelations(9);
	    
	    app.run();

	    
	    app.m_traceLoader.loadnodes();
	    app.m_traceLoader.loadrelations(); 	   		

	    app.close();
	   
	    System.out.println("sucess!");
    }     
    


}
