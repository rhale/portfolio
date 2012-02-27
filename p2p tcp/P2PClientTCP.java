

import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import java.io.*;
import java.net.*;
import java.util.*;
public class P2PClientTCP {
    public static void main(String args[]) throws Exception
    {
       // ServerSocket servSock = new ServerSocket(5001);
       // out.write(byteBuffer);
        Scanner in = new Scanner(System.in);
         final String ports1[][] =  { {"127.0.0.1", "127.0.0.1"}, { "7001", "7003","7005","7007","7009","7011","7013"} };
         final String ports2[][] =  { {"127.0.0.1", "127.0.0.1"}, { "6001", "6003","6005","6007","6009","6011","6013"} };
        System.out.println("Select a network profile: (1 or 2");
        final int profile = in.nextInt();
        final int portOut;
        if(profile==1)
             portOut = 5555;
        else if(profile==2)
             portOut=5556;
        else {System.out.println("Incorrect profile, defaulting to 1");
             portOut = 5555;}
        System.out.println("Enter the inbound port number.");
        in = new Scanner(System.in);
        final int portIn = in.nextInt();
        System.out.println("run ready, press any key to continue");
        in = new Scanner(System.in);
        String begin = in.nextLine();

       Runnable r1 = new Runnable() {
       public void run() {
           byte[] byteBuffer = new byte[32];
           int recvMsgSize;
           ServerSocket servSock = null;
           try {
               servSock = new ServerSocket(portIn+1);
           } catch (IOException e) {
               e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
           }
           for (;;) {
        try {
            InetAddress ipAddress=null;
            Socket clntSock = servSock.accept();     // get client connection
             int f = 0;
            System.out.println("Handling client at " +
            clntSock.getInetAddress().getHostAddress() + " on port " +
             clntSock.getPort());
            InputStream in = clntSock.getInputStream();
            OutputStream out = clntSock.getOutputStream();
            String message1= "asdf";
            // receive until client disconnect
            while ((recvMsgSize = in.read(byteBuffer)) != -1){
                message1= new String(byteBuffer);

                //in.read(byteBuffer);
             for(int j = 0; j < message1.length(); j++){
                System.out.println(message1.substring(0,j));
                if(message1.charAt(j) == '%'){
                   ipAddress = InetAddress.getByName(message1.substring(1,j));
                    f = j;
                   break;
                }
           }
           for(int k =f+1; k < message1.length(); k++){
               System.out.println(message1.substring(f+1,k));
                        if(message1.charAt(k) == '%'){
                           // port = Integer.parseInt(message1.substring(f+1, k));
                            message1 = ipAddress + "%" + message1.substring(f+1, k) + "%" + message1.substring(k+1, message1.length()).toUpperCase();
                            break;
                        }
           }
            byteBuffer=message1.getBytes();
            out.write(byteBuffer, 0, recvMsgSize);
            System.out.println(message1);
            System.out.println("client finished send");
            }

            //in.read(byteBuffer);

            //byte[] message = message1.getBytes();
            //InputStream value = clntSock.getInputStream();
            //clntSock.getOutputStream().write(message);
            //clntSock.close();  //close the socket
            //System.out.println("loopingS");

        }catch( IOException e ) {
            System.out.println("Server error" + e.getMessage());
        }}
     }
    };
      Runnable r2 = new Runnable() {
        //PrintWriter out = null;
        //BufferedReader in = null;
        public void run() {
           String test = "test";
            int recvMsgSize;
            //byte[] sendBuffer= new byte[32];
           byte[] byteBuffer = test.getBytes();
           System.out.println("");
            Random r = new Random();       //randomize server destination
        int rand = r.nextInt(2);

            SocketAddress local1 = null;
            Socket sock1 = null;
            ServerSocket sock2 = null;
            try {
                local1 = new InetSocketAddress(InetAddress.getByName("localhost"),portOut);
                sock1 = new Socket("127.0.0.1", portOut);
                sock2 = new ServerSocket(portIn);
            } catch (IOException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }

        String sentence;
        for (;;) {
           if(profile==1){
             sentence = ("/"+ ports1[0][0]+"%"+ports1[1][0]+"%"+portIn+"%"+test);}
           else  { sentence = ("/"+ ports2[0][0]+"%"+ports2[1][0]+"%"+portIn+"%"+test);}
        try {
            byteBuffer=sentence.getBytes();



                InputStream in = sock1.getInputStream();
                OutputStream out = sock1.getOutputStream();

                System.out.println("client run");
	            //String userInput="test";
                int totalBytesRcvd = 0;  // Total bytes received so far
                int bytesRcvd;           // Bytes received in last read

	            sock1.getOutputStream().write(byteBuffer);
                //sock1.getInputStream().read(byteBuffer);
                System.out.println(new String(byteBuffer));
                //byteBuffer=null;
                  while (totalBytesRcvd < byteBuffer.length) {
                 if ((bytesRcvd = in.read(byteBuffer, totalBytesRcvd,  byteBuffer.length - totalBytesRcvd)) == -1)
                    throw new SocketException("Connection close prematurely");
                      //sock1.getInputStream().read(sendBuffer);
                      System.out.println("sent message " + new String(byteBuffer));
                    System.out.println("client loop");
                    totalBytesRcvd += bytesRcvd; }

	            	          //}

	           
                try { Thread.sleep(1500); } catch (Exception e) {}

             sock1.close();
              //sock1.connect(local1);
             sock1 = new Socket(InetAddress.getLocalHost(), portOut);
              System.out.println(sock1.isClosed());
          }catch( IOException e ) {
            System.out.println("Client error: " +e.getMessage());
          }
        }}
    };
    Thread thr1 = new Thread(r1);
    Thread thr2 = new Thread(r2);
    thr1.start();
    thr2.start();

    }
}

