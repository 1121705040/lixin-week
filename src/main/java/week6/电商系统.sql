# 商品分类表
create table commodity_classification
(
  id             varchar(32)      not null
    primary key comment '商品分类唯一主键',
  state          char(1)          null comment '分类状态  0删除    1有效',
  create_time    varchar(20)      null comment '创建时间',
  update_time    varchar(20)      null comment '更新时间',
  sort           int              null comment '分类排序,可根据热度排序',
  name           varchar(20)      null comment '分类名称',
  code           varchar(32)      null comment '分类的关系代码,00010002类似可表示分类之间的关系'
);

create index code_index
  on commodity_classification (code);
# 商品表,
create table commodity_base_info
(
  id             varchar(32)      not null
    primary key comment '商品唯一主键',
  state          char(1)          null comment '商品状态  0删除 1有效,2特价,3秒杀,4售空',
  create_time    varchar(20)      null comment '创建时间',
  update_time    varchar(20)      null comment '更新时间',
  sort           int              null comment '商品热度排序,可根据购买次数或收藏次数等指标排序',
  name           varchar(20)      null comment '商品名称',
  code           varchar(225)     null comment '商品与分类的关系代码,00010002类似可表示该商品属于那些分类,一个商品可能有多个分类',
  picture        varchar(220)     comment '商品图片位置',
  video          varchar(220)     comment '商品视频位置',
  price          double(11,2)     comment '商品价格',
  specifications varchar(32)      comment '商品规格id',
  coupon         varchar(32)      comment '商品优惠活动id',
  trade_in       varchar(32)      comment '商品以旧换新id',
  deliver_goods  varchar(220)     comment '货源',
  guarantee      varchar(64)      comment '保障信息',
  parameter      varchar(225)     comment '商品参数信息',
  evaluate       varchar(32)      comment '商品评价id',
  shop           varchar(225)     comment '店铺信息',
  label          varchar(225)     comment '标签信息'
);

create index label_index
  on commodity_base_info (label);
create index name_index
  on commodity_base_info (name);
create index shop_index
  on commodity_base_info (shop);

# 用户表
create table sys_user_info
(
  id             varchar(32)      not null
    primary key comment '用户唯一主键',
  state          char(1)          null comment '用户状态  0注销 1有效',
  create_time    varchar(20)      null comment '创建时间',
  update_time    varchar(20)      null comment '更新时间',
  account_number varchar(20)      null comment '账号',
  name           varchar(18)      null comment '用户名',
  password       varchar(20)      null comment '密码',
  head_portrait  varchar(220)     comment '头像图片位置',
  mailbox        varchar(28)      comment '邮箱',
  gender         char(2)          comment '性别',
  trade_in       varchar(32)      comment '商品以旧换新id',
  phone          varchar(13)      comment '手机号',
  balance        double(11,2)     comment '余额'
);

create index user_id_index
  on sys_user_info (id);

# 订单表
create table commodity_oder
(
  id             varchar(32)      not null
    primary key comment '订单唯一主键',
  state          char(1)          null comment '订单状态  0删除,1待付款,2代发货,3待收货,4待评价,5交易关闭,6交易成功',
  create_time    varchar(20)      null comment '创建时间',
  update_time    varchar(20)      null comment '更新时间',
  payment_method varchar(20)      null comment '支付方式',
  transaction_serial_number varchar(64)      null comment '交易流水号',
  payment_time   varchar(20)      null comment '付款时间',
  transaction_time   varchar(20)  null comment '成交时间',
  seller         varchar(32)      comment '卖家id',
  buyers         varchar(32)      comment '买家id',
  total_price_of_goods   double(11,2)          comment '商品总价',
  freight        varchar(32)      comment '运费id',
  refund_after_sales   varchar(32)      comment '退款售后id'
);

create index oder_id_index
  on commodity_oder (id);