package com.example.im.mylovepet.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import bean.MyDbBean;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "MY_DB_BEAN".
*/
public class MyDbBeanDao extends AbstractDao<MyDbBean, Long> {

    public static final String TABLENAME = "MY_DB_BEAN";

    /**
     * Properties of entity MyDbBean.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Image = new Property(1, String.class, "image", false, "IMAGE");
        public final static Property Name = new Property(2, String.class, "name", false, "NAME");
        public final static Property Type = new Property(3, String.class, "type", false, "TYPE");
        public final static Property Sterilization = new Property(4, String.class, "sterilization", false, "STERILIZATION");
        public final static Property Shengri = new Property(5, String.class, "shengri", false, "SHENGRI");
        public final static Property Kg = new Property(6, String.class, "kg", false, "KG");
        public final static Property Immune = new Property(7, String.class, "immune", false, "IMMUNE");
        public final static Property Intro = new Property(8, String.class, "intro", false, "INTRO");
    }


    public MyDbBeanDao(DaoConfig config) {
        super(config);
    }
    
    public MyDbBeanDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"MY_DB_BEAN\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"IMAGE\" TEXT," + // 1: image
                "\"NAME\" TEXT," + // 2: name
                "\"TYPE\" TEXT," + // 3: type
                "\"STERILIZATION\" TEXT," + // 4: sterilization
                "\"SHENGRI\" TEXT," + // 5: shengri
                "\"KG\" TEXT," + // 6: kg
                "\"IMMUNE\" TEXT," + // 7: immune
                "\"INTRO\" TEXT);"); // 8: intro
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"MY_DB_BEAN\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, MyDbBean entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String image = entity.getImage();
        if (image != null) {
            stmt.bindString(2, image);
        }
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(3, name);
        }
 
        String type = entity.getType();
        if (type != null) {
            stmt.bindString(4, type);
        }
 
        String sterilization = entity.getSterilization();
        if (sterilization != null) {
            stmt.bindString(5, sterilization);
        }
 
        String shengri = entity.getShengri();
        if (shengri != null) {
            stmt.bindString(6, shengri);
        }
 
        String kg = entity.getKg();
        if (kg != null) {
            stmt.bindString(7, kg);
        }
 
        String immune = entity.getImmune();
        if (immune != null) {
            stmt.bindString(8, immune);
        }
 
        String intro = entity.getIntro();
        if (intro != null) {
            stmt.bindString(9, intro);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, MyDbBean entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String image = entity.getImage();
        if (image != null) {
            stmt.bindString(2, image);
        }
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(3, name);
        }
 
        String type = entity.getType();
        if (type != null) {
            stmt.bindString(4, type);
        }
 
        String sterilization = entity.getSterilization();
        if (sterilization != null) {
            stmt.bindString(5, sterilization);
        }
 
        String shengri = entity.getShengri();
        if (shengri != null) {
            stmt.bindString(6, shengri);
        }
 
        String kg = entity.getKg();
        if (kg != null) {
            stmt.bindString(7, kg);
        }
 
        String immune = entity.getImmune();
        if (immune != null) {
            stmt.bindString(8, immune);
        }
 
        String intro = entity.getIntro();
        if (intro != null) {
            stmt.bindString(9, intro);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public MyDbBean readEntity(Cursor cursor, int offset) {
        MyDbBean entity = new MyDbBean( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // image
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // name
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // type
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // sterilization
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // shengri
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // kg
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // immune
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8) // intro
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, MyDbBean entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setImage(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setName(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setType(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setSterilization(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setShengri(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setKg(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setImmune(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setIntro(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(MyDbBean entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(MyDbBean entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(MyDbBean entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
