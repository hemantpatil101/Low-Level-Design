package Controller;

import java.util.List;
import java.util.Scanner;

import Models.Board;
import Models.Player;
import Strategies.WinningStrategy;

public class GameController {
    private final Board board;
    private final List<Player> playerList;
    private final WinningStrategy winningStrategy;
    

    public GameController(Board board,List<Player> players,WinningStrategy winningStrategy)
    {
        this.board = board;
        this.playerList = players;
        this.winningStrategy = winningStrategy;
    }

    public void StartGame()
    {
        int currentPlayerIndex = 0;
        Scanner scanner = new Scanner(System.in);
        boolean gameEnded = false;

        while(!gameEnded)
        {
            board.DisplayBoard();
            Player currentPlayer = playerList.get(currentPlayerIndex);
            System.out.println(currentPlayer.getName() + "'s turn (Symbol: " + currentPlayer.getSymbol() + ")");

            int row = scanner.nextInt();
            int col = scanner.nextInt();

            while(!board.Makemove(row, col, currentPlayer.getSymbol()))
            {
                System.out.println("Enter Row and Column");

                row = scanner.nextInt();
                col = scanner.nextInt();
            }

            if(winningStrategy.CheckWinner(board, currentPlayer))
            {
                board.DisplayBoard();
                System.out.println("Player " + currentPlayer.getName() + "won the Game");
                gameEnded = true;
                break;
            }

            if(board.IsFull())
            {
                board.DisplayBoard();
                System.out.println("Its a Draw, try one more Time");
                gameEnded = true;
                break;
            }
            
            currentPlayerIndex = (currentPlayerIndex + 1) % (playerList.size());
        }
        return;
    }
}
