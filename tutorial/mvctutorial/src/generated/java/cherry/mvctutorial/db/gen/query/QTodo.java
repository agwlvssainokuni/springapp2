package cherry.mvctutorial.db.gen.query;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;

import com.querydsl.sql.ColumnMetadata;
import java.sql.Types;




/**
 * QTodo is a Querydsl query type for BTodo
 */
@Generated("com.querydsl.sql.codegen.MetaDataSerializer")
public class QTodo extends com.querydsl.sql.RelationalPathBase<BTodo> {

    private static final long serialVersionUID = -1415166113;

    public static final QTodo todo = new QTodo("TODO");

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final StringPath description = createString("description");

    public final DateTimePath<java.time.LocalDateTime> doneAt = createDateTime("doneAt", java.time.LocalDateTime.class);

    public final NumberPath<Integer> doneFlg = createNumber("doneFlg", Integer.class);

    public final DatePath<java.time.LocalDate> dueDate = createDate("dueDate", java.time.LocalDate.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final NumberPath<Integer> lockVersion = createNumber("lockVersion", Integer.class);

    public final DateTimePath<java.time.LocalDateTime> postedAt = createDateTime("postedAt", java.time.LocalDateTime.class);

    public final StringPath postedBy = createString("postedBy");

    public final DateTimePath<java.time.LocalDateTime> updatedAt = createDateTime("updatedAt", java.time.LocalDateTime.class);

    public final com.querydsl.sql.PrimaryKey<BTodo> todoPkc = createPrimaryKey(id);

    public QTodo(String variable) {
        super(BTodo.class, forVariable(variable), "PUBLIC", "TODO");
        addMetadata();
    }

    public QTodo(String variable, String schema, String table) {
        super(BTodo.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QTodo(Path<? extends BTodo> path) {
        super(path.getType(), path.getMetadata(), "PUBLIC", "TODO");
        addMetadata();
    }

    public QTodo(PathMetadata metadata) {
        super(BTodo.class, metadata, "PUBLIC", "TODO");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(createdAt, ColumnMetadata.named("CREATED_AT").withIndex(9).ofType(Types.TIMESTAMP).withSize(23).withDigits(10).notNull());
        addMetadata(description, ColumnMetadata.named("DESCRIPTION").withIndex(7).ofType(Types.VARCHAR).withSize(5000).notNull());
        addMetadata(doneAt, ColumnMetadata.named("DONE_AT").withIndex(5).ofType(Types.TIMESTAMP).withSize(23).withDigits(10));
        addMetadata(doneFlg, ColumnMetadata.named("DONE_FLG").withIndex(6).ofType(Types.INTEGER).withSize(10).notNull());
        addMetadata(dueDate, ColumnMetadata.named("DUE_DATE").withIndex(4).ofType(Types.DATE).withSize(8).notNull());
        addMetadata(id, ColumnMetadata.named("ID").withIndex(1).ofType(Types.INTEGER).withSize(10).notNull());
        addMetadata(lockVersion, ColumnMetadata.named("LOCK_VERSION").withIndex(10).ofType(Types.INTEGER).withSize(10).notNull());
        addMetadata(postedAt, ColumnMetadata.named("POSTED_AT").withIndex(3).ofType(Types.TIMESTAMP).withSize(23).withDigits(10).notNull());
        addMetadata(postedBy, ColumnMetadata.named("POSTED_BY").withIndex(2).ofType(Types.VARCHAR).withSize(64).notNull());
        addMetadata(updatedAt, ColumnMetadata.named("UPDATED_AT").withIndex(8).ofType(Types.TIMESTAMP).withSize(23).withDigits(10).notNull());
    }

}

