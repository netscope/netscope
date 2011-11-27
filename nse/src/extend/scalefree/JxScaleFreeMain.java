package extend.scalefree;

public class JxScaleFreeMain {
	
    public static void main(String []args)
    {	
	    JxScaleFreeApplication app=new JxScaleFreeApplication(); 	    
	    
	    app.open();
        
	    app.generateNodes(1000);
	    app.saveNodes();
	    
	    app.generateRelations(999);
	    app.saveRelations();
	     
	    app.run(1);
 
	    app.close();
	   
	    System.out.println("success!");
    }     
}
