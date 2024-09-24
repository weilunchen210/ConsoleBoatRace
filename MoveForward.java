import java.util.Scanner;

public class MoveForward extends PowerUp{
	String name = "Move Forward";
	Scanner input = new Scanner(System.in);
	
	public MoveForward() {
		super("MoveForward", false);
	}

	@Override 
	public void PowerUpEffect(Player p) {
        System.out.println("Please input how much you want to move forward 1-6");
        int n = input.nextInt();
        
		if ((p.GetPlayerPos() + n) <= Game.GAME_END_POS) {
            p.SetPlayerPos(p.GetPlayerPos() + n);
            p.IncreaseScore(n);
        } 
        else {
            p.SetPlayerPos(Game.GAME_END_POS);
            p.IncreaseScore(p.GetScore() + (Game.GAME_END_POS - p.GetPlayerPos()));
        }
	}
    
	@Override 
    public String getEffectDescrption() {
        return "Move player forward X number of blocks.";
    }
	
}
