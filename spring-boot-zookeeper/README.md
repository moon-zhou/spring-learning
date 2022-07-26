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
2. 通过`homebrew`启动，启动后查看启动状态及对应配置文件：
   ```
   brew services start zookeeper
   brew services stop zookeeper
   ```
   ```
   $ brew services list
   Name       Status  User     File
   zookeeper  started xxxxxxxx ~/Library/LaunchAgents/homebrew.mxcl.zookeeper.plist
   ```
   配置文件详细：
   ```
   $ more homebrew.mxcl.zookeeper.plist
   <?xml version="1.0" encoding="UTF-8"?>
   <!DOCTYPE plist PUBLIC "-//Apple//DTD PLIST 1.0//EN" "http://www.apple.com/DTDs/PropertyList-1.0.dtd">
   <plist version="1.0">
   <dict>
           <key>EnvironmentVariables</key>
           <dict>
                   <key>SERVER_JVMFLAGS</key>
                   <string>-Dapple.awt.UIElement=true</string>
           </dict>
           <key>KeepAlive</key>
           <dict>
                   <key>SuccessfulExit</key>
                   <false/>
           </dict>
           <key>Label</key>
           <string>homebrew.mxcl.zookeeper</string>
           <key>ProgramArguments</key>
           <array>
                   <string>/opt/homebrew/opt/zookeeper/bin/zkServer</string>
                   <string>start-foreground</string>
           </array>
           <key>RunAtLoad</key>
           <true/>
           <key>WorkingDirectory</key>
           <string>/opt/homebrew/var</string>
   </dict>
   </plist>
   ```
3. macos本地部署伪集群
   1. 配置多个（3个）配置文件
   2. 启动时分别指定多个（3个）配置文件位置
   ```
   zkServer start/stop zoo1.cfg
   zkServer start/stop zoo2.cfg
   zkServer start/stop zoo3.cfg
   ```

### 基本操作
#### ls 查看
```
localhost:2181	$	ls /
[cluster, controller_epoch, controller, brokers, zookeeper, feature, admin, isr_change_notification, consumers, log_dir_event_notification, latest_producer_id_block, config]
localhost:2181	$	ls /zookeeper
[config, quota]
```

### get 获取节点数据和更新信息


### 代码操作
#### JAVA
#### 集成`spring boot`


### 客户端
[PrettyZoo,下载地址](https://github.com/vran-dev/PrettyZoo/releases)，github很慢，可以找些加速方法。

### 参考
1. [Zookeeper系列一：Zookeeper基础命令操作](https://juejin.cn/post/6844903615992168461)