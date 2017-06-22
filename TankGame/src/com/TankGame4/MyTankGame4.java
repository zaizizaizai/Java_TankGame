

/*
 * ���ܣ�̹����Ϸ3.0
 * 1.����̹��
 * 2.�ҵ�̹�˿������������ƶ�
 * 3.�ҵ�̹�˿��Է��ӵ���һ���������5���ӵ���
 * 4.���ҷ�̹�˻��ез�̹�ˣ��з�̹����ʧ������(��ըЧ��)
 * 5.����̹�˿��������ƶ�
 * 6.��������̹���ڹ涨�ķ�Χ���ƶ�
 * 7.�õз�̹��Ҳ�ܷ��ӵ�
 */
package com.TankGame4;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MyTankGame4 extends JFrame
{
	
	Mypanel mp=null;
	//���캯��
	public MyTankGame4()
	{
		mp=new Mypanel();
		
		//����mp�߳�
		Thread t=new Thread(mp);
		t.start();
		
		this.add(mp);
		//ע�����
		this.addKeyListener(mp);
		
		this.setTitle("̹�˴�ս");
		this.setLocation(400,250);
		this.setSize(620,450);
		this.setVisible(true);
		
	}

	
	
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		MyTankGame4 demo=new MyTankGame4();
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
	
	//����ը������
	Vector<Bomb> bombs=new Vector<Bomb>();
	
	//����ͼƬ
	Image image1=null;
	Image image2=null;
	Image image3=null;
	
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
			//��������̹��
			Thread thread=new Thread(et);
			thread.start();
			//����
			ets.add(et);
		}
		
		//��ʼ��ͼƬ
//		image1=Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/1.png"));
//		image2=Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/2.png"));
//		image3=Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/3.png"));
	}
	
	
	//����paint
	public void paint(Graphics g)
	{
		super.paint(g);
		//��ɫ����
		g.fillRect(0, 0, 600, 400);
		
		//�����Լ���̹��
		this.DrawTank(hero.getX(), hero.getY(), g, this.hero.direct,0);
		
		//��ss��ȡ��ÿ���ӵ��������Լ�̹�˵��ӵ�
		for(int i=0;i<hero.ss.size();i++)
		{
			Shot myShot=hero.ss.get(i);
			
			//����һ���ӵ�
			if(myShot!=null&&myShot.isLive==true)
			{
				g.draw3DRect(myShot.x, myShot.y, 1, 1, false);
			}
			if(myShot.isLive==false)
			{
				//ɾ���ӵ�
				hero.ss.remove(myShot);
			}
		}	
		
		//����ը��
		for(int i=0;i<bombs.size();i++)
		{
			//ȡ��ը��
			Bomb b=bombs.get(i);
			
			if(b.life>6)
			{
				g.drawImage(image1, b.x, b.y,30,30,this);
			}
			else if(b.life>3)
			{
				g.drawImage(image2, b.x, b.y,30,30,this);
			}
			else 
			{
				g.drawImage(image3, b.x, b.y,30,30,this);
			}
			//��ը���������ڼ�С
			b.lifeDown();
			
			if(b.life==0)
			{
				bombs.remove(b);
			}
		}
		
		//�������˵�̹��
		for(int i=0;i<ets.size();i++)
		{
			EnemyTank et=ets.get(i);
			if(et.isLive)
			{
				this.DrawTank(ets.get(i).getX(), ets.get(i).getY(), g, ets.get(i).getDirect(), 1);
			}
		}
		
		
	}
		
	
	
	//дһ������ר���ж��ӵ��Ƿ���е���̹��
	public void HitTank(Shot s,EnemyTank et)
	{
		//�жϸ�̹�˵ķ���
		switch (et.direct) {
		//�������̹�������ϻ�������
		case 0:
		case 1:
			//����
			if (s.x>et.x-5&&s.x<et.x+20&&s.y>et.y&&s.y<et.y+30)
			{
				//�ӵ���̹������
				s.isLive=false;
				et.isLive=false;
				//����һ��ը��,���뼯����
				Bomb b=new Bomb(et.x, et.y);
				bombs.add(b);
			}
			break;
			//�������̹���������������
		case 2:
		case 3:
			//����
			if (s.x>et.x&&s.x<et.x+30&&s.y>et.y&&s.y<et.y+20)
			{
				//�ӵ���̹������
				s.isLive=false;
				et.isLive=false;
				//����һ��ը��,���뼯����
				Bomb b=new Bomb(et.x, et.y);
				bombs.add(b);
			}
			break;			

		default:
			break;
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
			if(this.hero.ss.size()<5)
			{
				this.hero.ShotEnemy();
			}
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
			
			//�ж��Ƿ���ез�̹��
			for(int i=0;i<hero.ss.size();i++)
			{
				//ȡ��һ���ӵ�
				Shot myShot=hero.ss.get(i);
				//�ж��ӵ��Ƿ���
				if(myShot.isLive)
				{
					//ȡ��ÿ��̹��,���ӵ�ƥ��
					for(int j=0;j<ets.size();j++)
					{
						//ȡ��̹��
						EnemyTank et=ets.get(j);
						//�ж�̹���Ƿ���
						if(et.isLive)
						{
							this.HitTank(myShot, et);
						}
					}
				}
			}
			
			//�ػ�
			this.repaint();
		}
	}
	
}

