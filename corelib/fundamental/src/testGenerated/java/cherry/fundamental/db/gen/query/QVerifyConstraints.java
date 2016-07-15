package cherry.fundamental.db.gen.query;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;

import com.querydsl.sql.ColumnMetadata;
import java.sql.Types;




/**
 * QVerifyConstraints is a Querydsl query type for BVerifyConstraints
 */
@Generated("com.querydsl.sql.codegen.MetaDataSerializer")
public class QVerifyConstraints extends com.querydsl.sql.RelationalPathBase<BVerifyConstraints> {

    private static final long serialVersionUID = 718614417;

    public static final QVerifyConstraints verifyConstraints = new QVerifyConstraints("VERIFY_CONSTRAINTS");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public final NumberPath<Long> parentId = createNumber("parentId", Long.class);

    public final com.querydsl.sql.PrimaryKey<BVerifyConstraints> verifyConstraintsPkc = createPrimaryKey(id);

    public final com.querydsl.sql.ForeignKey<BVerifyConstraints> verifyConstraintsFk1 = createForeignKey(parentId, "ID");

    public final com.querydsl.sql.ForeignKey<BVerifyConstraints> _verifyConstraintsFk1 = createInvForeignKey(id, "PARENT_ID");

    public QVerifyConstraints(String variable) {
        super(BVerifyConstraints.class, forVariable(variable), "PUBLIC", "VERIFY_CONSTRAINTS");
        addMetadata();
    }

    public QVerifyConstraints(String variable, String schema, String table) {
        super(BVerifyConstraints.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QVerifyConstraints(Path<? extends BVerifyConstraints> path) {
        super(path.getType(), path.getMetadata(), "PUBLIC", "VERIFY_CONSTRAINTS");
        addMetadata();
    }

    public QVerifyConstraints(PathMetadata metadata) {
        super(BVerifyConstraints.class, metadata, "PUBLIC", "VERIFY_CONSTRAINTS");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(id, ColumnMetadata.named("ID").withIndex(1).ofType(Types.BIGINT).withSize(19).notNull());
        addMetadata(name, ColumnMetadata.named("NAME").withIndex(3).ofType(Types.VARCHAR).withSize(32));
        addMetadata(parentId, ColumnMetadata.named("PARENT_ID").withIndex(2).ofType(Types.BIGINT).withSize(19));
    }

}

