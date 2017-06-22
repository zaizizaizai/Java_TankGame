package com.TankGame3;

//子弹类
class Shot implements Runnable
{
	int x;
	int y;
	int direct;
	int speed=3;
	//子弹是否存活
	boolean isLive=true;
	public Shot(int x,int y,int direct)
	{
		this.x=x;
		this.y=y;
		this.direct=direct;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true)
		{
			try {
				Thread.sleep(50);
			} catch (Exception e) {
				// TODO: handle exception
			}
			switch (direct) 
			{
			//向上
			case 0:
				y-=speed;
				break;
			//向下
			case 1:
				y+=speed;
				break;
			//向左
			case 2:
				x-=speed;
				break;
			//向右
			case 3:
				x+=speed;
				break;
			}
			System.out.println("子弹坐标x="+x+"子弹坐标y="+y);
			//子弹何时死亡
			
			//判断该子弹是否碰到边缘
			if(x<0||x>600||y<0||y>400)
			{
				this.isLive=false;
				break;
			}
		}
	}
}



//坦克类
class Tank
{
	//坦克的颜色
	int color;
	
	public int getColor() {
		return color;
	}


	public void setColor(int color) {
		this.color = color;
	}

	//坦克的速度
	int speed=5;
	
	public int getSpeed() {
		return speed;
	}


	public void setSpeed(int speed) {
		this.speed = speed;
	}

	//坦克的横纵坐标
	int x=0;
	int y=0;
	
	public Tank(int x,int y)
	{
		this.x=x;
		this.y=y;
	}
	
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	//坦克方向
	//0上   1下   2左    3右
	int direct=0;

	
	public int getDirect() {
		return direct;
	}


	public void setDirect(int direct) {
		this.direct = direct;
	}

}

//敌人的坦克
class EnemyTank extends Tank
{
	public EnemyTank(int x,int y)
	{
		super(x, y);
	}
}

//我的坦克
class Hero extends Tank
{
	//子弹
	Shot s=null; 
	
	public Hero(int x,int y)
	{
		super(x, y);
	}
	
	//开火
	public void ShotEnemy()
	{
		switch(this.direct)
		{
		//上
		case 0:
			s=new Shot(x+10, y,0);
			break;
		//下
		case 1:
			s=new Shot(x+10, y+30,1);
			break;
		//左
		case 2:
			s=new Shot(x, y+10,2);
			break;
		//右
		case 3:
			s=new Shot(x+30, y+10,3);
			break;
		}
		//启动子弹线程
		Thread t=new Thread(s);
		t.start();
	}
	
	//坦克向上移动
	public void moveUp()
	{
		y-=speed;
	}
	//坦克向下移动
	public void moveDown()
	{
		y+=speed;
	}
	//坦克向左移动
	public void moveLeft()
	{
			x-=speed;
	}
	//坦克向右移动
	public void moveRight()
	{
			x+=speed;
	}
		
}


