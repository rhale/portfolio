import java.util.*;

public class PCB
{
	public int cpuid;
	public Object[] state=new Object[5];
	public int[] register = new int[16];
	/*public Object[] Sched=new Object[];
	public Object[] accounts=new Object[];
	public Object[] progent=new Object[];
	public Object[] resources=new Object[];*/
	public String Status;
	public String Status_Info;
	public int inputBuffer;
	public int outputBuffer;
	public int tempBuffer;
	public int priority;
	public int size;
	public int prct;
	public int vecct;
	public int jobNum;
	converter conv = new converter();

	protected PCB()
	{   }

	protected PCB(int id,int siz, int prio)
	{  
		jobNum = id;
		size = siz;
		priority = prio;
	}
	
	public void setPriority(int p)
	{
		priority = p;
	}
	
	public int getID()
	{
		return jobNum;
	}
	
	public int getSize()
	{
		return size;
	}
	
	public int getPriority()
	{
		return priority;
	}
	
	public void setPC(int pc)
	{
		prct = pc;
	}
	
	public void setInputBuff(String ib)
	{
		inputBuffer = conv.hexToDec(ib);
	}
	
	public void setOutputBuff(String ob)
	{
		outputBuffer = conv.hexToDec(ob);
	}
	
	public void setTempBuff(String tb)
	{
		tempBuffer = conv.hexToDec(tb);
	}
	
	public int getInputBuff()
	{
		return inputBuffer;
	}
	
	public int getOutputBuff()
	{
		return outputBuffer;
	}
	
	public int getTempBuff()
	{
		return tempBuffer;
	}
	
	public static synchronized PCB getInstance()
   {
      if (ref == null)
          ref = new PCB();
      return ref;
   }

   private static PCB ref;
}