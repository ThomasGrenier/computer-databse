package com.excilys.model;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QCompanyModel is a Querydsl query type for CompanyModel
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QCompanyModel extends EntityPathBase<CompanyModel> {

    private static final long serialVersionUID = 214695423L;

    public static final QCompanyModel companyModel = new QCompanyModel("companyModel");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public QCompanyModel(String variable) {
        super(CompanyModel.class, forVariable(variable));
    }

    public QCompanyModel(Path<? extends CompanyModel> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCompanyModel(PathMetadata<?> metadata) {
        super(CompanyModel.class, metadata);
    }

}

