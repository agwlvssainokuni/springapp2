package cherry.gallery.db.gen.query;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;

import com.querydsl.sql.ColumnMetadata;
import java.sql.Types;




/**
 * QAsyncProcess is a Querydsl query type for BAsyncProcess
 */
@Generated("com.querydsl.sql.codegen.MetaDataSerializer")
public class QAsyncProcess extends com.querydsl.sql.RelationalPathBase<BAsyncProcess> {

    private static final long serialVersionUID = -1101209222;

    public static final QAsyncProcess asyncProcess = new QAsyncProcess("ASYNC_PROCESS");

    public final StringPath asyncStatus = createString("asyncStatus");

    public final StringPath asyncType = createString("asyncType");

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final StringPath description = createString("description");

    public final DateTimePath<java.time.LocalDateTime> finishedAt = createDateTime("finishedAt", java.time.LocalDateTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final DateTimePath<java.time.LocalDateTime> launchedAt = createDateTime("launchedAt", java.time.LocalDateTime.class);

    public final StringPath launchedBy = createString("launchedBy");

    public final NumberPath<Integer> lockVersion = createNumber("lockVersion", Integer.class);

    public final DateTimePath<java.time.LocalDateTime> registeredAt = createDateTime("registeredAt", java.time.LocalDateTime.class);

    public final DateTimePath<java.time.LocalDateTime> startedAt = createDateTime("startedAt", java.time.LocalDateTime.class);

    public final DateTimePath<java.time.LocalDateTime> updatedAt = createDateTime("updatedAt", java.time.LocalDateTime.class);

    public final com.querydsl.sql.PrimaryKey<BAsyncProcess> asyncProcessPkc = createPrimaryKey(id);

    public QAsyncProcess(String variable) {
        super(BAsyncProcess.class, forVariable(variable), "PUBLIC", "ASYNC_PROCESS");
        addMetadata();
    }

    public QAsyncProcess(String variable, String schema, String table) {
        super(BAsyncProcess.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QAsyncProcess(Path<? extends BAsyncProcess> path) {
        super(path.getType(), path.getMetadata(), "PUBLIC", "ASYNC_PROCESS");
        addMetadata();
    }

    public QAsyncProcess(PathMetadata metadata) {
        super(BAsyncProcess.class, metadata, "PUBLIC", "ASYNC_PROCESS");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(asyncStatus, ColumnMetadata.named("ASYNC_STATUS").withIndex(5).ofType(Types.VARCHAR).withSize(3).notNull());
        addMetadata(asyncType, ColumnMetadata.named("ASYNC_TYPE").withIndex(4).ofType(Types.VARCHAR).withSize(3).notNull());
        addMetadata(createdAt, ColumnMetadata.named("CREATED_AT").withIndex(11).ofType(Types.TIMESTAMP).withSize(23).withDigits(10).notNull());
        addMetadata(description, ColumnMetadata.named("DESCRIPTION").withIndex(3).ofType(Types.VARCHAR).withSize(100).notNull());
        addMetadata(finishedAt, ColumnMetadata.named("FINISHED_AT").withIndex(9).ofType(Types.TIMESTAMP).withSize(23).withDigits(10));
        addMetadata(id, ColumnMetadata.named("ID").withIndex(1).ofType(Types.BIGINT).withSize(19).notNull());
        addMetadata(launchedAt, ColumnMetadata.named("LAUNCHED_AT").withIndex(7).ofType(Types.TIMESTAMP).withSize(23).withDigits(10));
        addMetadata(launchedBy, ColumnMetadata.named("LAUNCHED_BY").withIndex(2).ofType(Types.VARCHAR).withSize(100).notNull());
        addMetadata(lockVersion, ColumnMetadata.named("LOCK_VERSION").withIndex(12).ofType(Types.INTEGER).withSize(10).notNull());
        addMetadata(registeredAt, ColumnMetadata.named("REGISTERED_AT").withIndex(6).ofType(Types.TIMESTAMP).withSize(23).withDigits(10).notNull());
        addMetadata(startedAt, ColumnMetadata.named("STARTED_AT").withIndex(8).ofType(Types.TIMESTAMP).withSize(23).withDigits(10));
        addMetadata(updatedAt, ColumnMetadata.named("UPDATED_AT").withIndex(10).ofType(Types.TIMESTAMP).withSize(23).withDigits(10).notNull());
    }

}

