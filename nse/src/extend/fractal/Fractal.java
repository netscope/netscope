package extend.fractal;

/*此部分：在1000*1000个方格上按DLA形式生成10万个节点。
 **/
import java.util.*;
import java.math.*;

public class Fractal {
	int i = 0, j = 0, t = 0, n = 0;
	int loc_x, loc_y; // 粒子坐标
	int upordown;
	int radius;
	int distance;
	int forward;
	int l3, driver, graphmode;
	long l1, l2; // l1:试验次数
	int grids[][] = new int[1000][1000]; // 生成1000000万个格子
	Random random = new Random();

	JiNetworkTopology genfractalTopology(int Radius) {
		radius = Radius;
		for (i = 0; i < 1000; i++) {
			for (j = 0; j < 1000; j++)
				grids[i][j] = 0;
		}
		grids[500][500] = 1; // 中心点置1
		while (t < 100000) { // 释放一个粒子
			loc_x = random.nextInt(1000); // 释放点的横坐标
			loc_y = (int) Math.sqrt(radius * radius - (loc_x - 500)* (loc_y - 500)); // 求释放点的纵坐标
			upordown = random.nextInt(2); // 粒子在中心点的上侧或下侧
			if (upordown == 0) {
				loc_y = 500 + loc_y;
			} else {
				loc_y = 500 - loc_y;
			}
			if (grids[loc_x][loc_y + 1] == 1 | grids[loc_x][loc_y - 1] == 1
					| grids[loc_x + 1][loc_y] == 1
					| grids[loc_x - 1][loc_y] == 1)
				continue; // 如果初始产生的粒子周围已有凝聚体，则重新释放粒子。（半径圆上的点不能被凝聚)
			while (true) // 每移动一步，判断是否越界，是否周围有凝聚体。
			{
				forward = random.nextInt(4); // 向前运动一步
				if (forward == 0)
					loc_x = loc_x + 1;
				if (forward == 1)
					loc_x = loc_x - 1;
				if (forward == 2)
					loc_y = loc_y + 1;
				if (forward == 3)
					loc_y = loc_y - 1;
				distance = (int) Math.sqrt((loc_x - 500) * (loc_x - 500)
						+ (loc_y - 500) * (loc_y - 500));
				if (distance > radius)  //判断是否越界
					break;             //越界-重新释放
				if (grids[loc_x][loc_y + 1] == 1|grids[loc_x][loc_y - 1] == 1
			     	 |grids[loc_x + 1][loc_y] == 1|grids[loc_x - 1][loc_y] == 1)
				      {  t++;                  //生成粒子个数
				         grids[loc_x][loc_y] = 1; 
				         JiNode[t++].loc_x = loc_x; //保存粒子坐标(粒子即为网络节点)
				         JiNode[t++].loc_y = loc_y;
				         break; } //重新释放粒子
				else {
					       continue; //当前节点周围如果没有凝聚体，继续移动	
				}
			}
		}
	}

}
