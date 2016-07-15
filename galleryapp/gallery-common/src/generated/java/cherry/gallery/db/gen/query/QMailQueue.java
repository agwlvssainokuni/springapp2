package cherry.gallery.db.gen.query;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;

import com.querydsl.sql.ColumnMetadata;
import java.sql.Types;




/**
 * QMailQueue is a Querydsl query type for BMailQueue
 */
@Generated("com.querydsl.sql.codegen.MetaDataSerializer")
public class QMailQueue extends com.querydsl.sql.RelationalPathBase<BMailQueue> {

    private static final long serialVersionUID = 1112626643;

    public static final QMailQueue mailQueue = new QMailQueue("MAIL_QUEUE");

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Integer> lockVersion = createNumber("lockVersion", Integer.class);

    public final NumberPath<Long> mailId = createNumber("mailId", Long.class);

    public final DateTimePath<java.time.LocalDateTime> scheduledAt = createDateTime("scheduledAt", java.time.LocalDateTime.class);

    public final DateTimePath<java.time.LocalDateTime> updatedAt = createDateTime("updatedAt", java.time.LocalDateTime.class);

    public final com.querydsl.sql.PrimaryKey<BMailQueue> mailQueuePkc = createPrimaryKey(id);

    public QMailQueue(String variable) {
        super(BMailQueue.class, forVariable(variable), "PUBLIC", "MAIL_QUEUE");
        addMetadata();
    }

    public QMailQueue(String variable, String schema, String table) {
        super(BMailQueue.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QMailQueue(Path<? extends BMailQueue> path) {
        super(path.getType(), path.getMetadata(), "PUBLIC", "MAIL_QUEUE");
        addMetadata();
    }

    public QMailQueue(PathMetadata metadata) {
        super(BMailQueue.class, metadata, "PUBLIC", "MAIL_QUEUE");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(createdAt, ColumnMetadata.named("CREATED_AT").withIndex(5).ofType(Types.TIMESTAMP).withSize(23).withDigits(10).notNull());
        addMetadata(id, ColumnMetadata.named("ID").withIndex(1).ofType(Types.BIGINT).withSize(19).notNull());
        addMetadata(lockVersion, ColumnMetadata.named("LOCK_VERSION").withIndex(6).ofType(Types.INTEGER).withSize(10).notNull());
        addMetadata(mailId, ColumnMetadata.named("MAIL_ID").withIndex(2).ofType(Types.BIGINT).withSize(19).notNull());
        addMetadata(scheduledAt, ColumnMetadata.named("SCHEDULED_AT").withIndex(3).ofType(Types.TIMESTAMP).withSize(23).withDigits(10).notNull());
        addMetadata(updatedAt, ColumnMetadata.named("UPDATED_AT").withIndex(4).ofType(Types.TIMESTAMP).withSize(23).withDigits(10).notNull());
    }

}
