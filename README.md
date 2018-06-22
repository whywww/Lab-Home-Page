# 实验室主页Lab Home Page

I used Spring boot Framework to develop this website.

Administrators can login to the admin management system to edit information of users, publications and projects.

Members of the lab can login to edit their own information.

Visitors cannot register their own account. They can only browse the information of the lab.

If there are any questions, feel free to contact me 740602798@qq.com

# Database Configuration

- url: jdbc:mysql://127.0.0.1:3306/Lab


- username: root
- password: root


### Tables:

#### Usr:

usr_name	用户名		varchar(50) 	主键 NOT NULL

name		姓名		varchar(50)	可以重复

pswd		密码		varchar(20)	非空

authority	权限		integer		0是管理员，1是普通用户 非空 默认1

photo		照片		varchar(300)	服务器相对路径 默认路径 ../static/images/upload/default.jpg

education	身份		varchar(10)	（Phd/Master/Undergraduate）

email		邮箱		varchar(20)

research_area研究领域	varchar(50)

usr_describe	个人说明		text 

```
mysql> create table Usr(
    -> usr_name varchar(50) not null,
    -> name varchar(50),
    -> pswd varchar(20) not null,
    -> authority int not null default 1,
    -> photo varchar(300) default "../static/images/upload/default.jpg",
    -> education varchar(20),
    -> email varchar(50),
    -> research_area varchar(100),
    -> usr_describe text,
    -> primary key (usr_name)
    -> )default charset = utf8;
Query OK, 0 rows affected (0.29 sec)
```



#### Publication:

pub_id		刊物编号		integer		主键，自增

pub_title	刊物标题		varchar(100)

pub_date	发表日期		varchar(30)

type		刊物类型		varchar(10)	journal/conference/patent

type_name	期刊/会议名	varchar(50)	

patent_region 专利地区	varchar(50)	若不是专利则为null

patent_id	专利号		varchar(30)	若不是专利则为null

pub_href	刊物链接		varchar(100)

```
mysql> create table Publication(
    -> pub_id int not null auto_increment,
    -> pub_title varchar(100),
    -> pub_date varchar(30),
    -> type varchar(10),
    -> type_name varchar(50),
    -> patent_region varchar(50),
    -> patent_id varchar(30),
    -> pub_href varchar(100),
    -> primary key (pub_id)
    -> )default charset = utf8;
Query OK, 0 rows affected (0.21 sec)
```



#### PublicationAuthor表:

pub_id		论文编号		integer		主键，外键Publications(pub_id)

author		作者		varchar(50)	主键，外键Usr(usr_name)

cat			作者类别		integer		第一作者：1，第二作者：2，没有就为null

```
mysql> create table PublicationAuthor(
    -> pub_id int not null,
    -> usr_name varchar(50) not null,
    -> cat int,
    -> primary key (pub_id, usr_name),
    -> foreign key(pub_id) references publication(pub_id) on delete cascade on update cascade,
    -> foreign key(usr_name) references usr(usr_name) on delete cascade on update cascade
    -> )default charset = utf8;
Query OK, 0 rows affected (0.54 sec)
```



#### Project:

proj_id		项目编号		integer		主键

proj_name	项目名		varchar(100)

proj_href	项目链接		varchar(100)

proj_image	项目图片		varchar(100)	默认"../static/images/upload/proj/proj-default.jpg"

proj_describe项目描述	texts

```
mysql> create table project(
    -> proj_id int not null auto_increment,
    -> proj_name varchar(100),
    -> proj_href varchar(100),
    -> proj_image varchar(100) default "../static/images/upload/proj/proj-default.jpg",
    -> proj_describe text,
    -> primary key (proj_id)
    -> )default charset = utf8;
Query OK, 0 rows affected (0.31 sec)
```



#### ProjectMember:

proj_id		项目编号		integer		主键，外键Projects(proj_id)

usr_name	项目参与人员	varchar(50)	主键，外键Usr(usr_name)	

```
mysql> create table projectMember(
    -> proj_id int not null,
    -> usr_name varchar(50) not null,
    -> primary key (proj_id, usr_name),
    -> foreign key (proj_id) references project(proj_id) on delete cascade on update cascade,
    -> foreign key (usr_name) references usr(usr_name) on delete cascade on update cascade
    -> )default charset = utf8;
Query OK, 0 rows affected (0.37 sec)
```


# Lab-Home-Page
