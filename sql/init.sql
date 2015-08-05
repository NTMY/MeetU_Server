
drop database if exists meetu;

create database meetu CHARACTER SET utf8;

use meetu;

drop table if exists u_user;
create table u_user(
	id int(16) auto_increment primary key comment '用户主键',
	mobile varchar(16) comment '手机号',
	pwd varchar(64) comment '密码',
	imei varchar(64) comment '手机imei',
	name varchar(64) comment '真实姓名',
	nickname varchar(64) comment '昵称',
	birthdate varchar(64) comment '生日',
	gender varchar(4) comment '性别 男/女',
	qq varchar(32) comment 'qq',
	email varchar(64) comment 'email',
	company varchar(128) comment '公司名称',
	company_addr varchar(256) comment '公司地址',
	home_addr varchar(256) comment '家庭地址',
	regtime varchar(64) comment '注册时间',
	status varchar(3) not null comment '状态 0:正常 1:冻结'
) comment = '用户表';

drop table if exists u_device; 
create table u_device(
	imei varchar(64) primary key comment '手机imei(主键)',
	osName varchar(64) comment '操作系统名称',
	osVer varchar(32) comment '操作系统版本',
	deviceCompany varchar(64) comment '设备制造厂商',
	addTime timestamp default CURRENT_TIMESTAMP  comment '设备添加时间',
	lastTime timestamp default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '最后登陆时间' 
) comment = '用户设备表';


drop table if exists u_loc_his; 
create table u_loc_his(
	id int auto_increment primary key,
	user_id int(16) comment '用户id',
	longitude double comment '经度',
	latitude double comment '纬度',
	address varchar(256) comment '地址信息',
	business varchar(256) comment '商圈',
	uploadtime timestamp comment '本次上传时间'
) comment '用户历史位置信息表';

drop table if exists u_loc_curr;
create table u_loc_curr(
	user_id int(16) comment '用户id',
	longitude double comment '经度',
	latitude double comment '纬度',
	address varchar(256) comment '地址信息',
	business varchar(256) comment '商圈',
	uploadtime timestamp comment '本次上传时间'
) comment '用户当前位置信息表';


drop table if exists sys_param;
create table sys_param(
	_key varchar(32) comment '参数名称',
	_value varchar(128) comment '参数值',
	description varchar(128) comment '描述',
	primary key (_key, _value)
) comment '系统参数表';


insert into sys_param values ('upload_freq','600','上传频率(单位秒)');
insert into sys_param values ('meetu_freq','600','上传并查找频率(单位秒)');


drop table if exists sys_client_info;
create table sys_client_info (
	os varchar(16) not null comment '操作系统 android/ios',
	version varchar(8) not null comment '版本号',
	signature varchar(64) not null comment '数字签名信息',
	description varchar(128),
	primary key (os,version)
) comment '客户端信息表'; 

insert into sys_client_info values('android','0.1','123123','desc 0.1版本');


drop table if exists u_friends_rel;
create table u_friends_rel (
	userId int(16) not null comment '用户id',
	friendId int(16) not null comment '朋友id',
	relStatus varchar(4) not null comment '朋友状态0:正常 1:亲密状态 2:黑名单 3:已删除',
	happenTime timestamp comment '成为朋友的时间',
	primary key (userId , friendId)
) comment '用户好友关系表'; 


drop table if exists u_friends_req;
create table u_friends_req (
	reqId int primary key auto_increment comment '好友申请数据id(主键自增)',
	reqUserId int(16) not null comment '用户id(申请人)',
	reqFriendId int(16) not null comment '想添加好友的id(被申请人)',
	reqWay varchar(16) not null comment '添加途径 ID/MOBILE/NAME/QQ/EMAIL',
	reqFriendData varchar(64) not null comment '想添加好友的数据 与way对应',	
	reqMessage varchar(512) not null comment '请求留言',
	reqStatus varchar(4) not null comment '请求的状态 0:已提交,未处理 1:已同意 2:已拒绝 3:已忽略 4忽略并拒绝此人再次添加',
	reqTime timestamp comment '请求发送的时间',
	respTime timestamp comment '请求处理的时间'
) comment '用户好友请求表,在合适的时间推送给客户端'; 

drop table if exists sys_feedback;
create table sys_feedback(
	userId int(16) comment '反馈人用户id',
	content varchar(512) comment '反馈内容',
	feedbackTime timestamp comment '反馈时间'
) comment '用户反馈表';







