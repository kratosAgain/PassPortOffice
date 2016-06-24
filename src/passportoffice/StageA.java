package passportoffice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class StageA implements Stage {

	private ArrayList<Person> queueList;
	private ArrayList<Person> doneWithList;
	private ArrayList<Person> withStaffList;
	private ArrayList<Integer> arrivalTimes;
	private int totalPerson;
	private int totalStaff;
	
	//constructor if totalPerson and totalStaff are to be set
	public StageA(int totalPerson,int totalStaff){
		this.queueList =new ArrayList();
		this.doneWithList = new ArrayList();
		this.withStaffList = new ArrayList();	
		this.arrivalTimes = new ArrayList();
		this.totalPerson = totalPerson;
		this.totalStaff = totalStaff;
	}
	
	//default will be 1200 and 10
	public StageA(){
		this.queueList =new ArrayList();
		this.doneWithList = new ArrayList();
		this.withStaffList = new ArrayList();	
		this.arrivalTimes = new ArrayList();
		this.totalPerson = 1200;
		this.totalStaff =10;
	}
	
	
	
	
	//function to fill the arrival times with division in 4 hours and 4 groups
	
	public void fillArrivalTimes(){
		int one_batch = this.totalPerson/4;
		int currentTime=0;
		Random ran = new Random();
		while(currentTime<=180){
			for(int i= 0;i<one_batch;i++){
				int time = currentTime+ran.nextInt(59);
				this.arrivalTimes.add(time);
			}
			currentTime = currentTime + 60;
		}
		int leftOver = this.totalPerson%4;
		
		for(int i =0;i<leftOver;i++){
			this.arrivalTimes.add(ran.nextInt(240));
		}
		
		Collections.sort(this.arrivalTimes);
	}
	
	//filling the queue now 
	
	 public void fillQueueList(){
		Random ran = new Random();
		int id = 0;
		for(int i:this.arrivalTimes){
			int time = 1 + ran.nextInt(3);
			Person p = new Person(id,i);
			p.setTimeOnA(time);
			this.queueList.add(p);
			id++;
		}
	}
	
	//processing the person on stageA
	
	public void process(){
		int time = 0;
		int i = 0;
		//int totalSum = 0;
		while(this.queueList.size()>0 ){
			
			if(i>0){
				ArrayList<Person> toRemove = new ArrayList();
				for(int p=this.withStaffList.size()-1;p>=0;p--){
					if(this.withStaffList.get(p).getTimeLeaveA()==time){
						Person person = this.withStaffList.get(p);
						this.withStaffList.remove(p);
						toRemove.add(person);						
					}
				}
				Collections.reverse(toRemove);
				this.doneWithList.addAll(toRemove);
			}
			while(this.withStaffList.size()<this.totalStaff){
				if(this.queueList.size()>0  && this.queueList.get(0).getEntryTime() <= time){
					Person p = this.queueList.get(0);
					p.setTimeLeaveA(time + p.getTimeOnA());
					this.withStaffList.add(p);
					this.queueList.remove(0);
				}
				else{
					break;
				}
			}
			
			
			time++;
			i++;
			
		}
		
		while(this.withStaffList.size()>0){
			ArrayList<Person> toRemove = new ArrayList();
			for(int p=this.withStaffList.size()-1;p>=0;p--){
				if(this.withStaffList.get(p).getTimeLeaveA()==time){
					Person person = this.withStaffList.get(p);
					this.withStaffList.remove(p);
					toRemove.add(person);						
				}
			}
			//totalSum+=toRemove.size();
			Collections.reverse(toRemove);
			this.doneWithList.addAll(toRemove);
			time++;
		}
		
		
		
		//System.out.println(this.withStaffList.size());
	}
	
	
	//processing users in random pick fashion
	public void randomProcess(){
		int time = 0;
		int i = 0;
		Random ran = new Random();
		while(this.queueList.size()>0){
			if(i>0){
				ArrayList<Person> toRemove = new ArrayList();
				for(int p=this.withStaffList.size()-1;p>=0;p--){
					if(this.withStaffList.get(p).getTimeLeaveA()==time){
						Person person = this.withStaffList.get(p);
						this.withStaffList.remove(p);
						toRemove.add(person);						
					}
				}
				Collections.reverse(toRemove);
				this.doneWithList.addAll(toRemove);
			}
			int totalOptions = 0;
			for(int k=0;k<this.queueList.size();k++){
				if(this.queueList.get(k).getEntryTime() <=time){
					totalOptions++;
				}
				else{
					break;
				}
			}
			int attempts = 0;
			while(this.withStaffList.size()<this.totalStaff  && totalOptions>0){
				int ranIDX = ran.nextInt(totalOptions);
				if(this.queueList.size()>0  && this.queueList.get(ranIDX).getEntryTime() <= time){
					Person p = this.queueList.get(ranIDX);
					p.setTimeLeaveA(time + p.getTimeOnA());
					this.withStaffList.add(p);
					this.queueList.remove(ranIDX);
					totalOptions--;
					
				}
				if(attempts > totalOptions){
					break;
				}
				attempts++;
			}
			
			time++;
			i++;
			
		}
		
		while(this.withStaffList.size()>0){
			ArrayList<Person> toRemove = new ArrayList();
			for(int p=this.withStaffList.size()-1;p>=0;p--){
				if(this.withStaffList.get(p).getTimeLeaveA()==time){
					Person person = this.withStaffList.get(p);
					this.withStaffList.remove(p);
					toRemove.add(person);						
				}
			}
			
			Collections.reverse(toRemove);
			this.doneWithList.addAll(toRemove);
			time++;
		}
	}
	
	
	//return the donewith List
	public ArrayList<Person> getDoneWith(){
		return this.doneWithList;
	}
	
	
//	public static public void main(String[] args){
//		StageA a = new StageA();
//		a.fillArrivalTimes();
//		a.fillQueueList();
//		
//		//System.out.println(a.arrivalTimes);
//		//System.out.println(a.queueList.size());
//     	a.process();
//		//System.out.println(a.doneWithList.size());
////		a.randomProcess();
////		System.out.println(a.doneWithList.size());
//		StageB b = new StageB(a.getDoneWith());
//		b.fillQueueList();
//		b.randomProcess();
//		//System.out.println(b.getDoneWith());
//		StageC c = new StageC(b.getDoneWith());
//		c.fillQueueList();
//	}
	
	
}
