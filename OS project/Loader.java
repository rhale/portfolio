import java.io.*;
import java.util.*;

public class Loader //extends PCB
{

String Hex;
String Result;
String FileName="DataFile1.txt";


public void loader() throws FileNotFoundException
{

    Scanner scan=new Scanner(new File(FileName));
    String next;
    String size;
    String priority;
    String inputBuff;
    String outputBuff;
    String tempBuff;
    do
    {    next=scan.next();
        if(next.charAt(0) == '/')
        {    //PCB pcb=new PCB();
            if(scan.hasNext())
                next=scan.next();
            else
                break;
            if(next.charAt(0) =='J')
            {
                scan.next();
                size=scan.next();
                
//                System.out.println(size);
                priority=scan.next();
        //        System.out.println(priority);    
            }
            else if(next.charAt(0) =='D')
            {
                inputBuff=scan.next();
                System.out.println(inputBuff);
                outputBuff=scan.next();
                System.out.println(outputBuff);
                tempBuff=scan.next();
                System.out.println(tempBuff);
            }
//                else

            
        }
    //    else
        //    System.out.println();
        //next_line=br.readLine();
    }
    while(scan.hasNext() && next!= null);
}
    public static void main(String args[]) throws FileNotFoundException
    { 
        Loader load=new Loader();
        load.loader();
    }
}