package com.itheima.domain;

import java.io.IOException;

import org.itheima.game.utils.CollsionUtils;
import org.itheima.game.utils.DrawUtils;
import org.itheima.game.utils.SoundUtils;

import com.itheima.inter.Attackable;
import com.itheima.inter.Config;
import com.itheima.inter.Destroyable;
import com.itheima.inter.Direction;
import com.itheima.inter.Hipable;

public class Bullet extends Element implements Hipable,Destroyable{
	private Direction dir;
	private int speed = 3;

	public Bullet() {
		super();
	}

	public Bullet(MyTank mt) {
		int tx = mt.x;
		int ty = mt.y;
		int tWidth = mt.width;
		int tHeight = mt.height;
		this.dir = mt.getDir();

		/*
		 * 向上发射子弹: zx = tx + (tw - zw)/2; zy = ty - zh; 向下发射子弹: zx = tx + (tw -
		 * zw)/2; zy = ty + th; 向左发射子弹: zx = tx - zw; zy = ty+(th-zh)/2; 向右发射子弹:
		 * zx = tx + tw; zy = ty + (th - zh)/2;
		 */
		switch (dir) {
		case UP:
			try {
				int[] arr;
				arr = DrawUtils.getSize("res\\img\\bullet_u.gif");
				this.width = arr[0];
				this.height = arr[1];
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			this.x = tx + (tWidth - this.width) / 2;
			this.y = ty - this.height;
			
			break;
		case DOWN:
			try {
				int[] arr = DrawUtils.getSize("res\\img\\bullet_d.gif");
				this.width = arr[0];
				this.height = arr[1];
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			this.x = tx + (tWidth - this.width) / 2;
			this.y = ty + tHeight;
			
			break;
		case LEFT:
			try {
				int[] arr = DrawUtils.getSize("res\\img\\bullet_l.gif");
				this.width = arr[0];
				this.height = arr[1];
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			this.x = tx - this.width;
			this.y = ty + (tHeight - this.height) / 2;
			
			break;
		case RIGHT:
			try {
				int[] arr = DrawUtils.getSize("res\\img\\bullet_r.gif");
				this.width = arr[0];
				this.height = arr[1];
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			this.x = tx + tWidth;
			this.y = ty + (tHeight - this.height) / 2;
			
			break;
		}
		try {
			SoundUtils.play("res\\snd\\fire.wav", false);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void draw() {
		switch (dir) {
		case UP:
			try {
				DrawUtils.draw("res\\img\\bullet_d.gif", x, y);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			y -= speed;

			break;
		case DOWN:
			try {
				DrawUtils.draw("res\\img\\bullet_u.gif", x, y);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			y += speed;
			break;
		case LEFT:
			try {
				DrawUtils.draw("res\\img\\bullet_r.gif", x, y);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			x -= speed;
			break;
		case RIGHT:
			try {
				DrawUtils.draw("res\\img\\bullet_l.gif", x, y);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			x += speed;
			break;

		}

	}

	public boolean getIsDestroy() {
		if (x < 0 || x > Config.WIDTH || y < 0 || y > Config.HEIGHT) {
			return true;
		} else {
			return false;
		}
	}

	public boolean checkAttrack(Attackable steel) {
		
		Element st = (Element)steel;
		int x1 = this.x;
		int y1 = this.y;
		int w1 = this.width;
		int h1 = this.height;
		
		int x2 = st.x;
		int y2 = st.y;
		int w2 = st.width;
		int h2 = st.height;
		
		boolean flag = CollsionUtils.isCollsionWithRect(x1, y1, w1, h1, x2, y2, w2, h2);
		
		return flag;
	}

}
