package kernel;

import java.util.Random;

/* Reference 
 * - Effective Java对象创建（一）静态工厂方法代替构造函数, 2007, 
 *   http://www.blogjava.net/coundy/archive/2007/04/12/110043.html
 */

public class JxBaseFoundation {
	
	private static JxBaseFoundation m_foundation = null;

	/** 
	 * This is a static reference to a Random instance.
	 * This makes experiments repeatable, all you have to do is to set
	 * the seed of this Random class. 
	 */
	public static Random m_random = new Random();
	
	private JxBaseFoundation()
	{
		
	}
	
	public static JxBaseFoundation getSingleInstance()
    {
		if (m_foundation == null)
		{
			m_foundation = new JxBaseFoundation();
		}
		
		return m_foundation;
    }
	
	public static Random random() 
	{
		return m_random;
	}
	
	/**
	 * Creates an object from class name.
	 * 
	 * @param className
	 * @return
	 * @throws ClassNotFoundException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * 
	 * @example
	 * 	Class.forName("jdbc.DriverXYZ");
	 * 	Connection con = DriverManager.getConnection(url, "myLogin", "myPassword");
	 */
	@SuppressWarnings("rawtypes")
	public static Object createObject(String className)  
		throws ClassNotFoundException, InstantiationException, IllegalAccessException 
	{
		Class c = Class.forName(className);  
		return c.newInstance();  
	}  	
	
	
}
