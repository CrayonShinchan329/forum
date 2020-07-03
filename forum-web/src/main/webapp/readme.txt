题目：学生社团管理系统
开发语言：JAVA
数据库：MYSQL
JAVA开发框架：Spring MVC+Spring+Mybatis
前端开发框架：Bootstrap
前台模板引擎：Freemarker
环境要求：
 JDK7或更高版本
 Tomcat7.0或更高版本
 MySQL5.1或更高版本
搭建步骤
 1、在IDEA里导入该系统文件
 2、导入数据库，将forum.sql导入到mysql数据库中；(推荐使用Navicat for MySQL，只需要运行SQL文件即可)
 3、修改数据库信息，修改文件：E:\毕业设计y\shetuan\forum-web\src\main\resources\jdbc.properties（具体路径视自己情况而定）
    jdbc.driver=com.mysql.jdbc.Driver
    jdbc.url=jdbc:mysql://数据库服务器IP(本地直接用localhost):端口号(默认3306)/数据库名?characterEncoding=utf8&zeroDateTimeBehavior=convertToNull
    jdbc.user=数据库用户名
    jdbc.password=数据库密码
    #后台管理目录
    managePath=manage
 4、重启Tomcat。
  后台登录地址：http://localhost/（在我的电脑使用的是默认端口80）
  系统管理员：
        用户名：admin
        密码：123456
  其他用户（示例）
        用户名：王安生ceshi
        密码：123456
