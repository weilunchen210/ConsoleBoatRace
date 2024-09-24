public class Current extends River{

    public static final int CURRENT_STRONG = 6; 
    public static final int CURRENT_WEAK = 3; 

    public Current() {
        super("Current", CURRENT_STRONG, CURRENT_WEAK, false);
    }

    @Override
    public void EffectStrong (Player p) {
        if ((p.GetPlayerPos() + CURRENT_STRONG) <= Game.GAME_END_POS) {
            p.SetPlayerPos(p.GetPlayerPos() + CURRENT_STRONG);
        } 
        else {
            p.SetPlayerPos(Game.GAME_END_POS);
        }
    }

    @Override
    public void EffectWeak(Player p) {
        if ((p.GetPlayerPos() + CURRENT_WEAK) <= Game.GAME_END_POS) {
            p.SetPlayerPos(p.GetPlayerPos() + CURRENT_WEAK);
        } 
        else {
            p.SetPlayerPos(Game.GAME_END_POS);
        }
    }

    @Override 
    public String getEffectDescrption() {
        return "Move player forward 3-6 number of blocks.";
    }
}
