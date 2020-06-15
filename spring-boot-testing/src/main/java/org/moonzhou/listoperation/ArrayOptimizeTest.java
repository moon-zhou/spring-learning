package org.moonzhou.listoperation;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

/**
 * 空list，从头开始插入，比较性能
 * LinkedList 的平均执行（完成）时间和 ArrayList的时间差不多，
 *
 * @author moon-zhou
 */
@BenchmarkMode(Mode.AverageTime) // 测试完成时间
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Warmup(iterations = 2, time = 1, timeUnit = TimeUnit.SECONDS) // 预热次数和时间
@Measurement(iterations = 5, time = 5, timeUnit = TimeUnit.SECONDS) // 测试次数和时间
@Fork(1) // fork 1 个线程
@State(Scope.Thread)
public class ArrayOptimizeTest {

    private static final int maxSize = 1000; // 测试循环次数

    private static ArrayList<Integer> arrayList;
    private static LinkedList<Integer> linkedList;

    public static void main(String[] args) throws RunnerException {
        // 启动基准测试
        Options opt = new OptionsBuilder()
                .include(ArrayOptimizeTest.class.getSimpleName()) // 要导入的测试类
                .build();
        new Runner(opt).run(); // 执行测试
    }
    
    @Benchmark
    public void addArray(Blackhole blackhole) { // 中间删数组表
        arrayList = new ArrayList<Integer>();
        for (int i = 0; i < maxSize; i++) {
            arrayList.add(i);
        }
        // 为了避免 JIT 忽略未被使用的结果计算
        blackhole.consume(arrayList);
    }

    @Benchmark
    public void addLinked(Blackhole blackhole) { // 中间删除链表
        linkedList = new LinkedList<Integer>();
        for (int i = 0; i < maxSize; i++) {
            linkedList.add(i);
        }
        // 为了避免 JIT 忽略未被使用的结果计算
        blackhole.consume(linkedList);
    }
}
