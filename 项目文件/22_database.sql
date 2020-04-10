/*==============================================================*/
/* DBMS name:      Microsoft SQL Server 2012                    */
/* Created on:     2020/4/10 16:54:15                           */
/*==============================================================*/


if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('DictionaryDetail') and o.name = 'FK_DICTIONA_REFERENCE_DICTIONA')
alter table DictionaryDetail
   drop constraint FK_DICTIONA_REFERENCE_DICTIONA
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('Permission') and o.name = 'FK_PERMISSI_REFERENCE_PERMISSI')
alter table Permission
   drop constraint FK_PERMISSI_REFERENCE_PERMISSI
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('"User"') and o.name = 'FK_USER_REFERENCE_½ÇÉ«')
alter table "User"
   drop constraint FK_USER_REFERENCE_½ÇÉ«
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('UserInfo') and o.name = 'FK_USERINFO_REFERENCE_USER')
alter table UserInfo
   drop constraint FK_USERINFO_REFERENCE_USER
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('sign') and o.name = 'FK_SIGN_REFERENCE_USERINFO')
alter table sign
   drop constraint FK_SIGN_REFERENCE_USERINFO
go

if exists (select 1
            from  sysobjects
           where  id = object_id('Button')
            and   type = 'U')
   drop table Button
go

if exists (select 1
            from  sysobjects
           where  id = object_id('Dictionary')
            and   type = 'U')
   drop table Dictionary
go

if exists (select 1
            from  sysobjects
           where  id = object_id('DictionaryDetail')
            and   type = 'U')
   drop table DictionaryDetail
go

if exists (select 1
            from  sysobjects
           where  id = object_id('Permission')
            and   type = 'U')
   drop table Permission
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('"User"')
            and   name  = 'Index_1'
            and   indid > 0
            and   indid < 255)
   drop index "User".Index_1
go

if exists (select 1
            from  sysobjects
           where  id = object_id('"User"')
            and   type = 'U')
   drop table "User"
go

if exists (select 1
            from  sysobjects
           where  id = object_id('UserInfo')
            and   type = 'U')
   drop table UserInfo
go

if exists (select 1
            from  sysobjects
           where  id = object_id('sign')
            and   type = 'U')
   drop table sign
go

if exists (select 1
            from  sysobjects
           where  id = object_id('½ÇÉ«')
            and   type = 'U')
   drop table ½ÇÉ«
go

/*==============================================================*/
/* Table: Button                                                */
/*==============================================================*/
create table Button (
   Id                   int                  not null,
   Name                 nvarchar(20)         null,
   Code                 nvarchar(20)         null,
   constraint PK_BUTTON primary key (Id)
)
on ? (1,1)
go

/*==============================================================*/
/* Table: Dictionary                                            */
/*==============================================================*/
create table Dictionary (
   Id                   int                  not null,
   Code                 char(10)             null,
   constraint PK_DICTIONARY primary key (Id)
)
go

/*==============================================================*/
/* Table: DictionaryDetail                                      */
/*==============================================================*/
create table DictionaryDetail (
   Id                   int                  not null,
   ×Öµä±àºÅ                 int                  null,
   Code                 char(10)             null
)
go

/*==============================================================*/
/* Table: Permission                                            */
/*==============================================================*/
create table Permission (
   id                   int                  not null,
   ParentId             int                  null,
   Name                 nvarchar(20)         null,
   constraint PK_PERMISSION primary key (id)
)
go

/*==============================================================*/
/* Table: "User"                                                */
/*==============================================================*/
create table "User" (
   Id                   int                  not null,
   UserId               int                  not null,
   Creator              int                  null,
   CreationDate         datetime             null,
   RoleId               int                  null,
   UserName             int                  null,
   Password             nvarchar(20)         null,
   UserInfold           nvarchar(255)        null,
   Token                char(10)             null,
   LoginType            char(10)             null,
   constraint PK_USER primary key (UserId)
)
go

/*==============================================================*/
/* Index: Index_1                                               */
/*==============================================================*/
create index Index_1 on "User" (

)
go

/*==============================================================*/
/* Table: UserInfo                                              */
/*==============================================================*/
create table UserInfo (
   UserInfold           int                  not null,
   UserId               int                  null,
   CloseName            nvarchar(20)         null,
   RealName             nvarchar(20)         null,
   Telephone            nvarchar(20)         null,
   CourseId             int                  null,
   Email                nvarchar(32)         null,
   constraint PK_USERINFO primary key (UserInfold)
)
go

/*==============================================================*/
/* Table: sign                                                  */
/*==============================================================*/
create table sign (
   Ç©µ½id                 int                  not null,
   UserInfold           nvarchar(20)         not null,
   courseId             int                  null,
   signTime             nvarchar(20)         null,
   msg                  nvarchar(32)         null,
   constraint PK_SIGN primary key (Ç©µ½id)
)
go

/*==============================================================*/
/* Table: ½ÇÉ«                                                    */
/*==============================================================*/
create table ½ÇÉ« (
   Id                   int                  not null,
   Role                 nvarchar(20)         null,
   Creator              int                  null,
   CreationDate         datetime             null,
   ModificationDate     datetime             null,
   Modifier             nvarchar(20)         null,
   constraint PK_½ÇÉ« primary key (Id)
)
go

alter table DictionaryDetail
   add constraint FK_DICTIONA_REFERENCE_DICTIONA foreign key (Id)
      references Dictionary (Id)
go

alter table Permission
   add constraint FK_PERMISSI_REFERENCE_PERMISSI foreign key (ParentId)
      references Permission (id)
go

alter table "User"
   add constraint FK_USER_REFERENCE_½ÇÉ« foreign key (Id)
      references ½ÇÉ« (Id)
go

alter table UserInfo
   add constraint FK_USERINFO_REFERENCE_USER foreign key (UserId)
      references "User" (UserId)
go

alter table sign
   add constraint FK_SIGN_REFERENCE_USERINFO foreign key (UserInfold)
      references UserInfo (UserInfold)
go

