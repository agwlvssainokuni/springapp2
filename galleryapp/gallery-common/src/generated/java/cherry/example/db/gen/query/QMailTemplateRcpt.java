package cherry.example.db.gen.query;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;

import com.querydsl.sql.ColumnMetadata;
import java.sql.Types;




/**
 * QMailTemplateRcpt is a Querydsl query type for BMailTemplateRcpt
 */
@Generated("com.querydsl.sql.codegen.MetaDataSerializer")
public class QMailTemplateRcpt extends com.querydsl.sql.RelationalPathBase<BMailTemplateRcpt> {

    private static final long serialVersionUID = 1188629645;

    public static final QMailTemplateRcpt mailTemplateRcpt = new QMailTemplateRcpt("MAIL_TEMPLATE_RCPT");

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Integer> lockVersion = createNumber("lockVersion", Integer.class);

    public final StringPath rcptAddr = createString("rcptAddr");

    public final StringPath rcptType = createString("rcptType");

    public final NumberPath<Long> templateId = createNumber("templateId", Long.class);

    public final DateTimePath<java.time.LocalDateTime> updatedAt = createDateTime("updatedAt", java.time.LocalDateTime.class);

    public final com.querydsl.sql.PrimaryKey<BMailTemplateRcpt> mailTemplateRcptPkc = createPrimaryKey(id);

    public QMailTemplateRcpt(String variable) {
        super(BMailTemplateRcpt.class, forVariable(variable), "PUBLIC", "MAIL_TEMPLATE_RCPT");
        addMetadata();
    }

    public QMailTemplateRcpt(String variable, String schema, String table) {
        super(BMailTemplateRcpt.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QMailTemplateRcpt(Path<? extends BMailTemplateRcpt> path) {
        super(path.getType(), path.getMetadata(), "PUBLIC", "MAIL_TEMPLATE_RCPT");
        addMetadata();
    }

    public QMailTemplateRcpt(PathMetadata metadata) {
        super(BMailTemplateRcpt.class, metadata, "PUBLIC", "MAIL_TEMPLATE_RCPT");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(createdAt, ColumnMetadata.named("CREATED_AT").withIndex(6).ofType(Types.TIMESTAMP).withSize(23).withDigits(10).notNull());
        addMetadata(id, ColumnMetadata.named("ID").withIndex(1).ofType(Types.BIGINT).withSize(19).notNull());
        addMetadata(lockVersion, ColumnMetadata.named("LOCK_VERSION").withIndex(7).ofType(Types.INTEGER).withSize(10).notNull());
        addMetadata(rcptAddr, ColumnMetadata.named("RCPT_ADDR").withIndex(4).ofType(Types.VARCHAR).withSize(300).notNull());
        addMetadata(rcptType, ColumnMetadata.named("RCPT_TYPE").withIndex(3).ofType(Types.VARCHAR).withSize(3).notNull());
        addMetadata(templateId, ColumnMetadata.named("TEMPLATE_ID").withIndex(2).ofType(Types.BIGINT).withSize(19).notNull());
        addMetadata(updatedAt, ColumnMetadata.named("UPDATED_AT").withIndex(5).ofType(Types.TIMESTAMP).withSize(23).withDigits(10).notNull());
    }

}

