import java.util.Scanner;

public class MoveEnemyBackward extends PowerUp {
    String name = "MoveEnemyBackward";
    Scanner input = new Scanner(System.in);
	
    public MoveEnemyBackward() {
		super("MoveEnemyBackwards", false);
	}
    
    @Override 
	public void PowerUpEffect(Player p) {
    	System.out.println("Please input how much you want to move enemy back 1-6");
        int n = input.nextInt();
    	
    	if ((p.GetPlayerPos() - n) >= Game.GAME_START_POS) {
            p.SetPlayerPos(p.GetPlayerPos() - n);
        } 
        else {
            p.SetPlayerPos(Game.GAME_START_POS);
        }
	}
    
	@Override 
    public String getEffectDescrption() {
        return "Move enemy player backwards X number of blocks.";
    }
    
}
