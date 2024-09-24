
//Importing libraries
import java.io.File; // Import the File class
import java.io.FileNotFoundException;
import java.io.FileWriter; // Import the FileWriter class
import java.io.IOException; // Import the IOException class to handle errors
import java.io.*;
import java.util.Scanner;

public class Displayer {
    /*
     * Player p1 = new Player();
     * Player p2 = new Player();
     * 
     * Game game = new Game(p1, p2, null, null);
     * 
     */
    /*
     * Player p1 = new Player("Aba");
     * Player p2 = new Player("Baba");
     * 
     * Game game = new Game(p1, p2, "Aba", "Baba");
     * 
     */

    // Arrays for storing data
    // creating 5 player and scores array that stores player name and their score
    // respectively
    private String[] playerName = new String[5];
    private int[] scores = new int[5];
    private int scoresCounter = 0;

    // Game game = new Game();
    // Game game = new Game(p1, p2, "Aba", "Baba");

    public void displayPlayerTurn(String pl) {
        System.out.printf("It is now Player %s 's turn \n", pl);
    }

    public void displayWinner(String pl, int score) {
        System.out.printf("Congratulation %s!!! YOU ARE THE WINNER OF THIS RACE!!! You have ended the race with a score of %d\n", pl, score);
    }

    public void displayAllPlayerPos(Game g, String p1, String p2) {
        Player player1 = g.GetPlayerByName(p1);
        Player player2 = g.GetPlayerByName(p2);
        
        System.out.printf("Player 1 Position: %d\n", player1.GetPlayerPos() + 1);
        System.out.printf("Player 2 Position: %d\n", player2.GetPlayerPos() + 1);
    }

    public void displayRiver(Game g, River[] river, String p1, String p2) {
        Player player1 = g.GetPlayerByName(p1);
        Player player2 = g.GetPlayerByName(p2);

        for (int i = 0; i < 85; i ++) {
            if (i == 84) {
                System.out.print("--\n");
            }
            else {
                System.out.print("--");
            }
        }
        System.out.print(" [START]");
        for (int i = 0; i < river.length; i++) {
            
            if(river[i] != null) {
                if( river[i] instanceof Current ) {
                    System.out.print( "[C]");
                }

                else if( river[i] instanceof Trap) {
                    System.out.print( "[#]");
                }

                else if( river[i] instanceof SkipEnemy || river[i] instanceof MoveForward || river[i] instanceof MoveEnemyBackward ) {
                    System.out.print( "[?]");
                }
            }
            else {
                if (i == player1.GetPlayerPos() && i == player2.GetPlayerPos()) {
                    System.out.printf(" <1,2> ");
                }
                else if (i == player2.GetPlayerPos()) {
                    System.out.print(" <2> ");
                }
                else if (i == player1.GetPlayerPos()) {
                    System.out.print(" <1> ");
                }
                else {
                    System.out.print(" ");
                }
            }
        }
        System.out.print("[ END ] ");
        for (int i = 0; i < 85; i ++) {
            if (i == 0) {
                System.out.print("\n--");
            }
            else if (i == 84) {
                System.out.print("--\n");
            }
            else {
                System.out.print("--");
            }
        }

        /*
         * *
         * River[] river = game.GetRiver();
         * //System.out.println(game.GetRiver());
         * for( int i = 0; i < river.length; i++) {
         * System.out.println(river[i]) ;
         * }
         */
    }

    public void displayScore() {
        try {
            File myFile = new File("‘TopScore.txt");
            Scanner myReader = new Scanner(myFile);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                System.out.println(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void CreateFile() {
        try {
            File myFile = new File("‘TopScore.txt");
            if (myFile.createNewFile()) {
                scoresCounter = 0;

                System.out.println("File created: " + myFile.getName());
            }

            else {
                scoresCounter = 0;
                System.out.println("File already exists.");
            }

        }

        catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }

    public void WriteToFile(String inputName, int inputScore) {

        if (scoresCounter < 5) {
            try {
                // myUpdater.write("Files in Java might be tricky, but it is fun enough!");
                playerName[scoresCounter] = inputName;
                scores[scoresCounter] = inputScore;

                FileWriter myUpdater = new FileWriter("‘TopScore.txt");
                BufferedWriter bw = new BufferedWriter(myUpdater);
                rearrangeScores();

                bw.write("  ------------------\n ");
                bw.write("| Name      | Steps |\n");
                bw.write("  ------------------ \n");
                for (int i = 0; i <= scoresCounter; i++) {
                    int space = spacer(playerName[i]);

                    bw.write("   " + playerName[i] + new String(new char[space]).replace("\0", " ") + scores[i] + "\n");

                }

                scoresCounter += 1;
                bw.flush();
                myUpdater.close();

            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        }

        else if (scoresCounter >= 5) {
            try {
                updatePlayerName(inputName);
                updateScore(inputScore);

                FileWriter myUpdater = new FileWriter("‘TopScore.txt");
                BufferedWriter bw = new BufferedWriter(myUpdater);

                rearrangeScores();
                bw.write("  ------------------\n ");
                bw.write("| Name      | Steps |\n");
                bw.write("  ------------------ \n");
                for (int i = 0; i <= 4; i++) {
                    int space = spacer(playerName[i]);
                    bw.write("   " + playerName[i] + new String(new char[space]).replace("\0", " ") + scores[i] + "\n");

                }

                scoresCounter += 1;
                bw.flush();
                myUpdater.close();

            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        }
    }

    private int spacer(String name) {
        int temp = 15;
        temp = temp - name.length();
        return temp;
    }

    // newName will be Player.getName();
    // it cannot be added here as it would be a static referencing a non-static
    // method
    /*
     * updatePlayerName Method
     * What it does: Updates the score board plyer's name
     * 
     * @ param - newName - player's name object
     * 
     * @ return - Nothing
     */
    public void updatePlayerName(String newName) {
        // if index 5 has value, it means that all arrays are filled
        // need to push everything one array up
        if (playerName[4] != null) {
            String temp = playerName[4];
            playerName[4] = playerName[3];
            playerName[3] = playerName[2];
            playerName[2] = playerName[1];
            playerName[1] = temp;
            playerName[0] = newName;
            // playerName[0] = ply1.GetName();

        }

        // in this case, not every index should have data stored in it.
        else {
            for (int i = 0; i < 5; i++) { // checks if each player index
                // if empty, enter data at that particular index
                // if not look at next index
                if (playerName[i] == null) {
                    playerName[i] = newName;
                    break; // escape loop
                }

            }
        }

    }

    /*
     * updateScore Method
     * What it does: Updates the score board player's score
     * 
     * @ param - newScore - player's score object
     * 
     * @ return - Nothing
     */
    public void updateScore(int newScore) {
        // if index 5 has value, it means that all arrays are filled
        // need to push everything one array up
        if (scores[4] != 0) {
            int temp = scores[0];
            scores[4] = scores[3];
            scores[3] = scores[2];
            scores[2] = scores[1];
            scores[1] = temp;
            scores[0] = newScore;

        }

        // in this case, not every index should have data stored in it.
        else {
            for (int i = 0; i < 5; i++) { // checks if each player index
                // if empty, enter data at that particular index
                // if not look at next index
                if (scores[i] == 0) {
                    scores[i] = newScore;
                    break; // escape loop
                }

            }
        }
    }

    /*
     * Score rearranger Method
     * What it does: Rearrange the scores of the past players
     * 
     * @ param - No parameters
     * 
     * @ return - Nothing
     */
    public void rearrangeScores() {
        for (int i = 0; i < scores.length; i++) {
            for (int j = 0; j < scores.length; j++) {

                if (scores[i] > scores[j]) {

                    int tempScore = scores[j];
                    scores[j] = scores[i];
                    scores[i] = tempScore;

                    String tempName = playerName[j];
                    playerName[j] = playerName[i];
                    playerName[i] = tempName;
                }

                // else if the scores are equal, or already in correct order,
            }
        }
    }

}
