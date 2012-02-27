import java.io.*; 
import java.net.*;
import java.util.Scanner;

public class TCPRouter {
    //routing table

    String ipArray[][] =  { {"/127.0.0.1", "/192.168.2.13","/192.168.2.2"},
            {"6000", "6001", "6002", "6003","6004","6005","6006","6007","6008","6009","6010","6011","6012","6013","7000", "7001", "7002", "7003","7004","7005","7006","7007","7008","7009","7010","7011","7012","7013"} };

    InetAddress ipAddress;
    //DatagramPacket receivePacket = null;   
    ServerSocket serverSocket = null;
    Socket outgoingServerSocket = null;    
    byte[] receiveData = new byte[1024]; 
    byte[] sendData  = new byte[1024];
    final int profile;
    String port;
    String localPort;
    /**
     * default constructor
     */
    public TCPRouter() {
        System.out.println("Select a router profile for the associated clients: (1 or 2)");
        Scanner in1 = new Scanner(System.in);
        profile = in1.nextInt();
        if(profile==1)
        try {
            serverSocket = new ServerSocket(5555);

        }catch( IOException e ) {
            System.out.println( e.getMessage() );
        }
        else if(profile==2)
            try {
            serverSocket = new ServerSocket(5556);

        }catch( IOException e ) {
            System.out.println( e.getMessage() );
        }

        //for(;;)

    }
    /**
     * receives data from clients and prepare it for forwarding
     */
    public void getAndBuildData() throws Exception {
        byte[] byteBuffer = new byte[32];
           int recvMsgSize;

       while(true) {
           try {
           System.out.println("begin loop " + serverSocket.isClosed());
            String received="";
           Socket receiveSock=serverSocket.accept();
            //serverSocket.receive( receivePacket );
             System.out.println("Handling client at " +
                     receiveSock.getInetAddress().getHostAddress() + " on port " +
                     receiveSock.getPort());
           InputStream in = receiveSock.getInputStream();
            
              // System.out.println("asjaejfjeaif");
            if ((recvMsgSize = in.read(byteBuffer)) != -1){
                received= new String(byteBuffer);
                //byteBuffer=received.getBytes();
            //out.write(byteBuffer, 0, recvMsgSize);
                //in.read(byteBuffer);

            System.out.println(received);

            }
               System.out.println("client finished send");
            //String sentence = new String(receivePacket.getData());
           int f=0;
           int m=0;
           //sendData = received.getBytes();
                // Disassemble the received packet into: the payload-data - packed, the IP - made InetAddress type //
           for(int j = 0; j < received.length(); j++){
                if(received.charAt(j) == '%'){
                   ipAddress = InetAddress.getByName(received.substring(1,j));
                    f = j;
                   break;
                }
           }
           for(int k =f+1; k < received.length(); k++){
                        if(received.charAt(k) == '%'){
                            port = (received.substring(f+1, k));
                            m=k;
                            break;
                        }
           }
           for(int l =m+1; l < received.length(); l++){
                        if(received.charAt(l) == '%'){
                            localPort = (received.substring(m+1, l));
                            received = receiveSock.getInetAddress().getHostAddress() + "%" + port + "%" + serverSocket.getLocalPort() + "%" + received.substring(l+1, received.length());
                            break;
                        }
           }
           System.out.println("Send to "  + ipAddress + " on "+ port);
                  System.out.println("Data: "+received);
           sendData = received.getBytes();
       // ipAddress = receivePacket.getAddress();
       // Loop through the Routing Table to find an IP match

            for( int i = 0 ; i < 28; i++ ) {
                System.out.println("begin loop2, check " + ipArray[1][i]+" versus " + port);
                if( ipArray[1][i].equals(port) ) {
                    try {
                        if(profile==1){
                         if(localPort.equals(5556)==false){
                            outgoingServerSocket= new Socket("127.0.0.1",5556);}
                         else {outgoingServerSocket=new Socket("127.0.0.1",6001);}
                        }
                        else {
                         if(localPort.equals(5555)==false){
                            outgoingServerSocket= new Socket("127.0.0.1",5555);}
                         else {outgoingServerSocket=new Socket("127.0.0.1",7001);}

                        }
                        }catch( IOException e ) {
                            System.out.println( e.getMessage() );}
                            System.out.println("found "+port + ", sending message");
									 OutputStream out = outgoingServerSocket.getOutputStream();
                        out.write(sendData, 0, recvMsgSize);
                    break;   }
                }

            }catch ( IOException e ) {
            System.out.println("Server error" + e.getMessage());

           //this.Router( 0, sendData, ipAddress, port );
       }

       try { Thread.sleep(1500); } catch (Exception e) {}
       }
    }

    public static void main(String args[]) throws Exception { 
        TCPRouter tcpServer = new TCPRouter();

        tcpServer.getAndBuildData();
    }
	 }