package extend.cellular;                        

import trace.JxDbConnection;

public class JxCellularAutomataApplication 
{
    int vel_in=5;           //输入速度固定为5
    int vel_inc=2;          //速度增量为2
    int time=0;             //时间
    int id=0;               //节点号   
    
    Node node[]=new Node[3];              //节点数组
    String link []=new String[3];         //边数组，使用数组前一定要先申明
    JxDbConnection dbconnect=new JxDbConnection();  //JDBC类，使用新的类之前一定要先用new声明
    String str1;
    String str2;
    
   
    public void init()                  
    {
    	;
	}
   public void step()                        //每一次对所有节点前进一步
	{  
      try
      {
    	for(id=0;id<3;id++)
	   {  //  node[id]=new Node();           //只定义数组无法使用，必须对数组每个元素付初值
    		if(id==0)                        //源节点
    	   {  
       		    if(vel_in>(node[id].que_capacity-node[id].que_length))   // 外部输入是否过大
		      {
       		     node[id].que_length=node[id].que_capacity;               //若过大，队列变满
       		     
		      }  else 
	            node[id].que_length+=vel_in;                             
       		   
               if(node[id].lastfailed==true)             //判断上一次发送是否失败
	         { 
	           node[id].sendcount=node[id].sendcount/2;
	           node[id].que_length-=node[id].sendcount;   //！！！！这一块可以简略
	         } else
	         { 
	           node[id].sendcount+=vel_inc;
	           node[id].que_length-=node[id].sendcount;
	         }
    	   } else if(id==3)                               //宿节点   
    	     {  if(node[id-1].sendcount>(node[id].que_capacity-node[id].que_length)) //宿节点接受的输入是否过大
    	       {
 	   		     node[id-1].lastfailed=true;
 	   		     node[id].que_length=node[id].que_capacity;
 	           } else
 	           {
 	        	  node[id-1].lastfailed=false; 
 	        	  node[id].que_length+=node[id-1].sendcount;
 	    	      node[id].que_length-=node[id].sendcount;              //宿节点输出速度固定不变
 	           }
 	         } 
    	     else                                                       //中间节点
    	   {  if(node[id-1].sendcount>(node[id].que_capacity-node[id].que_length)) //判断其前一节点发送是否失败
    	     {
	   		  node[id-1].lastfailed=true;
	   		  node[id].que_length=node[id].que_capacity;
	         }else    
	         { 
	           node[id-1].lastfailed=false;                         //前一节点发送成功
	           node[id].que_length+=node[id-1].sendcount;
	         }
	         if(node[id].lastfailed==true)                         //判断本节点上次发送是否失败
	         {
	           node[id].sendcount=node[id].sendcount/2; 	           //若失败则速度减半
	           node[id].que_length-=node[id].sendcount;
	         }else                                               
	         {
	           node[id].sendcount+=vel_inc;                           //若成功速度增加
	           node[id].que_length-=node[id].sendcount;
	         }
    	   }
   	          if(id<3)                                              // 边的条数比节点数少1
	          {    
	        	  node[id].packet_sum+=node[id].sendcount;          //每条边输出的总包数
		          str1= Integer.toString(id);  
		          str2= Integer.toString(id+1); 
		          link[id]=str1+" "+str2;                           //每条边的编号    
		       }	           
	    }
      }catch( NullPointerException e)
      {
    	  
    	  ;
      }
   }  
	
	
    public void run()                                     //运行10次
   { /*
    try
	  { dbconnect.opendatabase("testdbx.script");
	    dbconnect.createnodetable();                     // 创建节点表   
	    dbconnect.createlinktable();                     // 创建边表
        for(time=0;time<10;time++);
		{   
			step();
			System.out.println("I Love You");            //print输出不换行,println输出换行。
		  //System.out.println(node[id].que_length);
			for(id=0;id<3;id++)
			{
				System.out.println(node[id].que_length);
				System.out.println(node[id].packet_sum);
				dbconnect.insertnodetable(time, id, node[id].que_length);
				dbconnect.insertlinktable(time, link[id], node[id].packet_sum);
			}
		 }
	   }catch(NullPointerException e)
	    {
		    ;
	    }
	    */
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
       JxCellularAutomataApplication linesimu = new JxCellularAutomataApplication();
       linesimu.run();
   
       }

}
