

/*
 * ���ܣ�̹����Ϸ3.0
 * 1.����̹��
 * 2.�ҵ�̹�˿������������ƶ�
 * 3.�ҵ�̹�˿��Է��ӵ�
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
	//���캯��
	public MyTankGame3()
	{
		mp=new Mypanel();
		
		//����mp�߳�
		Thread t=new Thread(mp);
		t.start();
		
		this.add(mp);
		//ע�����
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

//�ҵ����
class Mypanel extends JPanel implements KeyListener,Runnable
{
	//����һ���ҵ�̹��
	Hero hero=null;
	
	//������˵�̹��
	Vector<EnemyTank>ets=new  Vector<EnemyTank>();
	int enSize=3;
	
	//���캯��
	public Mypanel()
	{
		//��ʼ���Լ���̹��
		hero=new Hero(100, 100);
		
		//��ʼ������̹��
		for(int i=0;i<enSize;i++)
		{
			//����һ������̹��
			EnemyTank et=new EnemyTank((i+1)*50, 0);
			et.setColor(0);
			et.setDirect(1);
			//����
			ets.add(et);
		}
	}
	
	
	//����paint
	public void paint(Graphics g)
	{
		super.paint(g);
		//��ɫ����
		g.fillRect(0, 0, 600, 400);
		
		//�����Լ���̹��
		this.DrawTank(hero.getX(), hero.getY(), g, this.hero.direct,0);
		
		
		//�����ӵ�
		if(hero.s!=null&&hero.s.isLive==true)
		{
			g.draw3DRect(hero.s.x, hero.s.y, 1, 1, false);
		}
		
		//�����Լ���̹��
		for(int i=0;i<ets.size();i++)
		{
			this.DrawTank(ets.get(i).getX(), ets.get(i).getY(), g, ets.get(i).getDirect(), 1);
		}	
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
		case 0:
					
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
			g.drawLine(x+10, y+15,x+10,y);
			break;
		//����
		case 1:
			//1.������ߵľ���
			g.fill3DRect(x, y, 5, 30,false);
			//2.�����ұ߾���
			g.fill3DRect(x+15, y, 5, 30,false);
			//3.�м�ľ���
			g.fill3DRect(x+5, y+5, 10, 20,false);
			//4.����Բ��
			g.fillOval(x+5, y+10, 10, 10);
			//5.��������
			g.drawLine(x+10, y+30,x+10,y+18);
			break;
		//����	
		case 2:
			//1.�����ϱߵľ���
			g.fill3DRect(x, y, 30, 5,false);
			//2.�����±߾���
			g.fill3DRect(x, y+15, 30, 5,false);
			//3.�м�ľ���
			g.fill3DRect(x+5, y+5, 20, 10,false);
			//4.����Բ��
			g.fillOval(x+12, y+4, 10, 10);
			//5.��������
			g.drawLine(x+15, y+10,x,y+10);
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
			g.fillOval(x+10, y+5, 10, 10);
			//5.��������
			g.drawLine(x+15, y+10,x+30,y+10);
			break;
			
		default:
			break;
		}
		
		
			}

	//�����´��� a����  s����  d����  w���� 
	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getKeyCode()==KeyEvent.VK_W)
		{
			//�����ҵ�̹�˵ķ���
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
		//�ж�����Ƿ���j��
		if(arg0.getKeyCode()==KeyEvent.VK_J)
		{
			this.hero.ShotEnemy();
		}
		
		
		//�������»���panel
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
		//ÿ��100�����ػ�
		while(true)
		{
			try {
				Thread.sleep(100);
			} catch (Exception e) {
				// TODO: handle exception
			}
			//�ػ�
			this.repaint();
		}
	}
	
}

