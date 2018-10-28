package com.itheima.domain;

public abstract class Element {
	protected int x;
	protected int y;
	protected int width;
	protected int height;
	public Element() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Element(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
	public abstract void draw();
	
	public int getLevel(){
		return 0;
	}
	
}
