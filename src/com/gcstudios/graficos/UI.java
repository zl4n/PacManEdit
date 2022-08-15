package com.gcstudios.graficos;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import com.gcstudios.main.Game;



public class UI {

	public void render(Graphics g) {
		g.setColor(Color.WHITE);
		g.setFont(new Font ("arial", Font.BOLD,28));
		g.drawString("Apple"+Game.maca_number_now+"/"+Game.maca_number,50, 665);
		

		
		g.setColor(Color.red);
		g.fillRect(25, 25, 80, 25);
		g.setColor(Color.green);
		g.fillRect(25, 25, (int) ((Game.player.life /Game.player.maxlife)* 80), 25);
		g.setColor(Color.white);
		g.setFont(new Font ("arial", Font.BOLD, 18));
		g.drawString( "Life: "+(int)Game.player.life,30 ,48);
	}
	
}
