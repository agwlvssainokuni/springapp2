package cherry.gallery.db.gen.query;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;

import com.querydsl.sql.ColumnMetadata;
import java.sql.Types;




/**
 * QAsyncProcessException is a Querydsl query type for BAsyncProcessException
 */
@Generated("com.querydsl.sql.codegen.MetaDataSerializer")
public class QAsyncProcessException extends com.querydsl.sql.RelationalPathBase<BAsyncProcessException> {

    private static final long serialVersionUID = 1921165789;

    public static final QAsyncProcessException asyncProcessException = new QAsyncProcessException("ASYNC_PROCESS_EXCEPTION");

    public final NumberPath<Long> asyncId = createNumber("asyncId", Long.class);

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final StringPath exception = createString("exception");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Integer> lockVersion = createNumber("lockVersion", Integer.class);

    public final DateTimePath<java.time.LocalDateTime> updatedAt = createDateTime("updatedAt", java.time.LocalDateTime.class);

    public final com.querydsl.sql.PrimaryKey<BAsyncProcessException> asyncProcessExceptionPkc = createPrimaryKey(id);

    public QAsyncProcessException(String variable) {
        super(BAsyncProcessException.class, forVariable(variable), "PUBLIC", "ASYNC_PROCESS_EXCEPTION");
        addMetadata();
    }

    public QAsyncProcessException(String variable, String schema, String table) {
        super(BAsyncProcessException.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QAsyncProcessException(Path<? extends BAsyncProcessException> path) {
        super(path.getType(), path.getMetadata(), "PUBLIC", "ASYNC_PROCESS_EXCEPTION");
        addMetadata();
    }

    public QAsyncProcessException(PathMetadata metadata) {
        super(BAsyncProcessException.class, metadata, "PUBLIC", "ASYNC_PROCESS_EXCEPTION");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(asyncId, ColumnMetadata.named("ASYNC_ID").withIndex(2).ofType(Types.BIGINT).withSize(19).notNull());
        addMetadata(createdAt, ColumnMetadata.named("CREATED_AT").withIndex(5).ofType(Types.TIMESTAMP).withSize(23).withDigits(10).notNull());
        addMetadata(exception, ColumnMetadata.named("EXCEPTION").withIndex(3).ofType(Types.CLOB).withSize(2147483647).notNull());
        addMetadata(id, ColumnMetadata.named("ID").withIndex(1).ofType(Types.BIGINT).withSize(19).notNull());
        addMetadata(lockVersion, ColumnMetadata.named("LOCK_VERSION").withIndex(6).ofType(Types.INTEGER).withSize(10).notNull());
        addMetadata(updatedAt, ColumnMetadata.named("UPDATED_AT").withIndex(4).ofType(Types.TIMESTAMP).withSize(23).withDigits(10).notNull());
    }

}

