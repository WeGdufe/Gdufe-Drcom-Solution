# Gdufe-Drcom-Solution

**严禁商业使用，拒绝变相套现**

本项目收集了广东财经大学的Dr.com城市热点上网解决方案，不包含官方客户端。  
大多数程序为信院13，14级折腾而来，此项目存放可执行版，大部分程序内置源码。

>**2019-09-22 更新说明：** 在大佬们毕业后，官方更新了web版，所以下面的 windows、mac版可用性未知

## Windows
官方客户端各种恶心检测，建议使用 `EasyDrcom` 客户端，没有Wifi、代理检测等，只做上网使用。  
EasyDrcom 原为哈尔滨工业大学项目，13级 `XeonForce` 做了广财的适配，14级 [HowquaX](https://github.com/HowquaX) 提供一键安装包。 

主程序需要安装 `WinPcap` 库才能运行。
1. 小白：直接用[一键安装包](./Win/EasyDrcomSetup_with_winpcap_v1.7.zip)，安装不上则手动安装。Win10装 `Win10Pcap.msi`，其他系统装 `WinPcap.exe` 。 
1. 懂行的：有 `WinPcap` 库的直接用 [EasyDrcom_without_winpcap.exe](./Win/EasyDrcom_without_winpcap.exe)  
![](http://epa.gdufe.edu.cn/ueditor/php/upload/image/20161115/1479221388315255.png)

源码： https://github.com/HowquaX/EasyDrcomGUI-For-GDUFE  
源码修改点见 http://howqua.top/archives/1245.html

## Mac
苹果电脑可用 [官方版](http://xxb.gdufe.edu.cn/2016/0902/c4975a95360/page.htm)
 或者 [EasyDrcom 苹果版 - By HowquaX](./Mac/广财EasyDrcom_for_Mac.zip)，官方版会修改部分系统文件，影响开发，所以才有了苹果版。
源码： https://github.com/HowquaX/EasyDrcomGUI-MacOS

源码修改点见： http://howqua.top/archives/1245.html

## Linux
Linux的官方版不可用。
1. 若账号不是电信提速账号，使用 [各平台通用的Python版](./各平台通用/drcomD_Gdufe.py)
编辑该文件，修改学号密码和ip后可用。命令行： `nohup python drcomD_Gdufe.py & ` ，停止按 `Ctrl+Z`
1. 提速账号可使用Web版  

## 各系统通用
 除开以上针对系统的方案，还有[各系统通用的Python版](./各平台通用/drcomD_Gdufe.py)，需要python环境，2.x和3.x都行，需要修改文件里的学号密码和ip，运行后不可关闭该程序/终端。

## 路由器
1. 推荐 http://tieba.baidu.com/p/4788388982 这个是贴吧的教程，自带文件了
2. 有个没人维护的 [路由器版](./路由器)，原作者：  `XeonForce` 。目测没有针对2016年9月的官方更新而改版本号，不确保可用。

## 神器 - 提速账号多人登陆
以上均为普通账号的方案，此处**低调使用**：电信提速账号可用Web版登陆，可多人登陆。  
虽然官方去掉了登陆输入框界面，但接口还在，但只能是提速账号才可用。  
多人登陆需全部人都用网页版登陆，不能用官方客户端，否则全部人掉线。

1. 校内网直接打开html登陆就行，之后网页可关闭
1. 提供了python版用于开机自动登陆使用，加在计划任务里就行了
1. 也提供Android版（连 `gdufe` Wifi）和对应源码，可与网页/Python版一起用

另如果需要普通账号的安卓版转见 [小广财](https://github.com/WeGdufe/SmallGdufe-Android) 。 

## 其他
14数院某大佬 搞了个去掉wifi检测的官方drcom补丁(Windows系统)，关掉360等管家后把文件覆盖到 `C:\Drcom\DrUpdateClient` 就行。

## 联系各大佬

13计算机 wintercoder  https://github.com/wintercoder    QQ：792875586 （备注下是这里来的）
13计算机 XeonForce 暂无
14计算机 HowquaX http://howqua.top/archives/1245.html  、https://github.com/HowquaX  
14数院某大佬  暂无  
欢迎其他大佬参与[WeGdufe系列项目](https://github.com/WeGdufe/)。技术学习可以加群： 631036490 




## License
GPL 感染大法，虽然许可本身不限制商用，只要求开源，但该项目组不得用于商业盈利
