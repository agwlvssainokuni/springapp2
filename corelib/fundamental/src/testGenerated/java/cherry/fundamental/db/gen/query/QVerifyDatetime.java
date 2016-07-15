package cherry.fundamental.db.gen.query;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;

import com.querydsl.sql.ColumnMetadata;
import java.sql.Types;




/**
 * QVerifyDatetime is a Querydsl query type for BVerifyDatetime
 */
@Generated("com.querydsl.sql.codegen.MetaDataSerializer")
public class QVerifyDatetime extends com.querydsl.sql.RelationalPathBase<BVerifyDatetime> {

    private static final long serialVersionUID = 533014528;

    public static final QVerifyDatetime verifyDatetime = new QVerifyDatetime("VERIFY_DATETIME");

    public final DatePath<java.time.LocalDate> dt = createDate("dt", java.time.LocalDate.class);

    public final DateTimePath<java.time.LocalDateTime> dtm = createDateTime("dtm", java.time.LocalDateTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final TimePath<java.time.LocalTime> tm = createTime("tm", java.time.LocalTime.class);

    public final com.querydsl.sql.PrimaryKey<BVerifyDatetime> verifyDatetimePkc = createPrimaryKey(id);

    public QVerifyDatetime(String variable) {
        super(BVerifyDatetime.class, forVariable(variable), "PUBLIC", "VERIFY_DATETIME");
        addMetadata();
    }

    public QVerifyDatetime(String variable, String schema, String table) {
        super(BVerifyDatetime.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QVerifyDatetime(Path<? extends BVerifyDatetime> path) {
        super(path.getType(), path.getMetadata(), "PUBLIC", "VERIFY_DATETIME");
        addMetadata();
    }

    public QVerifyDatetime(PathMetadata metadata) {
        super(BVerifyDatetime.class, metadata, "PUBLIC", "VERIFY_DATETIME");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(dt, ColumnMetadata.named("DT").withIndex(2).ofType(Types.DATE).withSize(8));
        addMetadata(dtm, ColumnMetadata.named("DTM").withIndex(4).ofType(Types.TIMESTAMP).withSize(23).withDigits(10));
        addMetadata(id, ColumnMetadata.named("ID").withIndex(1).ofType(Types.BIGINT).withSize(19).notNull());
        addMetadata(tm, ColumnMetadata.named("TM").withIndex(3).ofType(Types.TIME).withSize(6));
    }

}

