package app;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.example.im.mylovepet.dao.DaoMaster;
import com.example.im.mylovepet.dao.DaoSession;

/**
 * Created by I'M宋国召 on 2018/3/29.
 */

public class MyApplication extends Application {
    private static MyApplication application;
    private DaoSession mDaoSession;

    public static MyApplication getApplication() {
        return application;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
        createData();
    }

    private void createData() {
        DaoMaster.OpenHelper openHelper = new DaoMaster.DevOpenHelper(application, "pet.db");
        SQLiteDatabase sqLiteDatabase = openHelper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(sqLiteDatabase);
        mDaoSession = daoMaster.newSession();
    }

    public DaoSession getDaoSession() {
        return mDaoSession;
    }

}
