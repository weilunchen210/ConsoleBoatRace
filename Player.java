public class Player {
    public static final int DEFAULT_PLAYER1_ID = 0;
    public static final int DEFAULT_PLAYER2_ID = 1;

    private int pos;
    private int score;
    private String name;
    private boolean isSkipped;

    public Player() {
        SetName("");
        SetPlayerPos(Game.GAME_START_POS);
        this.isSkipped = false;
    }

    public Player(String name) {
        SetName(name);
        SetPlayerPos(Game.GAME_START_POS);
        this.isSkipped = false;
    }

    public void SetName(String name) {
        this.name = name;
    }

    public String GetName() {
        return this.name;
    }

    public void SetPlayerPos(int n) {
        this.pos = n;
    }

    public int GetPlayerPos() {
        return this.pos;
    }

    public void SetScore(int n) {
        this.score = n;
    }

    public int GetScore() {
        return this.score;
    }

    public void IncreaseScore(int n) {
        if (n > 0) {
            SetScore(GetScore() + n);
        }
    }

    public void SetIsSkipped(boolean condition) {
        if (this.isSkipped != condition) {
            this.isSkipped = condition;
        }
    }

    public boolean GetIsSkipped() {
        return this.isSkipped;
    }
}
