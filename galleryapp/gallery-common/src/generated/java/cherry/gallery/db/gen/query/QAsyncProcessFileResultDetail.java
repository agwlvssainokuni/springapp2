package cherry.gallery.db.gen.query;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;

import com.querydsl.sql.ColumnMetadata;
import java.sql.Types;




/**
 * QAsyncProcessFileResultDetail is a Querydsl query type for BAsyncProcessFileResultDetail
 */
@Generated("com.querydsl.sql.codegen.MetaDataSerializer")
public class QAsyncProcessFileResultDetail extends com.querydsl.sql.RelationalPathBase<BAsyncProcessFileResultDetail> {

    private static final long serialVersionUID = 6577436;

    public static final QAsyncProcessFileResultDetail asyncProcessFileResultDetail = new QAsyncProcessFileResultDetail("ASYNC_PROCESS_FILE_RESULT_DETAIL");

    public final NumberPath<Long> asyncId = createNumber("asyncId", Long.class);

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final StringPath description = createString("description");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Integer> lockVersion = createNumber("lockVersion", Integer.class);

    public final NumberPath<Long> recordNumber = createNumber("recordNumber", Long.class);

    public final DateTimePath<java.time.LocalDateTime> updatedAt = createDateTime("updatedAt", java.time.LocalDateTime.class);

    public final com.querydsl.sql.PrimaryKey<BAsyncProcessFileResultDetail> asyncProcessFileResultDetailPkc = createPrimaryKey(id);

    public QAsyncProcessFileResultDetail(String variable) {
        super(BAsyncProcessFileResultDetail.class, forVariable(variable), "PUBLIC", "ASYNC_PROCESS_FILE_RESULT_DETAIL");
        addMetadata();
    }

    public QAsyncProcessFileResultDetail(String variable, String schema, String table) {
        super(BAsyncProcessFileResultDetail.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QAsyncProcessFileResultDetail(Path<? extends BAsyncProcessFileResultDetail> path) {
        super(path.getType(), path.getMetadata(), "PUBLIC", "ASYNC_PROCESS_FILE_RESULT_DETAIL");
        addMetadata();
    }

    public QAsyncProcessFileResultDetail(PathMetadata metadata) {
        super(BAsyncProcessFileResultDetail.class, metadata, "PUBLIC", "ASYNC_PROCESS_FILE_RESULT_DETAIL");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(asyncId, ColumnMetadata.named("ASYNC_ID").withIndex(2).ofType(Types.BIGINT).withSize(19).notNull());
        addMetadata(createdAt, ColumnMetadata.named("CREATED_AT").withIndex(6).ofType(Types.TIMESTAMP).withSize(23).withDigits(10).notNull());
        addMetadata(description, ColumnMetadata.named("DESCRIPTION").withIndex(4).ofType(Types.VARCHAR).withSize(100));
        addMetadata(id, ColumnMetadata.named("ID").withIndex(1).ofType(Types.BIGINT).withSize(19).notNull());
        addMetadata(lockVersion, ColumnMetadata.named("LOCK_VERSION").withIndex(7).ofType(Types.INTEGER).withSize(10).notNull());
        addMetadata(recordNumber, ColumnMetadata.named("RECORD_NUMBER").withIndex(3).ofType(Types.BIGINT).withSize(19).notNull());
        addMetadata(updatedAt, ColumnMetadata.named("UPDATED_AT").withIndex(5).ofType(Types.TIMESTAMP).withSize(23).withDigits(10).notNull());
    }

}

