# info-collect
此项目为易班新生信息收集web端  
项目使用SSM+thymeleaf+lombok+pagehelper+bootstrap完成  
分Manager, 教师, 学生三端  
由学号工号判断身份(校级权限)后保存Token到session以识别身份  

# 学生端:
在manager规定的时间内完成填写  
学生访问后调用一次接口将返回数据保存到IdModel对象以减少接口调用次数  
大部分信息来源于易班接口返回的学生数据, 学生只需填一少部分  
填写后主页填写按钮隐藏, 我的信息按钮显示, 学生预览确认信息并可在规定时间内更改  

# 教师端:
在manager规定时间后预览并可下载Excel  
预览由pagehelper完成分页  
Excel生成时机由timer定时器实现, 时间为学生填写时间结束(0点后)当天  

# Manager:
设定起始时间, 可无条件访问所有页面并下载  

# jdbc.properties 懒得加密, 直接删了ε=ε=ε=┏(゜ロ゜;)┛