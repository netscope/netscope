package nse.cellular;

public class Node {
	int que_capacity=50;
	int que_length=0;
	int loc_x;
	int loc_y;
	int loc_z;
	int sendcount;
	int lostcount;
	int actualcount;
	int send=3;
	int packet_sum=0;
	boolean lastfailed=false;
}
