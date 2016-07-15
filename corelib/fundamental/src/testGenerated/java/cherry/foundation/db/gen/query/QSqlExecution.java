package cherry.foundation.db.gen.query;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;

import com.querydsl.sql.ColumnMetadata;
import java.sql.Types;




/**
 * QSqlExecution is a Querydsl query type for BSqlExecution
 */
@Generated("com.querydsl.sql.codegen.MetaDataSerializer")
public class QSqlExecution extends com.querydsl.sql.RelationalPathBase<BSqlExecution> {

    private static final long serialVersionUID = -1961419746;

    public static final QSqlExecution sqlExecution = new QSqlExecution("SQL_EXECUTION");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath login = createString("login");

    public final StringPath name = createString("name");

    public final com.querydsl.sql.PrimaryKey<BSqlExecution> sqlExecutionPkc = createPrimaryKey(id);

    public QSqlExecution(String variable) {
        super(BSqlExecution.class, forVariable(variable), "PUBLIC", "SQL_EXECUTION");
        addMetadata();
    }

    public QSqlExecution(String variable, String schema, String table) {
        super(BSqlExecution.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QSqlExecution(Path<? extends BSqlExecution> path) {
        super(path.getType(), path.getMetadata(), "PUBLIC", "SQL_EXECUTION");
        addMetadata();
    }

    public QSqlExecution(PathMetadata metadata) {
        super(BSqlExecution.class, metadata, "PUBLIC", "SQL_EXECUTION");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(id, ColumnMetadata.named("ID").withIndex(1).ofType(Types.BIGINT).withSize(19).notNull());
        addMetadata(login, ColumnMetadata.named("LOGIN").withIndex(2).ofType(Types.VARCHAR).withSize(32));
        addMetadata(name, ColumnMetadata.named("NAME").withIndex(3).ofType(Types.VARCHAR).withSize(32));
    }

}

