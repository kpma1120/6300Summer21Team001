package edu.gatech.seclass.jobcompare6300;

public class SettingModel {
    private int settingId;
    private int salary;
    private int share;
    private int bonus;
    private int telework;
    private int leave;

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public void setShare(int share) {
        this.share = share;
    }

    public void setBonus(int bonus) {
        this.bonus = bonus;
    }

    public void setTelework(int telework) {
        this.telework = telework;
    }

    public void setLeave(int leave) {
        this.leave = leave;
    }

    public int getSalary() {
        return salary;
    }

    public int getShare() {
        return share;
    }

    public int getBonus() {
        return bonus;
    }

    public int getTelework() {
        return telework;
    }

    public int getLeave() {
        return leave;
    }
}