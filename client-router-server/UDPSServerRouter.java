import java.io.*; 
import java.net.*;
public class UDPSServerRouter {
    //routing table
    String ipArray[][] =  { {"/127.0.0.1", "/192.168.2.13","/192.168.2.2"},
            {"21213", "21214", "21215", "21216","21217","21218","21219","21220","21221","21222","21223","21224","21225","21226"} };
    InetAddress ipAddress;
    DatagramPacket receivePacket = null;   
    DatagramSocket serverSocket = null;
    DatagramSocket outgoingServerSocket = null;    
    byte[] receiveData = new byte[1024]; 
    byte[] sendData  = new byte[1024]; 
    String port;
    /**
     * default constructor
     */
    public UDPSServerRouter() {
        try {
            serverSocket = new DatagramSocket(21212);
        
        }catch( SocketException e ) {
            System.out.println( e.getMessage() );
        }
        try {
            outgoingServerSocket = new DatagramSocket(21210);
        }catch( SocketException e ) {
            System.out.println( e.getMessage() );
        }
    }
    /**
     * receives data from clients and prepare it for forwarding
     */
    public void getAndBuildData() throws Exception {
       while(true) {
           System.out.println("begin loop " + serverSocket.isClosed());
            receivePacket = new DatagramPacket( receiveData, receiveData.length); 
            serverSocket.receive( receivePacket );
		   System.out.println("From " +receivePacket.getPort() + " " + receivePacket.getAddress());
            String sentence = new String(receivePacket.getData());
           int f=0;
           sendData = sentence.getBytes();
		// Disassemble the received packet into: the payload-data - packed, the IP - made InetAddress type //
           for(int j = 0; j < sentence.length(); j++){
                if(sentence.charAt(j) == '%'){
                   ipAddress = InetAddress.getByName(sentence.substring(1,j));
                    f = j;
                   break;
                }
           }
           for(int k =f+1; k < sentence.length(); k++){
                        if(sentence.charAt(k) == '%'){
                            port = (sentence.substring(f+1, k));
                            sentence = receivePacket.getAddress() + "%" + receivePacket.getPort() + "%" + sentence.substring(k+1, sentence.length());
                            break;
                        }
           }
           System.out.println("Send to "  + ipAddress + " on "+ port);
		  System.out.println("Data: "+sentence);
           sendData = sentence.getBytes();
       // ipAddress = receivePacket.getAddress();
       // Loop through the Routing Table to find an IP match

            for( int i = 0 ; i < 14; i++ ) {
                System.out.println("begin loop2, check " + ipArray[1][i]+" versus " + ipAddress);
                if( ipArray[1][i].equals(port) ) {

                            System.out.println("found "+port + ", sending message");
                        this.Router( i, sendData, ipAddress, ipArray[1][i] );
                    break;   }
                }
            }

           //this.Router( 0, sendData, ipAddress, port );
       }


    public static void main(String args[]) throws Exception { 
        UDPSServerRouter udpServer = new UDPSServerRouter();
        udpServer.getAndBuildData();
    }
    /**
     * determines which interface to bind to
     */
    public void Router( int link, byte[] message, InetAddress ipAddress,
        String ports ) throws Exception {
        
        int port = Integer.parseInt( ports );

        switch(link) {
            case 0:
                this.sendMessage( message, ipAddress, port );
                System.out.println("Sending to " + ipAddress + " " + port);
                break;
            case 1:
                this.sendMessage( message, ipAddress, port );
                System.out.println("Sending to " + ipAddress + " " + port);
                break;
            case 2:
                this.sendMessage( message, ipAddress, port );
                System.out.println("Sending to " + ipAddress + " " + port);
                break;
            case 3:
                this.sendMessage( message, ipAddress, port );
                System.out.println("Sending to " + ipAddress + " " + port);
                break;
            case 4:
                this.sendMessage( message, ipAddress, port );
                System.out.println("Sending to " + ipAddress + " " + port);
                break;
            case 5:
                this.sendMessage( message, ipAddress, port );
                System.out.println("Sending to " + ipAddress + " " + port);
                break;
            case 6:
                this.sendMessage( message, ipAddress, port );
                System.out.println("Sending to " + ipAddress + " " + port);
                break;
            case 7:
                this.sendMessage( message, ipAddress, port );
                System.out.println("Sending to " + ipAddress + " " + port);
                break;
            case 8:
                this.sendMessage( message, ipAddress, port );
                System.out.println("Sending to " + ipAddress + " " + port);
                break;
            case 9:
                this.sendMessage( message, ipAddress, port );
                System.out.println("Sending to " + ipAddress + " " + port);
                break;
            case 10:
                this.sendMessage( message, ipAddress, port );
                System.out.println("Sending to " + ipAddress + " " + port);
                break;
            case 11:
                this.sendMessage( message, ipAddress, port );
                System.out.println("Sending to " + ipAddress + " " + port);
                break;

            case 12:
                this.sendMessage( message, ipAddress, port );
                System.out.println("Sending to " + ipAddress + " " + port);
                break;
            case 13:
                this.sendMessage( message, ipAddress, port );
                System.out.println("Sending to " + ipAddress + " " + port);
                break;
            case 14:
                this.sendMessage( message, ipAddress, port );
                System.out.println("Sending to " + ipAddress + " " + port);
                break;
            case 15:
                this.sendMessage( message, ipAddress, port );
                System.out.println("Sending to " + ipAddress + " " + port);
                break;
            default : 
                System.out.println("No interface to bind to");
                break;
	}
    }
    /**
     * sends the message to ipAddress, port
     */
    public void sendMessage( byte[] message,  InetAddress ipAddress, int port ) {

        try {
            DatagramPacket sendPacket = new DatagramPacket( message, message.length, 
                ipAddress, port );
            System.out.println(sendPacket.getAddress() + " " + sendPacket.getPort() + " " + sendPacket.getData());
            outgoingServerSocket.send(sendPacket);
        }catch( IOException e ) {
            System.out.println( e.getMessage() );
        }
    }
}
