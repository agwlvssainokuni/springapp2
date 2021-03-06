package cherry.fundamental.db.gen.query;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;

import com.querydsl.sql.ColumnMetadata;
import java.sql.Types;




/**
 * QVerifySecure is a Querydsl query type for BVerifySecure
 */
@Generated("com.querydsl.sql.codegen.MetaDataSerializer")
public class QVerifySecure extends com.querydsl.sql.RelationalPathBase<BVerifySecure> {

    private static final long serialVersionUID = -1117641412;

    public static final QVerifySecure verifySecure = new QVerifySecure("VERIFY_SECURE");

    public final StringPath bigdec = createString("bigdec");

    public final StringPath bigint = createString("bigint");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath int32 = createString("int32");

    public final StringPath int64 = createString("int64");

    public final StringPath str = createString("str");

    public final com.querydsl.sql.PrimaryKey<BVerifySecure> verifySecurePkc = createPrimaryKey(id);

    public QVerifySecure(String variable) {
        super(BVerifySecure.class, forVariable(variable), "PUBLIC", "VERIFY_SECURE");
        addMetadata();
    }

    public QVerifySecure(String variable, String schema, String table) {
        super(BVerifySecure.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QVerifySecure(Path<? extends BVerifySecure> path) {
        super(path.getType(), path.getMetadata(), "PUBLIC", "VERIFY_SECURE");
        addMetadata();
    }

    public QVerifySecure(PathMetadata metadata) {
        super(BVerifySecure.class, metadata, "PUBLIC", "VERIFY_SECURE");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(bigdec, ColumnMetadata.named("BIGDEC").withIndex(6).ofType(Types.VARCHAR).withSize(136));
        addMetadata(bigint, ColumnMetadata.named("BIGINT").withIndex(5).ofType(Types.VARCHAR).withSize(136));
        addMetadata(id, ColumnMetadata.named("ID").withIndex(1).ofType(Types.BIGINT).withSize(19).notNull());
        addMetadata(int32, ColumnMetadata.named("INT32").withIndex(3).ofType(Types.VARCHAR).withSize(72));
        addMetadata(int64, ColumnMetadata.named("INT64").withIndex(4).ofType(Types.VARCHAR).withSize(72));
        addMetadata(str, ColumnMetadata.named("STR").withIndex(2).ofType(Types.VARCHAR).withSize(136));
    }

}

