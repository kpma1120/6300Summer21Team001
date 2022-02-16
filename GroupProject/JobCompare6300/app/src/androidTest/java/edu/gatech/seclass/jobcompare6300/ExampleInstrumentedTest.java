package edu.gatech.seclass.jobcompare6300;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

    private DBHandler dbh;
    //https://stackoverflow.com/questions/34259151/android-sqlite-database-unit-testing
    @Before
    public void setup(){

    }

    @After
    public void tearDown(){

    }



    @Test
    public void testEmptyDatabase(){
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        DBHandler dbh= DBHandler.getDbHandler(appContext, "testDB");
        dbh.onUpgrade(dbh.getWritableDatabase(), 0, 1);
        int originalNumberOfJobOffers = dbh.getOfferedJobs().size();
        assertEquals(0, originalNumberOfJobOffers);
    }


    @Test
    public void testCreateJobDetails(){
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        DBHandler dbh= DBHandler.getDbHandler(appContext, "testDB");
        dbh.onUpgrade(dbh.getWritableDatabase(), 0, 1);
        int originalNumberOfJobOffers = dbh.getOfferedJobs().size();
        JobDetail new_job = new JobDetail();
        new_job.setTitle("Some title;!");
        new_job.setBonus(1000);
        new_job.setCity("Las Vegas");
        new_job.setCompany("Johnson & Johnson");
        new_job.setCostOfLiving(2);
        new_job.setLeave(20);
        new_job.setSalary(87000);
        new_job.setShare(100);
        new_job.setState("Nevada");
        new_job.setTelework(10);

        dbh.saveJobOffer(new_job);

        List<JobDetail> jobOffers = dbh.getOfferedJobs();
        int newNumberOfJobs = jobOffers.size();
        new_job = jobOffers.get(newNumberOfJobs-1);

        assertEquals(1, newNumberOfJobs );

        assertEquals(new_job.getTitle(), "Some title;!");
        assertEquals(new_job.getBonus(), 1000);
        assertEquals(new_job.getCity(), "Las Vegas");
        assertEquals(new_job.getCompany(), "Johnson & Johnson");
        assertEquals(new_job.getCostOfLiving(), 2);
        assertEquals(new_job.getLeave(), 20);
        assertEquals(new_job.getSalary(), 87000);
        assertEquals(new_job.getState(), "Nevada");
        assertEquals(new_job.getTelework(), 10);
        assertEquals(new_job.getShare(), 100);



    }

    @Test
    public void testAdjustedSalary(){
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        DBHandler dbh= DBHandler.getDbHandler(appContext, "testDB");
        dbh.onUpgrade(dbh.getWritableDatabase(), 0, 1);

        int originalNumberOfJobOffers = dbh.getOfferedJobs().size();
        JobDetail new_job = new JobDetail();
        new_job.setCostOfLiving(79);
        new_job.setSalary(87000);
        assertEquals(87000/(double) 79, new_job.getAdjustedYearlySalary(), 0.5);

    }

    @Test
    public void testAdjustedBonus(){
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        DBHandler dbh= DBHandler.getDbHandler(appContext, "testDB");
        dbh.onUpgrade(dbh.getWritableDatabase(), 0, 1);

        int originalNumberOfJobOffers = dbh.getOfferedJobs().size();
        JobDetail new_job = new JobDetail();
        new_job.setCostOfLiving(79);
        new_job.setBonus(10000);
        assertEquals(10000/(double) 79, new_job.getAdjustedYearlyBonus(), 0.5);

    }

    @Test
    public void testScore1(){

        JobDetail new_job = new JobDetail();
        new_job.setCostOfLiving(95);
        new_job.setSalary(200000);
        new_job.setBonus(25000);
        new_job.setTelework(2);
        new_job.setLeave(60);
        new_job.setShare(50);
        new_job.calculateScore(2, 1, 3, 1, 2);

        assertEquals(699.69, new_job.getScore(), 0.5);

    }

    @Test
    public void testScore2(){

        JobDetail new_job = new JobDetail();
        new_job.setCostOfLiving(140);
        new_job.setSalary(200000);
        new_job.setBonus(10000);
        new_job.setTelework(5);
        new_job.setLeave(30);
        new_job.setShare(2000);
        new_job.calculateScore(2, 1, 3, 1, 2);

        assertEquals(507.427, new_job.getScore(), 0.5);

    }

    @Test
    public void testScore3(){

        JobDetail new_job = new JobDetail();
        new_job.setCostOfLiving(140);
        new_job.setSalary(200000);
        new_job.setBonus(10000);
        new_job.setTelework(5);
        new_job.setLeave(30);
        new_job.setShare(2000);
        new_job.calculateScore(1, 1, 1, 1, 1);

        assertEquals(378.02, new_job.getScore(), 0.5);

    }

}