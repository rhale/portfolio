import java.io.*;
import java.util.*;
import java.util.LinkedList;


public class ShortTermScheduler
{
	Loader load = new Loader();
	Queue<PCB> queue = new LinkedList<PCB>();
	CPU cpu =new CPU();
	private PCB pcb;
	
	public ShortTermScheduler()// constructor
	{
    
  			queue=new LinkedList();
	}
	
	
	public void AddJob() // method  to add a process and execute
	{
		load.loader();
		//load.quickSort(load.arlist,0,load.arlist.size()-1);
		System.out.println(load.getJobs());
		for(int i = 0; i < load.getJobs(); i++)
		{
			queue.add(load.arlist.get(i));
		}
	}
		
	public void STS()  // method to execute an instruction when the current process finishes
	{
		while(!queue.isEmpty()&&(STATE_COMPLETED))
		{
			CPU.execute(queue.remove());
		}
	}
	
	public static void main(String [] args) throws FileNotFoundException
	{
		ShortTermScheduler Short = new ShortTermScheduler();
		Short.addJob();
		Short.STS();
		
		}	
}

