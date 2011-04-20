package extend.dla;
import java.util.*;
import extend.cellular.Node;
public class scalefree {
	int i=0,j=0;
	int id=0;
    SFNode sfnode[]=new SFNode[10000];  
    Edge edge[]=new Edge[10000];
    Random random=new Random(); 
	public void gen_scalefree_topo() //产生结构
	{ 
	  for(i=0;i<100;i++)
	  {
		for(j=0;j<100;j++)    //生成10000个点
	    {               
		  sfnode[id++].loc_x= random.nextInt(100); 
		  sfnode[id++].loc_y = random.nextInt(100);
	    }
		
      }
 }
	public void save_node_topo()  //保存点结构
	{
	   	
	}
	public void save_edge_topo()  //保存边结构
	{
	 	
	}
	public void save_node_packetlength() // 保存点-包长度
	{
		 
	}
	public void load_node_topo() //下载节点结构
	{
		
	}
	public void load_edge_topo() //下载边结构
	{
	  	
	}
	public void load_node_packetlength()//下载节点包长度
	{
		
	}
	public void  run()
	{
		
	}
  }

