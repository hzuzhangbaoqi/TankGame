package com.itheima.domain;

import java.io.IOException;

import org.itheima.game.utils.DrawUtils;
import org.itheima.game.utils.SoundUtils;

import com.itheima.inter.Destroyable;

public class Blast extends Element implements Destroyable{
	//成员变量每个实例化出来的对象都会有一份
	private String[] imgsPath = { "res\\img\\blast_1.gif", "res\\img\\blast_2.gif", "res\\img\\blast_3.gif",
			"res\\img\\blast_4.gif", "res\\img\\blast_1.gif", "res\\img\\blast_5.gif", "res\\img\\blast_6.gif",
			"res\\img\\blast_7.gif", "res\\img\\blast_8.gif" };
	private int len = imgsPath.length;
	private int index;
	private boolean flag = false;

	public Blast() {

	}

	public Blast(int x, int y, int width, int height, int blood) {
		int bw = 0;
		int bh = 0;
		int[] arr;
		try {
			arr = DrawUtils.getSize("res\\img\\blast_1.gif");
			bw = arr[0];
			bh = arr[1];
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			SoundUtils.play("res\\snd\\blast.wav");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.x = x - (bw - width) / 2;
		this.y = y - (bh - height) / 2;
		if (blood > 0) {
			len = len / 2;
		}
	}

	@Override
	public void draw() {
		String imgPath = imgsPath[index];
		index++;
		if (index >= len) {
			index = 0;
			flag = true;
		}
		try {
			DrawUtils.draw(imgPath, x, y);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public boolean getIsDestroy(){
		return flag;
	}

}
