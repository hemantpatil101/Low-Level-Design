package Strategies;

import Models.Board;
import Models.Player;

public interface WinningStrategy {
    public boolean CheckWinner(Board board, Player player);
}
