public class Main {
    private final static char SYMBOL = 'X';
    private final static int PORT_SERVER = 1870;
    private final static String ADDRESS_PEER = "10.27.100.235";
    private final static int PORT_PEER = 1871;


    public static void main(String[] args) {
        TicTacToe ticTacToe = new TicTacToe(SYMBOL);

        TCPConsoleServer server = new TCPConsoleServer(PORT_SERVER, ticTacToe);
        server.start();

        TCPConsoleClient client = new TCPConsoleClient(ADDRESS_PEER, PORT_PEER, ticTacToe);
        client.start();
    }
}