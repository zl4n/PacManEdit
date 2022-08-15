package com.gcstudios.entities;


import java.awt.Graphics;
import java.awt.image.BufferedImage;


import com.gcstudios.main.Game;
import com.gcstudios.world.Camera;
import com.gcstudios.world.World;

public class Player extends Entity{
	
	public boolean right,up,left,down;
	
	public int lasDir = 1;
	
	private boolean moved = false;
	
	private int frames = 0, maxFrames = 5, index = 0, maxIndex =3;
	
	private BufferedImage[] rightPlayer;
	private BufferedImage[] leftPlayer;

	public boolean isDamaged = false;
	private int damageFrames = 0;

	public double life = 100,maxlife=100;
	
	
	

	public Player(int x, int y, int width, int height, double speed, BufferedImage sprite) {
		super(x, y, width, height,speed, sprite);
		
		rightPlayer   = new BufferedImage[4];
		leftPlayer    = new BufferedImage[4];
		
		for(int i = 0; i < 4; i++) {
			
			rightPlayer[i] = Game.spritesheet.getSprite(32 + (i*16), 0, 16, 16);
		}
		
		for(int i = 0; i < 4; i++) {
			
			leftPlayer[i] = Game.spritesheet.getSprite(32 + (i*16), 16, 16, 16);
		}
	
	}
	
	public void tick(){
		depth = 1;
		moved = false;
		if(right && World.isFree((int)(x+speed),this.getY())) {
			moved = true;
			x+=speed;
			lasDir = 1;
		}
		else if(left && World.isFree((int)(x-speed),this.getY())) {
			moved = true;
			x-=speed;
			lasDir = -1;
		}
		if(up && World.isFree(this.getX(),(int)(y-speed))){
			moved = true;
			y-=speed;
		}
		else if(down && World.isFree(this.getX(),(int)(y+speed))){
			moved = true;
			y+=speed;
		}
		
		if(moved) {
			frames++;
			if(frames == maxFrames) {
				frames = 0;
				index++;
				if(index > maxIndex)
					index = 0;
			}
		}
		
		eatMaca();
		
		if(isDamaged) {
			this.damageFrames++;
			if(this.damageFrames == 8) {
				this.damageFrames = 0;
				isDamaged = false;
			}
		}
		
		if(life<=0) {
			//Game over!
			life = 0;
			World.restartGame();
		}
		
		if(Game.maca_number == Game.maca_number_now) {
			
			World.restartGame();
		}	
		
		
	}	
	
	private void eatMaca() {
		for(int i = 0 ;  i < Game.entities.size() ; i++) {
			Entity current  = Game.entities.get(i);
				if(current instanceof Maca) {
					if(Entity.isColidding(this, current)) {
						Game.maca_number_now++;
						Game.entities.remove(i);
						return; 
					}
				}
		}
		
	}

	public void render(Graphics g) {
			
		if(lasDir == 1) {
			g.drawImage(rightPlayer[index], this.getX() - Camera.x,this.getY() - Camera.y, null);
		}else {
			g.drawImage(leftPlayer[index], this.getX() - Camera.x,this.getY() - Camera.y, null);
		}
	  }
			
	

}
