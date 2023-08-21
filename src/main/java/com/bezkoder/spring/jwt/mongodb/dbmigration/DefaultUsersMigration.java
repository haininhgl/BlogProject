package com.bezkoder.spring.jwt.mongodb.dbmigration;

import com.bezkoder.spring.jwt.mongodb.constants.Constants;
import com.bezkoder.spring.jwt.mongodb.entity.Role;
import com.bezkoder.spring.jwt.mongodb.entity.RoleType;
import com.bezkoder.spring.jwt.mongodb.entity.User;
import io.mongock.api.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.Set;

/**
 * Creates the initial database setup.
 */
@ChangeUnit(id = "users-initialization", order = "001")
public class DefaultUsersMigration {

    private final Logger log = LoggerFactory.getLogger(DefaultUsersMigration.class);
    private final MongoTemplate mongoTemplate;

    public DefaultUsersMigration(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Execution
    public void changeSet() {
//        // create system role
//        Role systemRole = new Role();
//        systemRole.setSystemRole(true);
//        systemRole.setName(RoleType.valueOf("ROLE_ADMIN"));
//        systemRole = mongoTemplate.save(systemRole);
//
//
//        // create system user
//        User systemUser = new User();
//        systemUser.setUsername(Constants.ADMIN);
//        systemUser.setPassword("$2a$10$gSAhZrxMllrbgj/kkK9UceBPpChGWJA7SYIb1Mqo.n5aNLq1/oRrC");
//        systemUser.setFullName("Administrator");
//        systemUser.setEmail("admin@miraway.vn");
//        systemUser.setRoles(Set.of(systemRole));
//
//        mongoTemplate.save(systemUser);

        // Create system roles
        Role adminRole = new Role();
        adminRole.setSystemRole(true);
        adminRole.setName(RoleType.ROLE_ADMIN);
        adminRole = mongoTemplate.save(adminRole);

        Role userRole = new Role();
        userRole.setSystemRole(true);
        userRole.setName(RoleType.ROLE_USER);
        userRole = mongoTemplate.save(userRole);

        // Create system user with both roles
        User systemUser = new User();
        systemUser.setUsername(Constants.ADMIN);
        systemUser.setPassword("$2a$10$gSAhZrxMllrbgj/kkK9UceBPpChGWJA7SYIb1Mqo.n5aNLq1/oRrC");
        systemUser.setFullName("Administrator");
        systemUser.setEmail("admin@miraway.vn");
        systemUser.setRoles(Set.of(adminRole, userRole));

        mongoTemplate.save(systemUser);

        log.info("Chay nao day");
    }

    @RollbackExecution
    public void rollback() {
        mongoTemplate.dropCollection("users");
        mongoTemplate.dropCollection("roles");
    }
}
