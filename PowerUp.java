public class PowerUp extends River{

    public static final int POWERUP_EFFECT = 0;

    public PowerUp() {
        super("PowerUp", POWERUP_EFFECT, POWERUP_EFFECT, false);
    }

    public PowerUp(String nm, boolean isSpecial) {
        super(nm, POWERUP_EFFECT, POWERUP_EFFECT, isSpecial);
    }

    public void PowerUpEffect(Player p, boolean isSpecial) {

    }

    public void PowerUpEffect(Player p) {

    }
}
