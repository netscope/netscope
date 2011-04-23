/**
 * 
 */
package extend.scalefree;
import java.util.*;
/**
 * @author Allen
 *
 */
public class JxScaleFreeSimuApplication {
	Random random=new Random(); 
	JxScaleFreeNode []node= new JxScaleFreeNode[10000];
	int i,j; 
	int id;
	void init(){};
	void evolve(){
	};
   void gen_topo(){
	  for(i=0;i<100;i++)
	  {
		for(j=0;j<100;j++)    //生成10000个点
	    {               
		  node[id++].loc_x= random.nextInt(100); //此处需要保证生成的节点不同
		  node[id++].loc_y = random.nextInt(100);
	    }
		
		
      }
    }
}

