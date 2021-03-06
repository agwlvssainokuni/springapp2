-- Project Name : ExampleApp
-- Date/Time    : 2015/10/12 7:25:42
-- Author       : agwlvssainokuni
-- RDBMS Type   : IBM DB2
-- Application  : A5:SQL Mk-2

-- 休日マスタ
create table dayoff_master (
  name VARCHAR(10) default 'standard' not null
  , dt DATE not null
  , type VARCHAR(2) default '00' not null
  , updated_at TIMESTAMP default CURRENT_TIMESTAMP not null
  , created_at TIMESTAMP default CURRENT_TIMESTAMP not null
  , lock_version INTEGER default 1 not null
  , constraint dayoff_master_PKC primary key (name,dt)
) ;

-- 業務日時マスタ
create table bizdatetime_master (
  id BIGINT not null AUTO_INCREMENT
  , bizdate DATE default CURRENT_DATE not null
  , offset_day INTEGER default 0 not null
  , offset_hour INTEGER default 0 not null
  , offset_minute INTEGER default 0 not null
  , offset_second INTEGER default 0 not null
  , updated_at TIMESTAMP default CURRENT_TIMESTAMP not null
  , created_at TIMESTAMP default CURRENT_TIMESTAMP not null
  , lock_version INTEGER default 1 not null
  , constraint bizdatetime_master_PKC primary key (id)
) ;

comment on table dayoff_master is '休日マスタ';
comment on column dayoff_master.name is '識別名';
comment on column dayoff_master.dt is '年月日';
comment on column dayoff_master.type is '休日区分';
comment on column dayoff_master.updated_at is '更新日時';
comment on column dayoff_master.created_at is '作成日時';
comment on column dayoff_master.lock_version is 'ロックバージョン';

comment on table bizdatetime_master is '業務日時マスタ';
comment on column bizdatetime_master.id is 'ID';
comment on column bizdatetime_master.bizdate is '業務日付';
comment on column bizdatetime_master.offset_day is 'オフセット(日)';
comment on column bizdatetime_master.offset_hour is 'オフセット(時)';
comment on column bizdatetime_master.offset_minute is 'オフセット(分)';
comment on column bizdatetime_master.offset_second is 'オフセット(秒)';
comment on column bizdatetime_master.updated_at is '更新日時';
comment on column bizdatetime_master.created_at is '作成日時';
comment on column bizdatetime_master.lock_version is 'ロックバージョン';
