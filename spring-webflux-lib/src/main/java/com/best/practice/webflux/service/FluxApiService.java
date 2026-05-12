package com.best.practice.webflux.service;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.util.function.Tuple2;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

@Service
public class FluxApiService {
    public static void main(String[] args) throws InterruptedException {
//        new FluxApiService().createFluxByJust();
//        new FluxApiService().createFluxByArray();
//        new FluxApiService().createFluxByIter();
//        new FluxApiService().createFluxByStream();
//        new FluxApiService().createFluxByInterval();
//        new FluxApiService().createFluxByRange();
        new FluxApiService().composeFluxByMerge();
        new FluxApiService().composeFluxByZip();
        new FluxApiService().composeFluxByZipCustom();
        new FluxApiService().composeFluxByFirst();
        Thread.sleep(6000); // 等待足够时间让 5 个元素发射完
    }
    /* =========================================== Flux的创建操作 ===========================================*/
    public void createFluxByJust(){
        //发布
        Flux<String> fruitFlux = Flux.just("Apple", "Orange", "Banana");
        //订阅
        fruitFlux.subscribe(fruit -> {
            System.out.println("just:"+fruit);
        });
    }
    public void createFluxByArray(){
        //发布
        String[] fruits = new String[]{"Apple", "Orange", "Banana"};
        Flux<String> fruitFlux = Flux.fromArray(fruits);
        //订阅
        fruitFlux.subscribe(fruit -> {
            System.out.println("array:"+fruit);
        });
    }
    public void createFluxByIter(){
        //发布
        String[] fruits = new String[]{"Apple", "Orange", "Banana"};
        List<String> fruitList = Arrays.asList(fruits);
        Flux<String> fruitFlux = Flux.fromIterable(fruitList);
        //订阅
        fruitFlux.subscribe(fruit -> {
            System.out.println("iter:"+fruit);
        });
    }
    public void createFluxByStream(){
        //发布
        String[] fruits = new String[]{"Apple", "Orange", "Banana"};
        Stream<String> fruitStream = Arrays.stream(fruits);
        Flux<String> fruitFlux = Flux.fromStream(fruitStream);
        //订阅
        fruitFlux.subscribe(fruit -> {
            System.out.println("stream:"+fruit);
        });
    }

    public void createFluxByInterval(){
        Flux<Long> intervalFlux = Flux.interval(Duration.ofSeconds(1))
                .take(5);
        //订阅
        intervalFlux.subscribe(time -> {
            System.out.println("interval:"+time);
        });
    }
    public void createFluxByRange(){
        Flux<Integer> rangeFlux = Flux.range(1, 5);
        //订阅
        rangeFlux.subscribe(time -> {
            System.out.println("range:"+time);
        });
    }

    /* =========================================== Flux的组合操作 ===========================================*/

    /**
     * mergeWith
     * zip:
     * zip，提供合并函数
     * first：
     */
    public void composeFluxByMerge(){
        //延迟发送
        Flux<String> odd = Flux.just("o-1", "o-3", "o-5", "o-7", "o-9")
                .delayElements(Duration.ofMillis(500));
        //延迟订阅与延迟发送
        Flux<String> even = Flux.just("e-2","e-4","e-6","e-8","e-10")
                .delaySubscription(Duration.ofMillis(250))
                .delayElements(Duration.ofMillis(500));
        Flux<String> naturalFlux = odd.mergeWith(even);
        naturalFlux.doOnNext(System.out::println).blockLast();
    }

    public void composeFluxByZip(){
        Flux<String> kFlux = Flux.just("k-1","k-2","k-3");
        Flux<String> vFlux = Flux.just("v-1","v-2","v-3","v-4");
        //默认数量不一致的直接丢掉了v-4
        kFlux.zipWith(vFlux).doOnNext(System.out::println).blockLast();
    }
    public void composeFluxByZipCustom(){
        Flux<String> kFlux = Flux.just("k-1","k-2","k-3");
        Flux<String> vFlux = Flux.just("v-1","v-2","v-3","v-4");
        Flux<String> customFlux = kFlux.zipWith(vFlux, (k, v) -> k + " like " + v);
        customFlux.doOnNext(System.out::println).blockLast();
    }


    /**
     * 只输出先有数据的Flux
     */
    public void composeFluxByFirst(){
        Flux<String> tFlux = Flux.just("t-1","t-2","t-3")
                .delayElements(Duration.ofMillis(250));
        Flux<String> gFlux = Flux.just("g-1","g-2","g-3");
        //
        Flux.firstWithSignal(tFlux,gFlux).doOnNext(System.out::println).blockLast();
    }
    /* =========================================== Flux的转换操作 ===========================================*/
    public void convertFlux(){
        Flux<String> kFlux = Flux.just("k-1","k-2","k-3");
        Flux<String> vFlux = Flux.just("v-1","v-2","v-3","v-4");
        kFlux.zipWith(vFlux).doOnNext(System.out::println).blockLast();
    }

    /* =========================================== Flux的逻辑操作 ===========================================*/
    public void logicFlux(){

    }
}
