show databases;

# create database books_platform
use books_platform;

show tables;
drop table teacher;
drop table school;
drop table user;
drop table contribute;
drop table admiss_contribute;
drop table solicitation;
drop table approved_contribute;
drop table recommend;
drop table read_tasks;
drop table books;
drop table books_contribute;
drop table solicitation_contribute;
drop table read_tasks;
drop table read_tasks_contribute;


# 修改account唯一
ALTER TABLE teacher
    ADD CONSTRAINT unique_constraint_teacher_account UNIQUE (account);
ALTER TABLE user
    ADD CONSTRAINT unique_constraint_user_account UNIQUE (account);


-- 创建教师表
CREATE TABLE teacher
(
    id              INT auto_increment,
    token           varchar(255) CHARACTER SET utf8mb4 comment 'token',
    name            VARCHAR(50) CHARACTER SET utf8mb4 comment '姓名',
    account         VARCHAR(50) CHARACTER SET utf8mb4 comment '账号',
    password        VARCHAR(50) CHARACTER SET utf8mb4 comment '密码',
    openid          VARCHAR(255) CHARACTER SET utf8mb4 comment '第三方登录id',
    phone           VARCHAR(20) comment '电话号码',
    profile_picture VARCHAR(255) CHARACTER SET utf8mb4 comment '头像',
    create_time     DATETIME comment '账号创建时间',
    PRIMARY KEY (id),
    unique (account)
);

-- 创建学校表
CREATE TABLE school
(
    id          INT auto_increment,
    school_name VARCHAR(50) CHARACTER SET utf8mb4 comment '学校名字',
    PRIMARY KEY (id)
);

-- 创建用户表s
CREATE TABLE user
(
    id              INT auto_increment,
    token           varchar(255) CHARACTER SET utf8mb4 comment 'token',
    name            VARCHAR(50) CHARACTER SET utf8mb4 comment '姓名',
    account         VARCHAR(50) CHARACTER SET utf8mb4 comment '账号',
    password        VARCHAR(50) CHARACTER SET utf8mb4 comment '密码',
    openid          VARCHAR(255) CHARACTER SET utf8mb4 comment '第三方登录id',
    phone           VARCHAR(20) comment '电话号码',
    profile_picture VARCHAR(255) CHARACTER SET utf8mb4 comment '头像',
    create_time     DATETIME comment '账号创建时间',
    PRIMARY KEY (id),
    unique (account)

);

-- 创建投稿信息表
CREATE TABLE contribute
(
    id                 INT auto_increment comment '投稿ID',
    title              VARCHAR(255) CHARACTER SET utf8mb4 NOT NULL comment '稿件标题',
    brief_introduction VARCHAR(255) CHARACTER SET utf8mb4 comment '投稿信息简介',
    url                VARCHAR(255) CHARACTER SET utf8mb4 comment '稿件URL',
    summary            VARCHAR(255) CHARACTER SET utf8mb4 comment '简介',
    user_id            INT comment '用户ID',
    picture            VARCHAR(255) CHARACTER SET utf8mb4 comment '封面URL',
    create_time        DATETIME comment '创建时间',
    update_time        DATETIME comment '更新时间',
    PRIMARY KEY (id)
);

-- 创建通过信件表
CREATE TABLE admiss_contribute
(
    id            INT auto_increment,
    contribute_id INT comment '外码投稿id',
    admiss_time   DATETIME comment '通过成为稿件同意时间',
    PRIMARY KEY (id)
);

-- 创建书目表
CREATE TABLE books
(
    id INT auto_increment PRIMARY KEY
);

-- 稿件征收表
CREATE TABLE solicitation
(
    id                   INT auto_increment comment '征收ID',
    teacher_id           INT comment '征收教师ID',
    cover_url            VARCHAR(255) CHARACTER SET utf8mb4 comment '征收封面URL',
    title                VARCHAR(255) CHARACTER SET utf8mb4 comment '征收标题',
    introduction         VARCHAR(255) CHARACTER SET utf8mb4 comment '征收简介',
    requirements         VARCHAR(255) CHARACTER SET utf8mb4 comment '征收要求',
    solicitation_content VARCHAR(255) CHARACTER SET utf8mb4 comment '征收内容',
    PRIMARY KEY (id)
);

-- approved_contribute: 审核关系表
CREATE TABLE approved_contribute
(
    id                     INT auto_increment PRIMARY KEY comment 'ID',
    contribute_id          INT comment '投稿ID',
    teacher_id             INT comment '审核教师ID（外码）',
    review_info            INT comment '审核信息',
    review_time            DATETIME comment '审核时间',
    review_result          INT comment '审核结果',
    improvement_suggestion VARCHAR(255) CHARACTER SET utf8mb4 comment '改进建议'
);

-- read_tasks: 阅读发布关系表
CREATE TABLE read_tasks
(
    id              INT auto_increment PRIMARY KEY comment 'ID',
    books_id        INT comment '书目ID',
    task_title      VARCHAR(255) CHARACTER SET utf8mb4 comment '任务标题',
    task_suggestion VARCHAR(255) CHARACTER SET utf8mb4 comment '任务建议',
    task_detail     VARCHAR(255) CHARACTER SET utf8mb4 comment '任务详情',
    create_time     DATETIME comment '发布时间',
    deadline        DATETIME comment '截止时间',
    teacher_id      INT comment '发布教师ID'
);

-- recommend: 推荐关系表
CREATE TABLE recommend
(
    id              INT auto_increment PRIMARY KEY,
    teacher_id      INT comment '教师ID',
    contribute_id   INT comment '稿件ID',
    reason          VARCHAR(255) CHARACTER SET utf8mb4 comment '推荐理由',
    recommend_level INT comment '推荐等级'
);

-- books_contribute: 书目关系表
CREATE TABLE books_contribute
(
    id            INT auto_increment PRIMARY KEY comment 'ID',
    books_id      VARCHAR(255) CHARACTER SET utf8mb4 comment '外键books的ID',
    contribute_id INT comment '外键contribute的ID'
);

-- solicitation_contribute: 征收投入稿件关系表（多对多）
CREATE TABLE solicitation_contribute
(
    id              INT auto_increment PRIMARY KEY comment 'ID',
    solicitation_id INT comment '外码征收ID',
    contribute_id   INT comment '外键contribute的ID'
);


# 阅读任务实体SQL
CREATE TABLE read_tasks
(
    id             INT auto_increment PRIMARY KEY comment 'ID',
    teacher_id      INT,
    task_title      VARCHAR(255) CHARACTER SET utf8mb4 comment '任务标题',
    task_suggestion VARCHAR(255) CHARACTER SET utf8mb4 comment '任务建议',
    task_detail     VARCHAR(255) CHARACTER SET utf8mb4 comment '任务详情',
    create_time     DATETIME comment '发布时间',
    deadline       DATETIME comment '结束时间'
);


# 阅读任务和稿件的关系表
CREATE TABLE read_tasks_contribute
(
    id            INT auto_increment PRIMARY KEY comment 'ID',
    read_tasks_id INT,
    contribute_id INT
);