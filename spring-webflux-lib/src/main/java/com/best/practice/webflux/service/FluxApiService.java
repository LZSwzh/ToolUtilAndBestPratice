package com.best.practice.webflux.service;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import reactor.util.function.Tuple2;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

@Service
public class FluxApiService {
    public static void main(String[] args) throws InterruptedException {
        // 构造相关API
//        new FluxApiService().createFluxByJust();
//        new FluxApiService().createFluxByArray();
//        new FluxApiService().createFluxByIter();
//        new FluxApiService().createFluxByStream();
//        new FluxApiService().createFluxByInterval();
//        new FluxApiService().createFluxByRange();
        // 组合相关API
//        new FluxApiService().composeFluxByMerge();
//        new FluxApiService().composeFluxByZip();
//        new FluxApiService().composeFluxByZipCustom();
//        new FluxApiService().composeFluxByFirst();
        // 转换相关API
        new FluxApiService().convertFluxByMap();
        new FluxApiService().convertFluxByFlatMap();
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
    /* =========================================== Flux的过滤操作 ===========================================*/

    /**
     * skip(n):跳过n个元素，
     * skip(Duration):根据传入时间跳过
     * take(n):只取前n个元素
     * take(Duration):根据传入时间选择
     * filter(Predicate):过滤
     * distinct：去重
     */
    /* =========================================== Flux的转换操作 ===========================================*/
    //map: 同步一对一转换
    public void convertFluxByMap(){
        Flux<String> nameFlux = Flux.just("tom","jack","zhangsan");
        Flux<Integer> ageFlux = Flux.just(23,33,21);
        Flux<Tuple2<String, Integer>> tuple2Flux = nameFlux.zipWith(ageFlux);
        Flux<User> userFlux = tuple2Flux.map(tuple -> {
            return User.builder()
                    .userName(tuple.getT1())
                    .userAge(tuple.getT2())
                    .build();
        });
        userFlux.doOnNext(System.out::println).blockLast();
    }
    //.flatMap(item -> mono/flux)  // 异步一对多，不保序
    public void convertFluxByFlatMap(){
        System.out.println("================================================");
        Flux<String> nameFlux = Flux.just("tom","jack","zhangsan");
        Flux<Integer> ageFlux = Flux.just(23,33,21);
        Flux<Tuple2<String, Integer>> tuple2Flux = nameFlux.zipWith(ageFlux);
        Flux<User> userFlux = tuple2Flux.flatMap(
                n -> Mono.just(n)
                        .map(t -> User.builder()
                                .userName(t.getT1())
                                .userAge(t.getT2())
                                .build()
                        ).log()
                        .subscribeOn(Schedulers.parallel())

        );
        userFlux.doOnNext(System.out::println).blockLast();
    }

    /* =========================================== Flux的收集操作 ===========================================*/

    // buffer(n): 将元素按指定数量分组，收集到 List 中
    public void convertFluxByBuffer(){
        Flux<String> fruitFlux = Flux.just("Apple", "Orange", "Banana", "Kiwi", "Grape");
        // 每2个元素为一组
        fruitFlux.buffer(2)
                .doOnNext(batch -> System.out.println("buffer batch: " + batch))
                .blockLast();
    }

    // collectList: 将 Flux 中所有元素收集为一个 List，返回 Mono<List<T>>
    public void convertFluxByCollectList(){
        Flux<String> fruitFlux = Flux.just("Apple", "Orange", "Banana");
        Mono<List<String>> listMono = fruitFlux.collectList();
        listMono.doOnNext(list -> System.out.println("collectList: " + list))
                .block();
    }

    // collectMap: 将 Flux 中的元素收集为 Map，需要指定 key 提取函数
    public void convertFluxByCollectMap(){
        Flux<String> nameFlux = Flux.just("tom", "jack", "zhangsan");
        Flux<Integer> ageFlux = Flux.just(23, 33, 21);
        Flux<User> userFlux = nameFlux.zipWith(ageFlux, (name, age) ->
                User.builder().userName(name).userAge(age).build()
        );
        // 以 userName 作为 key，User 对象作为 value
        userFlux.collectMap(User::getUserName)
                .doOnNext(map -> System.out.println("collectMap: " + map))
                .block();
    }

    /* =========================================== Flux的逻辑操作 ===========================================*/

    // any: 只要有一个元素满足条件就返回 true
    public void logicFluxByAny(){
        Flux<Integer> numFlux = Flux.just(1, 3, 5, 8, 9);
        Mono<Boolean> hasEven = numFlux.any(n -> n % 2 == 0);
        hasEven.doOnNext(result -> System.out.println("any 偶数: " + result))
                .block();
    }

    // all: 所有元素都满足条件才返回 true
    public void logicFluxByAll(){
        Flux<Integer> numFlux = Flux.just(2, 4, 6, 8);
        Mono<Boolean> allEven = numFlux.all(n -> n % 2 == 0);
        allEven.doOnNext(result -> System.out.println("all 偶数: " + result))
                .block();
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    static class User{
        private String userName;
        private Integer userAge;
    }
}
