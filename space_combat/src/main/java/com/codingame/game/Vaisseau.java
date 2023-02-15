package com.codingame.game;
import com.codingame.gameengine.module.entities.GraphicEntityModule;
import com.google.inject.Inject;
import java.util.List;
import com.codingame.gameengine.module.entities.Sprite;

public class Vaisseau {
	private int posX;
	private int posY;
	private int lposX;
	private int lposY;
	private int width;
	private int height;
	private int w;
	private int h;
	private float dir;
	private int vx = 2;
	private int vy;
	private boolean in_jump;
	private boolean lock_jump;
	private float dir_jump;
	private int posVY;
	private float step_collision;
	private int id;
	
	public Vaisseau(int id, int w, int h, int x, int y) {
		this.id = id;
		this.w = w;
		this.h = h;
		this.posX = x;
		this.posY = y;
		this.posVY = y;
		this.lposX = x;
		this.lposY = y;
		this.width = 175;
		this.height = 50;
		this.dir = 1.0f;
		this.dir = 1.0f;
		this.vx = 200;
		this.vy = 200;
		this.in_jump = false;
		this.lock_jump =false;
		this.dir_jump = -1.0f;
		this.step_collision = 200.0f;
	}
	
	public int getX() {
		return this.posX;
	}
	
	public int getY() {
		return this.posY;
	}
	
	public void setLX(int lx) {
		this.lposX = lx;
	}
	
	public void setX(int x) {
		this.posX = x;
		
	}
	
	public boolean getJump() {
		return this.in_jump;
	}
	
	private void InJump() {
		if (!this.in_jump) {
			this.lock_jump = true;
			this.in_jump = true;
		}
	}
	
	
	private double distance(float x, float y, float x2, float y2) {
		return Math.sqrt((x-x2)*(x-x2) + (y-y2)*(y-y2));
	}
	
	public void CollisionCross(Cross[] cv) {
		
		float interx = (float)Math.abs(this.posX - this.lposX) / this.step_collision;
		float dhighy = Math.abs(this.posY - this.lposY);
		
		float x = this.lposX;
		float y = this.lposY;
		
		//System.err.println(x + " " + this.posX + " " + interx);
		
		boolean up = true;
		if(this.lposY > this.posY)up = false;
		
		if(x < this.posX) {
			
		
			while(x <= this.posX) {
				
				
			
				for(int i = 0;i < cv.length;++i) {
					if(!cv[i].getDeath()) {
						float p = Math.abs((float)this.posY - y ) / dhighy;
						if(up)y += p * dhighy;
						else y -= p * dhighy;
						double d = this.distance(x+(float)this.width/2.0f, y+(float)this.height/2.0f , cv[i].getX()+75/2, cv[i].getY()+75/2);
						if (d <= 200) {
							cv[i].setDeath();
							//System.err.println("Collsix " + this.id);
						}
					}
				}
				
				x += interx;
			

			}
		}
		else if (x > this.posX){
			while(x >= this.posX) {
												
				for(int i = 0;i < cv.length;++i) {
					if(!cv[i].getDeath()) {
						float p = Math.abs((float)this.posY - y ) / dhighy;
						if(up)y += p * dhighy;
						else y -= p * dhighy;
						double d = this.distance(x+(float)this.width/2.0f, y+(float)this.height/2.0f , cv[i].getX()+75/2, cv[i].getY()+75/2);
						if (d <= 200) {
							cv[i].setDeath();
							//System.err.println("Collsept " + this.id);
						}
					}
				}
				
				x -= interx;
			

			}
			
		}
		else {
			for(int i = 0;i < cv.length;++i) {
				if(!cv[i].getDeath()) {
					
					double d = this.distance(this.posX+(float)this.width/2.0f, this.posY+(float)this.height/2.0f, cv[i].getX()+75/2, cv[i].getY()+75/2);
					if (d <= 200) {
						cv[i].setDeath();
					}
				}
			}
		}
		
		
		
	}
	
	public void  Move(GraphicEntityModule gem, Sprite sv, boolean jumpb) {
			
		//this.posX += this.vx * this.dir

		///this.lposX = this.posX;
		this.lposY = this.posY;
		
		if(jumpb) {
			this.InJump();
		}
		if(this.in_jump) {
			this.posY += this.dir_jump * this.vy;
			if(this.posY < this.posVY - this.height*2) {
				this.dir_jump = 1.0f;
			}
			
			if(this.dir_jump == 1.0 && this.posY >= this.posVY) {
				this.posY= this.posVY;
				this.lock_jump = false;
				this.in_jump = false;
				this.dir_jump = -1.0f;
			}
			
			
		}
		
		//System.err.println(sv.getX() + " " + this.posX);
		if(this.posX + this.width > this.w) {
			this.dir = -1.0f;
			this.posX = this.w - this.width;
		}
		
		if ( this.posX < 0) {
			this.dir = 1.0f;
			this.posX = 0;
		}
			
		
			
			
			
			
			
	}
	
	
	
}
