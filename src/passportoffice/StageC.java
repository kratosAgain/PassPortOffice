package passportoffice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class StageC implements Stage {
	private ArrayList<Person> queueList;
	private ArrayList<Person> doneWithList;
	private ArrayList<Person> withStaffList;
	private int totalPerson;
	private int totalStaff;
	
	public StageC(ArrayList<Person> queueList,int totalStaff){
		this.queueList = queueList;
		this.totalPerson = queueList.size();
		this.totalStaff = totalStaff;
		this.doneWithList = new ArrayList();
		this.withStaffList = new ArrayList();	
	}
	
	public StageC(ArrayList<Person> queueList){
		this.queueList = queueList;
		this.totalPerson = queueList.size();
		this.totalStaff = 5;
		this.doneWithList = new ArrayList();
		this.withStaffList = new ArrayList();	
	}
	
	public void fillQueueList(){
		Random ran = new Random();
		for(int i=0;i<this.queueList.size();i++){
			int time = 5+ran.nextInt(10);
			Person p = this.queueList.get(i);
			p.setTimeOnC(time);
			this.queueList.set(i, p);
		}
		//System.out.println(this.queueList);
	}
	
	public void process(){
		int time = 0;
		int i =0;
		
		while(this.queueList.size()>0){
			if(i>0){
				ArrayList<Person> toRemove = new ArrayList();
				for(int p=this.withStaffList.size()-1;p>=0;p--){
					if(this.withStaffList.get(p).getTimeLeaveC()==time){
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
					p.setTimeLeaveC(time + p.getTimeOnC());
					p.setExitTime(time+p.getTimeOnC());
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
				if(this.withStaffList.get(p).getTimeLeaveC()==time){
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
	}
	
	public void randomProcess(){
		int time = 0;
		int i = 0;
		Random ran = new Random();
		while(this.queueList.size()>0){
			if(i>0){
				ArrayList<Person> toRemove = new ArrayList();
				for(int p=this.withStaffList.size()-1;p>=0;p--){
					if(this.withStaffList.get(p).getTimeLeaveC()==time){
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
					p.setTimeLeaveC(time + p.getTimeOnC());
					p.setExitTime(time + p.getTimeOnC());
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
				if(this.withStaffList.get(p).getTimeLeaveC()==time){
					Person person = this.withStaffList.get(p);
					this.withStaffList.remove(p);
					toRemove.add(person);						
				}
			}
			
			Collections.reverse(toRemove);
			this.doneWithList.addAll(toRemove);
			time++;
		}
		//System.out.println(this.doneWithList.size());
	}
	
	public ArrayList<Person> getDoneWith(){
		return this.doneWithList;
	}

	//this method is not implmented //no use here
	@Override
	public void fillArrivalTimes() {
		// TODO Auto-generated method stub
		
	}
}
