### ZooKeeper

1.下载 http://www.apache.org/dyn/closer.cgi/zookeeper/

2.修改 zookeeper-3.4.9/conf/zoo-sample.cfg 为 zoo.cfg，修改里面的内容dataDir=/Users/Ray/Desktop/zookeeper-3.4.9/temp，这里选择一个储存zk内容的文件夹

3.terminal进入到bin目录下，使用./zkServer.sh start 开启服务，./zkServer.sh stop 停止服务

4.使用zkCli.sh –server localhost:2181 连接到zk服务器，输入help查看可用的zk命令

5.连接到zk后，可用用 ls / 查看所包含的内容

```shell
[zk: localhost:2181(CONNECTED) 11] ls /
[zk, zookeeper]
```

6.创建一个znode，使用 create /zk myData 创建了一个新的znode节点"zk"  以及它关联的字符串

```shell
[zk: localhost:2181(CONNECTED) 15] create /zk myData
Created /zk
```

7.再次使用 ls / 查看创建的节点

8.使用get /zk 查看储存的节点信息

```shell
[zk: localhost:2181(CONNECTED) 17] get /zk 
myData
cZxid = 0x4
ctime = Thu Apr 20 16:50:32 CST 2017
mZxid = 0x4
mtime = Thu Apr 20 16:50:32 CST 2017
pZxid = 0x4
cversion = 0
dataVersion = 0
aclVersion = 0
ephemeralOwner = 0x0
dataLength = 6
numChildren = 0

```

9.重新设置节点，set /zk hahaha

```shell
[zk: localhost:2181(CONNECTED) 18] set /zk hahaha
cZxid = 0x4
ctime = Thu Apr 20 16:50:32 CST 2017
mZxid = 0x5
mtime = Thu Apr 20 16:53:29 CST 2017
pZxid = 0x4
cversion = 0
dataVersion = 1
aclVersion = 0
ephemeralOwner = 0x0
dataLength = 6
numChildren = 0

```

10.删除节点 delete /zk