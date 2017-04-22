在ubuntu通过proxychains使用socks5代理

1.装pip，pip装ss

```shell
 $ apt-get install -y  python-pip
 $ pip install shadowsocks -i http://pypi.douban.com/simple
```

2.创建ss.json文件

```json
{
    "server":"my_server_ip",
    "server_port":8388,
    "local_address": "127.0.0.1",
    "local_port":1080,
    "password":"mypassword",
    "timeout":300,
    "method":"aes-256-cfb",
    "fast_open": false
}
```

3.在同级目录下输入

```shell
$ sslocal -d start -c ss.json 
```

4.装git

```shell
$ sudo apt-get -y install git-all
```

5.git走ss代理

```shell
//设置代理
$ git config --global http.proxy 'socks5://127.0.0.1:1080' 
$ git config --global https.proxy 'socks5://127.0.0.1:1080'


//取消代理
$ git config --global --unset http.proxy
$ git config --global --unset https.proxy
```

6.从 GitHub 下载 proxychains-ng

```shell
$ git clone https://github.com/rofl0r/proxychains-ng.git‘

$ wget https://github.com/rofl0r/proxychains-ng/archive/master.zip
```

7.解压

```shell
$ tar -zxvf master.zip
$ unzip master.zip 
```

8.编译

```shell
cd proxychains-ng
./configure
make && make install
cp ./src/proxychains.conf /etc/proxychains.conf
cd .. && rm -rf proxychains-ng
```

9.编辑conf文件，将最后一行改为 socks5  127.0.0.1 1080

10.然后就可以使用 proxychains4 curl google.com 进行代理了

