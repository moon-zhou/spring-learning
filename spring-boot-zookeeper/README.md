## zookeeper

### 安装
[官网下载](https://zookeeper.apache.org/releases.html)，mac还可以通过homebrew进行安装。
```
// macos
brew install zookeeper
```

### 检查配置
homebrew安装的文件在`/opt/homebrew/Cellar`目录下，配置文件均在`/opt/homebrew/etc`下。进入zk的配置目录，查看配置信息，尤其是数据目录。
```
$ cd /opt/homebrew/etc/zookeeper

$ more zoo.cfg
# The number of milliseconds of each tick
tickTime=2000
# The number of ticks that the initial
# synchronization phase can take
initLimit=10
# The number of ticks that can pass between
# sending a request and getting an acknowledgement
syncLimit=5
# the directory where the snapshot is stored.
# do not use /tmp for storage, /tmp here is just
# example sakes.
dataDir=/opt/homebrew/var/run/zookeeper/data
# the port at which the clients will connect
clientPort=2181
# the maximum number of client connections.
# increase this if you need to handle more clients
#maxClientCnxns=60
#
# Be sure to read the maintenance section of the
# administrator guide before turning on autopurge.
#
# http://zookeeper.apache.org/doc/current/zookeeperAdmin.html#sc_maintenance
#
# The number of snapshots to retain in dataDir
#autopurge.snapRetainCount=3
# Purge task interval in hours
# Set to "0" to disable auto purge feature
#autopurge.purgeInterval=1

## Metrics Providers
#
# https://prometheus.io Metrics Exporter
#metricsProvider.className=org.apache.zookeeper.metrics.prometheus.PrometheusMetricsProvider
#metricsProvider.httpPort=7000
#metricsProvider.exportJvmInfo=true
```
确认数据目录：`dataDir=/opt/homebrew/var/run/zookeeper/data`，无需改动。

### 配置关联
```
/opt/homebrew/etc/zookeeper
$ ls
defaults         log4j.properties zoo.cfg

/opt/homebrew/etc/zookeeper
$ more defaults
[ -z "$ZOOCFGDIR" ] && export ZOOCFGDIR="/opt/homebrew/etc/zookeeper"
```

### 启动
1. 直接启动
   ```
   zkServer start
   zkServer stop
   ```
2. 通过`homebrew`启动
   ```
   brew services start zookeeper
   brew services stop zookeeper
   ```
3. macos本地部署伪集群
   1. 配置多个（3个）配置文件
   2. 启动时分别指定多个（3个）配置文件位置
   ```
   zkServer start/stop zoo1.cfg
   zkServer start/stop zoo2.cfg
   zkServer start/stop zoo3.cfg
   ```

### 客户端
[PrettyZoo,下载地址](https://github.com/vran-dev/PrettyZoo/releases)，github很慢，可以找些加速方法。

### 参考