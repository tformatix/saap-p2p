import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;


public class TCPConsoleClient extends Thread {
    private final String mAddress; // IP address
    private final int mPort; // port number
    private final TicTacToe mTicTacToe;


    public TCPConsoleClient(String _address, int _port, TicTacToe _ticTacToe) {
        mAddress = _address;
        mPort = _port;
        mTicTacToe = _ticTacToe;
    }

    @Override
    public void run() {
        Socket socket = null;
        try {
            BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in)); // Input from Console

            if(consoleReader.readLine().equals("connect")) {
                socket = new Socket(mAddress, mPort);

                // ### OUTPUT ###
                OutputStream socketOutputStream = socket.getOutputStream();
                PrintStream socketPrint = new PrintStream(socketOutputStream, true);

                System.out.printf("-- CLIENT COMMUNICATION INITIATED with %s:%s --%n", mAddress, mPort);

                while (!socket.isClosed()) {
                    System.out.println("Enter Field (e.g. A2):");
                    String field = consoleReader.readLine();

                    mTicTacToe.takeInput(field, false);

                    // Output to Socket
                    socketPrint.println(field);
                    System.out.printf("-- CLIENT SENT %s --%n", field);
                }
            }

        } catch (UnknownHostException _e) {
            System.out.printf("-- CLIENT ERROR: host unknown (%s) --%n", _e.getMessage());
        } catch (IOException _e) {
            System.out.printf("-- CLIENT ERROR: I/O problems (%s) --%n", _e.getMessage());
        } catch (Exception _e) {
            System.out.printf("-- CLIENT UNKNOWN ERROR: %s --%n", _e.getMessage());
        } finally {
            if (socket != null) try {
                socket.close();
                System.out.println("-- CLIENT SUCCESS: socket closed --");
            } catch (IOException _e) {
                System.out.printf("-- CLIENT ERROR: socket not closable (%s) --%n", _e.getMessage());
            }
        }
    }
}
