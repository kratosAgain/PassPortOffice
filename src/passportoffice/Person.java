package passportoffice;

public class Person {

	private int entryTime;
	private int exitTime;
	private int timeOnA;
	private int timeLeaveA=-1;
	private int timeOnB;
	private int timeOnC;
	private int timeLeaveB=-1;
	private int timeLeaveC=-1;
	private int id;
	
	public Person(int id,int entryTime){
		this.id = id;
		this.entryTime = entryTime;
	}
	
	//start of getters
	
	int getEntryTime(){
		return this.entryTime;
	}
	
	int getExitTime(){
		return this.exitTime;
	}
	
	int getTimeOnA(){
		return this.timeOnA;
	}
	
	int getTimeOnB(){
		return this.timeOnB;
	}
	
	int getTimeOnC(){
		return this.timeOnC;
	}
	
	int getTimeLeaveA(){
		return this.timeLeaveA;
	}
	
	int getTimeLeaveB(){
		return this.timeLeaveB;
	}
	
	int getTimeLeaveC(){
		return this.timeLeaveC;
	}
	
	int getID(){
		return this.id;
	}
	
	//start of setters 
	
	void setEntryTime(int time){
		this.entryTime = time;
	}
	
	void setExitTime(int time){
		this.exitTime=time;
	}
	
	void setTimeOnA(int time){
		this.timeOnA = time;
	}
	
	void setTimeOnB(int time){
		this.timeOnB = time;
	}
	
	void setTimeOnC(int time){
		this.timeOnC=time;
	}
	
	void setTimeLeaveA(int time){
		this.timeLeaveA=time;
	}
	
	void setTimeLeaveB(int time){
		this.timeLeaveB=time;
	}
	
	void setTimeLeaveC(int time){
		this.timeLeaveC = time;
	}
	
	//total time spent on booths
	
	int timeSpentOnBooths(){
		return this.timeOnA + this.timeOnB + this.timeOnC;
	}
	
	//total time in office 
	int totalTime(){
		return this.exitTime - this.entryTime;
	}
	
	//time wasted in queues	
	int timeWasted(){
		return this.totalTime() - this.timeSpentOnBooths();
	}
	
	public String toString(){
		String s = "ID: "+this.id+" Entry Time: "+this.entryTime + " ExitTime: "+this.getExitTime() ;
		return s;
	}
	
	
}
