package coatocl.exaatocl.videoshowfromgallery;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class external_permission extends AppCompatActivity {


    public static final String PREF_NAME ="shared";
    private static final int EXTERNAL_STORAGE_PERMISSION = 29;
    FloatingActionButton floButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_external_permission);

        floButton = findViewById(R.id.floButton);

    }

    public void publicly(View view)
    {

        if(ContextCompat.checkSelfPermission(getApplicationContext(),Manifest.permission.WRITE_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED)
        {
            SharedPreferences sharedPreferences=getSharedPreferences("shared",MODE_PRIVATE);

            SharedPreferences.Editor edit=sharedPreferences.edit();

            edit.putBoolean("hasLoggedIn",true);
            edit.commit();

            Intent intent23 =new Intent(external_permission.this,MainActivity.class);
            startActivity(intent23);
        }
        else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, EXTERNAL_STORAGE_PERMISSION);
        }
    }

}
