package com.TankGame4;

import java.util.Vector;

//炸弹类
class Bomb
{
	//炸弹坐标
	int x,y;
	//炸弹生命期
	int life=9;
	//炸弹是否存活
	boolean isLived;
	public Bomb(int x,int y)
	{
		this.x=x;
		this.y=y;
	}
	//生命期减少
	public void lifeDown()
	{
		if(life>0)
		{
			life--;
		}
		else
		{
			this.isLived=false;
		}
	}
}

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
	int speed=2;
	
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
class EnemyTank extends Tank implements Runnable
{
	boolean isLive=true;
	
	//子弹
	Shot s=null; 
	Vector<Shot> ss=new Vector<Shot>();
	
	public EnemyTank(int x,int y)
	{
		super(x, y);
	}
	
	@Override
	public void run() 
	{
		// TODO Auto-generated method stub
		//敌人坦克自行移动
		while(true)
		{
			switch (this.direct) {
			//敌人坦克正在向上移动
			case 0:
				for(int i=0;i<20;i++)
				{
					if(y>0)
					{
						y-=speed;
					}
					
					try 
					{
						Thread.sleep(50);
					} catch (InterruptedException e) 
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				break;
			//敌人坦克正在向下移动
			case 1:
				for(int i=0;i<20;i++)
				{
					if(y<370)
					{
						y+=speed;
					}
					try 
					{
						Thread.sleep(50);
					} catch (InterruptedException e) 
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				break;
			//敌人坦克正在向左移动
			case 2:
				for(int i=0;i<20;i++)
				{
					if(x>0)
					{
						x-=speed;
					}
					try 
					{
						Thread.sleep(50);
					} catch (InterruptedException e) 
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				break;
			//敌人坦克正在向右移动
			case 3:
				for(int i=0;i<20;i++)
				{
					if(x<600)
					{
						x+=speed;
					}
					try 
					{
						Thread.sleep(50);
					} catch (InterruptedException e) 
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				break;

			default:
				break;
			}
			//让敌人坦克产生一个新的方向
			this.direct=(int)(Math.random()*4);
			
			//判断敌人坦克是否死亡
			if(this.isLive==false)
			{
				//坦克死亡后退出线程
				break;
			}
		}
	}
}

//我的坦克
class Hero extends Tank
{
	//子弹
	Shot s=null; 
	Vector<Shot> ss=new Vector<Shot>();
	
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
			//创建一颗子弹
			s=new Shot(x+10, y,0);
			//把子弹加入到向量
			ss.add(s);
			break;
		//下
		case 1:
			s=new Shot(x+10, y+30,1);
			ss.add(s);
			break;
		//左
		case 2:
			s=new Shot(x, y+10,2);
			ss.add(s);
			break;
		//右
		case 3:
			s=new Shot(x+30, y+10,3);
			ss.add(s);
			break;
		}
		//启动子弹线程
		Thread t=new Thread(s);
		t.start();
	}
	
	//坦克向上移动
	public void moveUp()
	{
		if(y>0)
		{
			y-=speed;
		}
	}
	//坦克向下移动
	public void moveDown()
	{
		if(y<370)
		{
			y+=speed;
		}
	}
	//坦克向左移动
	public void moveLeft()
	{
		if(x>0)
		{
			x-=speed;
		}
	}
	//坦克向右移动
	public void moveRight()
	{
		if(x<600)
		{
			x+=speed;
		}
	}
		
}


