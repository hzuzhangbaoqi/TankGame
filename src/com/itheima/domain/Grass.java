package com.itheima.domain;

import java.io.IOException;
import java.util.Arrays;

import org.itheima.game.utils.DrawUtils;

public class Grass extends Element {
	public Grass() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Grass(int x, int y, int width, int height) {
		super();
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	public Grass(int x, int y) {
		try {
			int[] arr = DrawUtils.getSize("res\\img\\grass.gif");
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
		try {
			DrawUtils.draw("res\\img\\grass.gif", x, y);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public int getLevel() {
		return 1;
	}

}
