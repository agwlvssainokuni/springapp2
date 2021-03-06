package cherry.gallery.db.gen.query;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;

import com.querydsl.sql.ColumnMetadata;
import java.sql.Types;




/**
 * QAsyncProcessFileArg is a Querydsl query type for BAsyncProcessFileArg
 */
@Generated("com.querydsl.sql.codegen.MetaDataSerializer")
public class QAsyncProcessFileArg extends com.querydsl.sql.RelationalPathBase<BAsyncProcessFileArg> {

    private static final long serialVersionUID = -1381945272;

    public static final QAsyncProcessFileArg asyncProcessFileArg = new QAsyncProcessFileArg("ASYNC_PROCESS_FILE_ARG");

    public final StringPath argument = createString("argument");

    public final NumberPath<Long> asyncId = createNumber("asyncId", Long.class);

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Integer> lockVersion = createNumber("lockVersion", Integer.class);

    public final DateTimePath<java.time.LocalDateTime> updatedAt = createDateTime("updatedAt", java.time.LocalDateTime.class);

    public final com.querydsl.sql.PrimaryKey<BAsyncProcessFileArg> asyncProcessFileArgPkc = createPrimaryKey(id);

    public QAsyncProcessFileArg(String variable) {
        super(BAsyncProcessFileArg.class, forVariable(variable), "PUBLIC", "ASYNC_PROCESS_FILE_ARG");
        addMetadata();
    }

    public QAsyncProcessFileArg(String variable, String schema, String table) {
        super(BAsyncProcessFileArg.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QAsyncProcessFileArg(Path<? extends BAsyncProcessFileArg> path) {
        super(path.getType(), path.getMetadata(), "PUBLIC", "ASYNC_PROCESS_FILE_ARG");
        addMetadata();
    }

    public QAsyncProcessFileArg(PathMetadata metadata) {
        super(BAsyncProcessFileArg.class, metadata, "PUBLIC", "ASYNC_PROCESS_FILE_ARG");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(argument, ColumnMetadata.named("ARGUMENT").withIndex(3).ofType(Types.VARCHAR).withSize(100).notNull());
        addMetadata(asyncId, ColumnMetadata.named("ASYNC_ID").withIndex(2).ofType(Types.BIGINT).withSize(19).notNull());
        addMetadata(createdAt, ColumnMetadata.named("CREATED_AT").withIndex(5).ofType(Types.TIMESTAMP).withSize(23).withDigits(10).notNull());
        addMetadata(id, ColumnMetadata.named("ID").withIndex(1).ofType(Types.BIGINT).withSize(19).notNull());
        addMetadata(lockVersion, ColumnMetadata.named("LOCK_VERSION").withIndex(6).ofType(Types.INTEGER).withSize(10).notNull());
        addMetadata(updatedAt, ColumnMetadata.named("UPDATED_AT").withIndex(4).ofType(Types.TIMESTAMP).withSize(23).withDigits(10).notNull());
    }

}

