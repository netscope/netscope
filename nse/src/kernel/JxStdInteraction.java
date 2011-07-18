package kernel;
import java.util.*;
public class JxStdInteraction implements JiInteraction {
	
	/**定义relation对象，并转换为JxStdRelation */
	JiRelation relation=new JxStdRelation();   
	
   /** 得到 已存储的边集合和点集合*/
   JxRelationCollection relationSet=JxRelationCollection.relationSet;
   JxNodeCollection nodeSet=JxRelationCollection.nodeSet;
     
   Random random=new Random();
     
	/**对所有的边做一次包交换*/
	public void interact()
	{ 	
	   /**定义随机序列*/	
       int edgeCount=relationSet.count();
	   int []randomSerial=new int[edgeCount];
	   randomSerial=JxRelationCollection.randomSerial(edgeCount);
	  
	    for(int i=0;i<edgeCount;i++){			

          /** 随机得到一条边 */ 
		  int relationId=randomSerial[i];
		  relation = relationSet.get(relationId);
		  
		    int nodefrom=relation.getNodeFrom();
		    int nodeto=relation.getNodeTo();
		  
			/**发送节点-接收节点*/
			JiNode sender =new JxStdNode();
		    JiNode receiver=new JxStdNode();
		    
		    /**随机确定发送方向*/
		    int temp=random.nextInt(2);
		    if(temp==0){
		       sender=nodeSet.get(nodefrom);	
		       receiver=nodeSet.get(nodeto);	
		    }
		    else{
		       sender=nodeSet.get(nodeto);	
			   receiver=nodeSet.get(nodefrom);	
		    }			
		     /**发送点的包个数*/
		     int senderValue=sender.getValue();  
		 		
		    
		 	 /**接收点的剩余空间*/
		     int receiverVolume=receiver.getCapacity()-receiver.getValue();
		 
		     /**边的带宽*/
		    int bandwidth=relation.getBandWidth();
		       
		   	int packet=Minimum(senderValue,receiverVolume,bandwidth);
		 	
			sender.setValue(senderValue-packet); 
			
			int receiverValue =receiver.getValue();
	        receiver.setValue(receiverValue+packet);
	        
	        int packetsum =relation.getPacketSum()+packet;
	        relation.setPacketSum(packetsum); //记录边上包的流量  	        
	        }	
	       for(int i=0;i<nodeSet.count();i++)
		   {
			  System.out.println("nodeId：" +nodeSet.get(i).getId()+" loc_x "+nodeSet.get(i).getLocx()+" loc_y "+ nodeSet.get(i).getLocy()+" nodelength "+nodeSet.get(i).getValue());
		   }
	}
			   
		public int  Minimum(int a,int b,int c) { //发送包的个数要小于这三个值				   
			   
			   int minimum=0;
			   int mini=a;
			   if(b<mini)
			    mini=b;   
			   if(c<mini)
			    mini=c;
	          if(mini==0)
	          {
	        	minimum=0;
	          }
	          else
	          {
	        	minimum=random.nextInt(mini);
	          }  
	          return minimum;	 
		}	


		
}
			

	
