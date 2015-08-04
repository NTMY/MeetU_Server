
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
	os_name varchar(64) comment '操作系统名称',
	os_ver varchar(32) comment '操作系统版本',
	device_company varchar(64) comment '设备制造厂商'

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










