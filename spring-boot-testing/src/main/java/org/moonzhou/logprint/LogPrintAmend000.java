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
 * 修改日志级别为debug，验证日志输出正常
 * https://juejin.im/post/5ee07ce76fb9a047ff1abe01
 */
@BenchmarkMode(Mode.AverageTime) // 测试完成时间
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Warmup(iterations = 2, time = 2, timeUnit = TimeUnit.SECONDS) // 预热 2 轮，每次 2s
@Measurement(iterations = 5, time = 3, timeUnit = TimeUnit.SECONDS) // 测试 5 轮，每次 3s
@Fork(1) // fork 1 个线程
@State(Scope.Thread) // 每个测试线程一个实例
@Slf4j
public class LogPrintAmend000 {
    public static void main(String[] args) throws RunnerException {
        // 启动基准测试
        Options opt = new OptionsBuilder()
                .include(LogPrintAmend000.class.getName() + ".*") // 要导入的测试类
                .build();
        new Runner(opt).run(); // 执行测试
    }

    @Setup
    public void init() {
        // 启动 spring boot
        SpringApplication.run(SpringBootTestingApplication.class);
    }

    @Benchmark
    public void logPrint() {
        log.debug("show debug");
        log.info("show info");
        log.error("show error");
    }
}
