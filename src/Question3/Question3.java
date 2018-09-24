package Question3;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;

public class Question3 {
	
	public static void main(String[] args) {
		Question3 q3=new Question3();
		System.out.println("result="+q3.getNSmallest(502));
		System.out.println("result="+q3.getNSmallest(0));
		System.out.println("result="+q3.getNSmallest(-2));
		System.out.println("result="+q3.getNSmallest(302));
		System.out.println("result="+q3.getNSmallest(500));
	}
	
	
	public int getNSmallest(int n) {
		if(n>500||n<0)
			return -1;
		
		Queue<Integer> q=new PriorityQueue<Integer>();
		
		//Generate 500 random numbers and store in priority queue, numbers are sorted in it
		Random rand = new Random();
		for(int i=0;i<500;i++) {			
			q.add(rand.nextInt(5000)+0);
		}
		
		//pop n numbers which are smaller 
		while(n>1) {
			q.poll();
			n--;
		}
	
		return q.peek();

	}
}
