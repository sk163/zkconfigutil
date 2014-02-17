package org.other;


public final class TestCacheMiss {
	
	public long long1 = 0L;
	public long p1, p2, p3, p4, p5, p6,p7; // 注释
	public long long2 = 0L;
	public long p8, p9, p10, p11, p12, p13, p14; // 注释

	public static void main(String[] args) {
		TestCacheMiss app = new TestCacheMiss();
		
		app.startThread1();
		app.startThread2();
	}
	
	public void startThread1(){
		
		new Thread(new Runnable(){

			@Override
			public void run() {
				long temp = 0L;
				while(true){
					++ long1;
					if(temp ++ == 1000000000)
						break;
				}
			}
			
		}).start();
		
	}
	
public void startThread2(){
		
		new Thread(new Runnable(){

			@Override
			public void run() {
				long temp = 0L;
				while(true){
					++ long2;
					if(temp ++ == 1000000000)
						break;
				}
			}
			
		}).start();
		
	}

}
