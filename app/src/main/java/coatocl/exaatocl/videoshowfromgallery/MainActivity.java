package coatocl.exaatocl.videoshowfromgallery;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements  RecyclerViewInterface{

    RecyclerView recycler;
    Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recycler = findViewById(R.id.recycler);

        recycler.setLayoutManager(new LinearLayoutManager(this));
        adapter =new Adapter(this,this);
        recycler.setAdapter(adapter);
    }

    @Override
    public void onItemClick(int position)
    {

        Intent intentFinal = new Intent(MainActivity.this, SeekBar.class);
        Uri uri=Uri.fromFile(Constant.allMediaList.get(position));
        intentFinal.putExtra("uri",uri.toString());
        startActivity(intentFinal);

    }
}