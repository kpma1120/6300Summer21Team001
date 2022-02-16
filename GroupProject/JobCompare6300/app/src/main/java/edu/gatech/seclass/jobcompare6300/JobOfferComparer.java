package edu.gatech.seclass.jobcompare6300;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class JobOfferComparer extends AppCompatActivity {
    private boolean isFromRanking;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.compare_job_offers);
        bindData();
    }

    protected void bindData() {
        DBHandler dbHandler = DBHandler.getDbHandler(this);
        JobDetail currentJob= dbHandler.getCurrentJob();
        JobDetail offeredJob= null;
        TableLayout table = (TableLayout) findViewById(R.id.jobConparisonLayout);

        SettingModel setting= dbHandler.getSettings();
        Intent intent = getIntent();
        String offeredId =intent.getStringExtra("id");
        String id1 =intent.getStringExtra("id1");
        String id2 =intent.getStringExtra("id2");
        String isd1Current =intent.getStringExtra("d1");
        String isd2Current =intent.getStringExtra("d2");

        if (offeredId !=null)
        {

            TableRow row = attachRow("ITEM", "Current", "Offered", true);
            table.addView(row);
            offeredJob= dbHandler.getOfferedJob(offeredId);

        }
        else if (id1!=null && id2!=null)
        {
            isFromRanking=true;
            TableRow row = attachRow("ITEM", "Job1", "Job2", true);
            table.addView(row);
            if(id1.equals("10000"))
            {
                offeredJob= dbHandler.getOfferedJob(id2);
            }
            else if(id2.equals("10000"))
            {
                offeredJob= dbHandler.getOfferedJob(id1);
            }
            else
            {
                currentJob= dbHandler.getOfferedJob(id1);
                offeredJob= dbHandler.getOfferedJob(id2);

            }


        }

     if (offeredJob !=null && currentJob!=null) {
         TableRow row2 = attachRow("Title", currentJob.getTitle(), offeredJob.getTitle(), false);
         TableRow row3 = attachRow("Company", currentJob.getCompany(), offeredJob.getCompany(), false);
         TableRow row4 = attachRow("Location", currentJob.getCity()+ " ,"+currentJob.getState(),
                 offeredJob.getCity()+ " ,"+offeredJob.getState(), false);
         TableRow row5 = attachRow("Adjusted Salary", ""+(currentJob.getAdjustedYearlySalary()), ""+offeredJob.getAdjustedYearlySalary(), false);
         TableRow row6 = attachRow("Adjusted Bonus", ""+currentJob.getAdjustedYearlyBonus(), ""+offeredJob.getAdjustedYearlyBonus(), false);
         TableRow row7 = attachRow("Telework days", ""+currentJob.getTelework(), ""+offeredJob.getTelework(), false);
         TableRow row8 = attachRow("Leave", ""+currentJob.getLeave(), ""+offeredJob.getLeave(), false);
         TableRow row9 = attachRow("Share", ""+currentJob.getShare(), ""+offeredJob.getLeave(), false);
         table.addView(row2);
         table.addView(row3);
         table.addView(row4);
         table.addView(row5);
         table.addView(row6);
         table.addView(row7);
         table.addView(row8);
         table.addView(row9);
     }
    }

    private TableRow attachRow(String col1, String col2, String col3, boolean isHeader)
    {
        TableRow row = new TableRow(this);
        TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
        row.setLayoutParams(lp);

        TextView tvid1 = new TextView(this);
        TextView tvid2 = new TextView(this);
        TextView tvid3 = new TextView(this);

        tvid1.setText(col1);
        tvid2.setText(col2);
        tvid3.setText(col3);
        if (isHeader)
        {

            tvid1.setTypeface(null, Typeface.BOLD);
            tvid2.setTypeface(null, Typeface.BOLD);
            tvid3.setTypeface(null, Typeface.BOLD);
        }
        row.addView(tvid1);
        row.addView(tvid2);
        row.addView(tvid3);
        return row;

    }

    public void returnFromComparisonToMain(View view)
    {
        startActivity(new Intent(JobOfferComparer.this, Application.class));
    }
    public void returnToOffer(View view)
    {
        if(isFromRanking)
        startActivity(new Intent(JobOfferComparer.this, JobRanker.class));
        else
            startActivity(new Intent(JobOfferComparer.this, JobOfferEditor.class));

    }
}
