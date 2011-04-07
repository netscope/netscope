package mesh;
public class JiNode {
	int node_id;
	int que_capacity=50;
	int que_length=0;
	int loc_x;
	int loc_y;
	int lost_count;
	int actual_count;
	int send_out=1;
	int packet_sum=0;
	int tx_power=7;
	boolean lastfailed=false;
	public int Minimum(int a,int b)
	{  int x=a;
       int y=b;
       int min=x;
       if(y<min)
    	   min=y;
		return min;
     }
}
