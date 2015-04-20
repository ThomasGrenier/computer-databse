package com.excilys.model;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QComputerModel is a Querydsl query type for ComputerModel
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QComputerModel extends EntityPathBase<ComputerModel> {

    private static final long serialVersionUID = -1398798885L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QComputerModel computerModel = new QComputerModel("computerModel");

    public final QCompanyModel company;

    public final DateTimePath<java.time.LocalDateTime> discontinued = createDateTime("discontinued", java.time.LocalDateTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final DateTimePath<java.time.LocalDateTime> introduced = createDateTime("introduced", java.time.LocalDateTime.class);

    public final StringPath name = createString("name");

    public QComputerModel(String variable) {
        this(ComputerModel.class, forVariable(variable), INITS);
    }

    public QComputerModel(Path<? extends ComputerModel> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QComputerModel(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QComputerModel(PathMetadata<?> metadata, PathInits inits) {
        this(ComputerModel.class, metadata, inits);
    }

    public QComputerModel(Class<? extends ComputerModel> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.company = inits.isInitialized("company") ? new QCompanyModel(forProperty("company")) : null;
    }

}

