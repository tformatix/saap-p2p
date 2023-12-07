public class TicTacToe {
    public String mBoard = " , , , , , , , , ";
    private final char mSymbol;
    
    TicTacToe(char _symbol){
        this.mSymbol = _symbol;
    }

    void takeInput(String _field) {

        char[] arr = mBoard.toCharArray();

        switch (_field) {
            case "A1":
                arr[0] = mSymbol;
                break;
            case "B1":
                arr[2] = mSymbol;
                break;
            case "C1":
                arr[4] = mSymbol;
                break;
            case "A2":
                arr[6] = mSymbol;
                break;
            case "B2":
                arr[8] = mSymbol;
                break;
            case "C2":
                arr[10] = mSymbol;
                break;
            case "A3":
                arr[12] = mSymbol;
                break;
            case "B3":
                arr[14] = mSymbol;
                break;
            case "C3":
                arr[16] = mSymbol;
                break;
            default:
                System.out.println("Not valid");
                break;
        }
        mBoard = new String(arr);
        printGrid();

    }

    void printGrid() {
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
