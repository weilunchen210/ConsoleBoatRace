import java.util.Scanner;

public class Game {
    public static final int DEFAULT_RIVER_SIZE = 100;
    public static final int DEFAULT_PLAYER_AMT = 2;
    public static final int DEFAULT_DICE_MIN = 1;
    public static final int DEFAULT_DICE_MAX = 6;
    public static final int GAME_START_POS = 0;
    public static final int GAME_END_POS = 99;
    
    public static final int BLOCK_IS_TRAP = 1;
    public static final int BLOCK_IS_CURRENT = 2;
    public static final int BLOCK_IS_MOVE_FORWARD = 3;
    public static final int BLOCK_IS_MOVE_ENEMY_BACKWARD = 4;
    public static final int BLOCK_IS_SKIP_ENEMY = 5;
    public static final int BLOCK_IS_EMPTY = 0;

    public static final int PLAYER1_WIN = 1;
    public static final int PLAYER2_WIN = 2;

    public static final int INVALID = -1;

    private Player[] allPlayer;
    private River[] river;

    private int winningPos;
    private String currPlayer;


    public Game(String n1, String n2) {
        this.river = new River[DEFAULT_RIVER_SIZE];
        this.allPlayer = new Player[DEFAULT_PLAYER_AMT];
        this.winningPos = GAME_END_POS;
        this.currPlayer = n1;
        allPlayer[Player.DEFAULT_PLAYER1_ID] = new Player(n1);
        allPlayer[Player.DEFAULT_PLAYER2_ID] = new Player(n2);

        for (int i = 0; i < DEFAULT_RIVER_SIZE; i ++) 
        {
            if (i == 9 || i == 29 || i == 49 || i == 69 || i == 89) {
                int rand_num = getRandomNumber(1, 4);
                if (rand_num == 1) {
                    river[i] = new MoveForward();
                }
                else if (rand_num == 2) {
                    river[i] = new MoveEnemyBackward();
                }
                else if (rand_num == 3) {
                    river[i] = new SkipEnemy();
                }
            }
            
        }

        for (int i = 0; i < DEFAULT_RIVER_SIZE; i += 10) {
            if (i > 9 && i < 19) {
                river[getRandomPos(river, 10, 18)] = new Trap();
                river[getRandomPos(river, 10, 18)] = new Current();
            }
            else if (i > 19 && i < 29) {
                river[getRandomPos(river, 20, 28)] = new Trap();
                river[getRandomPos(river, 20, 28)] = new Current();
            }
            else if (i > 29 && i < 39) {
                river[getRandomPos(river, 30, 38)] = new Trap();
                river[getRandomPos(river, 30, 38)] = new Current();
            }
            else if (i > 39 && i < 49) {
                river[getRandomPos(river, 40, 48)] = new Trap();
                river[getRandomPos(river, 40, 48)] = new Current();
            }
            else if (i > 49 && i < 59) {
                river[getRandomPos(river, 50, 58)] = new Trap();
                river[getRandomPos(river, 50, 58)] = new Current();
            }
            else if (i > 59 && i < 69) {
                river[getRandomPos(river, 60, 68)] = new Trap();
                river[getRandomPos(river, 60, 68)] = new Current();
            }
            else if (i > 69 && i < 79) {
                river[getRandomPos(river, 70, 78)] = new Trap();
                river[getRandomPos(river, 70, 78)] = new Current();
            }
            else if (i > 79 && i < 89) {
                river[getRandomPos(river, 80, 88)] = new Trap();
                river[getRandomPos(river, 80, 88)] = new Current();
            }
            else if (i > 89 && i < 99) {
                river[getRandomPos(river, 90, 98)] = new Trap();
                river[getRandomPos(river, 90, 98)] = new Current();
            }
        }
    }

    private int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min) ) + min);
    }

    private int CheckBlockLanded (Player p, River[] r) {
        if (r[p.GetPlayerPos()] != null) 
        {
            if (r[p.GetPlayerPos()] instanceof Trap) {
                return BLOCK_IS_TRAP;
            }
            else if (r[p.GetPlayerPos()] instanceof Current) {
                return BLOCK_IS_CURRENT;
            }
            else if (r[p.GetPlayerPos()] instanceof MoveForward) {
                return BLOCK_IS_MOVE_FORWARD;
            }
            else if (r[p.GetPlayerPos()] instanceof MoveEnemyBackward) {
                return BLOCK_IS_MOVE_ENEMY_BACKWARD;
            }
            else if (r[p.GetPlayerPos()] instanceof SkipEnemy) {
                return BLOCK_IS_SKIP_ENEMY;
            }
        }
        return BLOCK_IS_EMPTY;
    }

    private boolean CheckBlockFilled (River[] r, int pos) {
        if (r[pos] != null) {
            return true;
        }
        return false;
    }

    private int getRandomPos(River[] r, int min, int max) {
        int index = 0;
        boolean end = false;

        while(!end) {
            index = getRandomNumber(min, max);

            if (!CheckBlockFilled(r, index)) {
                return index;
            }
        }
        return index;
    }

    public Player GetPlayerByName(String nm) {
        if (allPlayer[Player.DEFAULT_PLAYER1_ID].GetName().toUpperCase().equals(nm.toUpperCase()) ) {
            return allPlayer[Player.DEFAULT_PLAYER1_ID];
        }
        else {
            return allPlayer[Player.DEFAULT_PLAYER2_ID];
        }
    }
    
    public void SetCurrPlayer(String n) {
        this.currPlayer = n;
    }

    public String GetCurrPlayer() {
        return this.currPlayer;
    }

    public void RollDice(Player p) {
        int move = getRandomNumber(DEFAULT_DICE_MIN, DEFAULT_DICE_MAX + 1);
        MovePlayerForward(p, move);
        System.out.println(p.GetName()+ " has rolled " + move);
    }

    public int CheckWinCondition(Player p1, Player p2) {
    	Player player1 = GetPlayerByName(p1.GetName());
        Player player2 = GetPlayerByName(p2.GetName());
    	if (player1.GetPlayerPos() == (this.winningPos)) {
            return PLAYER1_WIN;
        }
        else if (player2.GetPlayerPos() == (this.winningPos)) {
            return PLAYER2_WIN;
        }
        return INVALID;
    }

    public void MovePlayerForward (Player p, int n) {
        if ((p.GetPlayerPos() + n) <= GAME_END_POS) {
            p.SetPlayerPos(p.GetPlayerPos() + n);
            p.SetScore(p.GetPlayerPos() + n);
        } 
        else {
            p.SetPlayerPos(GAME_END_POS);
            p.SetScore(p.GetScore() + (GAME_END_POS - p.GetPlayerPos()));
        }
    }

    public void ResetSkip() {
        for (int i = 0; i < DEFAULT_PLAYER_AMT; i++) {
            if (allPlayer[i].GetIsSkipped()) {
                allPlayer[i].SetIsSkipped(false);
            }
        }
    }

    public void SwitchPlayer() {
        if (currPlayer.toUpperCase().equals(allPlayer[Player.DEFAULT_PLAYER1_ID].GetName().toUpperCase()) && allPlayer[Player.DEFAULT_PLAYER2_ID].GetIsSkipped() == false) {
            SetCurrPlayer(allPlayer[Player.DEFAULT_PLAYER2_ID].GetName());
        }
        else if (currPlayer.toUpperCase().equals(allPlayer[Player.DEFAULT_PLAYER2_ID].GetName().toUpperCase()) && allPlayer[Player.DEFAULT_PLAYER1_ID].GetIsSkipped() == false) {
            SetCurrPlayer(allPlayer[Player.DEFAULT_PLAYER1_ID].GetName());
        }
    }

    public Player GetOpponent() {
        if (currPlayer.toUpperCase().equals(allPlayer[Player.DEFAULT_PLAYER1_ID].GetName().toUpperCase() ) ) {
            return allPlayer[Player.DEFAULT_PLAYER2_ID];
        }
        else {
            return allPlayer[Player.DEFAULT_PLAYER1_ID];
        }
    }

    public River[] GetRiver() {
        return this.river;
    }

    public void CheckPlayerLand() {
        Player p = GetPlayerByName(currPlayer);

        if (CheckBlockLanded(p, river) != BLOCK_IS_EMPTY) 
        {
            if (CheckBlockLanded(p, river) == BLOCK_IS_TRAP) {
            	int effect = getRandomNumber(1, 3);
    
                if (effect == 1) {
                    Trap t = (Trap)river[p.GetPlayerPos()];
                    t.EffectStrong(p);
                    System.out.println("\nYou have hit a strong trap, trap strangth: 6.\n");
                }
                else if (effect == 2) {
                    Trap t = (Trap)river[p.GetPlayerPos()];
                    t.EffectWeak(p);
                    System.out.println("\nYou have hit a weak trap, trap strangth: 3.\n");
                }
            }
            else if (CheckBlockLanded(p, river) == BLOCK_IS_CURRENT) {
                int effect = getRandomNumber(1, 3);
                if (effect == 1) {
                    Current c = (Current)river[p.GetPlayerPos()];
                    c.EffectStrong(p);
                    p.IncreaseScore(Current.CURRENT_STRONG);
                    System.out.println("\nYou have hit a strong current, current strangth: 6.\n");
                }
                else if (effect == 2) {
                    Current c = (Current)river[p.GetPlayerPos()];
                    c.EffectWeak(p);
                    p.IncreaseScore(Current.CURRENT_WEAK);
                    System.out.println("\nYou have hit a strong current, current strangth: 3.\n");
                }
            }
            else if (CheckBlockLanded(p, river) == BLOCK_IS_MOVE_FORWARD) {
                MoveForward f = (MoveForward)river[p.GetPlayerPos()];
                System.out.println("\nYou landed on a power up and it's a move forward power up.");
                System.out.println("Effect: " + f.getEffectDescrption() + "\n");
                f.PowerUpEffect(p);
            }
            else if (CheckBlockLanded(p, river) == BLOCK_IS_MOVE_ENEMY_BACKWARD) {
                Player enemy = GetOpponent();
                MoveEnemyBackward e = (MoveEnemyBackward)river[p.GetPlayerPos()];
                System.out.println("\nYou landed on a power up and it's a move enemy backwards power up.");
                System.out.println("Effect: " + e.getEffectDescrption() + "\n");
                e.PowerUpEffect(enemy);
            }
            else if (CheckBlockLanded(p, river) == BLOCK_IS_SKIP_ENEMY) {
                Player enemy = GetOpponent();
                SkipEnemy s = (SkipEnemy)river[p.GetPlayerPos()];
                System.out.println("\nYou landed on a power up and it's a skip enemy power up.");
                System.out.println("Effect: " + s.getEffectDescrption() + "\n");
                s.PowerUpEffect(enemy, false);
            }
        }
    }

}
