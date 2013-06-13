trunk
=====

bricks framework development source

上传初始代码，bricks组件及测试web/service，其中datas为配置的mock数据源

###### jar包说明 start ########
AspectLib   组件化配置工具
ModuleTest  组件化测试

Bricks	框架主要包
Bricks_Depends  框架主要依赖包
Management	框架的管理组件包
Service		测试服务包
BricksWebTest	web测试包

lib	依赖夹
datas	mock数据文件夹

modules_design.jpg	组件化设计图
modules_package.jpg	组件间包图

测试需要正常启动需要配置Bricks_Depends项目下的data/config/config.properties
###### jar包说明 end ########

###### 配置git忽略文件 start ########
鉴于git的管理文件需要忽略
build
dist
请在库路径下.gitignore
追加如下内容
.gitignore
dist
build

没有文件可以手工创建
###### 配置git忽略文件 end ########


######## netbeans 7 配置git start #########
1.git复制对应的git路径
2.远程（remotes）---》提取(pull)  服务器上的文件
修改文件的提交
3.新建文件，在项目下进行add,并提交到本地库中
4.推入（push）到远程

######## netbeans 7 配置git end #########

添加IRC及TravisCI支持

1.IRC的讨论频道为#bricksdev ,项目push和构建测试结果会在频道中显示。

2.已经完成TravisCI的配置，git push后会编译并测试Service项目及依赖项目。构建失败时会给出邮件提示。

* [![build status](https://secure.travis-ci.org/bricksdev/trunk.png)](https://travis-ci.org/bricksdev/trunk) on travis-ci.org (master branch)
