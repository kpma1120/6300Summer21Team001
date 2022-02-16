package edu.gatech.seclass.jobcompare6300;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class CurrentJobEditor extends AppCompatActivity {

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
    private Context context;
    private  boolean savingEroor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.current_job_details);
        title   = (EditText)findViewById(R.id.currentJobTitle);
        company   = (EditText)findViewById(R.id.currentJobCompany);
        city  = (EditText)findViewById(R.id.currentJobCity);
        state  = (EditText)findViewById(R.id.currentJobState);
        costOfLiving   = (EditText)findViewById(R.id.currentJobCostOfLiving);
        salary  = (EditText)findViewById(R.id.currentJobSalary);
        bonus   = (EditText)findViewById(R.id.currentJobAnualBonus);
        telework  = (EditText)findViewById(R.id.currentJobTeleworkDays);
        share  = (EditText)findViewById(R.id.currentJobShares);
        leave  = (EditText)findViewById(R.id.currentJobVacationdays);

        JobDetail jdm= DBHandler.getDbHandler(this).getCurrentJob();
        if (jdm !=null)
        {
            title.setText(jdm.getTitle(), TextView.BufferType.EDITABLE);
            company.setText(jdm.getCompany(), TextView.BufferType.EDITABLE);
            city.setText(jdm.getCity(), TextView.BufferType.EDITABLE);
            state.setText(jdm.getState(), TextView.BufferType.EDITABLE);
            costOfLiving.setText(""+jdm.getCostOfLiving(), TextView.BufferType.EDITABLE);
            salary.setText(""+jdm.getSalary(), TextView.BufferType.EDITABLE);
            bonus.setText(""+jdm.getBonus(), TextView.BufferType.EDITABLE);
            telework.setText(""+jdm.getTelework(), TextView.BufferType.EDITABLE);
            leave.setText(""+jdm.getLeave(),TextView.BufferType.EDITABLE);
            share.setText(""+jdm.getShare(), TextView.BufferType.EDITABLE);

        }


    }

    public void saveJobDetails(View view)
    {
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
            JobDetail jdm = new JobDetail();
            jdm.setTitle(String.valueOf(title.getText()));
            jdm.setCompany(String.valueOf(company.getText()));
            jdm.setCity(String.valueOf(city.getText()));
            jdm.setState(String.valueOf(state.getText()));
            jdm.setCostOfLiving(Integer.parseInt(String.valueOf(costOfLiving.getText())));
            jdm.setSalary(Integer.parseInt(String.valueOf(salary.getText())));
            jdm.setBonus(Integer.parseInt(String.valueOf(bonus.getText())));
            jdm.setTelework(Integer.parseInt(String.valueOf(telework.getText())));
            jdm.setLeave(Integer.parseInt(String.valueOf(leave.getText())));
            jdm.setShare(Integer.parseInt(String.valueOf(share.getText())));
            DBHandler.getDbHandler(this).saveCurrentJob(jdm);
            dialog(this, " Current Job Successfully Saved");
        }

    }
    protected void validator(EditText editText, String fieldName)

    {
        if (TextUtils.isEmpty(editText.getText())) {
            editText.setError(fieldName+" is required!");
            savingEroor=true;
        }
    }
    protected void minMaxvalidator(EditText editText, int min, int max)
    {
        int val=  Integer.parseInt(String.valueOf(editText.getText()));
        if (val<min || val >max) {
            editText.setError("Value should be between "+ min + " & "+ max);
            savingEroor=true;
        }
    }

    public void returnFromJobDetailsToMain(View view)
    {
        startActivity(new Intent(CurrentJobEditor.this, Application.class));
    }

    private  void dialog(Context context, String message) {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
        builder1.setMessage(message);
        builder1.setCancelable(true);
        AlertDialog alert11 = builder1.create();
        alert11.show();
    }

}
