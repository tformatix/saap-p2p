import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPConsoleServer extends Thread {
    private final int mPort;
    private Socket mCommunicationSocket;
    private TicTacToe mTicTacToe;

    public TCPConsoleServer(int _port, TicTacToe _ticTacToe) {
        mPort = _port;
        mTicTacToe = _ticTacToe;
    }

    @Override
    public void run() {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(mPort);
            accept(serverSocket);
        } catch (IOException _e) {
            System.out.printf("-- SERVER ERROR: I/O problems (%s) --%n", _e.getMessage());
        } catch (Exception _e) {
            System.out.printf("-- SERVER UNKNOWN ERROR: %s --%n", _e.getMessage());
        } finally {
            if (serverSocket != null && !serverSocket.isClosed())
                try {
                    serverSocket.close();
                    System.out.println("-- SERVER SUCCESS: socket closed --");
                } catch (IOException _e) {
                    System.out.printf("-- SERVER ERROR: socket not closable (%s) --%n", _e.getMessage());
                }
        }
    }

    private void accept(ServerSocket serverSocket) throws IOException {
        while(true) {
            System.out.println("Server waiting for clients ...");

            Socket communicationSocket = serverSocket.accept();
            System.out.printf("Client connected to server %s:%s %n", communicationSocket.getInetAddress(), communicationSocket.getPort());
            mCommunicationSocket = communicationSocket;
            new Thread(() ->
            {
                try {
                    handleCommunication();
                } catch (IOException _e) {
                    System.out.printf("-- SERVER ERROR: I/O problems (%s) --%n", _e.getMessage());
                }
            }
            ).start();
        }
    }

    private void handleCommunication() throws IOException {
        // ### INPUT ###
        InputStream socketInputStream = mCommunicationSocket.getInputStream();
        BufferedReader socketReader = new BufferedReader(new InputStreamReader(socketInputStream));

        String receivedMessage;
        while ((receivedMessage = socketReader.readLine()) != null) {
            System.out.printf("-- SERVER RECEIVED %s --%n", receivedMessage);
            // TODO: print board
            mTicTacToe.board = receivedMessage;
        }
    }
}
