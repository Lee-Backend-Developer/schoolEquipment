package com.equipment.school_equipment.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QRental is a Querydsl query type for Rental
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QRental extends EntityPathBase<Rental> {

    private static final long serialVersionUID = 1357032368L;

    public static final QRental rental = new QRental("rental");

    public final ListPath<ClassTime, QClassTime> classTime = this.<ClassTime, QClassTime>createList("classTime", ClassTime.class, QClassTime.class, PathInits.DIRECT2);

    public final ListPath<Equipment, QEquipment> equipment = this.<Equipment, QEquipment>createList("equipment", Equipment.class, QEquipment.class, PathInits.DIRECT2);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath rentalChk = createBoolean("rentalChk");

    public QRental(String variable) {
        super(Rental.class, forVariable(variable));
    }

    public QRental(Path<? extends Rental> path) {
        super(path.getType(), path.getMetadata());
    }

    public QRental(PathMetadata metadata) {
        super(Rental.class, metadata);
    }

}

