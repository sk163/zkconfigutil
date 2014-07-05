package com.jerry.zkconfigutil;

/**
 * not JUNIT
 * 
 * @author JERRY
 *
 */
public final class TestZkConfigUtil {

	public static void main(String[] args) throws InterruptedException {
		while (true) {
			System.out.println("Demo.F1 = " + Demo.F1);
			loop();
		}
	}

	public static void loop() throws InterruptedException {
		Thread.sleep(2000L);
	}

}
