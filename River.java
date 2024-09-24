
public class River {
    public static final int MIN_TRAP = 5;
    public static final int MIN_CURRENT = 5;
    public static final int DEFAULT_POWERUP = 6;

    public static final int MAX_TRAP = 8;
    public static final int MAX_CURRENT = 8;

    private String objName;
    private int effectStrong;
    private int effectWeak;
    private boolean isSpecial;

    public River() {
        SetObjectName("");
        SetEffectStrong(0);
        SetEffectWeak(0);
        SetIsSpecial(false);
    }

    public River(String nm, int effectStrong, int effectWeak, boolean isSpecial) {
        SetObjectName(nm);
        SetEffectStrong(effectStrong);
        SetEffectWeak(effectWeak);
        SetIsSpecial(isSpecial);
    }

    public void SetObjectName(String s) {
        this.objName = s;
    }

    public String getObjName() {
        return this.objName;
    }

    public void SetEffectStrong(int n) {
        this.effectStrong = n;
    }

    public int GetEffectStrong() {
        return this.effectStrong;
    }

    public void SetEffectWeak(int n) {
        this.effectWeak = n;
    }

    public int GetEffectWeak() {
        return this.effectWeak;
    }

    public void SetIsSpecial(boolean isSpecial) {
        this.isSpecial = isSpecial;
    }

    public boolean GetIsSpecial() {
        return this.isSpecial;
    }

    public String getEffectDescrption() {
        return "";
    }

    public void EffectStrong(Player p) {

    }

    public void EffectWeak(Player p) {

    }

}
