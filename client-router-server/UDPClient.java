import com.sun.servicetag.SystemEnvironment;

import java.io.*;
import java.net.*; 
import java.util.Random;
import java.util.Scanner;

class UDPClient {
    public static void main(String args[]) throws Exception 
    {
      // Read message/data from keyboard or file //
     int avgTime = 0;
      //InetAddress IPAddress = InetAddress.getByName("localhost"); //ip address of the client
      InetAddress server1 = InetAddress.getByName("192.168.2.13"); //ip address of the router
      //String server[][]= new String[2][4];
       String server[][] =  { {"127.0.0.1", "127.0.0.1"}, {"21213", "21214", "21215", "21216"} };
            //array of known server addresses and ports to be chosen at random as destination
	  //Prepare data structure for payloads - sending and receiving //
      byte[] sendData = new byte[1024]; //payload
      byte[] receiveData = new byte[1024];
        System.out.println("Set port number:\n"); //query user to set port number before beginning message run
        Scanner in = new Scanner(System.in);
      int input=in.nextInt();
        System.out.println("\n\nEnter IP of router\n");
        Scanner in2 = new Scanner(System.in);
        String inputS = in2.nextLine();
        System.out.println("\n\nChoose long or short messages\n");
        Scanner in3 = new Scanner(System.in);
        String inputS2 = in3.nextLine();
      int f = 0;
      String message = "", message1 = "", message2 = "";
	  in.close();
      DatagramSocket clientSocket = new DatagramSocket(input);
      if(inputS.equals("localhost") || inputS.equals("127.0.0.1"))
           server1 = InetAddress.getByName("localhost");
      else server1 = InetAddress.getByName(inputS);
     while(true){
         if(f>=100){           //send n messages total before quitting
             System.out.println("reached 100 messages, exiting...");
             break;
         }
         message1 = "This is a much longer message to test processing time and any significant changes in network traffic time, This is a much longer message to test processing time and any significant changes in network traffic time,This is a much longer message to test processing time and any significant changes in network traffic time, message #" + f;
         message2 = "Short format message";
         if(inputS2.toLowerCase().equals("long"))
             message = message1;
         else if(inputS2.toLowerCase().equals("short"))
            message = message2;
         else {
             System.out.println("Invalid message length specified, defaulting to short");
             message = message2;
         }
      // Construct the message itself - and 'pack' it
         System.out.println("Current port is " + input+"\n");
        Random r = new Random();       //randomize server destination
        int rand = r.nextInt(2);
        String sentence = ("/"+ server[0][rand]+"%"+server[1][rand]+"%"+message);
        sendData = sentence.getBytes();   //format data string to byte array
         System.out.println("Sending '"+sentence+"' to host " + server1 + " at port 21212");
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, server1, 21212); //spayload to send
        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);  //payload to receive
        double startTime = System.nanoTime();//time to receive processed message from server
        clientSocket.send(sendPacket); //send payload
        clientSocket.receive(receivePacket);  //blocking receive
        double estimatedTime = System.nanoTime() - startTime;
        System.out.println(("Completed in " +(estimatedTime/1000000 + " milliseconds"))); //format nanosecond to millisecond
        String serverResult = new String(receivePacket.getData());

        String modifiedSentence = new String(receivePacket.getData()); //unpack message
          int k = 0;
        for(int i = 0; i < modifiedSentence.length(); i++){
            if(k>=2){
                modifiedSentence = modifiedSentence.substring(i, modifiedSentence.length());
                break;
            }
            if(modifiedSentence.charAt(i) == '%')
              k++;

        }     //parse data for message modified by server
        System.out.println("FROM SERVER:" + modifiedSentence);


         avgTime+=estimatedTime; //add current average time to total and increment
         f++;
    }
   avgTime = (avgTime / 1000000) / f;      //find average of time in milliseconds
   System.out.println("Average time: " + avgTime + "milliseconds");
  }

} 
      

