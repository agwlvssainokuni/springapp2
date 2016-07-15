package cherry.example.db.gen.query;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;

import com.querydsl.sql.ColumnMetadata;
import java.sql.Types;




/**
 * QAsyncProcessCommandResult is a Querydsl query type for BAsyncProcessCommandResult
 */
@Generated("com.querydsl.sql.codegen.MetaDataSerializer")
public class QAsyncProcessCommandResult extends com.querydsl.sql.RelationalPathBase<BAsyncProcessCommandResult> {

    private static final long serialVersionUID = 1079161838;

    public static final QAsyncProcessCommandResult asyncProcessCommandResult = new QAsyncProcessCommandResult("ASYNC_PROCESS_COMMAND_RESULT");

    public final NumberPath<Long> asyncId = createNumber("asyncId", Long.class);

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final NumberPath<Integer> exitValue = createNumber("exitValue", Integer.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Integer> lockVersion = createNumber("lockVersion", Integer.class);

    public final StringPath stderr = createString("stderr");

    public final StringPath stdout = createString("stdout");

    public final DateTimePath<java.time.LocalDateTime> updatedAt = createDateTime("updatedAt", java.time.LocalDateTime.class);

    public final com.querydsl.sql.PrimaryKey<BAsyncProcessCommandResult> asyncProcessCommandResultPkc = createPrimaryKey(id);

    public QAsyncProcessCommandResult(String variable) {
        super(BAsyncProcessCommandResult.class, forVariable(variable), "PUBLIC", "ASYNC_PROCESS_COMMAND_RESULT");
        addMetadata();
    }

    public QAsyncProcessCommandResult(String variable, String schema, String table) {
        super(BAsyncProcessCommandResult.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QAsyncProcessCommandResult(Path<? extends BAsyncProcessCommandResult> path) {
        super(path.getType(), path.getMetadata(), "PUBLIC", "ASYNC_PROCESS_COMMAND_RESULT");
        addMetadata();
    }

    public QAsyncProcessCommandResult(PathMetadata metadata) {
        super(BAsyncProcessCommandResult.class, metadata, "PUBLIC", "ASYNC_PROCESS_COMMAND_RESULT");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(asyncId, ColumnMetadata.named("ASYNC_ID").withIndex(2).ofType(Types.BIGINT).withSize(19).notNull());
        addMetadata(createdAt, ColumnMetadata.named("CREATED_AT").withIndex(7).ofType(Types.TIMESTAMP).withSize(23).withDigits(10).notNull());
        addMetadata(exitValue, ColumnMetadata.named("EXIT_VALUE").withIndex(3).ofType(Types.INTEGER).withSize(10).notNull());
        addMetadata(id, ColumnMetadata.named("ID").withIndex(1).ofType(Types.BIGINT).withSize(19).notNull());
        addMetadata(lockVersion, ColumnMetadata.named("LOCK_VERSION").withIndex(8).ofType(Types.INTEGER).withSize(10).notNull());
        addMetadata(stderr, ColumnMetadata.named("STDERR").withIndex(5).ofType(Types.VARCHAR).withSize(1000).notNull());
        addMetadata(stdout, ColumnMetadata.named("STDOUT").withIndex(4).ofType(Types.VARCHAR).withSize(1000).notNull());
        addMetadata(updatedAt, ColumnMetadata.named("UPDATED_AT").withIndex(6).ofType(Types.TIMESTAMP).withSize(23).withDigits(10).notNull());
    }

}

