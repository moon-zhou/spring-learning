package org.moonzhou.threadpool.actuator.config;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author moon zhou
 */
@Component
public class MyThreadPollHealthIndicator implements HealthIndicator {

	private ThreadPoolExecutor executor;

	public MyThreadPollHealthIndicator(ThreadPoolExecutor executor) {
		this.executor = executor;
	}

	@Override
	public Health health() {
		// 核心线程数
		int corePoolSize = executor.getCorePoolSize();
		// 活跃线程数
		int activeCount = executor.getActiveCount();
		// 完成的任务数
		long completedTaskCount = executor.getCompletedTaskCount();
		// 线程数历史高峰线
		int largestPoolSize = executor.getLargestPoolSize();
		// 当前池中线程数
		int poolSize = executor.getPoolSize();
		BlockingQueue<Runnable> queue = executor.getQueue();
		// 队列剩余空间数
		int remainingCapacity = queue.remainingCapacity();
		// 队列中的任务
		int queueSize = queue.size();
		// 最大线程数
		int maximumPoolSize = executor.getMaximumPoolSize();

		// 如果当前活跃线程数 大于  80%的最大线程数，就认证是down
		double rate = BigDecimal.valueOf(activeCount)
				.divide(BigDecimal.valueOf(maximumPoolSize), 2, BigDecimal.ROUND_HALF_UP)
				.doubleValue();

		Map<String, Object> infoMap = new HashMap<String, Object>();
		infoMap.put("核心线程数", corePoolSize);
		infoMap.put("当前活跃线程数", activeCount);
		infoMap.put("线程峰值", largestPoolSize);
		infoMap.put("完成的任务数", completedTaskCount);
		infoMap.put("当前池中线程数", poolSize);
		infoMap.put("队列剩余大小", remainingCapacity);
		infoMap.put("当前队列中的任务数", queueSize);
		infoMap.put("设置最大线程数", maximumPoolSize);
		if (rate > 0.8) {
			return Health.down().withDetails(infoMap).build();
		} else {
			return Health.up().withDetails(infoMap).build();
		}

	}
}