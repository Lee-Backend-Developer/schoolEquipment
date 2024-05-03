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

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRental rental = new QRental("rental");

    public final QClasstimes classtimesId;

    public final QEquipment equipmentId;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath rentalChk = createBoolean("rentalChk");

    public final NumberPath<Integer> rentalCnt = createNumber("rentalCnt", Integer.class);

    public QRental(String variable) {
        this(Rental.class, forVariable(variable), INITS);
    }

    public QRental(Path<? extends Rental> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QRental(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QRental(PathMetadata metadata, PathInits inits) {
        this(Rental.class, metadata, inits);
    }

    public QRental(Class<? extends Rental> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.classtimesId = inits.isInitialized("classtimesId") ? new QClasstimes(forProperty("classtimesId")) : null;
        this.equipmentId = inits.isInitialized("equipmentId") ? new QEquipment(forProperty("equipmentId"), inits.get("equipmentId")) : null;
    }

}

