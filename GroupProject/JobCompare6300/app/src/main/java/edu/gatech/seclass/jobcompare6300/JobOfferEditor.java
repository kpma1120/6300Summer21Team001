package edu.gatech.seclass.jobcompare6300;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class JobOfferEditor extends AppCompatActivity {
    private EditText title;
    private EditText company;
    private EditText city;
    private EditText state;
    private EditText costOfLiving;
    private EditText salary;
    private EditText bonus;
    private EditText telework;
    private EditText leave;
    private EditText share;
    private Button compareButton;
    private JobDetail currentJobDetails;
    private JobDetail offeredJobDetails;
    private  boolean savingEroor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.job_offer);
        compareButton= (Button)  findViewById(R.id.compareOfferWithCurrent);
        compareButton.setEnabled(false);

        title   = (EditText)findViewById(R.id.offeredJobTitle);
        company   = (EditText)findViewById(R.id.offeredJobCompany);
        city  = (EditText)findViewById(R.id.offeredJobCity);
        state  = (EditText)findViewById(R.id.offeredJobState);
        costOfLiving   = (EditText)findViewById(R.id.offeredJobCostOfLiving);
        salary  = (EditText)findViewById(R.id.offeredJobSalary);
        bonus   = (EditText)findViewById(R.id.offeredJobAnualBonus);
        telework  = (EditText)findViewById(R.id.offeredJobTeleworkDays);
        share  = (EditText)findViewById(R.id.offeredJobShares);
        leave  = (EditText)findViewById(R.id.offeredJobVacationdays);
            }

    public void saveJobDetails(View view) {
        offeredJobDetails = new JobDetail();
        savingEroor=false;

        validator(title, "Title");
        validator(company, "Company");
        validator(city, "City");
        validator(state, "State");
        validator(costOfLiving, "Cost Of Living index");
        validator(salary, "Salary");
        validator(bonus, "Bonus");
        validator(telework, "Telework");
        validator(leave, "Leave");
        validator(share, "Share");

        if (!savingEroor) {
            minMaxvalidator(costOfLiving, 0, 1000);
            minMaxvalidator(telework, 0, 7);
            minMaxvalidator(salary, 0, Integer.MAX_VALUE);
            minMaxvalidator(leave, 0, 365);
            minMaxvalidator(share, 0, Integer.MAX_VALUE);
        }

        if (!savingEroor) {
        offeredJobDetails.setTitle(String.valueOf(title.getText()));
        offeredJobDetails.setCompany(String.valueOf(company.getText()));
        offeredJobDetails.setCity(String.valueOf(city.getText()));
        offeredJobDetails.setState(String.valueOf(state.getText()));
        offeredJobDetails.setCostOfLiving(Integer.parseInt(String.valueOf(costOfLiving.getText())));
        offeredJobDetails.setSalary(Integer.parseInt(String.valueOf(salary.getText())));
        offeredJobDetails.setBonus(Integer.parseInt(String.valueOf(bonus.getText())));
        offeredJobDetails.setTelework(Integer.parseInt(String.valueOf(telework.getText())));
        offeredJobDetails.setLeave(Integer.parseInt(String.valueOf(leave.getText())));
        offeredJobDetails.setShare(Integer.parseInt(String.valueOf(share.getText())));

        DBHandler dbhandler = DBHandler.getDbHandler(this);
        dbhandler.saveJobOffer(offeredJobDetails);
        offeredJobDetails = dbhandler.getOfferedJob();
        currentJobDetails = dbhandler.getCurrentJob();
            dialog(this, "Job Offer Successfully Saved");
    }
            if (currentJobDetails != null && offeredJobDetails != null) {
                compareButton.setEnabled(true);
            }
        }


    protected void validator(EditText editText, String fieldName)

    {
        if (TextUtils.isEmpty(editText.getText())) {
            editText.setError(fieldName+" is required!");
            savingEroor=true;
        }
    }

    protected  void minMaxvalidator(EditText editText, int min, int max)
    {
      int val=  Integer.parseInt(String.valueOf(editText.getText()));
      if (val<min || val >max) {
            editText.setError("Value should be between "+ min + " & "+ max);
            savingEroor=true;
        }
    }


    public void compareOfferedJobWithCurrentJob(View view)
    {
        if (currentJobDetails!=null &&  offeredJobDetails!=null)
        {
            String value=offeredJobDetails.getId();
            Intent intent = new Intent(JobOfferEditor.this, JobOfferComparer.class);
            intent.putExtra("id",value);
            startActivity(intent);

        }

    }

    public void returnFromJobDetailsToMain(View view)
    {
        startActivity(new Intent(JobOfferEditor.this, Application.class));
    }

    public void enterAnotherOffer(View view)
    {
        finish();
        startActivity(getIntent());
    }

    public  void dialog(Context context, String message) {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
        builder1.setMessage(message);
        builder1.setCancelable(true);
        AlertDialog alert11 = builder1.create();
        alert11.show();
    }

}
