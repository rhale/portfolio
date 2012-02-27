import java.io.*;
import java.util.*;
import java.util.LinkedList;


public class LongTermScheduler
{
	Loader load = new Loader();
	Queue<PCB> queue = new LinkedList<PCB>();
	RAM rams = new RAM();
	
	public LongTermScheduler()
	{

	}
	
	public void LTS() throws FileNotFoundException
	{
		load.loader();
		load.quickSort(load.arlist,0,load.arlist.size()-1);
		System.out.println(load.getJobs());
		for(int i = 0; i < load.getJobs(); i++)
		{
			queue.add(load.arlist.get(i));
		}
		while(!queue.isEmpty())
		{
			rams.saveData(queue.remove());
		}
	}
	
	public static void main(String [] args) throws FileNotFoundException
	{
		LongTermScheduler longy = new LongTermScheduler();
		longy.LTS();
		/*
		while(!queue.isEmpty())
		{
			System.out.println(rams.saveData(queue.remove()));
		}*/
	}	
}

