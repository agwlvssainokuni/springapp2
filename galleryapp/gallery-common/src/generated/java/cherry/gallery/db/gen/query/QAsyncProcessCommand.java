package cherry.gallery.db.gen.query;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;

import com.querydsl.sql.ColumnMetadata;
import java.sql.Types;




/**
 * QAsyncProcessCommand is a Querydsl query type for BAsyncProcessCommand
 */
@Generated("com.querydsl.sql.codegen.MetaDataSerializer")
public class QAsyncProcessCommand extends com.querydsl.sql.RelationalPathBase<BAsyncProcessCommand> {

    private static final long serialVersionUID = 423478361;

    public static final QAsyncProcessCommand asyncProcessCommand = new QAsyncProcessCommand("ASYNC_PROCESS_COMMAND");

    public final NumberPath<Long> asyncId = createNumber("asyncId", Long.class);

    public final StringPath command = createString("command");

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Integer> lockVersion = createNumber("lockVersion", Integer.class);

    public final DateTimePath<java.time.LocalDateTime> updatedAt = createDateTime("updatedAt", java.time.LocalDateTime.class);

    public final com.querydsl.sql.PrimaryKey<BAsyncProcessCommand> asyncProcessCommandPkc = createPrimaryKey(id);

    public QAsyncProcessCommand(String variable) {
        super(BAsyncProcessCommand.class, forVariable(variable), "PUBLIC", "ASYNC_PROCESS_COMMAND");
        addMetadata();
    }

    public QAsyncProcessCommand(String variable, String schema, String table) {
        super(BAsyncProcessCommand.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QAsyncProcessCommand(Path<? extends BAsyncProcessCommand> path) {
        super(path.getType(), path.getMetadata(), "PUBLIC", "ASYNC_PROCESS_COMMAND");
        addMetadata();
    }

    public QAsyncProcessCommand(PathMetadata metadata) {
        super(BAsyncProcessCommand.class, metadata, "PUBLIC", "ASYNC_PROCESS_COMMAND");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(asyncId, ColumnMetadata.named("ASYNC_ID").withIndex(2).ofType(Types.BIGINT).withSize(19).notNull());
        addMetadata(command, ColumnMetadata.named("COMMAND").withIndex(3).ofType(Types.VARCHAR).withSize(100).notNull());
        addMetadata(createdAt, ColumnMetadata.named("CREATED_AT").withIndex(5).ofType(Types.TIMESTAMP).withSize(23).withDigits(10).notNull());
        addMetadata(id, ColumnMetadata.named("ID").withIndex(1).ofType(Types.BIGINT).withSize(19).notNull());
        addMetadata(lockVersion, ColumnMetadata.named("LOCK_VERSION").withIndex(6).ofType(Types.INTEGER).withSize(10).notNull());
        addMetadata(updatedAt, ColumnMetadata.named("UPDATED_AT").withIndex(4).ofType(Types.TIMESTAMP).withSize(23).withDigits(10).notNull());
    }

}

