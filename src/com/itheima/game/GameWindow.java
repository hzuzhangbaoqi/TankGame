package com.itheima.game;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.CopyOnWriteArrayList;

import org.itheima.game.Window;
import org.itheima.game.utils.DrawUtils;
import org.itheima.game.utils.SoundUtils;
import org.lwjgl.input.Keyboard;

import com.itheima.domain.Blast;
import com.itheima.domain.Bullet;
import com.itheima.domain.Element;
import com.itheima.domain.Grass;
import com.itheima.domain.MyTank;
import com.itheima.domain.Steel;
import com.itheima.domain.Wall;
import com.itheima.domain.Water;
import com.itheima.inter.Attackable;
import com.itheima.inter.Blockable;
import com.itheima.inter.Config;
import com.itheima.inter.Destroyable;
import com.itheima.inter.Direction;
import com.itheima.inter.Hipable;
import com.itheima.inter.Moveable;

public class GameWindow extends Window {
	Wall wall;
	MyTank mt;
	List<Element> list = new CopyOnWriteArrayList<Element>();

	public GameWindow(String title, int width, int height, int fps) {
		super(title, width, height, fps);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onCreate() {
		try {
			SoundUtils.play("res\\snd\\start.wav", false);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/**
		 * 墙
		 */
		for (int i = 0; i < Config.WIDTH / 64; i++) {
			if (i == 3 || i == 7) {
				continue;
			}
			Element wall = new Wall(i * 64, 64);
			list.add(wall);
		}

		/**
		 * 水墙
		 */
		for (int i = 1; i < Config.WIDTH / 64; i++) {
			if (i == 6 || i == 7) {
				continue;
			}
			Element water = new Water(i * 64, 64 * 5);
			list.add(water);
		}
		/**
		 * 草丛
		 */
		for (int i = 1; i < Config.WIDTH / 64 - 1; i++) {
			if (i == 4) {
				continue;
			}
			Element grass = new Grass(i * 64, 64 * 3);
			list.add(grass);
		}

		/**
		 * 铁墙
		 */
		for (int i = 1; i < Config.WIDTH / 64; i++) {
			if (i == 3 || i == 7) {
				continue;
			}
			Element steel = new Steel(i * 64, 64 * 7);
			list.add(steel);
		}

		mt = new MyTank((Config.WIDTH - 64) / 2, (Config.HEIGHT - 64));
		list.add(mt);

		list.sort(new Comparator<Element>() {

			@Override
			public int compare(Element o1, Element o2) {
				// TODO Auto-generated method stub
				// 渲染级别
				return o1.getLevel() - o2.getLevel();
			}
		});
	}

	@Override
	protected void onMouseEvent(int key, int x, int y) {
		// 鼠标事件
		// TODO Auto-generated method stub

	}

	@Override
	protected void onKeyEvent(int key) {
		// 键盘事件
		// TODO Auto-generated method stub

		switch (key) {
		case Keyboard.KEY_UP:
			mt.move(Direction.UP);
			break;
		case Keyboard.KEY_DOWN:
			mt.move(Direction.DOWN);
			break;
		case Keyboard.KEY_LEFT:
			mt.move(Direction.LEFT);
			break;
		case Keyboard.KEY_RIGHT:
			mt.move(Direction.RIGHT);
			break;
		case Keyboard.KEY_SPACE:
			Bullet bullet = mt.shot();
			if (bullet != null) {
				list.add(bullet);
			}
			break;
		}

	}

	@Override
	protected void onDisplayUpdate() {
		// 屏幕刷新
		/*System.out.println(list.size());*/
		// 解决list集合线程安全问题,方式1:使用for循环;方式2:使用原生迭代器Iterator;方式3:使用CopyOnWriteArrayList集合保证线程安全
		/*
		 * for(int i = 0;i<list.size();i++){ list.get(i).draw(); if(list.get(i)
		 * instanceof Bullet){ if(((Bullet) list.get(i)).isDestroy()){
		 * list.remove(list.get(i)); } } }
		 */
		for (Element e : list) {
			e.draw();
		}
		
		for(Element e:list){
			if (e instanceof Hipable) {
				for(Element e1:list){
					if(e1 instanceof Attackable){
						Hipable bl = (Hipable)e;
						Attackable st = (Attackable)e1;
						boolean flag = bl.checkAttrack(st);
						if(flag){
							System.out.println("子弹碰上了");
							list.remove(e);
							Blast blast = st.showBlast();
							list.add(blast);
							break;
						}
					}
				}
			}
		}
		
		for (Element e1 : list) {
			if (e1 instanceof Moveable) {
				// e1是坦克
				for (Element e2 : list) {
					if (e2 instanceof Blockable) {
						//e2是障碍物
						Moveable mt = (Moveable) e1;
						Blockable block = (Blockable) e2;
						boolean flag = mt.checkHit(block);
						if (flag) {
							System.out.println("碰上了");
							break; 
						}
					}
				}
			}
		}
		
		for(Element e : list){
			if(e instanceof Destroyable){
				if(((Destroyable) e).getIsDestroy()){
					list.remove(e);
					break;
				}
			}
		}

	}
}
