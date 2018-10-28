package com.itheima.domain;

import java.io.IOException;
import java.util.Arrays;

import org.itheima.game.utils.DrawUtils;

import com.itheima.inter.Attackable;
import com.itheima.inter.Blockable;
import com.itheima.inter.Destroyable;

public class Wall extends Element implements Blockable, Attackable,Destroyable{

	private int blood = 3;

	public Wall() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Wall(int x, int y, int width, int height, int blood) {
		super();
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.blood = blood;
	}

	public Wall(int x, int y) {
		try {
			int[] arr = DrawUtils.getSize("res\\img\\wall.gif");
			this.width = arr[0];
			this.height = arr[1];
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.x = x;
		this.y = y;
	}

	/**
	 * 绘制图片
	 */
	public void draw() {
		try {
			DrawUtils.draw("res\\img\\wall.gif", x, y);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int getLevel() {
		return 0;
	}

	@Override
	public Blast showBlast() {
		blood--;
		Blast blast = new Blast(x, y, width, height, blood);
		return blast;
	}


	@Override
	public boolean getIsDestroy() {
		if(blood <= 0){
			return true;
		}else{
			return false;
		}
	}

}
