package passportoffice;

import java.util.ArrayList;

public class ComparisonSuite {

	public void compare(){
		this.normalValues();
		System.out.println();
		this.randomValues();
	}
	
	//using normal technique where next user is the one who is first in queue according to arrival time
	public void normalValues(){
		StageA a = new StageA();
		a.fillArrivalTimes();
		a.fillQueueList();
		a.process();
		StageB b = new StageB(a.getDoneWith());
		b.fillQueueList();
		b.process();
		StageC c = new StageC(b.getDoneWith());
		c.fillQueueList();
		c.process();
		//System.out.println(c.getDoneWith());
		int totalTimeTakenByStaff = this.findMaxTime(c.getDoneWith()) - 0;
		double averageUserTime = this.findAverageTimePerUser(c.getDoneWith());
		double averageUserTimeWasted = this.findAverageTimeWasted(c.getDoneWith());
		System.out.println("Analysis when next user selected on arrival time queue:"
				+ "\nTotal Time Taken By Staff:  "+totalTimeTakenByStaff
				+ "\nAverage User time (from entry to exit) : "+averageUserTime
				+ "\nAverage User time wasted in queues : "+averageUserTimeWasted);
		
		
	}
	
	//using random technique where next user is a random pick from available users at that time
	
	public void randomValues(){
		StageA a = new StageA();
		a.fillArrivalTimes();
		a.fillQueueList();
		a.randomProcess();
		StageB b = new StageB(a.getDoneWith());
		b.fillQueueList();
		b.randomProcess();
		StageC c = new StageC(b.getDoneWith());
		c.fillQueueList();
		c.randomProcess();
		ArrayList<Person> gdw=c.getDoneWith();
				//this.SetExitTimes(c.getDoneWith());
		//System.out.println(c.getDoneWith());
		int totalTimeTakenByStaff = this.findMaxTime(gdw) - 0;
		double averageUserTime = this.findAverageTimePerUser(gdw);
		double averageUserTimeWasted = this.findAverageTimeWasted(gdw);
		System.out.println("Analysis when next user selected on randomly from available queue:"
				+ "\nTotal Time Taken By Staff:  "+totalTimeTakenByStaff
				+ "\nAverage User time (from entry to exit) : "+averageUserTime
				+ "\nAverage User time wasted in queues : "+averageUserTimeWasted);
	}
	
	ArrayList<Person> SetExitTimes(ArrayList<Person> list){
		for(Person p:list){
			p.setExitTime(p.getExitTime() + p.timeSpentOnBooths());
		}
		return list;
	}
	
	//find maximum exit time i.e the time when staff have finished off with all users
	int findMaxTime(ArrayList<Person> list){
		int max = 0;
		for(Person p:list){
			max = (p.getExitTime()>max)?p.getExitTime():max;
		}
		return max;
	}
	
	//find average time for user (from entry time to exit time)
	double findAverageTimePerUser(ArrayList<Person> list){
		int n = list.size();
		double sum = 0;
		for(Person p:list){
			sum = sum + p.totalTime();
		}
		double avg =  sum / n;
		return avg;
	}
	
	//average of time wasted by users waiting in queue
	double findAverageTimeWasted(ArrayList<Person> list){
		int n = list.size();
		double sum = 0;
		for(Person p:list){
			sum = sum + p.timeWasted();
		}
		double avg = sum / n;
		return avg;
	}
}
