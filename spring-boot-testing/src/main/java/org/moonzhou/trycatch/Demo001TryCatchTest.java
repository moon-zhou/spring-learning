package org.moonzhou.trycatch;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

/**
 * @author moon zhou
 * try-catch in for iteration or out, almost no performance loss
 *
 * https://juejin.cn/post/7204121228016091197
 */
@BenchmarkMode(Mode.AverageTime) // 测试完成时间
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Warmup(iterations = 2, time = 1, timeUnit = TimeUnit.SECONDS) // 预热次数和时间
@Measurement(iterations = 5, time = 5, timeUnit = TimeUnit.SECONDS) // 测试次数和时间
@Fork(1) // fork 1 个线程
@State(Scope.Thread)
public class Demo001TryCatchTest {

    public static void main(String[] args) throws RunnerException {
        // 启动基准测试
        Options opt = new OptionsBuilder()
                .include(Demo001TryCatchTest.class.getSimpleName()) // 要导入的测试类
                .build();
        new Runner(opt).run(); // 执行测试
    }

    @Benchmark
    public void tryfor(Blackhole blackhole) {
        try {
            for (int i = 0; i < 5000; i++) {
                blackhole.consume(i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Benchmark
    public void fortry(Blackhole blackhole) {
        for (int i = 0; i < 5000; i++) {
            try {
                blackhole.consume(i);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}