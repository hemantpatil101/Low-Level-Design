package ChessGame;

public class ChessGame {
    public static void main(String[] args) {
        Game game = new Game();
        Player whitePlayer = game.getCurrentTurn();

        // Attempt to move white king from (0,4) to (1,4)
        boolean moveResult = game.playerMove(whitePlayer, 0, 4, 1, 4);
        System.out.println("Move successful: " + moveResult);
    }
}

class Spot {
    private int x;
    private int y;
    private Piece piece;

    public Spot(int x, int y, Piece piece) {
        this.x = x;
        this.y = y;
        this.piece = piece;
    }

    public int getX() { return x; }
    public int getY() { return y; }
    public Piece getPiece() { return piece; }
    public void setPiece(Piece piece) { this.piece = piece; }
    public boolean isOccupied() { return piece != null; }
}

abstract class Piece {
    private boolean isWhite;
    private boolean isKilled = false;

    public Piece(boolean isWhite) {
        this.isWhite = isWhite;
    }

    public boolean isWhite() { return isWhite; }
    public boolean isKilled() { return isKilled; }
    public void setKilled(boolean killed) { isKilled = killed; }

    public abstract boolean canMove(Board board, Spot start, Spot end);
}

class King extends Piece {
    public King(boolean isWhite) {
        super(isWhite);
    }

    @Override
    public boolean canMove(Board board, Spot start, Spot end) {
        if (end.isOccupied() && end.getPiece().isWhite() == this.isWhite()) {
            return false;
        }

        int x = Math.abs(start.getX() - end.getX());
        int y = Math.abs(start.getY() - end.getY());

        return x <= 1 && y <= 1;
    }
}

class Board {
    private Spot[][] spots;

    public Board() {
        spots = new Spot[8][8];
        initializeBoard();
    }

    private void initializeBoard() {
        // Initialize empty spots
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                spots[i][j] = new Spot(i, j, null);
            }
        }

        // Place white king
        spots[0][4].setPiece(new King(true));

        // Place black king
        spots[7][4].setPiece(new King(false));

        // You can initialize other pieces similarly
    }

    public Spot getSpot(int x, int y) {
        if (x < 0 || x > 7 || y < 0 || y > 7) {
            throw new IllegalArgumentException("Invalid board position");
        }
        return spots[x][y];
    }
}

class Player {
    private boolean isWhiteSide;
    private boolean isHumanPlayer;

    public Player(boolean isWhiteSide) {
        this.isWhiteSide = isWhiteSide;
        this.isHumanPlayer = true; // Assuming human player for now
    }

    public boolean isWhiteSide() { return isWhiteSide; }
    public boolean isHumanPlayer() { return isHumanPlayer; }
}

class Move {
    private Player player;
    private Spot start;
    private Spot end;
    private Piece pieceMoved;
    private Piece pieceKilled;

    public Move(Player player, Spot start, Spot end) {
        this.player = player;
        this.start = start;
        this.end = end;
        this.pieceMoved = start.getPiece();
        this.pieceKilled = end.getPiece();
    }

    public Player getPlayer() { return player; }
    public Spot getStart() { return start; }
    public Spot getEnd() { return end; }
    public Piece getPieceMoved() { return pieceMoved; }
    public Piece getPieceKilled() { return pieceKilled; }
}

class Game {
    private Player[] players;
    private Board board;
    private Player currentTurn;
    private java.util.List<Move> movesPlayed;

    public Game() {
        players = new Player[2];
        players[0] = new Player(true);
        players[1] = new Player(false);
        board = new Board();
        currentTurn = players[0];
        movesPlayed = new java.util.ArrayList<>();
    }

    public boolean playerMove(Player player, int startX, int startY, int endX, int endY) {
        Spot start = board.getSpot(startX, startY);
        Spot end = board.getSpot(endX, endY);
        Piece sourcePiece = start.getPiece();

        if (sourcePiece == null) {
            System.out.println("No piece at starting position.");
            return false;
        }

        if (sourcePiece.isWhite() != player.isWhiteSide()) {
            System.out.println("Not your piece.");
            return false;
        }

        if (!sourcePiece.canMove(board, start, end)) {
            System.out.println("Invalid move for the piece.");
            return false;
        }

        // Execute move
        Piece destPiece = end.getPiece();
        if (destPiece != null) {
            destPiece.setKilled(true);
        }
        

        Move move = new Move(player, start, end);
        movesPlayed.add(move);

        end.setPiece(sourcePiece);
        start.setPiece(null);

        // Switch turn
        currentTurn = currentTurn == players[0] ? players[1] : players[0];
        return true;
    }

    public Player getCurrentTurn() { return currentTurn; }
}
