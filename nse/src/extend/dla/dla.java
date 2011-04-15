package extend.dla;
/*此部分：在1000*1000个方格上按DLA形式生成10万个节点。
  **/
import java.util.*;
import java.math.*;
public class dla {
int i,j;               //粒子坐标
int highorlow;       
int radius;          
int distance;
int next;
int l3,driver,graphmode;
long l1,l2;                         //l1:试验次数
int grids[][]=new int[1000][1000];
Random random=new Random();
public void dla()
{
   for(i=0;i<1000;i++)
	{
	   for(j=0;j<1000;j++)
	     grids[i][j]=0;  	
	}
	grids[500][500]=1;	        //中心点置1
	
	for(l1=0;l1<100000;l1++)    //释放10万个粒子
	{
		i=random.nextInt(1000);
		j=(int)Math.sqrt(radius*radius-(i-500)*(i-500));
		highorlow=random.nextInt(2);
		if(highorlow==0)
		{
			j=500+j;
		}
		else
		{
		   j=500-j;
	    }
	}
}
	public void NextStep()       //向前运动一步
   { 
	next=random.nextInt(4);
	  if(next==0)
		  i=i+1;
	  if(next==1)
		  i=i-1;  
	  if(next==2)
		  j=j+1;
	  if(next==3)
		  j=j-1;
	}
	public int Distacnce()       //到中心点的距离
	{
	  distance=(int)Math.sqrt((i-500)*(i-500)+(j-500)*(j-500)); 
	      return distance;
	}
	boolean aggregation=false;
	public boolean aggregation()   //凝聚
	{ 
	  if((grids[i+1][j]|grids[i-1][j]|grids[i][j-1]|grids[i][j+1])==1)
		    aggregation=true;
		  return aggregation;
	}
	 public void main()
	 {dla();
	 aggregation();
	 }
}

