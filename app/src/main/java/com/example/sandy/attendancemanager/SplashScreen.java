package com.example.sandy.attendancemanager;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.VideoView;

import com.felipecsl.gifimageview.library.GifImageView;

import org.apache.commons.io.IOExceptionWithCause;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;

public class SplashScreen extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle sa)
    {
        super.onCreate(sa);
        try
        {
            VideoView videoView = new VideoView(this);
            setContentView(videoView);
            Uri path = Uri.parse("android.resource://" + getPackageName()+"/"+R.raw.load );
            videoView.setVideoURI(path);
            videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    jump();
                }
            });
               videoView.start();
        }catch (Exception e)
        {
    jump();
        }

    }
    private void jump()
    {
        if(isFinishing())
                return;
        startActivity(new Intent(this,MainActivity.class));
        finish();

    }
}
