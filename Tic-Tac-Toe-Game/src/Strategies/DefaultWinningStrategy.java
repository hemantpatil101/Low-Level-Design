package Strategies;

import Models.Board;
import Models.Player;

public class DefaultWinningStrategy implements WinningStrategy{
    public boolean CheckWinner(Board board,Player player)
    {
       for(int i=0;i<board.getSize();i++)
       {
           if(isWinningRow(board, i, player.getSymbol()) || isWinningCol(board, i, player.getSymbol()))
           {
               return true;
           }
       }

       return isWinningDiagonal(board, player.getSymbol());
    }

    private boolean isWinningRow(Board board, int row, char symbol) {
        for (int col = 0; col < board.getSize(); col++) {
            if (board.getCell(row, col) != symbol) return false;
        }
        return true;
    }

    private boolean isWinningCol(Board board, int col, char symbol) {
        for (int row = 0; row < board.getSize(); row++) {
            if (board.getCell(row, col) != symbol) return false;
        }
        return true;
    }

    private boolean isWinningDiagonal(Board board, char symbol) {
        int size = board.getSize();
        boolean mainDiag = true, antiDiag = true;

        for (int i = 0; i < size; i++) {
            if (board.getCell(i, i) != symbol) mainDiag = false;
            if (board.getCell(i, size - 1 - i) != symbol) antiDiag = false;
        }

        return mainDiag || antiDiag;
    }
}
