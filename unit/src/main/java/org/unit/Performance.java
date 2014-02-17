package org.unit;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

import org.apache.log4j.Logger;

/**
 * 性能输出
 * @author JERRY
 *
 */
public final class Performance {
	
	private static final Logger logger = Logger.getLogger(Performance.class);
	
	public static void performance(final AtomicLong count, Long time, TimeUnit timeUnit){
		final Timer timer = new Timer("PERFORMANCE");
		
		final long preSeconds = TimeUnit.SECONDS.convert(time, timeUnit);
		final TimerTask timerTask = new TimerTask(){

			@Override
			public void run() {
				while(true){
					long l = count.get();
					if(!count.compareAndSet(l, 0L)){
						continue;
					}
					logger.info(" performance : "+l+"/"+preSeconds+"s");
					break;
				}
				
			}
		};
		
		timer.schedule(timerTask, preSeconds * 1000, preSeconds * 1000);
	}
}
