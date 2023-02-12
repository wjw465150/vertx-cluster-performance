# vertx-cluster-performance

> 作者: 白石(https://github.com/wjw465150/vertx-cluster-performance)

Reproducer for performance degradation when running Vert.x 4 in clustered mode.

Setup: 

- **ConsumerVerticle** listens on the eventbus and replies to messages it receives.
- **ProducerVerticle** sends 500k eventbus messages and logs the duration of replies.
- **WithClusterStarter** starts Vert.x in clustered mode and deploys both verticles.
- **WithoutClusterStarter** starts Vert.x in non clustered mode and deploys both verticles.

The only difference between the two scenario's is Vert.x is started with or without cluster.
Both verticles are deployed as worker verticles to rule out eventloop blocking issues.

Timings (on my 2020 ThinkPad T41):
## without cluster
first message received after `10ms`, done after `2420ms`.

```
Starting in non-clustered mode
Starting ConsumerVerticle
Starting ProducerVerticle
Starting to send messages.
Consumer registered after 7 ms
Received first message after 10 ms
20000 eventbus messages sent in 106 ms.
40000 eventbus messages sent in 176 ms.
60000 eventbus messages sent in 216 ms.
80000 eventbus messages sent in 260 ms.
100000 eventbus messages sent in 310 ms.
120000 eventbus messages sent in 380 ms.
140000 eventbus messages sent in 402 ms.
160000 eventbus messages sent in 423 ms.
180000 eventbus messages sent in 446 ms.
200000 eventbus messages sent in 554 ms.
220000 eventbus messages sent in 573 ms.
240000 eventbus messages sent in 592 ms.
260000 eventbus messages sent in 612 ms.
280000 eventbus messages sent in 688 ms.
300000 eventbus messages sent in 708 ms.
320000 eventbus messages sent in 729 ms.
340000 eventbus messages sent in 749 ms.
360000 eventbus messages sent in 772 ms.
380000 eventbus messages sent in 799 ms.
400000 eventbus messages sent in 902 ms.
420000 eventbus messages sent in 926 ms.
440000 eventbus messages sent in 1919 ms.
460000 eventbus messages sent in 1938 ms.
480000 eventbus messages sent in 1958 ms.
500000 eventbus messages sent in 1980 ms.
replyCount: 10000 after 2010 ms
replyCount: 20000 after 2026 ms
replyCount: 30000 after 2038 ms
replyCount: 40000 after 2046 ms
replyCount: 50000 after 2055 ms
replyCount: 60000 after 2063 ms
replyCount: 70000 after 2072 ms
replyCount: 80000 after 2081 ms
replyCount: 90000 after 2089 ms
replyCount: 100000 after 2098 ms
replyCount: 110000 after 2107 ms
replyCount: 120000 after 2115 ms
replyCount: 130000 after 2124 ms
replyCount: 140000 after 2132 ms
replyCount: 150000 after 2141 ms
replyCount: 160000 after 2149 ms
replyCount: 170000 after 2158 ms
replyCount: 180000 after 2166 ms
replyCount: 190000 after 2175 ms
replyCount: 200000 after 2183 ms
replyCount: 210000 after 2192 ms
replyCount: 220000 after 2200 ms
replyCount: 230000 after 2208 ms
replyCount: 240000 after 2216 ms
replyCount: 250000 after 2224 ms
replyCount: 260000 after 2232 ms
replyCount: 270000 after 2241 ms
replyCount: 280000 after 2249 ms
replyCount: 290000 after 2257 ms
replyCount: 300000 after 2266 ms
replyCount: 310000 after 2274 ms
replyCount: 320000 after 2283 ms
replyCount: 330000 after 2292 ms
replyCount: 340000 after 2301 ms
replyCount: 350000 after 2309 ms
replyCount: 360000 after 2318 ms
replyCount: 370000 after 2326 ms
replyCount: 380000 after 2335 ms
replyCount: 390000 after 2343 ms
replyCount: 400000 after 2352 ms
replyCount: 410000 after 2360 ms
replyCount: 420000 after 2368 ms
replyCount: 430000 after 2377 ms
replyCount: 440000 after 2384 ms
replyCount: 450000 after 2390 ms
replyCount: 460000 after 2396 ms
replyCount: 470000 after 2402 ms
replyCount: 480000 after 2408 ms
replyCount: 490000 after 2414 ms
replyCount: 500000 after 2420 ms
all replies received in 2420 ms
```



## with cluster
first message received after `51ms`, done after `4546ms`.

```
Starting in clustered mode
二月 12, 2023 10:27:21 上午 com.hazelcast.system
信息: [192.168.56.1]:5701 [dev] [4.2.6] Hazelcast 4.2.6 (20221125 - 622d299) starting at [192.168.56.1]:5701
二月 12, 2023 10:27:21 上午 com.hazelcast.spi.discovery.integration.DiscoveryService
信息: [192.168.56.1]:5701 [dev] [4.2.6] No discovery strategy is applicable for auto-detection
二月 12, 2023 10:27:22 上午 com.hazelcast.instance.impl.Node
信息: [192.168.56.1]:5701 [dev] [4.2.6] Using Multicast discovery
二月 12, 2023 10:27:22 上午 com.hazelcast.cp.CPSubsystem
警告: [192.168.56.1]:5701 [dev] [4.2.6] CP Subsystem is not enabled. CP data structures will operate in UNSAFE mode! Please note that UNSAFE mode will not provide strong consistency guarantees.
二月 12, 2023 10:27:22 上午 com.hazelcast.internal.diagnostics.Diagnostics
信息: [192.168.56.1]:5701 [dev] [4.2.6] Diagnostics disabled. To enable add -Dhazelcast.diagnostics.enabled=true to the JVM arguments.
二月 12, 2023 10:27:22 上午 com.hazelcast.core.LifecycleService
信息: [192.168.56.1]:5701 [dev] [4.2.6] [192.168.56.1]:5701 is STARTING
二月 12, 2023 10:27:25 上午 com.hazelcast.internal.cluster.ClusterService
信息: [192.168.56.1]:5701 [dev] [4.2.6] 

Members {size:1, ver:1} [
	Member [192.168.56.1]:5701 - 7aea407b-bdbb-41f3-b5f3-d7b3752ad068 this
]

二月 12, 2023 10:27:25 上午 com.hazelcast.core.LifecycleService
信息: [192.168.56.1]:5701 [dev] [4.2.6] [192.168.56.1]:5701 is STARTED
二月 12, 2023 10:27:25 上午 com.hazelcast.internal.partition.impl.PartitionStateManager
信息: [192.168.56.1]:5701 [dev] [4.2.6] Initializing cluster partition table arrangement...
Starting ConsumerVerticle
Starting ProducerVerticle
Starting to send messages.
Consumer registered after 18 ms
Received first message after 51 ms
20000 eventbus messages sent in 254 ms.
40000 eventbus messages sent in 560 ms.
60000 eventbus messages sent in 686 ms.
80000 eventbus messages sent in 775 ms.
100000 eventbus messages sent in 919 ms.
120000 eventbus messages sent in 986 ms.
140000 eventbus messages sent in 1063 ms.
160000 eventbus messages sent in 1164 ms.
180000 eventbus messages sent in 1671 ms.
200000 eventbus messages sent in 1782 ms.
220000 eventbus messages sent in 1856 ms.
240000 eventbus messages sent in 1947 ms.
260000 eventbus messages sent in 2108 ms.
280000 eventbus messages sent in 2200 ms.
300000 eventbus messages sent in 2303 ms.
320000 eventbus messages sent in 2399 ms.
340000 eventbus messages sent in 2498 ms.
360000 eventbus messages sent in 2608 ms.
380000 eventbus messages sent in 2867 ms.
400000 eventbus messages sent in 3029 ms.
420000 eventbus messages sent in 3125 ms.
440000 eventbus messages sent in 3226 ms.
460000 eventbus messages sent in 3332 ms.
480000 eventbus messages sent in 3436 ms.
500000 eventbus messages sent in 3538 ms.
replyCount: 10000 after 4001 ms
replyCount: 20000 after 4017 ms
replyCount: 30000 after 4030 ms
replyCount: 40000 after 4043 ms
replyCount: 50000 after 4057 ms
replyCount: 60000 after 4070 ms
replyCount: 70000 after 4083 ms
replyCount: 80000 after 4096 ms
replyCount: 90000 after 4109 ms
replyCount: 100000 after 4122 ms
replyCount: 110000 after 4135 ms
replyCount: 120000 after 4148 ms
replyCount: 130000 after 4159 ms
replyCount: 140000 after 4170 ms
replyCount: 150000 after 4180 ms
replyCount: 160000 after 4190 ms
replyCount: 170000 after 4200 ms
replyCount: 180000 after 4211 ms
replyCount: 190000 after 4222 ms
replyCount: 200000 after 4233 ms
replyCount: 210000 after 4243 ms
replyCount: 220000 after 4253 ms
replyCount: 230000 after 4263 ms
replyCount: 240000 after 4273 ms
replyCount: 250000 after 4283 ms
replyCount: 260000 after 4294 ms
replyCount: 270000 after 4305 ms
replyCount: 280000 after 4315 ms
replyCount: 290000 after 4326 ms
replyCount: 300000 after 4337 ms
replyCount: 310000 after 4348 ms
replyCount: 320000 after 4359 ms
replyCount: 330000 after 4371 ms
replyCount: 340000 after 4382 ms
replyCount: 350000 after 4393 ms
replyCount: 360000 after 4405 ms
replyCount: 370000 after 4416 ms
replyCount: 380000 after 4427 ms
replyCount: 390000 after 4438 ms
replyCount: 400000 after 4448 ms
replyCount: 410000 after 4458 ms
replyCount: 420000 after 4468 ms
replyCount: 430000 after 4478 ms
replyCount: 440000 after 4488 ms
replyCount: 450000 after 4498 ms
replyCount: 460000 after 4508 ms
replyCount: 470000 after 4518 ms
replyCount: 480000 after 4528 ms
replyCount: 490000 after 4537 ms
replyCount: 500000 after 4546 ms
all replies received in 4546 ms
```

------

<<<<<< [END] >>>>>>
