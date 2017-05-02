### 坑点

Mapper.xml一定要放在resources中，因为maven项目的约定配置文件必须放resources里，src目录下的xml文件默认不会编译到target。