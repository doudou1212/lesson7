#配置#
##1.所需软件##
数据库：mysql 5.7   
服务器：Tomcat 7.0   
IDE: IntelliJ JAVA
##2.数据库##
1. 需要在mysql中创建一个名为“book”的数据库
2. 创建一个用户名:root，密码:123456的账户（或者在Dao包中的DBInfo类中将用户名和密码换成自己数据库中存在的用户名和密码）
3. 在book库中创建“booksinfo”和“bookmarks”两张数据表

###booksinfo建表语句###
*booksinfo:用于存储书籍信息的数据表*  
create table booksinfo(  
\`id\` INT(11) not null auto\_increment comment 'book id',  
\`title\` VARCHAR(255) not null default '0' comment 'book name',  
\`create_time\` INT(11) not null default 0 comment 'create\_time',   
\`is\_deleted\` INT(1) not null default 0 comment '0:not deleted  1:is deleted',  
primary key (\`id\`),  
key idx\_booksinfo (id,is\_deleted)  
);

###bookmarks建表语句###
*bookmarks:用于存储标签信息*  
create table boomarks(  
\`id\` INT(11) not null auto\_increment comment 'mark id',  
\`title\` VARCHAR(255) not null default '0' comment 'book name',  
\`url\` VARCHAR(255) not null default '0' comment 'the url of mark',  
\`create_time\` INT(11) not null default 0 comment 'create_time',  
\`is\_deleted\` INT(1) not null  default 0 comment '0:not deleted  1:is deleted',  
primary key (\`id\`),  
key idx\_booksinfo (id,is\_deleted)  
);

##3.需要的jar包##
项目所需的jar包都在lib目录下。   
运行工程时，需要将lib目录下的jar包添加到项目的libary中；   
同时将lib目录下的jar包复制到Tomcat的lib目录中。
##4.运行##
运行工程后，会有两个页面，其中：
inserBooks.jsp：点击页面上的按钮，将项目中resources目录里的JSON文件解析，并添加到数据库中，因此需要**先运行**   
index.jsp:书签管理及搜索功能

##5.遗留问题##
1. 想在dao层只封装对数据库的操作，但是在查询时，如果关闭了对数据库的连接，ResultSet就不能访问了。只能在dao层解析了查询结果，但是这样就做了dao层不应该做的事情。   
2. 比如这条数据：    
{
    "title": "好玩的免费软件：Gmail Drive （Gmail 硬盘） - BeanSoft's Java Blog - BlogJava",
    "created": "1387243195"
}，我的数据库插入语句是：  
       String sql = "INSERT INTO booksinfo (title,create\_time,is_deleted) VALUES ('"+bookname+"' ,"+new Integer(createtime)+",0);";     
因为JSON数据中的title里带有单引号，构造出的SQL语句就变成了：     
INSERT INTO booksinfo (title,...) VALUES ('BeanSoft's', ... )    
这样的数据插入数据库时会有单引号匹配的错误，要怎么解决？     
*ps:似乎在数据库中插入单引号不是一种好的做法*
