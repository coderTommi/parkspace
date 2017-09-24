drop table if exists AccountCheck;

drop table if exists Baseuser;

drop table if exists Bill;

drop index t_black_list_ix1 on BlackList;

drop table if exists BlackList;

drop index caruser_ix1 on Caruser;

drop table if exists Caruser;

drop index Community_ix1 on Community;

drop table if exists Community;

drop table if exists Integral;

drop index t_parking_space_ix1 on ParkingSpace;

drop table if exists ParkingSpace;

drop index t_parking_space_bill_ix3 on ParkingSpaceBill;

drop index t_parking_space_bill_ix2 on ParkingSpaceBill;

drop index t_parking_space_bill_ix1 on ParkingSpaceBill;

drop table if exists ParkingSpaceBill;

drop index t_parking_space_bill_his_ix4 on ParkingSpaceBillHis;

drop index t_parking_space_bill_his_ix3 on ParkingSpaceBillHis;

drop index t_parking_space_bill_his_ix2 on ParkingSpaceBillHis;

drop index t_parking_space_bill_his_ix1 on ParkingSpaceBillHis;

drop table if exists ParkingSpaceBillHis;

drop table if exists PropertyMgmtUser;

drop index t_share_config_ix1 on ShareConfig;

drop table if exists ShareConfig;

drop index spaceowner_ix1 on SpaceOwner;

drop table if exists SpaceOwner;

drop table if exists Wallet;

drop table if exists Zone;

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
   createBy             varchar(30) not null comment '创建人',
   createTime           datetime not null comment '创建时间',
   modifyBy             varchar(30) not null comment '修改人',
   modifyTime           datetime not null comment '修改时间',
   primary key (userId)
);

alter table Baseuser comment '用户表（Baseuser）：记录用户的基本信息';

/*==============================================================*/
/* Table: Bill                                                  */
/*==============================================================*/
create table Bill
(
   billId               varchar(64) not null comment '账单ID',
   payer                varchar(64) not null comment '付款人',
   payee                varchar(64) not null comment '收款人',
   transDate            date not null comment '交易日期',
   billType             int(1) not null comment '账单类型 0：充值 1：提现  2：交押金  3：提取押金4：分配	5：入账	6：出账',
   amount               decimal(15, 2) not null comment '账单金额',
   sate                 char(10) not null default '0' comment '0: 正常  1：失败	2：对账异常',
   orderJnlNo           varchar(64) comment '订单号',
   primary key (billId)
);

alter table Bill comment '账单（Bill）
记录账号变动情况，包括充值、体现、交押金、提取押金、分配账单金额';

/*==============================================================*/
/* Table: BlackList                                             */
/*==============================================================*/
create table BlackList
(
   UUID                 varchar(64) not null comment '主键',
   userId               varchar(64) comment '用户id',
   isCancel             int(0) not null default 0 comment '是否取消，1，是，0否，默认0',
   memo                 varchar(256) comment '备注',
   createTime           datetime not null comment '创建时间',
   modifyTime           datetime not null comment '修改时间',
   primary key (UUID)
);

alter table BlackList comment '黑名单，记录用户黑名单信息';

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
   isauth               int(1) not null default 0 comment '是否认证 0:否 1：是，默认1，-1表示禁用',
   memo                 varchar(256) comment '备注',
   createBy             varchar(30) not null comment '创建人',
   createTime           datetime not null comment '创建时间',
   modifyBy             varchar(30) not null comment '修改人',
   modifyTime           datetime not null comment '修改时间',
   primary key (userId, carno)
);

alter table Caruser comment '车主表（Caruser）';

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
   zoneid               varchar(64) comment '区域ID',
   comname              varchar(128) not null comment '小区名称',
   address              varchar(256) comment '小区地址',
   isenable             int(1) not null default 0 comment '是否开通  0：否  1：是,默认0，如果是2表示禁用',
   memo                 varchar(256) comment '备注',
   createBy             varchar(30) not null comment '创建人',
   createTime           datetime not null comment '创建时间',
   modifyBy             varchar(30) not null comment '修改人',
   modifyTime           datetime not null comment '修改时间',
   primary key (comid)
);

alter table Community comment '小区表:记录小区的基本信息';

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
   val                  integer comment '积分值',
   primary key (userId)
);

alter table Integral comment '积分表(Integral)';

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
   primary key (spaceno)
);

alter table ParkingSpace comment '停车位:记录车位的基本信息，该信息应该有物业或系统管理员进行初始化';

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
   createTime           datetime not null comment '创建时间',
   primary key (orderJnlNo)
);

alter table ParkingSpaceBill comment '车位订单，用来记录车位的订单信息
预约
使用中
使用完成';

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
   recodeTime           datetime not null comment '记录时间，表示该流水记录的时间',
   primary key (UUID)
);

alter table ParkingSpaceBillHis comment '车位订单流水，记录该车为订单中的预定-使用-延长使用-结算整个流程
状态为结算的订单作为对账订单';

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
   comid                varchar(64) not null comment '小区ID',
   userId               varchar(64) not null comment '用户id',
   isAdmin              int(1) not null default 0 comment '是否是管理员 0:否  1：是,默认为0，如果为-1表示禁用',
   memo                 varchar(256) comment '备注',
   createBy             varchar(30) not null comment '创建人',
   createTime           datetime not null comment '创建时间',
   modifyBy             varchar(30) not null comment '修改人',
   modifyTime           datetime not null comment '修改时间',
   primary key (comid, userId)
);

alter table PropertyMgmtUser comment '物业人员信息表（PropertyMgmtUser）:维护物业人员的管理关系';

/*==============================================================*/
/* Table: ShareConfig                                           */
/*==============================================================*/
create table ShareConfig
(
   UUID                 varchar(64) not null comment '主键',
   spaceno              varchar(30) comment '车位编号,形如3-101',
   isOpen               int(1) not null default 1 comment '是否开启，1：开启，0未开启；默认开启',
   shareType            int(1) not null comment '共享类型：1周期性时间段，0自定义时间段',
   startTime            varchar(10) not null comment '开始时间，格式（24h）13:24:00',
   endTime              varchar(10) not null comment '截至时间，格式（24h）14:24:00',
   internalDate         varchar(256) comment '日期（中间使用英文逗号分割）
            周期性时间段：0,1,2,3,4,5,6；表示星期天到星期六
            自定义时间：2017-01-01,2017-02-03表示开始日期到截止日期',
   createBy             varchar(30) not null comment '创建人',
   modifyBy             varchar(30) not null comment '修改人',
   createTime           datetime not null comment '创建时间',
   modifyTime           datetime not null comment '修改时间',
   primary key (UUID)
);

alter table ShareConfig comment '共享时间设置表：记录车位的共享时间段的设置信息';

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
   isauth               int(1) default 0 comment '是否认证:0未认证，1认证，默认0，-1表示禁用不在公开车位',
   memo                 varchar(256) comment '备注',
   createBy             varchar(30) not null comment '创建人',
   createTime           datetime not null comment '创建时间',
   modifyBy             varchar(30) not null comment '修改人',
   modifyTime           datetime not null comment '修改时间',
   carno                varchar(16) comment '车牌号,对于车主的车牌号只做记录，不做校验，可以不输入',
   primary key (spaceno)
);

alter table SpaceOwner comment '车位业主信息表,包含物业车位信息';

/*==============================================================*/
/* Index: spaceowner_ix1                                        */
/*==============================================================*/
create index spaceowner_ix1 on SpaceOwner
(
   userId
);

/*==============================================================*/
/* Table: Wallet                                                */
/*==============================================================*/
create table Wallet
(
   userId               varchar(64) comment '用户id',
   pledge               decimal(15,2) comment '押金',
   balance              decimal(15, 2) comment '余额',
   bonus                decimal(15, 2) comment '奖金', 
   unclosedAmt			decimal(15, 2) comment '待结算金额',
   lastTrsTime			timestamp		comment '上次交易时间',
   openTime				timestamp		comment '开通时间'
);

alter table Wallet comment '钱包表';

/*==============================================================*/
/* Table: Zone                                                  */
/*==============================================================*/
create table Zone
(
   zoneid               varchar(64) not null comment '区域ID',
   zonename             varchar(128) not null comment '区域名称',
   isenable             int(1) not null default 0 comment '状态  0：否  1：是，-1,表示删除，默认0',
   province             varchar(100) not null comment '省编码',
   city                 varchar(100) not null comment '市',
   zone                 varchar(100) not null comment '区',
   memo                 varchar(256) comment '备注',
   createBy             varchar(30) not null comment '创建人',
   createTime           datetime not null comment '创建时间',
   modifyBy             varchar(30) not null comment '修改人',
   modifyTime           datetime not null comment '修改时间',
   primary key (zoneid)
);

alter table Zone comment '行政区域（Zone），记录区域的基本信息';

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

alter table PropertyMgmtUser add constraint FK_pu_Ref_community foreign key (comid)
      references Community (comid) on delete restrict on update restrict;

alter table ShareConfig add constraint FK_sc_ref_so foreign key (spaceno)
      references SpaceOwner (spaceno) on delete restrict on update restrict;

alter table SpaceOwner add constraint FK_so_ref_space foreign key (spaceno)
      references ParkingSpace (spaceno) on delete restrict on update restrict;

alter table SpaceOwner add constraint FK_su_ref_user foreign key (userId)
      references Baseuser (userId) on delete restrict on update restrict;

alter table Wallet add constraint FK_wallet_ref_user foreign key (userId)
      references Baseuser (userId) on delete restrict on update restrict;
