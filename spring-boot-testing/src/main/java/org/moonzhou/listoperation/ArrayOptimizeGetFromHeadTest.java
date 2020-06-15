package org.moonzhou.listoperation;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

/**
 * 从现有list头部开始查询，比较性能
 *
 * ArrayList 的平均执行时间比 LinkedList快，具体快多少以实测为准
 *
 * @author moon-zhou
 */
@BenchmarkMode(Mode.AverageTime) // 测试完成时间
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Warmup(iterations = 2, time = 1, timeUnit = TimeUnit.SECONDS) // 预热次数和时间
@Measurement(iterations = 5, time = 5, timeUnit = TimeUnit.SECONDS) // 测试次数和时间
@Fork(1) // fork 1 个线程
@State(Scope.Thread)
public class ArrayOptimizeGetFromHeadTest {
    private static final int maxSize = 1000; // 测试循环次数
    private static final int operationSize = 100; // 操作次数

    private static ArrayList<Integer> arrayList;
    private static LinkedList<Integer> linkedList;

    public static void main(String[] args) throws RunnerException {
        // 启动基准测试
        Options opt = new OptionsBuilder()
                .include(ArrayOptimizeGetFromHeadTest.class.getSimpleName()) // 要导入的测试类
                .build();
        new Runner(opt).run(); // 执行测试
    }

    @Setup
    public void init() {
        // 启动执行事件
        arrayList = new ArrayList<Integer>();
        linkedList = new LinkedList<Integer>();
        for (int i = 0; i < maxSize; i++) {
            arrayList.add(i);
            linkedList.add(i);
        }
    }

    @Benchmark
    public void findArrayByFirst() {
        for (int i = 0; i < operationSize; i++) {
            arrayList.get(i);
        }
    }

    @Benchmark
    public void findLinkedyByFirst() { 
        for (int i = 0; i < operationSize; i++) {
            linkedList.get(i);
        }
    }
}
