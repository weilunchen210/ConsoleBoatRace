public class Trap extends River{

    public static final int TRAP_STRONG = 6; 
    public static final int TRAP_WEAK = 3; 
    

    public Trap() {
        super("Trap", TRAP_STRONG, TRAP_WEAK, false);
    }

    @Override
    public void EffectStrong (Player p) {
        if ((p.GetPlayerPos() - TRAP_STRONG) >= Game.GAME_START_POS) {
            p.SetPlayerPos(p.GetPlayerPos() - TRAP_STRONG);
        } 
        else {
            p.SetPlayerPos((p.GetPlayerPos() + 1) - 1);
        }
    }

    @Override
    public void EffectWeak(Player p) {
        if ((p.GetPlayerPos() - TRAP_WEAK) >= Game.GAME_START_POS) {
            p.SetPlayerPos(p.GetPlayerPos() - TRAP_WEAK);
        } 
        else {
            p.SetPlayerPos(Game.GAME_START_POS);
        }
    }

    @Override 
    public String getEffectDescrption() {
        return "Move player backwards 3-6 number of blocks.";
    }
}
