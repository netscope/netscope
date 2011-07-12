package kernel;
import java.util.*;
public class JxStdInteraction implements JiInteraction {
    
	
	/**定义relation对象，并转换为JxStdRelation */
	JiRelation relation=new JxStdRelation();
    JxStdRelation stdrelation=(JxStdRelation)relation;
    
    
    /** 得到 已存储的边集合和点集合*/
    JxRelationCollection edgeCollection=stdrelation.edgeCollection;
	JxNodeCollection nodeCollection=stdrelation.nodeCollection;
	
	
	Random random=new Random();
	int packetsum=0;
	
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
			
		    int senderValue=sender.getValue();
		    int receiverVolume=receiver.getCapacity()-receiver.getValue();
		    int bandwidth=relation.getBandWidth();
		    
			packetsum=Minimum(senderValue,receiverVolume,bandwidth);	    	  
	        sender.setValue(senderValue-packetsum);     
	        receiver.setValue(receiverVolume+packetsum);
	        
	        packetsum+=packetsum;
	        }
			relation.setPacketSum(packetsum); //记录边上包的流量    
        }
			   
		public int  Minimum(int a,int b,int c) { //发送包的个数要小于这三个值		
			   
			   int mini;
                
			   int minimum=a;
			   
			   if(b<minimum)
			    minimum=b;
			    
			   if(c<minimum)
			    minimum=c;
		        
			    mini=random.nextInt(minimum);

	            return mini;	 
		}	
}
			

	
