package com.codingame.game;
import java.util.List;

import com.codingame.gameengine.core.AbstractPlayer.TimeoutException;
import com.codingame.gameengine.core.AbstractReferee;
import com.codingame.gameengine.core.MultiplayerGameManager;
import com.codingame.gameengine.module.entities.GraphicEntityModule;
import com.codingame.gameengine.module.endscreen.EndScreenModule;
import com.google.inject.Inject;
import com.codingame.gameengine.module.entities.Sprite;
import com.codingame.gameengine.module.entities.Text;

public class Referee extends AbstractReferee {
    // Uncomment the line below and comment the line under it to create a Solo Game
    // @Inject private SoloGameManager<Player> gameManager;
    @Inject private MultiplayerGameManager<Player> gameManager;
    @Inject private GraphicEntityModule graphicEntityModule;
    @Inject private EndScreenModule endScreenModule;
    Vaisseau vaisseau, vaisseauboss;
    Sprite vaisseau_sprite, vboss_sprite;
    int score = 0, scoreboss=0;
    int nb_cross = 50;
    Cross cross[] = new Cross[nb_cross];
    Sprite cross_sprite[] = new Sprite[nb_cross];
    
    Text scoretext, scbosstext;
    
    @Override
    public void init() {
        // Initialize your game here.
    	vaisseau = new Vaisseau(0, 1920, 1080, 1000, 1000);
    	vaisseauboss = new Vaisseau(1, 1920, 1080, 1500, 1000);
    	    	
    	drawBackground();
    	
    	scoretext = graphicEntityModule.createText("Player: " + score)
        .setFontFamily("Lato")
        .setStrokeThickness(5) // Adding an outline
        .setStrokeColor(0x000050)
        .setFontSize(50)
        .setX(0).setY(0)
        .setFillColor(0xff0000);

    	scbosstext = graphicEntityModule.createText("BOSS: 0")
        .setFontFamily("Lato")
        .setStrokeThickness(5) // Adding an outline
        .setStrokeColor(0x000050)
        .setFontSize(50)
        .setX(0).setY(75)
        .setFillColor(0xff0000);
    }
    
    public void drawBackground() {
    	graphicEntityModule.createSprite()
        .setImage("sky.jpg")
        .setAnchor(0);
    	
    	vaisseau_sprite = graphicEntityModule.createSprite()
        .setImage("vaisseau.png")
        .setX(vaisseau.getX())
        .setY(vaisseau.getY());
    	
    	vboss_sprite = graphicEntityModule.createSprite()
    	        .setImage("ennemy.png")
    	        .setX(vaisseauboss.getX())
    	        .setY(vaisseauboss.getY());
    	
    	String[] nomcross = {"croix.png", "croixptl.png", "croixrg.png", "croixsh.png", "croixtrop.png", "croixtrop2.png",};
    	
    	int inm = 0;
    	for(int i = 0;i < nb_cross;++i) {
    		cross[i] = new Cross(1920, 1080);
    		cross_sprite[i] = graphicEntityModule.createSprite()
    		.setImage(nomcross[inm]);
    		inm  =(inm+1)%nomcross.length;
    		
    	}
    }

    @Override
    public void gameTurn(int turn) {
    	int pl = 0;
        for (Player player : gameManager.getActivePlayers()) {
        	String s = "";
        	if(pl == 0) {
        		player.sendInputLine(vaisseau.getX() + " " + vaisseau.getY());
        	}
        	else {
        		player.sendInputLine(vaisseauboss.getX() + " " + vaisseauboss.getY());
        	}
        	++pl;
        	//nb_cross
            player.sendInputLine(String.valueOf(nb_cross));
            for(int i = 0;i < nb_cross;++i) {
            	player.sendInputLine(cross[i].getX() + " " + cross[i].getY() + " " + cross[i].getValue());
            }
                        
            player.execute();
        }
        
        for(int i = 0;i < nb_cross;++i) {
        	cross[i].Move(cross_sprite[i]);
        	cross_sprite[i].setX(cross[i].getX()).setY(cross[i].getY());        
        }
        
        scoretext.setText("Player : " + score);
        scbosstext.setText("BOSS : " + scoreboss);
        
      
        //for (Player player : gameManager..getActivePlayers()) {
        Player player =  gameManager.getActivePlayers().get(0);
        try {
            	
                List<String> outputs = player.getOutputs();
                
                String ans = outputs.get(0);
                String[] as = ans.split(" ");
                //System.err.println(as[2].equals("JUMP"));
                boolean jump  = false;
                if(as.length == 3 && as[2].equals("JUMP")) {
                	jump = true;
                }
                /*else if(as.length == 3 && as[2] != "JUMP") {
                	player.deactivate("Bad output !!!" + player.getIndex());
                }*/
                vaisseau.setLX(vaisseau.getX());
                //if(!vaisseau.getJump() || !jump) {
                	
                	vaisseau.setX(Integer.parseInt(as[0]));
                //}
                vaisseau.Move(graphicEntityModule, vaisseau_sprite, jump);
                vaisseau.CollisionCross(cross);
                for(int i = 0;i < nb_cross;++i) {
                	if(cross[i].getDeath()) {
                		score += cross[i].getValue();
                		player.setScore(score);
                		cross[i].Init();
                		
                	}
                }
                vaisseau_sprite.setX(vaisseau.getX()).setY(vaisseau.getY());
                
                
            } catch (TimeoutException e) {
                player.deactivate(String.format("$%d timeout!", player.getIndex()));
            }
        
        //boss
        
        if(gameManager.getActivePlayers().size() == 2) {
	        player =  gameManager.getActivePlayers().get(1);
	        try {
	            	
	                List<String> outputs = player.getOutputs();
	                
	                String ans = outputs.get(0);
	                String[] as = ans.split(" ");
	                //System.err.println(as[2].equals("JUMP"));
	                boolean jump  = false;
	                if(as.length == 3 && as[2].equals("JUMP")) {
	                	jump = true;
	                }
	                /*else if(as.length == 3 && as[2] != "JUMP") {
	                	player.deactivate("Bad output !!!" + player.getIndex());
	                }*/
	                vaisseauboss.setLX(vaisseauboss.getX());
	                //if(!vaisseauboss.getJump() || !jump) {
	                	
	                	vaisseauboss.setX(Integer.parseInt(as[0]));
	                	
	                //}
	                vaisseauboss.Move(graphicEntityModule, vboss_sprite, jump);
	                vaisseauboss.CollisionCross(cross);
	                for(int i = 0;i < nb_cross;++i) {
	                	if(cross[i].getDeath()) {
	                		scoreboss += cross[i].getValue();
	                		player.setScore(scoreboss);
	                		cross[i].Init();
	                		
	                	}
	                }
	                vboss_sprite.setX(vaisseauboss.getX()).setY(vaisseauboss.getY());
	                
	        
	        } catch (TimeoutException e) {
	            player.deactivate(String.format("$%d timeout!", player.getIndex()));
	        }
	        
        }
                
    }
    
    @Override
    public void onEnd() {
      endScreenModule.setScores(gameManager.getPlayers().stream().mapToInt(p -> p.getScore()).toArray());
    }
    
    
    
}
