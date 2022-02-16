package edu.gatech.seclass.jobcompare6300;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SettingsEditor extends AppCompatActivity {

    private  EditText salary;
    private  EditText bonus;
    private  EditText share;
    private  EditText leave;
    private  EditText telework;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.job_settings);

        salary   = (EditText)findViewById(R.id.salarySetting);
        bonus   = (EditText)findViewById(R.id.bonusSetting);
        share   = (EditText)findViewById(R.id.shareSetting);
        leave   = (EditText)findViewById(R.id.leaveSetting);
        telework  = (EditText)findViewById(R.id.teleworkSetting);

        SettingModel sm= DBHandler.getDbHandler(this).getSettings();

        salary.setText(""+sm.getSalary(), TextView.BufferType.EDITABLE);
        bonus.setText(""+sm.getBonus(), TextView.BufferType.EDITABLE);
        share.setText(""+sm.getShare(), TextView.BufferType.EDITABLE);
        leave.setText(""+sm.getLeave(), TextView.BufferType.EDITABLE);
        telework.setText(""+sm.getTelework(), TextView.BufferType.EDITABLE);

    }


    public void saveSetting(View view)
    {
        SettingModel sm = new SettingModel();
        sm.setSalary(getIntegerFromString(String.valueOf(salary.getText())));
        sm.setBonus(getIntegerFromString(String.valueOf(bonus.getText())));
        sm.setShare(getIntegerFromString(String.valueOf(share.getText())));
        sm.setLeave(getIntegerFromString(String.valueOf(leave.getText())));
        sm.setTelework(getIntegerFromString(String.valueOf(telework.getText())));
        DBHandler.getDbHandler(this).saveSetting(sm);


    }

    private int getIntegerFromString(String input)
    {
        int a;
        try {
            a = Integer.parseInt(input);
        }
        catch (NumberFormatException e)
        {
            a = 1;
        }
        return a;
    }


    public void returnFromSettingsToMain(View view)
    {
        startActivity(new Intent(SettingsEditor.this, Application.class));
    }

}
