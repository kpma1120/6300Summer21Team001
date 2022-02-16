package edu.gatech.seclass.jobcompare6300;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


public class JobRanker extends AppCompatActivity implements View.OnClickListener {
    private List<JobDetail> offeredAndCurrentjobs= new ArrayList();
    private Button compareButton;
    private TextView selectedJobs;


    private JobDetail selectedOne=null;
    private JobDetail selectedTwo=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.job_offers_rank);
        compareButton= (Button) findViewById(R.id.bcomparejoboffer);
        selectedJobs= (TextView) findViewById(R.id.selectedJobView);

        compareButton.setEnabled(false);
        displayRankedJobs();
    }

    @Override
    public void onClick(View v) {
        int id=   v.getId();
        if (selectedOne==null)
        {
            selectedOne= offeredAndCurrentjobs.stream().filter(a ->a.getId().equals(String.valueOf(id))).findFirst().get();
            selectedJobs.setText("Selected For Comparison: " +selectedOne.getCompany());

        }
        else if (selectedTwo ==null && !selectedOne.getId().equals(String.valueOf(id)))
        {
            selectedTwo= offeredAndCurrentjobs.stream().filter(a ->a.getId().equals(String.valueOf(id))).findFirst().get();
            selectedJobs.setText("Selected For Comparison: "+selectedOne.getCompany() + "  and " + selectedTwo.getCompany());

        }
        if(selectedOne!=null && selectedTwo!=null )
        {
            compareButton.setEnabled(true);
        }

        }

    protected List<JobDetail> jobRanker()
    {
        offeredAndCurrentjobs= new ArrayList<>();
        DBHandler dbHandler= DBHandler.getDbHandler(this);
        offeredAndCurrentjobs.addAll(dbHandler.getOfferedJobs());

        JobDetail cjd=dbHandler.getCurrentJob();
        if(cjd!=null) {
            cjd.setId("" + 10000);
            offeredAndCurrentjobs.add(cjd);
        }
        SettingModel setting= dbHandler.getSettings();

        for (JobDetail jd:  offeredAndCurrentjobs)
        {

            jd.calculateScore(setting.getBonus(), setting.getLeave(), setting.getSalary(),
                    setting.getShare(), setting.getTelework());

        }
        Comparator<JobDetail> comparator = Comparator.comparing(JobDetail::getScore);
        offeredAndCurrentjobs.sort(comparator.reversed());

        return offeredAndCurrentjobs;
    }

    protected void displayRankedJobs() {

        TableLayout table = (TableLayout) findViewById(R.id.jobofferRankLayout);
        TableRow row = attachRow("Title", "Company", "Score", true, "7777");
        table.addView(row);

        for (JobDetail jd:  jobRanker())
        {
            TableRow tr=null;
            if(jd.getId().equals("10000"))
            {
                tr= attachRow(jd.getTitle()+(" (Current)"), jd.getCompany(), ""+jd.getScore(), false, jd.getId());
                tr.setBackgroundColor(Color.GREEN);
            }
            else {
                tr = attachRow(jd.getTitle(), jd.getCompany(), "" + jd.getScore(), false, jd.getId());
            }
            table.addView(tr);
        }

    }

    private TableRow attachRow(String col1, String col2, String col3, boolean isHeader, String id)
    {
        TableRow row = new TableRow(this);
        TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
        row.setLayoutParams(lp);

        TextView tvid1 = new TextView(this);
        TextView tvid2 = new TextView(this);
        TextView tvid3 = new TextView(this);
        Button tvid4 = new Button(this);


        tvid1.setText(col1);
        tvid2.setText(col2);
        tvid3.setText(col3);
        tvid4.setText("Select");
        tvid4.setId(Integer.parseInt(id));
        tvid4.setOnClickListener(this);

        if (isHeader)
        {

            tvid1.setTypeface(null, Typeface.BOLD);
            tvid2.setTypeface(null, Typeface.BOLD);
            tvid3.setTypeface(null, Typeface.BOLD);
        }
        row.addView(tvid1);
        row.addView(tvid2);
        row.addView(tvid3);
        if(!isHeader){ row.addView(tvid4); }
        return row;

    }

    public void compareJobOffers(View view)
    {
        if (selectedOne!=null &&  selectedTwo!=null)
        {
            String value=selectedOne.getId();
            Intent intent = new Intent(JobRanker.this, JobOfferComparer.class);
            intent.putExtra("id1",value);
            intent.putExtra("id2",selectedTwo.getId());
            startActivity(intent);

        }

    }

    public void toMainMenu(View view)
    {
        startActivity(new Intent(JobRanker.this, Application.class));
    }
}

