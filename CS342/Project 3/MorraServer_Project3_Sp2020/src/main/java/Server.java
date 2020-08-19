

import java.util.ArrayList;
import java.util.function.Consumer;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    // stores the port number
    private int portNumber;

    private Consumer <Serializable> callBack; // for UI
    private Consumer <Serializable> callBackMonitor; // for UI
    private connectionThread cThread;
    private MorraInfo gamePlay;
    private ArrayList <String> keepTrack;
    private int resetGame;

    // private int newGame;

    Server (int portNumber, Consumer <Serializable> call1, Consumer <Serializable> call2)
    {
        // To handle calls to and from client
        this.callBack = call1;
        this.callBackMonitor= call2;

        this.cThread = new connectionThread();
        this.cThread.setDaemon(true);
        this.portNumber = portNumber;

        // supposed to keep track of the game status
        this.keepTrack = new ArrayList<>();

        // creating a new game using default constructor
        this.gamePlay = new MorraInfo();

        // setting newGameFlag to 0
        this.resetGame = 0;
    }

    // starts the connection between server and client
    public void startConnection() throws Exception
    {
        cThread.start();
    }

    // sends the serializable status of the game
    public void sendInformation (Serializable status) throws Exception
    {
        for (ClientThread td: this.cThread.socketList)
        {
            td.output.writeObject(status);
        }
    }

    // closes connection by closing socket
    public void closeConnection () throws Exception
    {
        for (ClientThread td: this.cThread.socketList)
        {
            td.connection.close();
        }
    }

    // gets the IP address
    public String getIPAddress()
    {
        return null;
    }
    // returns how many clients are on the server
    public int getClientNumber()
    {
        return this.cThread.socketList.size();
    }

    // returns port number
    int getPort()
    {
        return portNumber;
    }

    // This thread is responsible for regulating connection
    class connectionThread extends Thread
    {
        // contains the sockets that the client use to connect to server
        private ArrayList<ClientThread> socketList = new ArrayList<>();

        public void run()
        {
            try
            {
                ServerSocket mySocket = new ServerSocket(getPort());
                while (true)
                {
                    Socket sock = mySocket.accept();
                    ClientThread thread1 = new ClientThread (sock, socketList.size());
                    this.socketList.add(thread1);

                    // keeps track of how many clients are in the server
                    sendInformation("!clientNum" + this.socketList.size());
                    callBackMonitor.accept("!clientNum"+this.socketList.size());

                    thread1.start();
                }
            }
            catch (Exception e)
            {
                System.out.println(e);
                callBack.accept("Connection closed. This is from the connection thread server thread");
            }
        }
    }

    public class ClientThread extends Thread
    {
        int count;
        Socket connection;
        ObjectOutputStream output;
        ObjectInputStream input;

        ClientThread (Socket sock, int clientNumber)
        {
            this.connection = sock;
            this.count = clientNumber;

            try
            {
                this.connection.setTcpNoDelay(true);
                this.output = new ObjectOutputStream(this.connection.getOutputStream());
                this.input = new ObjectInputStream(this.connection.getInputStream());
                this.output.writeObject("You are client# "+ this.count);
            }
            catch (Exception e)
            {
                System.out.println(e);
            }
        }

        public void run()
        {
            try
            {
                while (true)
                {
                    Object inputData = input.readObject();
                    Object inputNumber = inputData;
                    if(inputData.toString().contains("!Guess"))
                        inputNumber = inputData.toString().replace("!Guess","");
                    else if(inputData.toString().contains("!Hand"))
                        inputNumber = inputData.toString().replace("!Hand","");


                    Serializable clientData = "Client# " + this.count + ": " + inputData;

                    if (clientData.toString().contains("!NewGame"))
                    {
                        Server.this.sendInformation("Client#" + this.count + " want to continue playing?");
                        callBack.accept("Client#" + this.count + " wants to continue playing");

                        Server.this.resetGame++;
                        if (Server.this.resetGame == 2)
                        {

                            System.out.println("NewGame");
                            Server.this.gamePlay = new MorraInfo();
                            callBackMonitor.accept("!NewGame");
                            Server.this.sendInformation("!noWaiting");
                            callBackMonitor.accept("!Score:" + gamePlay.scoreString());
                            Server.this.sendInformation("!Score:" + gamePlay.scoreString());
                        }
                    }
                    else
                    {
                        // what am I accepting here?
                        callBack.accept(clientData);
                        Server.this.sendInformation(clientData);

                        System.out.println("Entered else statement");
                        System.out.println(clientData);
                        gamePlay.addMove(this.count, Integer.parseInt(inputNumber.toString()));
                        // if no one won the game
                        if (gamePlay.roundOver())
                        {
                            callBack.accept("Round: "+gamePlay.getRoundNumber()/4+"is finished\n");
                            Server.this.sendInformation("Round: "+gamePlay.getRoundNumber()/4+" is finished\n");

                            //Server.this.sendInformation(gamePlay.printInfo());
                            int winner = gamePlay.evaluatePlayers();
                            if (winner == 0)
                            {
                                callBack.accept("Server: No one won!\n");
                                Server.this.sendInformation("Server: No one won!\n");
                            }
                            if (winner == 1)
                            {
                                callBack.accept("Server: Client#0 won!\n");
                                Server.this.sendInformation("Server: Client#0 won!\n");

                            }
                            if (winner == 2)
                            {
                                callBack.accept("Server: Client#1 won!\n");
                                Server.this.sendInformation("Server: Client#1 won!\n");

                            }
                            if (winner == 3)
                            {
                                callBack.accept("Server: No one won, it is a tie!\n");
                                Server.this.sendInformation("Server: No one won, it is a tie!\n");

                            }
                            Server.this.sendInformation("!noWaiting");

                            callBackMonitor.accept("!Score:" + gamePlay.scoreString());
                            Server.this.sendInformation(("!Score:" + gamePlay.scoreString()));
                        }
                        // if someone won the game
                        if (gamePlay.getPlayer1Score() == 2 || gamePlay.getPlayer2Score() == 2)
                        {
                            Server.this.sendInformation("Server: The game is over");
                            if (gamePlay.getPlayer1Score() == 2)
                            {
                                Server.this.sendInformation("Server: game winner: Client#0 ");
                            }
                            else
                            {
                                Server.this.sendInformation("Server: game winner: Client#1 ");
                            }
                            callBackMonitor.accept("Server: Game is over\n");
                            if (gamePlay.getPlayer1Score() == 2)
                            {
                                callBackMonitor.accept("Server: game winner: Client#0 ");
                            }
                            else
                            {
                                callBackMonitor.accept("Server: game winner: Client#1 ");
                            }
                            Server.this.sendInformation("!Game Over");

                        }
                    }
                }
            }
            catch (Exception e)
            {
                callBack.accept("OOOOPPs...Something wrong with the socket from client: " + count + "....closing down!");
            }
        }
    }


}
 //