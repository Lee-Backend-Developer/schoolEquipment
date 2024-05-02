package com.equipment.school_equipment.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QClasstimelist is a Querydsl query type for Classtimelist
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QClasstimelist extends EntityPathBase<Classtimelist> {

    private static final long serialVersionUID = -151575977L;

    public static final QClasstimelist classtimelist = new QClasstimelist("classtimelist");

    public final StringPath className = createString("className");

    public final EnumPath<com.equipment.school_equipment.domain.enumDomain.DayOfWeekEnum> dayOfWeek = createEnum("dayOfWeek", com.equipment.school_equipment.domain.enumDomain.DayOfWeekEnum.class);

    public final BooleanPath eightTime = createBoolean("eightTime");

    public final BooleanPath fiveTime = createBoolean("fiveTime");

    public final BooleanPath fourTime = createBoolean("fourTime");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath nineTime = createBoolean("nineTime");

    public final BooleanPath oneTime = createBoolean("oneTime");

    public final BooleanPath sevenTime = createBoolean("sevenTime");

    public final BooleanPath sixTime = createBoolean("sixTime");

    public final BooleanPath tenTime = createBoolean("tenTime");

    public final BooleanPath threeTime = createBoolean("threeTime");

    public final BooleanPath twoTime = createBoolean("twoTime");

    public QClasstimelist(String variable) {
        super(Classtimelist.class, forVariable(variable));
    }

    public QClasstimelist(Path<? extends Classtimelist> path) {
        super(path.getType(), path.getMetadata());
    }

    public QClasstimelist(PathMetadata metadata) {
        super(Classtimelist.class, metadata);
    }

}

