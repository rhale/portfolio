public class Driver
{
	public static void main(String args[])throws Exception
	{ 
	Loader load=new Loader();
	LongTermScheduler lSchedule=new LongTermScheduler();
	//ShortTermScheduler = sSchedule=new ShortTermScheduler();
	load.loader();
	lSchedule.LTS();
	//CPU cpu=new CPU();
	//ShortTerm.execute();
	}
}