## 前言：
图书管理系统项目使用eclipse开发工具和MySql数据库进行开发，maven方式创建项目，编码格式为utf-8（如出现乱码可改成utf-8），
项目分成三层架构，数据访问层、业务逻辑层和表现层，并没有使用框架等复杂的技术，所以页面上比较简单。
这里Servlet我只做数据返回，所以完全可以是实现前后端分离，
前后端分离的话推荐使用vue，如果不分离也可以使用layer或其它前端框架。
通过这个项目对JSP、Servlet、JDBC、mysql数据库等技术有一个初步的认识和理解，同时也希望能够帮助有需要的朋友。

## 代码编写规范：
Dao数据访问层：主要是定义增删改查以及需要用到的方法，DaoImpl是实现dao接口的方法，主要是写sql语句，只跟数据库打交道。
Service业务逻辑层：主要是定义业务的方法，比如登录、注册，而ServiceImpl是实现登录功能，Service层不应该出现sql语句。
Servlet：连接Jsp表现层和上一层Service,主要是封装了Jsp数据。
Jsp表现层：将Servlet的数据进行显示到Jsp中，这里其实用html也行。

## Mysql数据库和服务器
我使用的数据库版本为8.0.15；
服务器为Tocmat9.0.2；
数据表在src/test/resources的sql文件夹下。

## 项目功能
按使用人员分为两类：
一、学生
1.通过图书编号ISBN或书名查询图书信息；
2.可以对图书进行借阅操作；
3.查询自己的借阅的书籍，并对其进行归还操作。

二、管理员
1.显示图书馆所有的图书，或者通过书编号ISBN或书名查询图书信息；
2.可对图书进行增加（录入）、编辑和删除；
3.查询所有学生的借阅的图书，也可以通过学生学号和图书编号ISBN进行查询借阅的图书。

## 项目展示
![](https://github.com/zhouhangzooo/LibrarySystem/raw/master/src/test/resources/image/manager_home.jpg)
![](https://raw.githubusercontent.com/zhouhangzooo/LibrarySystem/master/src/test/resources/image/manager_home.jpg)
