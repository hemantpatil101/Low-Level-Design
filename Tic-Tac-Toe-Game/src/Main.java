import java.util.ArrayList;

import Controller.GameController;
import Models.Board;
import Models.Player;
import Strategies.DefaultWinningStrategy;
import Strategies.WinningStrategy;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        Board board = new Board(3);
        Player player1 = new Player("Player 1",'X');
        Player player2 = new Player("Player 2",'O');

        WinningStrategy winningStrategy = new DefaultWinningStrategy();
        List<Player> playerList = new ArrayList<>();

        playerList.add(player1);
        playerList.add(player2);

        GameController gameController = new GameController(board,playerList, winningStrategy);
        gameController.StartGame();
    }
}
