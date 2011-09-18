package extend.scalefree;

public class JxScaleFreeMain {
	
    public static void main(String []args)
    {	
	    JxScaleFreeApplication app=new JxScaleFreeApplication(); 	    
	    
	    app.open();
        
	    app.generateNodes(1000);
	    app.generateRelations(999);
	    
	    app.saveNodes();
	    app.saveRelations();
	    
	    app.run(10);
 
	    app.close();
	   
	    System.out.println("sucess!");
    }     
}
