package com.pbph.shoppingmall.greendao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.pbph.shoppingmall.model.dao.CouponSearchRecord;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "COUPON_SEARCH_RECORD".
*/
public class CouponSearchRecordDao extends AbstractDao<CouponSearchRecord, Long> {

    public static final String TABLENAME = "COUPON_SEARCH_RECORD";

    /**
     * Properties of entity CouponSearchRecord.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property SearchText = new Property(1, String.class, "searchText", false, "SEARCH_TEXT");
        public final static Property CreateTime = new Property(2, java.util.Date.class, "createTime", false, "CREATE_TIME");
    }


    public CouponSearchRecordDao(DaoConfig config) {
        super(config);
    }
    
    public CouponSearchRecordDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"COUPON_SEARCH_RECORD\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"SEARCH_TEXT\" TEXT NOT NULL ," + // 1: searchText
                "\"CREATE_TIME\" INTEGER NOT NULL );"); // 2: createTime
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"COUPON_SEARCH_RECORD\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, CouponSearchRecord entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindString(2, entity.getSearchText());
        stmt.bindLong(3, entity.getCreateTime().getTime());
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, CouponSearchRecord entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindString(2, entity.getSearchText());
        stmt.bindLong(3, entity.getCreateTime().getTime());
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public CouponSearchRecord readEntity(Cursor cursor, int offset) {
        CouponSearchRecord entity = new CouponSearchRecord( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.getString(offset + 1), // searchText
            new java.util.Date(cursor.getLong(offset + 2)) // createTime
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, CouponSearchRecord entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setSearchText(cursor.getString(offset + 1));
        entity.setCreateTime(new java.util.Date(cursor.getLong(offset + 2)));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(CouponSearchRecord entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(CouponSearchRecord entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(CouponSearchRecord entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
