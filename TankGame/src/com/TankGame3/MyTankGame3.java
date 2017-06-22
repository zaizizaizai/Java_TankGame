

/*
 * 功能：坦克游戏3.0
 * 1.画出坦克
 * 2.我的坦克可以上下左右移动
 * 3.我的坦克可以发子弹
 */
package com.TankGame3;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MyTankGame3 extends JFrame
{
	
	Mypanel mp=null;
	//构造函数
	public MyTankGame3()
	{
		mp=new Mypanel();
		
		//启动mp线程
		Thread t=new Thread(mp);
		t.start();
		
		this.add(mp);
		//注册监听
		this.addKeyListener(mp);
		
		this.setLocation(400,250);
		this.setSize(600,400);
		this.setVisible(true);
		
	}

	
	
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		MyTankGame3 demo=new MyTankGame3();
	}

}

//我的面板
class Mypanel extends JPanel implements KeyListener,Runnable
{
	//定义一个我的坦克
	Hero hero=null;
	
	//定义敌人的坦克
	Vector<EnemyTank>ets=new  Vector<EnemyTank>();
	int enSize=3;
	
	//构造函数
	public Mypanel()
	{
		//初始化自己的坦克
		hero=new Hero(100, 100);
		
		//初始化敌人坦克
		for(int i=0;i<enSize;i++)
		{
			//创建一辆敌人坦克
			EnemyTank et=new EnemyTank((i+1)*50, 0);
			et.setColor(0);
			et.setDirect(1);
			//加入
			ets.add(et);
		}
	}
	
	
	//重新paint
	public void paint(Graphics g)
	{
		super.paint(g);
		//黑色背景
		g.fillRect(0, 0, 600, 400);
		
		//画出自己的坦克
		this.DrawTank(hero.getX(), hero.getY(), g, this.hero.direct,0);
		
		
		//画出子弹
		if(hero.s!=null&&hero.s.isLive==true)
		{
			g.draw3DRect(hero.s.x, hero.s.y, 1, 1, false);
		}
		
		//画出自己的坦克
		for(int i=0;i<ets.size();i++)
		{
			this.DrawTank(ets.get(i).getX(), ets.get(i).getY(), g, ets.get(i).getDirect(), 1);
		}	
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
		case 0:
					
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
			g.drawLine(x+10, y+15,x+10,y);
			break;
		//向下
		case 1:
			//1.画出左边的矩形
			g.fill3DRect(x, y, 5, 30,false);
			//2.画出右边矩形
			g.fill3DRect(x+15, y, 5, 30,false);
			//3.中间的矩形
			g.fill3DRect(x+5, y+5, 10, 20,false);
			//4.画出圆形
			g.fillOval(x+5, y+10, 10, 10);
			//5.画出炮柱
			g.drawLine(x+10, y+30,x+10,y+18);
			break;
		//向左	
		case 2:
			//1.画出上边的矩形
			g.fill3DRect(x, y, 30, 5,false);
			//2.画出下边矩形
			g.fill3DRect(x, y+15, 30, 5,false);
			//3.中间的矩形
			g.fill3DRect(x+5, y+5, 20, 10,false);
			//4.画出圆形
			g.fillOval(x+12, y+4, 10, 10);
			//5.画出炮柱
			g.drawLine(x+15, y+10,x,y+10);
			break;
		//向右	
		case 3:
			//1.画出上边的矩形
			g.fill3DRect(x, y, 30, 5,false);
			//2.画出下边矩形
			g.fill3DRect(x, y+15, 30, 5,false);
			//3.中间的矩形
			g.fill3DRect(x+5, y+5, 20, 10,false);
			//4.画出圆形
			g.fillOval(x+10, y+5, 10, 10);
			//5.画出炮柱
			g.drawLine(x+15, y+10,x+30,y+10);
			break;
			
		default:
			break;
		}
		
		
			}

	//键按下处理 a向左  s向下  d向右  w向上 
	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getKeyCode()==KeyEvent.VK_W)
		{
			//设置我的坦克的方向
			this.hero.setDirect(0);
			this.hero.moveUp();
		}
		else if(arg0.getKeyCode()==KeyEvent.VK_S)
		{
			this.hero.setDirect(1);
			this.hero.moveDown();
		}
		else if(arg0.getKeyCode()==KeyEvent.VK_A)
		{
			this.hero.setDirect(2);
			this.hero.moveLeft();
		}
		else if(arg0.getKeyCode()==KeyEvent.VK_D)
		{
			this.hero.setDirect(3);
			this.hero.moveRight();
		}
		//判断玩家是否按下j键
		if(arg0.getKeyCode()==KeyEvent.VK_J)
		{
			this.hero.ShotEnemy();
		}
		
		
		//必须重新绘制panel
		this.repaint();
	}


	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void run() {
		// TODO Auto-generated method stub
		//每隔100毫秒重绘
		while(true)
		{
			try {
				Thread.sleep(100);
			} catch (Exception e) {
				// TODO: handle exception
			}
			//重绘
			this.repaint();
		}
	}
	
}

