
import java.util.Scanner;

public class Main {
	 public static void main(String[] args) {
     Displayer display = new Displayer();
     int optionsMainMenu = 0;
     int goBack = 0;
     int playAgain = 0;
     Scanner input = new Scanner(System.in);
     Boolean gameStart = false;
     Boolean mainMenu = true;
     Boolean inGame = false;
     Boolean finishGame = false;
     
     display.CreateFile();
	 while (mainMenu == true){
    	    System.out.println("==============================");
	        System.out.println("     Welcome to BoatRace      ");                     
	    	System.out.println("		 .  o ..");      
	    	System.out.println("	   o . o o.o");  
	    	System.out.println("           ...oo"); 
	    	System.out.println("             __[]__"); 
	    	System.out.println("          ___|_o_o_o|__"); 
	    	System.out.println("          \\----------/"); 
	    	System.out.println("           \\. ..  . /"); 
	    	System.out.println("     ^^^^^^^^^^^^^^^^^^^^");
	    	System.out.println("==============================");
	    	System.out.println("1. Play Game");
	    	System.out.println("2. Scoreboard");
	    	System.out.println("3. How to Play");
	    	System.out.println("4. Quit");
	    	System.out.println("==============================");
	    	System.out.println("Enter your option");
	    	optionsMainMenu = input.nextInt();
	    		    
	    		    if (optionsMainMenu == 1) {
	    		    	mainMenu = false;
	    		    	gameStart = true;
	    		    }
	    		    
	    		    else if(optionsMainMenu == 2) {
	    		    	mainMenu = false;
	    		    	display.displayScore();
	    		    	System.out.println("Enter 1 to go back");
	    		    	goBack = input.nextInt();
	    		    	  if (goBack == 1) {
	    		    		  mainMenu = true;
	    		    	  }
	    		    }
	    		    
	    		    else if(optionsMainMenu == 3) {	
	    		    	mainMenu = false;
	    		    	System.out.println("How to Play");
	    		    	System.out.println("=======================================================");
	    		    	System.out.println("1. This is a boat racing game played with two player");
	    		    	System.out.println("2. A player wins by reaching the 100th block");
	    		    	System.out.println("3. There will be traps,currents and powerups");
	    		    	System.out.println("4. Last, Have fun!");
	    		    	System.out.println("=======================================================");
	    		    	System.out.println("List of traps,current and powersup and what they do");
	    		    	System.out.println("Traps: moves you 3 or 6 blocks backwards depending if its weak or strong");
	    		    	System.out.println("Current: moves you 3 or6 blocks forward depending if its weak or strong");
	    		    	System.out.println("MoveForward: move you forward 1-6 blocks depending on what number you choose");
	    		    	System.out.println("MoveEnemyBackward: move enemy backward 1-6 blocks depending on what number you choose");
	    		    	System.out.println("SkipEnemy: Skip the opponent's turn");
	    		    	System.out.println("Press 1 to go back to the main menu");
	    		    	System.out.println("=======================================================");
	    		    	goBack = input.nextInt();
	    		    	  if (goBack == 1) {
	    		    		  mainMenu = true;
	    		    	  }
	    		    }
	    		   
	    		    else if(optionsMainMenu == 4) {
	    		    	System.out.println("Thanks for playing!");
	    		    	break;
	    		    }
	 
	       
	      while(gameStart == true) {
		     System.out.println("Please enter Player 1's name");
        	 Player player1 = new Player(input.next());
        	 System.out.println("Please enter Player 2's name");
        	 Player player2 = new Player(input.next());
        	 Game game = new Game(player1.GetName(),player2.GetName());
        	 River[] river = game.GetRiver();
             inGame = true;
        	 
        
        	 //ingame while loop
        	 while(inGame == true) {
        		 gameStart = false;
        		 if (game.CheckWinCondition(player1,player2) == 1 || game.CheckWinCondition(player1,player2) == 2) {
        			 finishGame=true;
        			 inGame=false;
        		 }
        			 
        		 if(game.GetCurrPlayer() == player1.GetName()) {
        		 System.out.println("=======================================================");
        		 display.displayRiver(game, river, player1.GetName(),player2.GetName());
        		 System.out.println("=======================================================");
        		 System.out.println("It is currently " + game.GetCurrPlayer() + "'s turn");
        		 System.out.println(game.GetCurrPlayer() + "'s current position");
        		 System.out.println("Enter anything to roll dice");
        		 System.out.println("=======================================================");
        		 display.displayAllPlayerPos(game,player1.GetName(),player2.GetName());
        		 input.next();
        		 game.RollDice(game.GetPlayerByName(game.GetCurrPlayer()));
        		 game.CheckPlayerLand();
        		 game.SwitchPlayer();
        		 }
        		 
        		 else if(game.GetCurrPlayer() == player2.GetName())
        		 { System.out.println("=======================================================");
        		 display.displayRiver(game, river, player1.GetName(),player2.GetName());
        		 System.out.println("=======================================================");
        		 System.out.println("It is currently " + game.GetCurrPlayer() + "'s turn");
        		 System.out.println("Enter anything to roll dice");
        		 System.out.println("=======================================================");
        		 display.displayAllPlayerPos(game,player1.GetName(),player2.GetName());
        		 input.next();
        		 game.RollDice(game.GetPlayerByName(game.GetCurrPlayer()));
        		 game.CheckPlayerLand();
        		 game.SwitchPlayer();
        		 }
        		 
        		 //Check whether got anyone win
        		 while(finishGame == true) {
        		 if (game.CheckWinCondition(player1,player2) == 1) {
        			System.out.println("=======================================================");
        			System.out.println(player1.GetName() +" has won with a score of "+ game.GetPlayerByName(player1.GetName()).GetScore() );
           		    System.out.println("Enter 0 to play again");
           		    System.out.println("Enter 1 to exit");
           		    System.out.println("======================================================="); 
           		    playAgain = input.nextInt();
           			    if (playAgain == 0) {
               			    display.WriteToFile(player1.GetName(), game.GetPlayerByName(player1.GetName()).GetScore());
           			    	inGame= false;
           			        gameStart=false;
           			        mainMenu=true;  
           			        finishGame=false;
           			    }
           			    else if (playAgain ==1) {
           			        inGame=false;
           			        gameStart=false;
           			        mainMenu=false;
           			        finishGame=false;
           			        System.out.println("Thanks for playing");
           			    }
           			    else if (playAgain != 1 || playAgain!= 2) {
           			    	System.out.println("Invalid command, please input 1 or 0");
           			    }
        		 }
           			          
           		 if (game.CheckWinCondition(player1,player2) == 2){
           			   System.out.println("=======================================================");
           			    System.out.println(player2.GetName() +"has won with a score of "+ game.GetPlayerByName(player2.GetName()).GetScore() );
            		    System.out.println("Enter 0 to play again");
            		    System.out.println("Enter 1 to exit");
            		    System.out.println("=======================================================");
            		    playAgain = input.nextInt() ;
            			    if (playAgain == 0) {
                    			display.WriteToFile(player2.GetName(), game.GetPlayerByName(player2.GetName()).GetScore());
            			    	inGame= false;
            			        gameStart=false;
            			        mainMenu=true;
            			     finishGame=false;
            			    }
            			    else if (playAgain ==1) {
            			        inGame=false;
            			        gameStart=false;
            			        mainMenu=false;
            			        finishGame=false;
            			        System.out.println("Thanks for playing");}
            			    else if (playAgain != 1 || playAgain!= 2) {
                    			System.out.println("Invalid command, please input 1 or 0");
            			    }
            			    
           		 }
        		 
        		
        		 }
        		 }
	      }
        
	 }
	 }
	}
	 



