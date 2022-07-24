## kafka

### 安装
[官网下载](https://kafka.apache.org/downloads)，mac还可以通过homebrew进行安装。
#### macos，使用homebrew进行安装
```
// 使用 brew deps 可以查看依赖关系；--tree 参数以树状显示依赖关系
$ brew deps --tree kafka
kafka
├── openjdk
└── zookeeper
    ├── openjdk
    └── openssl@1.1
        └── ca-certificates

// 安装
brew install kafka
```

### 检查配置
homebrew安装的文件在`/opt/homebrew/Cellar`目录下，配置文件均在`/opt/homebrew/etc`下。进入kafka的配置目录，查看配置信息，尤其是数据目录，listener地址。

server.properties
```
$ cd /opt/homebrew/etc/kafka

$ more server.properties
...
listeners=PLAINTEXT://:9092
advertised.listeners=PLAINTEXT://your.host.name:9092

log.dirs=/opt/homebrew/var/lib/kafka-logs
zookeeper.connect=localhost:2181

```

consumer.properties
```
$ more consumer.properties
# Licensed to the Apache Software Foundation (ASF) under one or more
# contributor license agreements.  See the NOTICE file distributed with
# this work for additional information regarding copyright ownership.
# The ASF licenses this file to You under the Apache License, Version 2.0
# (the "License"); you may not use this file except in compliance with
# the License.  You may obtain a copy of the License at
#
#    http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.
# see org.apache.kafka.clients.consumer.ConsumerConfig for more details

# list of brokers used for bootstrapping knowledge about the rest of the cluster
# format: host1:port1,host2:port2 ...
bootstrap.servers=localhost:9092

# consumer group id
group.id=test-consumer-group

# What to do when there is no initial offset in Kafka or if the current
# offset does not exist any more on the server: latest, earliest, none
#auto.offset.reset=
```

### 启动
```
brew services start kafka
```

启动时，没查到运行进程：`ps -ef | grep kafka`，使用homebrew查看服务情况：`brew services list`，发现状态是error。
进入kafka配置目录：`/opt/homebrew/var/log/kafka`，此处日志与实际配置不一致，待排查（TODO）。
```
/opt/homebrew/Cellar/kafka/3.2.0/libexec/bin/kafka-run-class.sh: line 342: /opt/homebrew/@@HOMEBREW_JAVA@@/bin/java: No such file or directory
/opt/homebrew/Cellar/kafka/3.2.0/libexec/bin/kafka-run-class.sh: line 342: exec: /opt/homebrew/@@HOMEBREW_JAVA@@/bin/java: cannot execute: No such file or directory
```
原因为：安装brew使用的中科大的镜像。重新安装：`HOMEBREW_BOTTLE_DOMAIN= brew reinstall kafka`。
安装后启动成功，查看状态：
```
ps -ef | grep kafka
brew services list
jps
```


### 关于使用kafka自带zk还是自己安装zk
建议分离，使用自己安装的zk。如果自己本地测试，方便起见可以直接使用kafka自带的zk，无需再额外下载。但是启动步骤，都需要先启动zk，再启动kafka。

参考意见：
1. 一是这个命令行工具是从 Kafka 0.5.x 开始后才集成进 Kafka 的二进制包里的，早期就是得自己部署，用过早期版本的可能就延续下这个习惯了。（现在不会应该很少有用这么古早版本的了吧……）
2. 二是自带的这个默认配置是单机版的，一般来说生产环境肯定是要做集群来保证高可用，如果直接改自带的这个配置也不是不可以，但万一改错了把 Kafka 弄坏了咋整？
3. 三是一般来说 ZK 集群和 Kafka 集群应该分离才对，但二进制包里它俩耦合在一起了，为了部署 ZK 集群连带着拷贝了 Kafka 目录有点儿多余（费点儿心思单独拷出来也不是不行……），所以索性不去动它，另起一套。


### 客户端
- macos：[Offset Explorer / kafka tool](https://www.kafkatool.com/download.html)
- Windows：[Kafka Assistant](http://www.redisant.cn/ka)

### 参考
1. [Mac OS 安装 Kafka](https://juejin.cn/post/7100071216727195684)
2. [Kafka——关于Kafka自带的ZooKeeper和自己安装的ZooKeeper的使用问题](https://www.cnblogs.com/zuiyue_jing/p/12781668.html)