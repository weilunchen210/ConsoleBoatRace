public class SkipEnemy extends PowerUp {
	
	public SkipEnemy() {
		super("SkipEnemy", true);
	}

	@Override
	public void PowerUpEffect(Player p, boolean isSpecial) {
		if (isSpecial) {
			if(!p.GetIsSkipped()) {
				p.SetIsSkipped(true);
			}
		}
	}

	@Override 
    public String getEffectDescrption() {
        return "Skip enemy player for 1 turn.";
    }
}
