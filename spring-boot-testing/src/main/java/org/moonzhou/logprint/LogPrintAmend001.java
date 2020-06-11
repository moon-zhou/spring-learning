package org.moonzhou.logprint;

import lombok.extern.slf4j.Slf4j;
import org.moonzhou.SpringBootTestingApplication;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.springframework.boot.SpringApplication;

import java.util.concurrent.TimeUnit;

/**
 * 注意验证的时候需要调整日志级别为error，看出此时append耗时高，如果是info，看不出啥问题
 */
@BenchmarkMode(Mode.AverageTime) // 测试完成时间
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Warmup(iterations = 2, time = 2, timeUnit = TimeUnit.SECONDS) // 预热 2 轮，每次 2s
@Measurement(iterations = 5, time = 3, timeUnit = TimeUnit.SECONDS) // 测试 5 轮，每次 3s
@Fork(1) // fork 1 个线程
@State(Scope.Thread) // 每个测试线程一个实例
@Slf4j
public class LogPrintAmend001 {

    private final static int MAX_FOR_COUNT = 100; // for 循环次数

    public static void main(String[] args) throws RunnerException {
        // 启动基准测试
        Options opt = new OptionsBuilder()
                .include(LogPrintAmend001.class.getName() + ".*") // 要导入的测试类
                .build();
        new Runner(opt).run(); // 执行测试
    }

    @Setup
    public void init() {
        SpringApplication.run(SpringBootTestingApplication.class);
    }

    @Benchmark
    public void appendLogPrint() {
        for (int i = 0; i < MAX_FOR_COUNT; i++) { // 循环的意图是为了放大性能测试效果
            // 先拼接
            StringBuilder sb = new StringBuilder();
            sb.append("Hello, ");
            sb.append("Java");
            sb.append(".");
            sb.append("Hello, ");
            sb.append("Redis");
            sb.append(".");
            sb.append("Hello, ");
            sb.append("MySQL");
            sb.append(".");
            // 再判断
            if (log.isInfoEnabled()) {
                log.info(sb.toString());
            }
        }
    }

    @Benchmark
    public void logPrint() {
        for (int i = 0; i < MAX_FOR_COUNT; i++) { // 循环的意图是为了放大性能测试效果
            log.info("Hello, {}.Hello, {}.Hello, {}.", "Java", "Redis", "MySQL");
        }
    }
}
