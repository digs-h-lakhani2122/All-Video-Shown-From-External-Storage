package coatocl.exaatocl.videoshowfromgallery;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.File;


public class SplashScreen extends AppCompatActivity
{
    private File storage;
    private String []storagePaths;
    int SPLASH_TIME=3000;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        storagePaths = StorageUtil.getStorageDirectories(this);

        for(String path:storagePaths)
        {
            storage = new File(path);
            Method.load_Directory_Files(storage);

        }


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferences sharedPreferences=getSharedPreferences(external_permission.PREF_NAME,MODE_PRIVATE);
                boolean hasLoggedIn=sharedPreferences.getBoolean("hasLoggedIn",false);

                if(hasLoggedIn)
                {
                    Intent intent=new Intent(SplashScreen.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }
                else
                {
                    Intent intent2=new Intent(SplashScreen.this,external_permission.class);
                    startActivity(intent2);
                    finish();
                }

            }
        },SPLASH_TIME);
    }
}
