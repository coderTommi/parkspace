drop function if exists f_get_parkHoursString;

drop  table  if   exists   vEnableBillShareConfig;

drop  table  if   exists   vCarAndSpaceOwnerUser;

drop table if exists AccountCheck;

alter table Baseuser
   drop primary key;

drop table if exists Baseuser;

alter table Bill
   drop primary key;

drop table if exists Bill;

drop index t_black_list_ix1 on BlackList;

alter table BlackList
   drop primary key;

drop table if exists BlackList;

drop index caruser_ix1 on Caruser;

alter table Caruser
   drop primary key;

drop table if exists Caruser;

drop index Community_ix1 on Community;

alter table Community
   drop primary key;

drop table if exists Community;

alter table Integral
   drop primary key;

drop table if exists Integral;

drop index t_parking_space_ix1 on ParkingSpace;

alter table ParkingSpace
   drop primary key;

drop table if exists ParkingSpace;

drop index t_parking_space_bill_ix3 on ParkingSpaceBill;

drop index t_parking_space_bill_ix2 on ParkingSpaceBill;

drop index t_parking_space_bill_ix1 on ParkingSpaceBill;

alter table ParkingSpaceBill
   drop primary key;

drop table if exists ParkingSpaceBill;

drop index t_parking_space_bill_his_ix4 on ParkingSpaceBillHis;

drop index t_parking_space_bill_his_ix3 on ParkingSpaceBillHis;

drop index t_parking_space_bill_his_ix2 on ParkingSpaceBillHis;

drop index t_parking_space_bill_his_ix1 on ParkingSpaceBillHis;

alter table ParkingSpaceBillHis
   drop primary key;

drop table if exists ParkingSpaceBillHis;

alter table PropertyMgmtUser
   drop primary key;

drop table if exists PropertyMgmtUser;

drop index t_share_config_ix1 on ShareConfig;

alter table ShareConfig
   drop primary key;

drop table if exists ShareConfig;

drop index spaceowner_ix1 on SpaceOwner;

alter table SpaceOwner
   drop primary key;

drop table if exists SpaceOwner;

alter table UserCommunity
   drop primary key;

drop table if exists UserCommunity;

drop table if exists Wallet;

alter table Zone
   drop primary key;

drop table if exists Zone;

alter table message
   drop primary key;

drop table if exists message;

alter table walletlock
   drop primary key;

drop table if exists walletlock;

/*==============================================================*/
/* Table: AccountCheck                                          */
/*==============================================================*/
create table AccountCheck
(
   userId               varchar(64) not null comment '用户ID',
   checkDate            date not null comment '对账日期',
   correct              int(1) not null default 0 comment '是否正常 0: 正常  1：不正常'
);

alter table AccountCheck comment '对账单';

/*==============================================================*/
/* Table: Baseuser                                              */
/*==============================================================*/
create table Baseuser
(
   userId               varchar(64) not null comment '用户id',
   userName             varchar(128) comment '用户名称',
   realname             varchar(128) comment '真实姓名',
   nickName             varchar(128) comment '昵称',
   userPwd              varchar(64) not null comment '用户密码',
   telephone            varchar(11) not null comment '手机号码',
   isAdmin              int(1) not null default 0 comment '是否是管理员 0:否  1：是',
   idtype               int(1) comment '证件类型 0: 身份证',
   idno                 varchar(32) comment '证件号码',
   state                int(1) not null default 0 comment '用户状态 0：正常	1：异常锁定,2：未交押金,3；已交押金',
   weixinAccount        varchar(100) not null comment '微信账号,用来汇款的账号',
   avator               varchar(256) comment '头像',
   memo                 varchar(256) comment '备注',
   createBy             varchar(64) not null comment '创建人',
   createTime           datetime not null comment '创建时间',
   modifyBy             varchar(64) not null comment '修改人',
   modifyTime           datetime not null comment '修改时间'
);

alter table Baseuser comment '用户表（Baseuser）：记录用户的基本信息';

alter table Baseuser
   add primary key (userId);

/*==============================================================*/
/* Table: Bill                                                  */
/*==============================================================*/
create table Bill
(
   billId               varchar(64) not null comment '账单ID',
   payer                varchar(64) not null comment '付款人',
   payee                varchar(64) not null comment '收款人',
   transdate            date not null comment '交易日期',
   transtime            timestamp not null comment '交易时间',
   billType             int(1) not null comment '账单类型 0：充值 1：提现  2：交押金  3：提取押金4：分配	5：入账	6：出账',
   amount               decimal(15, 2) not null comment '账单金额',
   state                int(1) not null default 0 comment '0: 正常  1：失败	2：对账异常',
   orderJnlNo           varchar(64) comment '订单号'
);

alter table Bill comment '账单（Bill）
记录账号变动情况，包括充值、体现、交押金、提取押金、分配账单金额';

alter table Bill
   add primary key (billId);

/*==============================================================*/
/* Table: BlackList                                             */
/*==============================================================*/
create table BlackList
(
   UUID                 varchar(64) not null comment '主键',
   userId               varchar(64) comment '用户id',
   isCancel             int(0) not null default 0 comment '是否取消，1，是，0否，默认0',
   userType             int(0) not null default 0 comment '用户类型：0业主，1车主，2，物业，3其他',
   memo                 varchar(256) comment '备注',
   createTime           datetime not null comment '创建时间',
   modifyTime           datetime not null comment '修改时间'
);

alter table BlackList comment '黑名单，记录用户黑名单信息';

alter table BlackList
   add primary key (UUID);

/*==============================================================*/
/* Index: t_black_list_ix1                                      */
/*==============================================================*/
create index t_black_list_ix1 on BlackList
(
   userId
);

/*==============================================================*/
/* Table: Caruser                                               */
/*==============================================================*/
create table Caruser
(
   userId               varchar(64) not null comment '用户id',
   carno                varchar(16) not null comment '车牌号',
   isauth               int(1) not null default 0 comment '状态0:未认证 1：已认证，默认1，-1表示禁用',
   parkCount            int(10) not null default 0 comment '停车次数：需要在订单成功之后+1,默认0'
);

alter table Caruser comment '车主表（Caruser）';

alter table Caruser
   add primary key (userId, carno);

/*==============================================================*/
/* Index: caruser_ix1                                           */
/*==============================================================*/
create index caruser_ix1 on Caruser
(
   carno
);

/*==============================================================*/
/* Table: Community                                             */
/*==============================================================*/
create table Community
(
   comid                varchar(64) not null comment '小区ID',
   price                decimal(15, 2) not null default 0 comment '单价，车位单价按小时计算',
   zoneid               varchar(64) comment '区域ID',
   comname              varchar(128) not null comment '小区名称',
   address              varchar(256) comment '小区地址',
   isenable             int(1) not null default 0 comment '状态  0：未开放  1：封闭式小区，2：开放式小区,默认0，如果是-1表示禁用',
   memo                 varchar(256) comment '备注',
   createBy             varchar(30) not null comment '创建人',
   createTime           datetime not null comment '创建时间',
   modifyBy             varchar(30) not null comment '修改人',
   modifyTime           datetime not null comment '修改时间'
);

alter table Community comment '小区表:记录小区的基本信息';

alter table Community
   add primary key (comid);

/*==============================================================*/
/* Index: Community_ix1                                         */
/*==============================================================*/
create index Community_ix1 on Community
(
   zoneid
);

/*==============================================================*/
/* Table: Integral                                              */
/*==============================================================*/
create table Integral
(
   userId               varchar(64) not null comment '用户id',
   val                  integer comment '积分值'
);

alter table Integral comment '积分表(Integral)';

alter table Integral
   add primary key (userId);

/*==============================================================*/
/* Table: ParkingSpace                                          */
/*==============================================================*/
create table ParkingSpace
(
   spaceno              varchar(30) not null comment '车位编号,形如3-101',
   comid                varchar(64) comment '小区ID',
   parkPositionFloor    varchar(30) comment '车位楼层,用来标记车位所在的楼层比如：地面0，地下-1，地下-2等',
   parkPositionZone     varchar(30) comment '表示车位所在的区域，比如A区、B区',
   parkPositionX        varchar(30) comment '车位横坐标',
   parkPositionY        varchar(30) comment '车位纵坐标',
   parkStatus           char(1) not null default 'N' comment '车位状态，1占用，0空闲，N不对外开放，默认为N',
   parkType             char(1) not null comment '车位类型：P:物业、O:业主、E:其他，未知车位类型',
   parkPositionDes      varchar(256) comment '位置描述,形如：靠近X号楼X单元',
   spaceOwner           varchar(64) comment '车位登记信息：用来记录表示车位所属人员信息，手机号码或者身份证号码',
   memo                 varchar(256) comment '备注',
   createBy             varchar(30) not null comment '创建人',
   createTime           datetime not null comment '创建时间',
   modifyBy             varchar(30) not null comment '修改人',
   modifyTime           datetime not null comment '修改时间',
   useCount             int(10) not null default 0 comment '使用次数：默认0，每次订单成功之后+1'
);

alter table ParkingSpace comment '停车位:记录车位的基本信息，该信息应该有物业或系统管理员进行初始化';

alter table ParkingSpace
   add primary key (spaceno);

/*==============================================================*/
/* Index: t_parking_space_ix1                                   */
/*==============================================================*/
create index t_parking_space_ix1 on ParkingSpace
(
   comid
);

/*==============================================================*/
/* Table: ParkingSpaceBill                                      */
/*==============================================================*/
create table ParkingSpaceBill
(
   orderJnlNo           varchar(64) not null comment '订单号',
   userId               varchar(64) not null comment '用户id',
   carno                varchar(16) not null comment '车牌号',
   spaceno              varchar(30) not null comment '车位编号,形如3-101',
   billStatus           int(1) not null comment '订单状态：1、预约中，2、使用中，3.延长使用中',
   parkHours            integer not null comment '停车时长，申请停车时长，单位为小时，不能超过24小时',
   unitPrice            decimal(15, 2) not null comment '单价，每小时计费',
   budgetPrice          decimal(15,2) not null comment '预算：=单价*停车时长',
   createTime           datetime not null comment '创建时间'
);

alter table ParkingSpaceBill comment '车位订单，用来记录车位的订单信息
预约
使用中
使用完成';

alter table ParkingSpaceBill
   add primary key (orderJnlNo);

/*==============================================================*/
/* Index: t_parking_space_bill_ix1                              */
/*==============================================================*/
create index t_parking_space_bill_ix1 on ParkingSpaceBill
(
   userId
);

/*==============================================================*/
/* Index: t_parking_space_bill_ix2                              */
/*==============================================================*/
create index t_parking_space_bill_ix2 on ParkingSpaceBill
(
   carno
);

/*==============================================================*/
/* Index: t_parking_space_bill_ix3                              */
/*==============================================================*/
create index t_parking_space_bill_ix3 on ParkingSpaceBill
(
   spaceno
);

/*==============================================================*/
/* Table: ParkingSpaceBillHis                                   */
/*==============================================================*/
create table ParkingSpaceBillHis
(
   UUID                 varchar(64) not null comment '主键',
   orderJnlNo           varchar(64) not null comment '订单号',
   userId               varchar(64) not null comment '用户id',
   carno                varchar(16) not null comment '车牌号',
   spaceno              varchar(30) not null comment '车位编号,形如3-101',
   billStatus           int(1) not null comment '订单状态：1、预约中，2、使用中，3.延长使用中，4，已结算、5取消订单',
   parkHours            integer not null comment '停车时长，申请停车时长，单位为小时，不能超过24小时，如果延长停车记录延长的时间',
   unitPrice            decimal(15, 2) not null comment '单价，每小时计费',
   budgetPrice          decimal(15,2) not null comment '预算：=单价*停车时长',
   createTime           datetime not null comment '创建时间，表示订单当前状态的时间',
   actualParkHours      decimal(15,2) comment '记录实际的停车时长',
   actualPrice          decimal(15,2) not null comment '预算：=单价*实际停车时长',
   recodeTime           datetime not null comment '记录时间，表示该流水记录的时间'
);

alter table ParkingSpaceBillHis comment '车位订单流水，记录该车为订单中的预定-使用-延长使用-结算整个流程
状态为结算的订单作为对账订单';

alter table ParkingSpaceBillHis
   add primary key (UUID);

/*==============================================================*/
/* Index: t_parking_space_bill_his_ix1                          */
/*==============================================================*/
create index t_parking_space_bill_his_ix1 on ParkingSpaceBillHis
(
   orderJnlNo
);

/*==============================================================*/
/* Index: t_parking_space_bill_his_ix2                          */
/*==============================================================*/
create index t_parking_space_bill_his_ix2 on ParkingSpaceBillHis
(
   userId
);

/*==============================================================*/
/* Index: t_parking_space_bill_his_ix3                          */
/*==============================================================*/
create index t_parking_space_bill_his_ix3 on ParkingSpaceBillHis
(
   carno
);

/*==============================================================*/
/* Index: t_parking_space_bill_his_ix4                          */
/*==============================================================*/
create index t_parking_space_bill_his_ix4 on ParkingSpaceBillHis
(
   spaceno
);

/*==============================================================*/
/* Table: PropertyMgmtUser                                      */
/*==============================================================*/
create table PropertyMgmtUser
(
   userId               varchar(64) not null comment '用户id'
);

alter table PropertyMgmtUser comment '物业人员信息表（PropertyMgmtUser）:维护物业人员的管理关系';

alter table PropertyMgmtUser
   add primary key (userId);

/*==============================================================*/
/* Table: ShareConfig                                           */
/*==============================================================*/
create table ShareConfig
(
   UUID                 varchar(64) not null comment '主键',
   spaceno              varchar(30) comment '车位编号,形如3-101',
   isAllDay             int(1) not null default 1 comment '是否全天：1是，0否，如果是全天对应的开始时间为00:00:00，截至时间23:59:59',
   isOpen               int(1) not null default 1 comment '//状态，1：开启，0未开启；默认开启,-1表示删除',
   shareType            int(1) not null comment '共享类型：1周期性时间段，0自定义时间段',
   startDate            varchar(10) comment '开始日期，格式（YYYY-MM-DD）2017-09-10，共享类型为自定义时记录该日期',
   startTime            varchar(10) not null comment '开始时间，格式（24h）13:24:00',
   endDate              varchar(10) comment '截至日期，格式（YYYY-MM-DD）2017-09-10，共享类型为自定义时记录该日期',
   endTime              varchar(10) not null comment '截至时间，格式（24h）14:24:00',
   internalDate         varchar(256) comment '周期（中间使用英文逗号分割），记录共享类型为周期性的星期数据
            周期性时间段：1,2,3,4,5,6,7；表示星期天到星期六,使用mysql函数DAYOFWEEK处理
            注意：如果同时选择星期六和星期天需要在最好增加星期天的编号，形成环路',
   createBy             varchar(30) not null comment '创建人',
   modifyBy             varchar(30) not null comment '修改人',
   createTime           datetime not null comment '创建时间',
   modifyTime           datetime not null comment '修改时间'
);

alter table ShareConfig comment '共享时间设置表：记录车位的共享时间段的设置信息';

alter table ShareConfig
   add primary key (UUID);

/*==============================================================*/
/* Index: t_share_config_ix1                                    */
/*==============================================================*/
create index t_share_config_ix1 on ShareConfig
(
   spaceno
);

/*==============================================================*/
/* Table: SpaceOwner                                            */
/*==============================================================*/
create table SpaceOwner
(
   spaceno              varchar(30) not null comment '车位编号,形如3-101',
   userId               varchar(64) not null comment '用户id',
   isauth               int(1) default 0 comment '状态:0未认证，1认证，默认0，-1表示禁用不在公开车位',
   carno                varchar(16) comment '车牌号,对于车主的车牌号只做记录，不做校验，可以不输入'
);

alter table SpaceOwner comment '车位业主信息表,包含物业车位信息';

alter table SpaceOwner
   add primary key (spaceno);

/*==============================================================*/
/* Index: spaceowner_ix1                                        */
/*==============================================================*/
create index spaceowner_ix1 on SpaceOwner
(
   userId
);

/*==============================================================*/
/* Table: UserCommunity                                         */
/*==============================================================*/
create table UserCommunity
(
   comid                varchar(64) not null comment '小区ID',
   userId               varchar(64) not null comment '用户id'
);

alter table UserCommunity comment '小区用户关联表：记录小区与用户之间的关系';

alter table UserCommunity
   add primary key (comid, userId);

/*==============================================================*/
/* Table: Wallet                                                */
/*==============================================================*/
create table Wallet
(
   userId               varchar(64) comment '用户id',
   pledge               decimal(15,2) comment '押金',
   balance              decimal(15,2) comment '余额',
   bonus                decimal(15,2) comment '奖金',
   unclosedAmt          decimal(15, 2) comment '待结算金额',
   lastTrsTime          timestamp comment '上次交易时间',
   openTime             timestamp comment '开通时间'
);

alter table Wallet comment '钱包表';

/*==============================================================*/
/* Table: Zone                                                  */
/*==============================================================*/
create table Zone
(
   zoneid               varchar(64) not null comment '区域ID',
   zonename             varchar(128) not null comment '区域名称',
   isenable             int(1) not null default 0 comment '状态  0：未开放  1：已开放，-1,表示删除，默认0',
   province             varchar(100) not null comment '省编码',
   city                 varchar(100) not null comment '市',
   zone                 varchar(100) not null comment '区',
   memo                 varchar(256) comment '备注',
   createBy             varchar(30) not null comment '创建人',
   createTime           datetime not null comment '创建时间',
   modifyBy             varchar(30) not null comment '修改人',
   modifyTime           datetime not null comment '修改时间'
);

alter table Zone comment '行政区域（Zone），记录区域的基本信息';

alter table Zone
   add primary key (zoneid);

/*==============================================================*/
/* Table: message                                               */
/*==============================================================*/
create table message
(
   UUID                 varchar(64) not null comment '主键',
   context              text not null comment '消息内容',
   status               int(1) comment '消息状态：0草稿，1，发布，2删除',
   messageObject        int(1) comment '消息对象：消息面向的人群0物业，1业主，2车主,-1 所有',
   comid                text comment '小区编号,如果不选择默认为全局，可以选择多个表示面向多个小区',
   enableStartTime      date not null comment '有效开始时间',
   enableEndTime        date not null comment '有效截至时间',
   memo                 varchar(256) comment '备注',
   createTime           datetime not null comment '创建时间',
   modifyTime           datetime not null comment '修改时间'
);

alter table message comment '消息（message)';

alter table message
   add primary key (UUID);

/*==============================================================*/
/* Table: walletlock                                            */
/*==============================================================*/
create table walletlock
(
   userId               varchar(64) not null comment '用户id',
   lockTime             timestamp comment '锁定时间'
);

alter table walletlock comment '钱包锁表(walletlock)';

alter table walletlock
   add primary key (userId);

/*==============================================================*/
/* View: vCarAndSpaceOwnerUser                                  */
/*==============================================================*/
create  VIEW      vCarAndSpaceOwnerUser
  as
SELECT
	c.userId,
	c.isauth,
	c.carno,
	'' AS spaceno,
	'C' AS userType
FROM
	caruser c
UNION ALL
	SELECT
		s.userId,
		s.isauth,
		s.carno,
		s.spaceno,
		'S' AS userType
	FROM
		spaceowner s;

/*==============================================================*/
/* View: vEnableBillShareConfig                                 */
/*==============================================================*/
create  VIEW      vEnableBillShareConfig
  as
SELECT
	UUID,
	spaceno,
	isAllDay,
	isOpen,
	shareType,
	startDate,
	startTime,
	endDate,
	endTime,
	internalDate,
	createBy,
	createTime,
	modifyBy,
	modifyTime,
	nowWeek,
	nowNextWeek,
	parkHourString
FROM
	(
		SELECT
			UUID,
			spaceno,
			isAllDay,
			isOpen,
			shareType,
			startDate,
			startTime,
			endDate,
			endTime,
			internalDate,
			createBy,
			createTime,
			modifyBy,
			modifyTime,
			dayofweek(now()) AS nowWeek,
			dayofweek(
				date_add(now(), INTERVAL 1 DAY)
			) AS nowNextWeek,
			(
				CASE
				WHEN s.shareType = 0 THEN
					TIMEDIFF(
						concat(s.endDate, ' ', s.endTime),
						now()
					)
				WHEN s.isAllDay = 0 THEN
					TIMEDIFF(s.endTime, curtime())
				WHEN s.isAllDay = 1
				AND s.internalDate LIKE concat('1,2,3,4,5,6,7', '%') THEN
					'A'
				WHEN s.isAllDay = 1 THEN
					f_get_parkHoursString (s.internalDate)
				ELSE
					'00:00:00'
				END
			) AS parkHourString
		FROM
			ShareConfig s
		WHERE
			s.isOpen = 1
		AND (
			(s.shareType = 0)
			OR (
				s.shareType = 1
				AND s.internalDate LIKE concat('%', dayofweek(now()), '%')
			)
		)
	) ss
WHERE
	ss.parkHourString > '00:00:00';

alter table BlackList add constraint FK_bl_ref_user foreign key (userId)
      references Baseuser (userId) on delete restrict on update restrict;

alter table Caruser add constraint FK_cu_ref_user foreign key (userId)
      references Baseuser (userId) on delete restrict on update restrict;

alter table Community add constraint FK_community_ref_zone foreign key (zoneid)
      references Zone (zoneid) on delete restrict on update restrict;

alter table Integral add constraint FK_integral_ref_user foreign key (userId)
      references Baseuser (userId) on delete restrict on update restrict;

alter table ParkingSpace add constraint fk_parkspace_ref_community foreign key (comid)
      references Community (comid) on delete restrict on update restrict;

alter table ParkingSpaceBill add constraint FK_sb_ref_cu foreign key (userId, carno)
      references Caruser (userId, carno) on delete restrict on update restrict;

alter table ParkingSpaceBill add constraint FK_sb_ref_so foreign key (spaceno)
      references SpaceOwner (spaceno) on delete restrict on update restrict;

alter table PropertyMgmtUser add constraint FK_pu_Ref_baseuser foreign key (userId)
      references Baseuser (userId) on delete restrict on update restrict;

alter table ShareConfig add constraint FK_sc_ref_so foreign key (spaceno)
      references SpaceOwner (spaceno) on delete restrict on update restrict;

alter table SpaceOwner add constraint FK_so_ref_space foreign key (spaceno)
      references ParkingSpace (spaceno) on delete restrict on update restrict;

alter table SpaceOwner add constraint FK_su_ref_user foreign key (userId)
      references Baseuser (userId) on delete restrict on update restrict;

alter table UserCommunity add constraint FK_UserCom_ref_com foreign key (comid)
      references Community (comid) on delete restrict on update restrict;

alter table UserCommunity add constraint FK_UserCom_ref_user foreign key (userId)
      references Baseuser (userId) on delete restrict on update restrict;

alter table Wallet add constraint FK_wallet_ref_user foreign key (userId)
      references Baseuser (userId) on delete restrict on update restrict;

alter table walletlock add constraint FK_walletlock_ref_user foreign key (userId)
      references Baseuser (userId) on delete restrict on update restrict;


create function f_get_parkHoursString (internalDate varchar(128)) 
RETURNS varchar(128)
begin
		DECLARE i int default 0;
		DECLARE j int default 0;
    outer_label:BEGIN  #设置一个标记并设置开始
    while(i <= 6) do
      if locate(DAYOFWEEK(date_add(now(),interval i day)),internalDate) > 0 THEN
				set j = i;
				set i = i + 1;
		  ELSE
				LEAVE  outer_label;  #满足条件，终止循环，跳转到end outer_label标记
      END IF;
	  end while;
		END outer_label;  #设置标记并结束
		return TIMEDIFF(concat(date_add(CURDATE(), interval j day),' 23:59:59'),now());
end;
