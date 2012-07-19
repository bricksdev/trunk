trunk
=====

bricks framework development source

上传初始代码，bricks组件及测试web/service，其中datas为配置的mock数据源

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

鉴于git的管理文件需要忽略
build
dist
请在库路径下.gitignore
追加如下内容
.gitignore
dist
build

没有文件可以手工创建

