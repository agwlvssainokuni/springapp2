package cherry.example.db.gen.query;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;

import com.querydsl.sql.ColumnMetadata;
import java.sql.Types;




/**
 * QAsyncProcessFileResult is a Querydsl query type for BAsyncProcessFileResult
 */
@Generated("com.querydsl.sql.codegen.MetaDataSerializer")
public class QAsyncProcessFileResult extends com.querydsl.sql.RelationalPathBase<BAsyncProcessFileResult> {

    private static final long serialVersionUID = 1361201619;

    public static final QAsyncProcessFileResult asyncProcessFileResult = new QAsyncProcessFileResult("ASYNC_PROCESS_FILE_RESULT");

    public final NumberPath<Long> asyncId = createNumber("asyncId", Long.class);

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Integer> lockVersion = createNumber("lockVersion", Integer.class);

    public final NumberPath<Long> ngCount = createNumber("ngCount", Long.class);

    public final NumberPath<Long> okCount = createNumber("okCount", Long.class);

    public final NumberPath<Long> totalCount = createNumber("totalCount", Long.class);

    public final DateTimePath<java.time.LocalDateTime> updatedAt = createDateTime("updatedAt", java.time.LocalDateTime.class);

    public final com.querydsl.sql.PrimaryKey<BAsyncProcessFileResult> asyncProcessFileResultPkc = createPrimaryKey(id);

    public QAsyncProcessFileResult(String variable) {
        super(BAsyncProcessFileResult.class, forVariable(variable), "PUBLIC", "ASYNC_PROCESS_FILE_RESULT");
        addMetadata();
    }

    public QAsyncProcessFileResult(String variable, String schema, String table) {
        super(BAsyncProcessFileResult.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QAsyncProcessFileResult(Path<? extends BAsyncProcessFileResult> path) {
        super(path.getType(), path.getMetadata(), "PUBLIC", "ASYNC_PROCESS_FILE_RESULT");
        addMetadata();
    }

    public QAsyncProcessFileResult(PathMetadata metadata) {
        super(BAsyncProcessFileResult.class, metadata, "PUBLIC", "ASYNC_PROCESS_FILE_RESULT");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(asyncId, ColumnMetadata.named("ASYNC_ID").withIndex(2).ofType(Types.BIGINT).withSize(19).notNull());
        addMetadata(createdAt, ColumnMetadata.named("CREATED_AT").withIndex(7).ofType(Types.TIMESTAMP).withSize(23).withDigits(10).notNull());
        addMetadata(id, ColumnMetadata.named("ID").withIndex(1).ofType(Types.BIGINT).withSize(19).notNull());
        addMetadata(lockVersion, ColumnMetadata.named("LOCK_VERSION").withIndex(8).ofType(Types.INTEGER).withSize(10).notNull());
        addMetadata(ngCount, ColumnMetadata.named("NG_COUNT").withIndex(5).ofType(Types.BIGINT).withSize(19).notNull());
        addMetadata(okCount, ColumnMetadata.named("OK_COUNT").withIndex(4).ofType(Types.BIGINT).withSize(19).notNull());
        addMetadata(totalCount, ColumnMetadata.named("TOTAL_COUNT").withIndex(3).ofType(Types.BIGINT).withSize(19).notNull());
        addMetadata(updatedAt, ColumnMetadata.named("UPDATED_AT").withIndex(6).ofType(Types.TIMESTAMP).withSize(23).withDigits(10).notNull());
    }

}

