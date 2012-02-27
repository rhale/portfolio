import java.io.*; 
import java.net.*; 
import java.util.Scanner;
class UDPServer { 
  public static void main(String args[]) throws Exception 
    { 
      System.out.println("Set port number:\n");
      Scanner in = new Scanner(System.in);
      int input=in.nextInt();

	  // The port of the server is: 21212
      DatagramSocket serverSocket = new DatagramSocket(input);
      in.close();
      System.out.println("Current port is " + input);
      int f = 0; int z = 0;
      int port=0;
      InetAddress ipAddress;
      byte[] receiveData = new byte[1024]; 
      byte[] sendData  = new byte[1024]; 
  
      while(true)
        {
          ipAddress = null;
          DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length); //construct receive
           serverSocket.receive(receivePacket);  //receive packet  for(int j = 0; j < sentence.length(); j++){
          System.out.println("From "  + receivePacket.getAddress() + " on "+ receivePacket.getPort());
		  String sentence = new String(receivePacket.getData()); //construct data from byte array
            for(int j = 0; j < sentence.length(); j++){
                System.out.println(sentence.substring(0,j));
                if(sentence.charAt(j) == '%'){
                   ipAddress = InetAddress.getByName(sentence.substring(1,j));
                    f = j;
                   break;
                }
           }
           for(int k =f+1; k < sentence.length(); k++){
               System.out.println(sentence.substring(f+1,k));
                        if(sentence.charAt(k) == '%'){
                            port = Integer.parseInt(sentence.substring(f+1, k));
                            sentence = ipAddress + "%" + port + "%" + sentence.substring(k+1, sentence.length());
                            break;
                        }
           }
		  System.out.println("From "  + ipAddress + " on "+ port);
		  System.out.println("Received "+sentence);
		  sentence = sentence.toUpperCase();
          System.out.println("Sending to " + receivePacket.getAddress() + " " + "21212");
          sendData = sentence.getBytes(); //repack to byte array
          DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, receivePacket.getAddress(), 21212); //prepare payload
           //echo
          serverSocket.send(sendPacket); //LAUNCH
          System.out.println(z);
            z++;
        }
    } 
}  

