package Models;

import java.util.Arrays;

public class Board{
    
    private final int size;
    private final char [][] grid;

    public Board(int size)
    {
       this.size = size;
       this.grid = new char[size][size];

       for(char[] row : grid)
       {
           Arrays.fill(row, '-');
       }
    }

    public void DisplayBoard()
    {
        for(int i=0;i<size;i++)
        {
            for(int j=0;j<size;j++)
            {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
    }

    public boolean Makemove(int row,int col,char character)
    {
        if(row >= 0 && row < size && col >= 0 && col < size && grid[row][col] == '-')
        {
            grid[row][col] = character;
            return true;
        }

        System.out.println("Invalid Move, Try Again !!");

        return false;
    }

    public boolean IsFull()
    {

        for(int i=0;i<size;i++)
        {
            for(int j=0;j<size;j++)
            {
                if(grid[i][j] == '-')
                return false;
            }
        }

        return true;
    }

    public int getSize()
    {
        return size;
    }

    public char getCell(int row,int col)
    {
        return grid[row][col];
    }
};