package com.pbph.shoppingmall.greendao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.pbph.shoppingmall.model.dao.AddressDefault;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "ADDRESS_DEFAULT".
*/
public class AddressDefaultDao extends AbstractDao<AddressDefault, Long> {

    public static final String TABLENAME = "ADDRESS_DEFAULT";

    /**
     * Properties of entity AddressDefault.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property AddSerId = new Property(1, int.class, "addSerId", false, "ADD_SER_ID");
        public final static Property AddName = new Property(2, String.class, "addName", false, "ADD_NAME");
        public final static Property AddPhone = new Property(3, String.class, "addPhone", false, "ADD_PHONE");
        public final static Property Province = new Property(4, String.class, "Province", false, "PROVINCE");
        public final static Property City = new Property(5, String.class, "City", false, "CITY");
        public final static Property District = new Property(6, String.class, "District", false, "DISTRICT");
        public final static Property ProvinceId = new Property(7, int.class, "ProvinceId", false, "PROVINCE_ID");
        public final static Property CityId = new Property(8, int.class, "CityId", false, "CITY_ID");
        public final static Property DistrictId = new Property(9, int.class, "DistrictId", false, "DISTRICT_ID");
        public final static Property AddAddress = new Property(10, String.class, "addAddress", false, "ADD_ADDRESS");
    }


    public AddressDefaultDao(DaoConfig config) {
        super(config);
    }
    
    public AddressDefaultDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"ADDRESS_DEFAULT\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"ADD_SER_ID\" INTEGER NOT NULL ," + // 1: addSerId
                "\"ADD_NAME\" TEXT NOT NULL ," + // 2: addName
                "\"ADD_PHONE\" TEXT NOT NULL ," + // 3: addPhone
                "\"PROVINCE\" TEXT," + // 4: Province
                "\"CITY\" TEXT," + // 5: City
                "\"DISTRICT\" TEXT," + // 6: District
                "\"PROVINCE_ID\" INTEGER NOT NULL ," + // 7: ProvinceId
                "\"CITY_ID\" INTEGER NOT NULL ," + // 8: CityId
                "\"DISTRICT_ID\" INTEGER NOT NULL ," + // 9: DistrictId
                "\"ADD_ADDRESS\" TEXT NOT NULL );"); // 10: addAddress
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"ADDRESS_DEFAULT\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, AddressDefault entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindLong(2, entity.getAddSerId());
        stmt.bindString(3, entity.getAddName());
        stmt.bindString(4, entity.getAddPhone());
 
        String Province = entity.getProvince();
        if (Province != null) {
            stmt.bindString(5, Province);
        }
 
        String City = entity.getCity();
        if (City != null) {
            stmt.bindString(6, City);
        }
 
        String District = entity.getDistrict();
        if (District != null) {
            stmt.bindString(7, District);
        }
        stmt.bindLong(8, entity.getProvinceId());
        stmt.bindLong(9, entity.getCityId());
        stmt.bindLong(10, entity.getDistrictId());
        stmt.bindString(11, entity.getAddAddress());
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, AddressDefault entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindLong(2, entity.getAddSerId());
        stmt.bindString(3, entity.getAddName());
        stmt.bindString(4, entity.getAddPhone());
 
        String Province = entity.getProvince();
        if (Province != null) {
            stmt.bindString(5, Province);
        }
 
        String City = entity.getCity();
        if (City != null) {
            stmt.bindString(6, City);
        }
 
        String District = entity.getDistrict();
        if (District != null) {
            stmt.bindString(7, District);
        }
        stmt.bindLong(8, entity.getProvinceId());
        stmt.bindLong(9, entity.getCityId());
        stmt.bindLong(10, entity.getDistrictId());
        stmt.bindString(11, entity.getAddAddress());
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public AddressDefault readEntity(Cursor cursor, int offset) {
        AddressDefault entity = new AddressDefault( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.getInt(offset + 1), // addSerId
            cursor.getString(offset + 2), // addName
            cursor.getString(offset + 3), // addPhone
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // Province
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // City
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // District
            cursor.getInt(offset + 7), // ProvinceId
            cursor.getInt(offset + 8), // CityId
            cursor.getInt(offset + 9), // DistrictId
            cursor.getString(offset + 10) // addAddress
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, AddressDefault entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setAddSerId(cursor.getInt(offset + 1));
        entity.setAddName(cursor.getString(offset + 2));
        entity.setAddPhone(cursor.getString(offset + 3));
        entity.setProvince(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setCity(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setDistrict(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setProvinceId(cursor.getInt(offset + 7));
        entity.setCityId(cursor.getInt(offset + 8));
        entity.setDistrictId(cursor.getInt(offset + 9));
        entity.setAddAddress(cursor.getString(offset + 10));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(AddressDefault entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(AddressDefault entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(AddressDefault entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}