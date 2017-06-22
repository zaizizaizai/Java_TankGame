package com.Tankgame2;

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
	
	public Hero(int x,int y)
	{
		super(x, y);
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


