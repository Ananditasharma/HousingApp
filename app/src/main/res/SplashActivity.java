
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.util.Log;
import android.util.SparseArray;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Database;

import com.enuke.unicon.Class.DataBaseClass;
import com.enuke.unicon.R;
import com.enuke.unicon.utils.Utils;


public class SplashActivity extends AppCompatActivity {
    private String userId = "", role = "";
    SQLiteDatabase dbase;
    private int toScreen; // 1 - autoLogin , // 0 - manual Login
    private Handler splashHandler;
    private static int SPLASH_TIME = 2000;
    private String TAG = "SplashActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        dbase = (new DataBaseClass(this)).getWritableDatabase();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(SplashActivity.this);
        splashHandler = new Handler();

        try {
            prefs = getSharedPreferences("MYPREF", Context.MODE_PRIVATE);
            String strPrefUserId = prefs.getString("currentUserId", null);
            String strPrefUserRole = prefs.getString("currentUserRole", null);
            if (strPrefUserId != null && strPrefUserRole != null) {
                userId = prefs.getString("currentUserId", null);
                role = prefs.getString("currentUserRole", null);
            }
        }catch (Exception e){
            Log.e(TAG, "userId from shared pref: " +e.getMessage());
        }

        /**
         * splash counter handler
         */
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                Userid = prefs.getInt("userid", 0); // which will return defalut value 0 if u are not login
//                if (Userid > 0) {
//                    startActivity(new Intent(SplashActivity.this, FeedActivity.class));
//                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
//                    finish();
//                } else {
//                    startActivity(new Intent(SplashActivity.this, SocialLoginActivity.class));
//                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
//                    finish();
//                }
//            }

        if(CheckIsDataAlreadyInDBorNot()){
            Log.i("AUTO LOGIN","user available going or auto login");
            toScreen = 1;
            autoLoginProcess();
        }else{
            Log.i("No CURRENT USER","no current user available, going to login screen");
            toScreen =0;
            splashHandler.postDelayed(splashRun, SPLASH_TIME);
        }
    }

    private Runnable splashRun = new Runnable() {
        @Override
        public void run() {
            if(toScreen == 1){
                Intent mySuperIntent = new Intent(SplashActivity.this, FeedActivity.class);
                mySuperIntent.putExtra("FROM",1); // autologin
                startActivity(mySuperIntent);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                finish();
            }else{
//                Intent mySuperIntent = new Intent(SplashActivity.this, SocialLoginActivity.class);
                Intent mySuperIntent = new Intent(SplashActivity.this, FeedActivity.class);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                startActivity(mySuperIntent);
                finish();
            }
        }
    };


    private void autoLoginProcess() {
        Log.i(TAG," Auto-login process verification started");
        checkForUser();
    }


    private void checkForUser(){
        Cursor cr_creator = dbase.rawQuery("SELECT * FROM CreatorUserInfo WHERE currentUserId = '" + userId + "'",null);
        Cursor cr_client = dbase.rawQuery("SELECT * FROM ClientUserInfo WHERE currentUserId='" +userId+ "'",null);
        if(role.equals("creator")){
            if(cr_creator.getCount()>0) {
                Log.i(TAG,"CreatorUserInfo Found in local database: ");
                toScreen = 1;
                splashHandler.postDelayed(splashRun,1000);
            }else{
                // rollback
                Log.i(TAG,"UserInfo NOT Found in local database: ");
                rollBack();
                toScreen = 0;
                splashHandler.postDelayed(splashRun,1000);
            }
        }else{
            if(cr_client.getCount() > 0){
                Log.i(TAG,"ClientUserInfo Found in local database: ");
                toScreen = 1;
                splashHandler.postDelayed(splashRun,1000);
            }else{
                // rollback
                Log.i(TAG,"UserInfo NOT Found in local database: ");
                rollBack();
                toScreen = 0;
                splashHandler.postDelayed(splashRun,1000);
            }
        }
    }

    private void rollBack(){
        dbase.execSQL("DELETE FROM CreatorUserInfo");
        dbase.execSQL("DELETE FROM ClientUserInfo");
    }

    public boolean CheckIsDataAlreadyInDBorNot() {
        Log.i(TAG," userId: " + userId);
        Cursor cursor = dbase.rawQuery("SELECT role FROM CreatorUserInfo WHERE currentUserId = '" + userId + "'", null);
        Cursor cursor2 = dbase.rawQuery("SELECT role FROM ClientUserInfo WHERE currentUserId='" +userId+ "'",null);
        if(role.equals("creator")){
            if(cursor.getCount() <= 0){
                Log.i(TAG," CreatorUserInfo not found.");
                cursor.close();
                return false;
            }
        }else{
            if(cursor2.getCount() <= 0){
                Log.i(TAG," ClientUserInfo not found.");
                cursor2.close();
                return false;
            }
        }
        cursor.close();
        cursor2.close();
        return true;
    }

}