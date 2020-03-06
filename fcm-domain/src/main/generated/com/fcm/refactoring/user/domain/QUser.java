package com.fcm.refactoring.user.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QUser is a Querydsl query type for User
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QUser extends EntityPathBase<User> {

    private static final long serialVersionUID = 306157227L;

    public static final QUser user = new QUser("user");

    public final NumberPath<Integer> age = createNumber("age", Integer.class);

    public final DateTimePath<java.time.LocalDateTime> createDateTime = createDateTime("createDateTime", java.time.LocalDateTime.class);

    public final BooleanPath enable = createBoolean("enable");

    public final EnumPath<com.fcm.refactoring.user.Gender> gender = createEnum("gender", com.fcm.refactoring.user.Gender.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath userId = createString("userId");

    public final StringPath userName = createString("userName");

    public final EnumPath<com.fcm.refactoring.user.UserType> userType = createEnum("userType", com.fcm.refactoring.user.UserType.class);

    public QUser(String variable) {
        super(User.class, forVariable(variable));
    }

    public QUser(Path<? extends User> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUser(PathMetadata metadata) {
        super(User.class, metadata);
    }

}

