
    alter table acct_role_authority 
        drop foreign key FKAE243466DE3FB930;

    alter table acct_role_authority 
        drop foreign key FKAE2434663FE97564;

    alter table acct_user_role 
        drop foreign key FKFE85CB3EDE3FB930;

    alter table acct_user_role 
        drop foreign key FKFE85CB3E836A7D10;

    drop table if exists acct_authority;

    drop table if exists acct_role;

    drop table if exists acct_role_authority;

    drop table if exists acct_user;

    drop table if exists acct_user_role;

    create table acct_authority (
        id bigint not null auto_increment,
        name varchar(255) not null unique,
        primary key (id)
    ) ENGINE=InnoDB;

    create table acct_role (
        id bigint not null auto_increment,
        name varchar(255) not null unique,
        primary key (id)
    ) ENGINE=InnoDB;

    create table acct_role_authority (
        role_id bigint not null,
        authority_id bigint not null
    ) ENGINE=InnoDB;

    create table acct_user (
        id bigint not null auto_increment,
        email varchar(255),
        login_name varchar(255) not null unique,
        name varchar(255),
        password varchar(255),
        primary key (id)
    ) ENGINE=InnoDB;

    create table acct_user_role (
        user_id bigint not null,
        role_id bigint not null
    ) ENGINE=InnoDB;

    alter table acct_role_authority 
        add constraint FKAE243466DE3FB930 
        foreign key (role_id) 
        references acct_role (id);

    alter table acct_role_authority 
        add constraint FKAE2434663FE97564 
        foreign key (authority_id) 
        references acct_authority (id);

    alter table acct_user_role 
        add constraint FKFE85CB3EDE3FB930 
        foreign key (role_id) 
        references acct_role (id);

    alter table acct_user_role 
        add constraint FKFE85CB3E836A7D10 
        foreign key (user_id) 
        references acct_user (id);
