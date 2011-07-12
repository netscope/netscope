package kernel;
import java.util.*;
public class JxStdInteraction implements JiInteraction {
    
	
	/**定义relation对象，并转换为JxStdRelation */
	JiRelation relation=new JxStdRelation();
    JxStdRelation stdrelation=(JxStdRelation)relation;
    
    
    /** 得到 已存储的边集合和点集合*/
<<<<<<< HEAD
    JxEdgeCollection edgeCollection=JxStdRelation.edgeCollection;
	JxNodeCollection nodeCollection=JxStdRelation.nodeCollection;
=======
    JxRelationCollection edgeCollection=stdrelation.edgeCollection;
	JxNodeCollection nodeCollection=stdrelation.nodeCollection;
>>>>>>> f7dd2920cbc8f6be9964d882446f0dca27b6d6f5
	
	int packetsum=0;
	Random random=new Random();

	
	/**对所有的边做一次包交换*/
	public void interact(){
		
		int edgeCount=edgeCollection.count();
		
		for(int i=0;i<edgeCount;i++){
			
			int []randomSerial=new int[edgeCount];
			
			/** 得到随机排列的边集 */
			randomSerial=JxStdRelation.randomSerial(edgeCount);
			
			/** 随机得到一条边 */
			int relationId=randomSerial[i];
			relation = edgeCollection.get(relationId);
			
		    int nodefrom=relation.getNodeFrom();
		    int nodeto=relation.getNodeTo();
		  
			/**发送节点-接收节点*/
			JiNode sender =new JxStdNode();
		    JiNode receiver=new JxStdNode();
		    
		    int temp=random.nextInt(2);
		    
		    if(temp==0){
		       sender=nodeCollection.get(nodefrom);	
		       receiver=nodeCollection.get(nodeto);	
		    }
		    else{
		       sender=nodeCollection.get(nodeto);	
			   receiver=nodeCollection.get(nodefrom);	
		    }
			
		    /**发送点的包的个数*/
		    int senderValue=sender.getValue();
		    
		    /**接收点的剩余空间*/
		    int receiverVolume=receiver.getCapacity()-receiver.getValue();
	
		   /**边的带宽*/
		    int bandwidth=relation.getBandWidth();
		    
		    
			int packet=Minimum(senderValue,receiverVolume,bandwidth);	    	  
	        sender.setValue(senderValue-packet);     
	        receiver.setValue(receiverVolume+packet);
	     
	        packetsum +=packet;
	        }
			relation.setPacketSum(packetsum); //记录边上包的流量    
        }
			   
		public int  Minimum(int a,int b,int c) { //发送包的个数要小于这三个值				   
			   
			   int minimum=0;
			   int mini=a;
			   if(b<mini)
			    mini=b;   
			   if(c<mini)
			    mini=c;
	          
			  minimum=random.nextInt(mini);  
	          return minimum;	 
		}	
}
			

	
