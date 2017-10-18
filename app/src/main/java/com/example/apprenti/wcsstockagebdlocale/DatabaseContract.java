package com.example.apprenti.wcsstockagebdlocale;

import android.provider.BaseColumns;

/**
 * Created by apprenti on 03/10/17.
 */

public class DatabaseContract {

    private DatabaseContract() {}


    public static class UserEntry implements BaseColumns {
        public static final String TABLE_NAME = "User";
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_EMAIL = "email";
        public static final String COLUMN_NAME_ID = "id";
    }

    public static final String SQL_DELETE_USER =
            "DROP TABLE IF EXISTS " + UserEntry.TABLE_NAME;


    public static class OrganizationEntry implements BaseColumns {
        public static final String TABLE_NAME = "Organization";
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_EMAIL = "website_url";
        public static final String COLUMN_NAME_ID = "id";
    }

    public static final String SQL_DELETE_ORGANIZATION =
            "DROP TABLE IF EXISTS " + OrganizationEntry.TABLE_NAME;


    public static class TweetEntry implements BaseColumns {
        public static final String TABLE_NAME = "Tweet";
        public static final String COLUMN_NAME_ID = "id";
        public static final String COLUMN_NAME_CONTENT = "content";
        public static final String COLUMN_NAME_USER_ID = "id_user";
    }

    public static final String SQL_DELETE_TWEET =
            "DROP TABLE IF EXISTS " + TweetEntry.TABLE_NAME;


    public static class BelongEntry implements BaseColumns {
        public static final String TABLE_NAME = "user_organization";
        public static final String COLUMN_NAME_ID = "id";
        public static final String COLUMN_NAME_ID_USER = "id_user";
        public static final String COLUMN_NAME_ID_ORGANIZATION = "id_organization";
    }

    public static final String SQL_DELETE_USER_ORGANIZATION =
            "DROP TABLE IF EXISTS " + BelongEntry.TABLE_NAME;

}


    /*CREATE TABLE `User` (
        `id` INT NOT NULL AUTO_INCREMENT,
        `name` varchar(30) NOT NULL,
        `email` varchar(50) NOT NULL,
        PRIMARY KEY (`id`)
        );
        CREATE TABLE `Organization` (
        `id` INT NOT NULL AUTO_INCREMENT,
        `name` varchar(30) NOT NULL,
        `website_url` varchar(50) NOT NULL,
        PRIMARY KEY (`id`)
        );
        CREATE TABLE `Tweet` (
        `id` INT NOT NULL AUTO_INCREMENT,
        `content` TEXT NOT NULL,
        `id_user` INT NOT NULL,
        PRIMARY KEY (`id`)
        );
        CREATE TABLE `user_organization` (
        `id` INT NOT NULL AUTO_INCREMENT,
        `id_user` INT NOT NULL,
        `id_organization` INT NOT NULL,
        PRIMARY KEY (`id`)
        );
        ALTER TABLE `Tweet` ADD CONSTRAINT `Tweet_fk0` FOREIGN KEY (`id_user`) REFERENCES `User`(`id`);
        ALTER TABLE `user_organization` ADD CONSTRAINT `user_organization_fk0` FOREIGN KEY (`id_user`) REFERENCES `User`(`id`);
        ALTER TABLE `user_organization` ADD CONSTRAINT `user_organization_fk1` FOREIGN KEY (`id_organization`) REFERENCES `Organization`(`id`);*/