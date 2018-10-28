package com.itheima.domain;

import java.io.IOException;
import java.util.Arrays;

import org.itheima.game.utils.CollsionUtils;
import org.itheima.game.utils.DrawUtils;
import org.itheima.game.utils.SoundUtils;

import com.itheima.inter.Blockable;
import com.itheima.inter.Config;
import com.itheima.inter.Direction;
import com.itheima.inter.Moveable;

public class MyTank extends Element implements Moveable{
	private Direction dir = Direction.UP;
	private int speed = 32;
	private long lastMilliSecond = 0;
	private Direction badDir = null;

	public MyTank() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Direction getDir() {
		return dir;
	}

	public void setDir(Direction dir) {
		this.dir = dir;
	}

	public MyTank(int x, int y, int width, int height) {
		super();
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	public MyTank(int x, int y) {
		try {
			int[] arr = DrawUtils.getSize("res\\img\\tank_u.gif");
			this.width = arr[0];
			this.height = arr[1];
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.x = x;
		this.y = y;
	}

	/**
	 * 绘制图片
	 */
	public void draw() {
		switch (this.dir) {
		case UP:
			try {
				DrawUtils.draw("res\\img\\tank_u.gif", x, y);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case DOWN:
			try {
				DrawUtils.draw("res\\img\\tank_d.gif", x, y);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case LEFT:
			try {
				DrawUtils.draw("res\\img\\tank_l.gif", x, y);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case RIGHT:
			try {
				DrawUtils.draw("res\\img\\tank_r.gif", x, y);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		}
	}

	public void move(Direction dir) {
		if (badDir == dir) {
			return;
		}

		if (dir != this.dir) {
			this.dir = dir;
			return;
		}

		switch (dir) {
		case UP:
			y -= speed;
			break;
		case DOWN:
			y += speed;
			break;
		case LEFT:
			x -= speed;
			break;
		case RIGHT:
			x += speed;
			break;
		}

		if (x < 0) {
			x = 0;
		}
		if (x > Config.WIDTH - 64) {
			x = Config.WIDTH - 64;
		}
		if (y < 0) {
			y = 0;
		}
		if (y > Config.HEIGHT - 64) {
			y = Config.HEIGHT - 64;
		}
	}

	public Bullet shot() {
		// TODO Auto-generated method stub
		long nowMilliSecond = System.currentTimeMillis();
		if (nowMilliSecond - lastMilliSecond > 300) {
			lastMilliSecond = nowMilliSecond;
			return new Bullet(this);
		} else {
			return null;
		}

	}

	public boolean checkHit(Blockable block) {
		// 坦克特有的方法
		
		Element e = (Element)block;
		
		int x1 = this.x;
		int y1 = this.y;
		int w1 = this.width;
		int h1 = this.height;

		switch (this.dir) {
		case UP:
			y1 -= speed;
			break;
		case DOWN:
			y1 += speed;
			break;
		case LEFT:
			x1 -= speed;
			break;
		case RIGHT:
			x1 += speed;
			break;
		}

		int x2 = e.x;
		int y2 = e.y;
		int w2 = e.width;
		int h2 = e.height;
		boolean flag = CollsionUtils.isCollsionWithRect(x1, y1, w1, h1, x2, y2, w2, h2);
		if (flag) {
			badDir = this.dir;
		} else {
			badDir = null;
		}
		return flag;
	}

}
