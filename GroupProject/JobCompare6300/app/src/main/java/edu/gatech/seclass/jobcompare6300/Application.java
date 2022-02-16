package edu.gatech.seclass.jobcompare6300;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.List;

public class Application extends AppCompatActivity {
        private Button currentJob;
        private Button newJobOffer;
        private Button settings;
        private Button compare;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            currentJob= (Button) findViewById(R.id.bCurrentJob);
            currentJob.setOnClickListener(new View.OnClickListener(){
                public void onClick(View v){
                    startActivity(new Intent(Application.this, CurrentJobEditor.class));
                }
            });


            newJobOffer= (Button) findViewById(R.id.bNewJobOffers);
            newJobOffer.setOnClickListener(new View.OnClickListener(){
                public void onClick(View v){
                    startActivity(new Intent(Application.this, JobOfferEditor.class));
                }
            });


            settings= (Button) findViewById(R.id.bSettings);
            settings.setOnClickListener(new View.OnClickListener(){
                public void onClick(View v){
                    startActivity(new Intent(Application.this, SettingsEditor.class));
                }
            });

            compare= (Button) findViewById(R.id.bCompareJobs);
            compare.setOnClickListener(new View.OnClickListener(){
                public void onClick(View v){
                    startActivity(new Intent(Application.this, JobRanker.class));
                }
            });
            List<JobDetail> offeredJobs= DBHandler.getDbHandler(this).getOfferedJobs();
            if (offeredJobs.isEmpty())
            {
                compare.setEnabled(false);
            }
            else
            {
                compare.setEnabled(true);
            }

        }

}