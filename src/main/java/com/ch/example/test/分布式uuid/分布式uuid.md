一、数据库发号器
每一次都请求数据库，通过数据库的自增ID来获取全局唯一ID
对于小系统来说，这是一个简单有效的方案，不过也就不符合讨论情形中的高并发的场景。
首先，数据库自增ID需要锁表
而且，UUID的生成强依赖于数据库，每次获取UUID都需要经过一次数据库的调用，性能损耗很大。
其实，在这种大并发的场景中，数据库的主键都不建议使用数据库的自增ID。因为虽然这个简单，但是如果随便业务发展，
需要对原有的数据进行重新分库分表的时候，可能会产生主键冲突，这影响了系统的平滑扩容，容易埋下坑。
二、中间件产生UUID
常用的中间件，以redis和zookeeper为例，都有产生分布式唯一ID的方案，如redis的getAndIncrement，zookeeper的sequenceId。
都是分布式UUID的解决方案。而且redis和zookeeper中间件的性能都很强大，比数据库要好。
缺点还是，UUID的生成强依赖于中间件，每次获取UUID都需要一次远程调用。
依赖远程调用的缺陷，可以通过一次取批量的方式来解决，据说weibo就是这么做的，从redis中批量取一堆。
强依赖于中间件这件事，总感觉是一个不好的设计。虽然现在的中间件可靠性都比较好，甚至可以做到5个9以上，
但是主业务流程强依赖于中间件，还是觉得有那么些不爽。比如强依赖数据库这个是可以接受的，但是依赖于zookeeper或redis从设计上看不可取。
三、UUID
Universally Unique IDentifier(UUID)，这是一个具有rfc标准的uuid，见RFC文档

4.1.4 Timestamp 时间戳
timestamp是一个60位bit的值。
对于V1版本的UUID来说，这代表着UTC的时间，也就是从1582年的10月15日的00：00：00.00到现在为止经过的时间，单位是100 nanosecond，对于那些没有可用UTC的系统来说，他们可以用本机时间代替UTC，只要他们在系统中始终保持着这种一致性。但是并不推荐这种做饭，因为本机时间和UTC时间只是一个时区位移的区别而已。
对于V3和V5版本的UUID来说，timestamp是一个60bit的值，由一个name来得到的。
对于V4版本的UUID来说，timestamp是一个60bit的随机数，或者说伪随机数。
timestamp中最重要的就是代表版本的那4位，位于time_hi_and_version字段中的第4到第7位，这是用来区分不同版本的，具体的内容参见4.1.3。

4.1.5. Clock Sequence 时钟序列
对于V1版本的UUID来说，顺序号是用来帮助避免当时钟后退可能带来的冲突，以及node id发生变化时可能引起的冲突。
比如，当系统由于断电等原因导致时间倒退时，UUID生成器无法确保是否已经有比当前设置的系统时间更大的UUID已经被生成了，所以始终序列ID需要进行更新，如果知道之前的值的话，更新的操作只需对其进行+1即可，如果不知道的话，应该设置成一个随机数。
同样的，如果节点的id变化了的话，比如某一块网卡从一台机器转插到另一台，通过重置序列号的方式也能减少产生冲突的可能。如果之前的序列号是已知的话，那么只需要简单地进行+1即可，当然，这种情况不大可能发生。
序列号的初始值必须是随机的，这样才可以减少与系统的相关性，这样可以更好地保护UUID，防止系统间迅速地切换破坏UUID的唯一性。所以，序列号的初始值一定是要与node id无关的。

4.1.6. Node 节点
对于V1版本的UUID来说，node字段是由IEEE 802的MAC地址组成的，通常是本机的地址，对于那些有多个IEEE 802地址的机器来说，任选一个作为node字段即可。对于那些没有IEEE地址的机器来说，可以用一个随机数或者伪随机数来代替。
对于V4版本的UUID来说，node字段是由随机数或者伪随机数构成。

4.4. Algorithms for Creating a UUID from Truly Random orPseudo-Random Numbers
v4版本的UUID设计就是通过随机数或者是伪随机数来生成UUID。

算法有以下规则：
1、最重要的两位，第6位和第7位，clock_seq_hi_and_reserved，分别设置成0和1.
2、最重要的四位，第12位到第15位，time_hi_and_version，设置成4.1.3描述的内容，0100。
3、其他的位设置成随机数或伪随机数即可。

总结
从定义中了解了V1和V4这两种比较有代表性的UUID生成规则，实际的生产应用中，V1好像并没有严格的实现。而V4这种基本都是伪随机数的做法，JDK的UUID就是这么干的。
这种完全随机的做法，好处是不用再依赖了，但是可读性较差，而且如果使用其作为主键的话，数据库中的索引会经常需要进行改动。

四、SnowFlake
snowflake算法是twitter所使用的生成UUID的算法。为了满足Twitter每秒上万条消息的请求，每条消息都必须分配一条唯一的id，且这些id还需要根据时间基本有序。



如图所示，这里第1位不可用，前41位表示时间，中间10位用来表示工作机器的id，后12位的序列号.
其中时间比较好理解，工作机器id则是机器标识，序列号是一个自增序列。有多少位表示在这一个单位时间内，此机器最多可以支持2^12个并发。在进入下一个时间单位后，序列号归0。

当然，这些字段的排序和定义也不一定要完全与他一致。比如第一位也可以使用起来，workerid还可以分成其他。
要保证根据时间大致有序，所以高位用来保存时间的内容是不可避免了，由于很多操作系统本身只支持毫秒级的时间，所以时间单位使用毫秒级就已经足够了。
这三个字段的长度分配分别与如下指标相关：系统设计可用时间、系统所包含的机器数量、系统设计的单机QPS。所以可以根据系统的实际情况，灵活进行调整。
worker id这个字段，为了不冲突，可以进行统一分配管理，也可以通过服务注册等方式来进行动态管理。当然第一种分配管理这种把work id写入到代码或者配置中的方式显然不可取，如果是小系统可以进行简单粗暴地redis的getAndIncrement进行处理，反正位数多，不怕浪费。

参照代码实现如下

sequenceMask = ~(-1L << sequenceBits);

public synchronized long nextId() {
    long currentTimeMillis = System.currentTimeMillis();
    if (currentTimeMillis < lastTimeMillis) {
        throw new RuntimeException(String.format("clock is moving backwards.
        Rejecting requests until %d.", lastTimeMillis));
    }

    if (currentTimeMillis == lastTimeMillis) {
        sequence = (sequence + 1) & sequenceMask;
        if (sequence == 0) {
            for (; currentTimeMillis <= lastTimeMillis; ) {
                currentTimeMillis = System.currentTimeMillis();
            }
        }
    } else {
        sequence = 0;
    }

    lastTimeMillis = currentTimeMillis;
    return ((currentTimeMillis - TWEPOCH) << timeLShift) |
        (dataCenterId << dataCenterLShift) |
        (workerId << workerLShift) |
        sequence;
}