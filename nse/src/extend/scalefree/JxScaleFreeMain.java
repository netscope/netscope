package extend.scalefree;

public class JxScaleFreeMain {
	
    public static void main(String []args)
    {	
	    JxScaleFreeApplication app=new JxScaleFreeApplication(); 	    
	    
	    app.open();
        
	    app.generateNodes(10);
	    app.generateRelations(9);
	    
	    app.run(10);

	    app.close();
	   
	    System.out.println("sucess!");
    }     
    


}
