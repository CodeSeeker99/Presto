package com.gmail.rishabh29b.project_sdpd;

import android.content.Intent;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

//import javax.sound.sampled.UnsupportedAudioFileException;
//import org.junit.Test;

import be.tarsos.dsp.AudioDispatcher;
import be.tarsos.dsp.AudioEvent;
import be.tarsos.dsp.AudioProcessor;
import be.tarsos.dsp.io.TarsosDSPAudioFormat;
import be.tarsos.dsp.io.UniversalAudioInputStream;
import be.tarsos.dsp.io.android.AndroidFFMPEGLocator;
import be.tarsos.dsp.io.android.AudioDispatcherFactory;
import be.tarsos.dsp.mfcc.MFCC;

public class Genre extends AppCompatActivity {

    TextView resultTextView;

    ArrayList<File> song;
    String songName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_genre);

        resultTextView = (TextView) findViewById(R.id.textViewResult);

        Intent i = getIntent();
        if(i == null) {
            resultTextView.setText("Error");
            Toast.makeText(this,"Something went Wrong",Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, MainPage.class));
        }
        else
            resultTextView.setText("...");

        //processing
        Bundle bundle = i.getExtras();
        song = (ArrayList)  bundle.getParcelableArrayList("Song");
        songName = i.getStringExtra("songN");
        int pos = i.getIntExtra("pos",0);

        File songFile = song.get(pos);

        onMFCC(songFile);

    }

    private void onMFCC(final File songFile) {

        int sampleRate = 44100;
        int bufferSize = 1024;
        int bufferOverlap = 128;
        float[] mfccArr;
        //final AudioDispatcher dispatcher = AudioDispatcherFactory.fromDefaultMicrophone(22050,1024,512);
        try{
            InputStream inStream = new FileInputStream(songFile);
            new AndroidFFMPEGLocator(this);
            final AudioDispatcher dispatcher = new AudioDispatcher(new UniversalAudioInputStream(inStream, new TarsosDSPAudioFormat(sampleRate, bufferSize, 1, true, true)), bufferSize, bufferOverlap);
            final MFCC mfcc = new MFCC(bufferSize, sampleRate, 12, 50, 300, 3000);
            dispatcher.addAudioProcessor(mfcc);
            dispatcher.addAudioProcessor(new AudioProcessor() {

                @Override
                public void processingFinished() {

                    if(mfcc == null)
                        return;
                    float[] mfccArr = mfcc.getMFCC();
                    System.out.println("DONE");
                    System.out.println(mfccArr[0]);
                    String s = String.valueOf(mfccArr[0]);
                    Toast.makeText(getApplicationContext(),s,Toast.LENGTH_SHORT).show();
                    //send data to cloud here

                    Uri uri = Uri.parse(songFile.toString());
                    MediaMetadataRetriever mmr = new MediaMetadataRetriever();
                    mmr.setDataSource(getApplicationContext(),uri);
                    String durationStr = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION);
                    int millSecond = Integer.parseInt(durationStr);
                    Toast.makeText(getApplicationContext(),"Duration : "+String.valueOf(millSecond),Toast.LENGTH_SHORT).show();
                }

                @Override
                public boolean process(AudioEvent audioEvent) {
                    return true;
                }
            });
            dispatcher.run();

        }catch (FileNotFoundException e){
            Log.d("Error","FileNotFound");
        }


    }
}





