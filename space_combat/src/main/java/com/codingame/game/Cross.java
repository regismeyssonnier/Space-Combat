package com.codingame.game;
import com.codingame.gameengine.module.entities.GraphicEntityModule;
import com.google.inject.Inject;
import java.util.List;
import java.util.Random;
import com.codingame.gameengine.module.entities.Sprite;

public class Cross {
	
	private int w;
	private int h;
	private int width;
	private int height;
	private int posX;
	private int posY;
	private double dir;
	private int value;
	private int vx;
	private boolean death;
	
	public Cross(int w, int h) {
		this.w = w;
		this.h = h;
		this.width = 75;
		this.height = 75;
		Random r = new Random();
		this.posX = r.nextInt(this.w);
		this.posY = r.nextInt(this.h/4);
		this.value = r.nextInt(1000);
		this.vx = 200;
		this.dir = 1.0;
		this.death = false;
		
	}
	
	public void setDeath() {
		this.death = true;
		
	}
	
	public boolean getDeath() {
		return this.death;
	}
	
	public int getValue() {
		return this.value;
	}
	
	public int getX() {
		return this.posX;
	}
	
	public int getY() {
		return this.posY;
	}
	
	public void setX(int x) {
		this.posX = x;
	}
	
	public void setY(int y) {
		this.posY = y;
	}
	
	public void Init() {
		Random r = new Random();
		this.posX = r.nextInt(this.w);
		this.posY = r.nextInt(this.h/4);
		this.vx = r.nextInt(100) + 200;
		this.death = false;
	}
	
	public void Move(Sprite sp) {
		
				
		if(this.posY + this.height > this.h) {
			
			Random r = new Random();
			this.posX = r.nextInt(this.w);
			this.posY = r.nextInt(this.h/4);
			this.vx = r.nextInt(100) + 200;
		
		}
		else {
			this.posY += this.dir * this.vx;
		}
		
	}
	
	
	
	

}
