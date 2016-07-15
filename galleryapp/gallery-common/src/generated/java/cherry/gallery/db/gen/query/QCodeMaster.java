package cherry.gallery.db.gen.query;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;

import com.querydsl.sql.ColumnMetadata;
import java.sql.Types;




/**
 * QCodeMaster is a Querydsl query type for BCodeMaster
 */
@Generated("com.querydsl.sql.codegen.MetaDataSerializer")
public class QCodeMaster extends com.querydsl.sql.RelationalPathBase<BCodeMaster> {

    private static final long serialVersionUID = 388017558;

    public static final QCodeMaster codeMaster = new QCodeMaster("CODE_MASTER");

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath label = createString("label");

    public final NumberPath<Integer> lockVersion = createNumber("lockVersion", Integer.class);

    public final StringPath name = createString("name");

    public final NumberPath<Integer> sortOrder = createNumber("sortOrder", Integer.class);

    public final DateTimePath<java.time.LocalDateTime> updatedAt = createDateTime("updatedAt", java.time.LocalDateTime.class);

    public final StringPath value = createString("value");

    public final com.querydsl.sql.PrimaryKey<BCodeMaster> codeMasterPkc = createPrimaryKey(id);

    public QCodeMaster(String variable) {
        super(BCodeMaster.class, forVariable(variable), "PUBLIC", "CODE_MASTER");
        addMetadata();
    }

    public QCodeMaster(String variable, String schema, String table) {
        super(BCodeMaster.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QCodeMaster(Path<? extends BCodeMaster> path) {
        super(path.getType(), path.getMetadata(), "PUBLIC", "CODE_MASTER");
        addMetadata();
    }

    public QCodeMaster(PathMetadata metadata) {
        super(BCodeMaster.class, metadata, "PUBLIC", "CODE_MASTER");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(createdAt, ColumnMetadata.named("CREATED_AT").withIndex(7).ofType(Types.TIMESTAMP).withSize(23).withDigits(10).notNull());
        addMetadata(id, ColumnMetadata.named("ID").withIndex(1).ofType(Types.BIGINT).withSize(19).notNull());
        addMetadata(label, ColumnMetadata.named("LABEL").withIndex(4).ofType(Types.VARCHAR).withSize(50).notNull());
        addMetadata(lockVersion, ColumnMetadata.named("LOCK_VERSION").withIndex(8).ofType(Types.INTEGER).withSize(10).notNull());
        addMetadata(name, ColumnMetadata.named("NAME").withIndex(2).ofType(Types.VARCHAR).withSize(30).notNull());
        addMetadata(sortOrder, ColumnMetadata.named("SORT_ORDER").withIndex(5).ofType(Types.INTEGER).withSize(10).notNull());
        addMetadata(updatedAt, ColumnMetadata.named("UPDATED_AT").withIndex(6).ofType(Types.TIMESTAMP).withSize(23).withDigits(10).notNull());
        addMetadata(value, ColumnMetadata.named("VALUE").withIndex(3).ofType(Types.VARCHAR).withSize(5).notNull());
    }

}

