import java.io.*;
import java.net.*;

import com.sun.corba.se.impl.protocol.giopmsgheaders.Message;

class RUDP_Client
{
    class sendSegment
    {
        int seqacknum;
        byte[] data;
    }

    public static void main(String args[]) throws Exception
    {
        DatagramSocket clientSocket = new DatagramSocket();
        InetAddress IPAddress = InetAddress.getByName("localhost");
        byte[] sendData = new byte[1024];
        byte[] receiveData = new byte[1024];
        ObjectInputStream iStream = new ObjectInputStream(new ByteArrayInputStream(receiveData));
        

        while(true)
        {
            
            sendSegment sent = (sendSegment) iStream.readObject();
            iStream.close();
            receiveData = sent.data;
            int seq = sent.seqacknum;
            System.out.println(seq);
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 9876);
            clientSocket.send(sendPacket);
            DatagramPacket receievePacket = new DatagramPacket(receiveData,receiveData.length);
            clientSocket.receive(receievePacket);
            

            System.out.println("");
        }
    }
}