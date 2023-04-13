package coatocl.exaatocl.videoshowfromgallery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.VideoView;

public class SeekBar extends AppCompatActivity {

    VideoView videoView;
    Button forward,backward,pause,resume;
    android.widget.SeekBar seek;
    TextView s_start,s_end;
    int duration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seekbar);

        videoView = findViewById(R.id.videoView);
        forward = findViewById(R.id.forward);
        backward = findViewById(R.id.backward);
        pause = findViewById(R.id.pause);
        resume = findViewById(R.id.resume);
        seek = findViewById(R.id.seek);
        s_start = findViewById(R.id.s_start);
        s_end = findViewById(R.id.s_end);

        seek.setMax(videoView.getDuration());
//        s_end.setText(seek.getMax());

        Handler mseekbarHandler =new Handler();
        Runnable updateSeekbar =new Runnable() {
            @Override
            public void run() {
                seek.setProgress(videoView.getCurrentPosition());
                mseekbarHandler.postDelayed(this,50);
            }
        };

        duration=videoView.getDuration();

        Bundle bundle=getIntent().getExtras();
        if(bundle!=null)
        {
            Uri uri = Uri.parse(bundle.getString("uri"));
            videoView.setVideoURI(uri);
//            videoView.seekTo(5000);
            //call hndler
            mseekbarHandler.postDelayed(updateSeekbar,100);

            videoView.start();
        }

        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mseekbarHandler.removeCallbacks(updateSeekbar);
                    videoView.pause();
            }
        });

        resume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                videoView.start();
            }
        });

//        forward.setOnClickListener(new View.OnClickListener()
//        {
//            @Override
//            public void onClick(View v)
//            {
//                videoView.canSeekForward();
//            }
//        });
//
//        backward.setOnClickListener(new View.OnClickListener()
//        {
//            @Override
//            public void onClick(View v)
//            {
//                videoView.canSeekBackward();
//            }
//        });


        seek.setOnSeekBarChangeListener(new android.widget.SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(android.widget.SeekBar seekBar, int progress, boolean fromUser) {
                if(fromUser)
                {
                    videoView.seekTo(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(android.widget.SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(android.widget.SeekBar seekBar) {

            }
        });
    }
}
