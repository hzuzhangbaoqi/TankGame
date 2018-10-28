package com.itheima.domain;

import java.io.IOException;
import java.util.Arrays;

import org.itheima.game.utils.DrawUtils;

import com.itheima.inter.Blockable;

public class Water extends Element implements Blockable {
	public Water() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Water(int x, int y, int width, int height) {
		super();
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	public Water(int x, int y) {
		try {
			int[] arr = DrawUtils.getSize("res\\img\\water.gif");
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
			DrawUtils.draw("res\\img\\water.gif", x, y);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
