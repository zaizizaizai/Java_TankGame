
/*
 * ���ܣ�̹����Ϸ1.0
 * 1.����̹��
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
	//���캯��
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

//�ҵ����
class Mypanel extends JPanel
{
	//����һ���ҵ�̹��
	Hero hero=null;
	
	//���캯��
	public Mypanel()
	{
		//̹�˳�ʼλ��
		hero=new Hero(100, 100);
	}
	
	
	//����paint
	public void paint(Graphics g)
	{
		super.paint(g);
		//��ɫ����
		g.fillRect(0, 0, 400, 300);
		
		this.DrawTank(hero.getX(), hero.getY(), g, 3,0);
		
	}
	//����̹�˵ĺ���
	public void DrawTank(int x,int y,Graphics g,int direct,int type)
	{
		//�ж�̹������
		switch (type) 
		{
		//�ҵ�̹��
		case 0:
			g.setColor(Color.CYAN);
			break;
		//�з�̹��
		case 1:
			g.setColor(Color.yellow);
			break;
		}
		//�ж�̹�˷���
		switch (direct) {
		//����
		case 1:
					
			//�����ҵ�̹��(��ʱ�ٷ�װ��һ��̹��)
			//1.������ߵľ���
			g.fill3DRect(x, y, 5, 30,false);
			//2.�����ұ߾���
			g.fill3DRect(x+15, y, 5, 30,false);
			//3.�м�ľ���
			g.fill3DRect(x+5, y+5, 10, 20,false);
			//4.����Բ��
			g.fillOval(x+5, y+10, 10, 10);
			//5.��������
			g.drawLine(x+10, y+18,x+10,y);
			break;
		//����
		case 2:
			//1.������ߵľ���
			g.fill3DRect(x, y, 5, 30,false);
			//2.�����ұ߾���
			g.fill3DRect(x+15, y, 5, 30,false);
			//3.�м�ľ���
			g.fill3DRect(x+5, y+5, 10, 20,false);
			//4.����Բ��
			g.fillOval(x+5, y+8, 10, 10);
			//5.��������
			g.drawLine(x+10, y+30,x+10,y+18);
			break;
		//����	
		case 3:
			//1.�����ϱߵľ���
			g.fill3DRect(x, y, 30, 5,false);
			//2.�����±߾���
			g.fill3DRect(x, y+15, 30, 5,false);
			//3.�м�ľ���
			g.fill3DRect(x+5, y+5, 20, 10,false);
			//4.����Բ��
			g.fillOval(x+12, y+4, 10, 10);
			//5.��������
			g.drawLine(x, y+10,x+12,y+10);
			break;
		//����	
		case 4:
			//1.�����ϱߵľ���
			g.fill3DRect(x, y, 30, 5,false);
			//2.�����±߾���
			g.fill3DRect(x, y+15, 30, 5,false);
			//3.�м�ľ���
			g.fill3DRect(x+5, y+5, 20, 10,false);
			//4.����Բ��
			g.fillOval(x+8, y+4, 10, 10);
			//5.��������
			g.drawLine(x+12, y+10,x+30,y+10);
			break;
			

		default:
			break;
		}
		
		
			}
	
}

//̹����
class Tank
{
	//̹�˵ĺ�������
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

//�ҵ�̹��
class Hero extends Tank
{
	public Hero(int x,int y)
	{
		super(x, y);
	}
}
