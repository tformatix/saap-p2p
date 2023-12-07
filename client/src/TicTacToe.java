import java.util.HashMap;
import java.util.Map;

public class TicTacToe {
    private final static char SYMBOL = 'X';
    private final static char SYMBOL_PEER = '0';
    private final static Map<String, Integer> fieldMap = Map.of(
            "A1", 0,
            "B1", 2,
            "C1", 4,
            "A2", 6,
            "B2", 8,
            "C2", 10,
            "A3", 12,
            "B3", 14,
            "C3", 16
    );
    private String mBoard = " , , , , , , , , ";
    private boolean mIsWinnerDeclared;

    public void takeInput(String _field, boolean _isFromPeer) {
        if(!mIsWinnerDeclared) {
            char symbol = _isFromPeer ? SYMBOL : SYMBOL_PEER;
            char[] arr = mBoard.toCharArray();

            int idx = fieldMap.getOrDefault(_field, -1);
            if (idx > 0) {
                arr[idx] = symbol;
            }

            mBoard = new String(arr);
            printGrid();

            String winner = checkForWinner(mBoard.split(","));
            if (winner.equals(String.valueOf(SYMBOL))) {
                mIsWinnerDeclared = true;
                System.out.println("You have won!");
            } else if (winner.equals(String.valueOf(SYMBOL_PEER))) {
                mIsWinnerDeclared = true;
                System.out.println("Opponent has won!");
            }
        } else {
            System.out.println("Game already over!");
        }
    }

    private String checkForWinner(String[] board) {
        for (int i = 0; i < 3; i++) {
            if (checkLine(board[i * 3], board[i * 3 + 1], board[i * 3 + 2])) {
                return board[i * 3];
            }
            if (checkLine(board[i], board[i + 3], board[i + 6])) {
                return board[i];
            }
        }
        if (checkLine(board[0], board[4], board[8])) {
            return board[0];
        }
        if (checkLine(board[2], board[4], board[6])) {
            return board[2];
        }

        return "";
    }

    private boolean checkLine(String a, String b, String c) {
        return a.equals(b) && b.equals(c) && !a.trim().equals("");
    }

    public void printGrid() {
        char[] arr = mBoard.toCharArray();
        char a1 = arr[0];
        char b1 = arr[2];
        char c1 = arr[4];
        char a2 = arr[6];
        char b2 = arr[8];
        char c2 = arr[10];
        char a3 = arr[12];
        char b3 = arr[14];
        char c3 = arr[16];

        System.out.println("  | A  | B | C |");
        System.out.println("  ______________");
        System.out.println("1 | " + a1 + "  | " + b1 + " | " + c1 + " |");
        System.out.println("2 | " + a2 + "  | " + b2 + " | " + c2 + " |");
        System.out.println("3 | " + a3 + "  | " + b3 + " | " + c3 + " |");
        System.out.println("  ______________");
    }
}
