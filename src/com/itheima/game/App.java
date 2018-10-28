package com.itheima.game;

import java.util.Comparator;
import java.util.TreeSet;

import com.itheima.inter.Config;

public class App {
	public static void main(String[] args) {
		GameWindow gw = new GameWindow(Config.TITLE, Config.WIDTH, Config.HEIGHT, Config.FPS);
		gw.start();
	}
}
