import java.io.*;
import java.net.*;
import java.util.Vector;

class RUDP_Server
{
    class sendSegment
    {
        int seqacknum;
        byte[] data;
    }

    public boolean moveWindow(int size)
    {
        return false;
    }
    public static void main (String args[]) throws Exception
    {
        ByteArrayOutputStream bStream = new ByteArrayOutputStream();
        ObjectOutput oo = new ObjectOutputStream((bStream));
        
        Vector window = new Vector(5);
        DatagramSocket serverSocket = new DatagramSocket(9876);
        byte[] receieveD = new byte[1024];
        byte[] sendData;
        File inputFile = new File("c:/Users/Trevor/Desktop/alice.txt");
        Scanner fromFile = new Scanner(inputFile);
        while(true)
        {
            sendSegment w = new sendSegment(0, "hello");
            oo.writeObject(w);
            oo.close();
            DatagramPacket receievePacket = new DatagramPacket(receieve, receieve.length);
            serverSocket.receive(receievePacket);
            String sentence = new String (receievePacket.getData());
            sendData = bStream.toByteArray();
            /*while(fromFile.hasNextLine())
            {

            }*/

            InetAddress IPAddress = receievePacket.getAddress();
            int port = receievePacket.getPort();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress,port);

            serverSocket.send(sendPacket);
        }
    }
}