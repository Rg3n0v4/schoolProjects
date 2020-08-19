import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.function.Consumer;

public class Client {

    private String IPAddress;
    private int portNumber;

    private connectionThread cThread = new connectionThread();
    private Consumer <Serializable> callBack;
    private Consumer<Serializable> callBackMonitor;

    Client (String IPAddress, int portNumber, Consumer <Serializable> callBack1, Consumer <Serializable> callBack2)
    {
        this.callBack = callBack1;
        this.callBackMonitor = callBack2;
        cThread.setDaemon(true);
        this.IPAddress = IPAddress;
        this.portNumber = portNumber;
    }

    // to start the connection with the server
    public void startConnection () throws Exception
    {
        cThread.start();
    }

    // to send information to the server
    public void send (Serializable info) throws Exception
    {
        cThread.output.writeObject(info);
    }

    // closes connection with the server
    public void closeConnection() throws Exception
    {
        cThread.socket.close();
    }

    class connectionThread extends Thread
    {
        private Socket socket;
        private ObjectOutputStream output;

        public void run()
        {
            try (
                Socket socket = new Socket (getIPAddress(), getPortNumber());
                ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream input = new ObjectInputStream(socket.getInputStream()))

                {
                    this.socket = socket;
                    this.output = output;
                    socket.setTcpNoDelay(true);

                    while (true)
                    {
                        Serializable data = (Serializable) input.readObject();
                        decode (data);
                    }
                }
                catch (Exception e)
                {
                    callBack.accept("Connection closed with client");
                }
            }

        }
        public String getIPAddress()
        {
            return this.IPAddress;
        }

        public int getPortNumber()
        {
            return this.portNumber;
        }

        private void decode (Serializable information)
        {
            String info = information.toString();

            if (info.charAt(0) == '!')
            {
                this.callBackMonitor.accept(information);
            }
            else
            {
                this.callBack.accept(information);
            }
        }


}
