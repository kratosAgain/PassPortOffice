package passportoffice;

import java.util.ArrayList;

interface Stage {
	//filling the time list for the new users
	void fillArrivalTimes();
	
	//filling the waiting user queue
	void fillQueueList();
	
	//processing upon the waiting queue in arrival time pick order
	void process();
	
	//processing upon the waiting queue in random time pick order
	void randomProcess();
	
	//return the list which is made by processing all users in the queue
	ArrayList<Person> getDoneWith();
}
