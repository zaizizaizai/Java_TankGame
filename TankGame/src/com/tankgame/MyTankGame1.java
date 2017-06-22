
/*
 * 功能：坦克游戏1.0
 * 1.画出坦克
 */
package com.tankgame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MyTankGame1 extends JFrame
{
	
	Mypanel mp=null;
	//构造函数
	public MyTankGame1()
	{
		mp=new Mypanel();
		
		
		this.add(mp);
		this.setLocation(600,400);
		this.setSize(400,300);
		this.setVisible(true);
		
	}

	
	
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		MyTankGame1 demo=new MyTankGame1();
	}

}

//我的面板
class Mypanel extends JPanel
{
	//定义一个我的坦克
	Hero hero=null;
	
	//构造函数
	public Mypanel()
	{
		//坦克初始位置
		hero=new Hero(100, 100);
	}
	
	
	//重新paint
	public void paint(Graphics g)
	{
		super.paint(g);
		//黑色背景
		g.fillRect(0, 0, 400, 300);
		
		this.DrawTank(hero.getX(), hero.getY(), g, 3,0);
		
	}
	//画出坦克的函数
	public void DrawTank(int x,int y,Graphics g,int direct,int type)
	{
		//判断坦克类型
		switch (type) 
		{
		//我的坦克
		case 0:
			g.setColor(Color.CYAN);
			break;
		//敌方坦克
		case 1:
			g.setColor(Color.yellow);
			break;
		}
		//判断坦克方向
		switch (direct) {
		//向上
		case 1:
					
			//画出我的坦克(到时再封装成一个坦克)
			//1.画出左边的矩形
			g.fill3DRect(x, y, 5, 30,false);
			//2.画出右边矩形
			g.fill3DRect(x+15, y, 5, 30,false);
			//3.中间的矩形
			g.fill3DRect(x+5, y+5, 10, 20,false);
			//4.画出圆形
			g.fillOval(x+5, y+10, 10, 10);
			//5.画出炮柱
			g.drawLine(x+10, y+18,x+10,y);
			break;
		//向下
		case 2:
			//1.画出左边的矩形
			g.fill3DRect(x, y, 5, 30,false);
			//2.画出右边矩形
			g.fill3DRect(x+15, y, 5, 30,false);
			//3.中间的矩形
			g.fill3DRect(x+5, y+5, 10, 20,false);
			//4.画出圆形
			g.fillOval(x+5, y+8, 10, 10);
			//5.画出炮柱
			g.drawLine(x+10, y+30,x+10,y+18);
			break;
		//向左	
		case 3:
			//1.画出上边的矩形
			g.fill3DRect(x, y, 30, 5,false);
			//2.画出下边矩形
			g.fill3DRect(x, y+15, 30, 5,false);
			//3.中间的矩形
			g.fill3DRect(x+5, y+5, 20, 10,false);
			//4.画出圆形
			g.fillOval(x+12, y+4, 10, 10);
			//5.画出炮柱
			g.drawLine(x, y+10,x+12,y+10);
			break;
		//向右	
		case 4:
			//1.画出上边的矩形
			g.fill3DRect(x, y, 30, 5,false);
			//2.画出下边矩形
			g.fill3DRect(x, y+15, 30, 5,false);
			//3.中间的矩形
			g.fill3DRect(x+5, y+5, 20, 10,false);
			//4.画出圆形
			g.fillOval(x+8, y+4, 10, 10);
			//5.画出炮柱
			g.drawLine(x+12, y+10,x+30,y+10);
			break;
			

		default:
			break;
		}
		
		
			}
	
}

//坦克类
class Tank
{
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
}

//我的坦克
class Hero extends Tank
{
	public Hero(int x,int y)
	{
		super(x, y);
	}
}
