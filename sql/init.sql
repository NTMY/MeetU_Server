
drop database if exists meetu;

create database meetu CHARACTER SET utf8;

use meetu;

drop table if exists u_user;
create table u_user(
	id int(16) auto_increment primary key comment '用户主键',
	mobile varchar(16) comment '手机号',
	pwd varchar(64) comment '密码',
	imei varchar(64) comment '当前设备手机imei',
	name varchar(64) comment '真实姓名',
	nickname varchar(64) comment '昵称',
	birthdate varchar(64) comment '生日',
	gender varchar(4) comment '性别 男/女',
	qq varchar(32) comment 'qq',
	email varchar(64) comment 'email',
	wechat varchar(32) comment '微信号',
	company varchar(128) comment '公司名称',
	company_addr varchar(256) comment '公司地址',
	home_addr varchar(256) comment '家庭地址',
	regtime varchar(64) comment '注册时间',
	mood varchar(512) comment '心情签名',
	status varchar(3) comment '状态 0:正常 10:冻结 90-98:admin 99:root',
	imgUrl varchar(256) comment '非高清头像',
	imgUrlHD varchar(256) comment '高清头像'
) comment = '用户表' engine=InnoDB;

drop table if exists u_device; 
create table u_device(
	imei varchar(64) primary key comment '手机imei(主键)',
	userId int(15) not null comment '所属用户id',
	osName varchar(64) comment '操作系统名称',
	osVer varchar(32) comment '操作系统版本',
	deviceCompany varchar(64) comment '设备制造厂商',
	lastTime timestamp default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '最后登陆时间' ,
	addTime timestamp comment '设备添加时间'
) comment = '用户设备表' engine=InnoDB;


drop table if exists u_loc_his; 
create table u_loc_his(
	id int auto_increment primary key,
	user_id int(16) comment '用户id',
	longitude double comment '经度',
	latitude double comment '纬度',
	address varchar(256) comment '地址信息',
	business varchar(256) comment '商圈',
	uploadtime timestamp comment '本次上传时间'
) comment '用户历史位置信息表' engine=InnoDB;

drop table if exists u_loc_curr;
create table u_loc_curr(
	user_id int(16) comment '用户id',
	longitude double comment '经度',
	latitude double comment '纬度',
	address varchar(256) comment '地址信息',
	business varchar(256) comment '商圈',
	uploadtime timestamp comment '本次上传时间'
) comment '用户当前位置信息表' engine=InnoDB;


drop table if exists sys_param;
create table sys_param(
	_key varchar(32) comment '参数名称',
	_value varchar(128) comment '参数值',
	description varchar(128) comment '描述',
	primary key (_key)
) comment '系统参数表' engine=InnoDB;


insert into sys_param values ('upload_freq','600','上传频率(单位秒)');
insert into sys_param values ('meetu_freq','600','上传并查找频率(单位秒)');
insert into sys_param values ('BAIDU_DEV_SN','F56034a118b5fedfc66f6e39498b9c2e','百度开发者SN');
insert into sys_param values ('BAIDU_PUSH_APIKEY','CiiSrq7SctOXARW0GrxKU9VA','百度云推送apikey');
insert into sys_param values ('BAIDU_PUSH_SECRETKEY','ZDtmKIqZ8rYDUx9n6VhikGOjVd3XOX6o','百度云推送密钥key');
insert into sys_param values ('MEETU_RANGE','3000','搜索附近的人距离范围(单位:米)');
insert into sys_param values ('JPUSH_MASTER_SECRET','35d0b56ced7282c7e5f7ee71','极光master_key');
insert into sys_param values ('JPUSH_APP_KEY','190029569855c6523b7004b3','极光app_key');



drop table if exists sys_app_ver;
create table sys_app_ver (
	id int(3) primary key auto_increment comment '自增主键',
	os varchar(16) not null comment '操作系统 android/ios',
	appVer varchar(8) not null comment '版本号',
	signature varchar(64) not null comment '数字签名信息',
	description varchar(128) comment '版本描述',
	isTop boolean not null comment '是否为最新' ,
	downloadUrl varchar(64) comment '下载地址'
) comment '客户端版本信息表' engine=InnoDB; 

insert into sys_app_ver values(0,'android','1.0.0','123123','desc 1.0.0版本',true,'www.baidu.com');


drop table if exists u_friends_rel;
create table u_friends_rel (
	userId int(16) not null comment '用户id',
	friendId int(16) not null comment '朋友id',
	statusRel varchar(4) not null comment '朋友状态0:正常 1:亲密状态 2:黑名单 3:已删除',
	happenTime timestamp comment '成为朋友的时间',
	primary key (userId , friendId)
) comment '用户好友关系表' engine=InnoDB; 


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
) comment '用户好友请求表,在合适的时间推送给客户端' engine=InnoDB; 

drop table if exists sys_feedback;
create table sys_feedback(
	userId int(16) comment '反馈人用户id',
	content varchar(512) comment '反馈内容',
	feedbackTime timestamp comment '反馈时间'
) comment '用户反馈表' engine=InnoDB;


drop table if exists push_info_baidu;
create table push_info_baidu(
	imei varchar(32) comment '硬件IMEI',
	userId int(16) comment 'meetu系统用户id',
	channelId varchar(32) comment '推送系统channelId',
	primary key(imei)
) comment '百度推送用户信息表' engine=InnoDB;


insert into push_info_baidu values('motoXpro',1,'3545744288033740498');
insert into push_info_baidu values('zte',2,'3605930564105372081');


drop table if exists u_friend_msg;
create table u_friend_msg(
	reqId int(16) primary key comment '关联好友申请表的主键',
	userId int(16) comment '留言人id', 
	msg varchar(512) comment '留言内容',
	msgDate timestamp comment '留言时间'
) comment '好友申请相互留言表' engine=InnoDB;


drop table if exists log_meet;
create table log_meet(
	id int(16) auto_increment primary key comment '主键',
	userId int(16) comment '主动用户id',
	user_longitude double comment '主动用户相遇地点经度',
	user_latitude double comment '主动用户相遇地点纬度',
	user_address varchar(256) comment '主动用户地点位置描述',
	friendId int(16) comment '被动用户id',
	friend_longitude double comment '被动用户相遇地点经度',
	friend_latitude double comment '被动用户相遇地点经度',
	friend_address varchar(256) comment '被动用户相遇地点位置描述',
	happenTime timestamp comment '相遇时间'
) comment '相遇记录,每个用户的相遇都会记录在此,如果一个用户同时与多人相遇,记录多条数据' engine=InnoDB









