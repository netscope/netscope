package kernel;

import java.lang.reflect.Constructor;
import java.util.*;

public class JxBaseFoundation 
{
    
	static Random rand=new Random();
    
    public static Random random()
    { 	
	  return rand;
    }
    
    public static Object createObject(String classname)
    {
    	  Object obj=null;
    	
     try{
    	  Class cla=Class.forName(classname);
    	  Constructor c = cla.getConstructor(null);
		  obj = c.newInstance();
    	}
     catch(Exception e)
    	{
    		
    	}
    	  return obj;
    }
    
       static JxBaseFoundation foundation=new JxBaseFoundation();
    
    public static JxBaseFoundation getSingleInstandce()
    {	
    	return foundation;
    }
}
