package edu.gatech.seclass.jobcompare6300;

public class JobDetail {
    private String id;
    private String title;
    private String company;
    private String city;
    private String state;
    private int costOfLiving;
    private int salary;
    private int bonus;
    private int telework;
    private int leave;
    private int share;
    private boolean isCurrentJob;
    private int score;

    public boolean isCurrentJob() {
        return isCurrentJob;
    }

    public void setCurrentJob(boolean currentJob) {
        isCurrentJob = currentJob;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
    public String getId() { return id; }
    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getCostOfLiving() {
        return costOfLiving;
    }

    public void setCostOfLiving(int costOfLiving) {
        this.costOfLiving = costOfLiving;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public int getBonus() {
        return bonus;
    }

    public void setBonus(int bonus) {
        this.bonus = bonus;
    }

    public int getTelework() {
        return telework;
    }

    public void setTelework(int telework) {
        this.telework = telework;
    }

    public int getLeave() {
        return leave;
    }

    public void setLeave(int leave) {
        this.leave = leave;
    }

    public int getShare() {
        return share;
    }

    public void setShare(int share) {
        this.share = share;
    }

    public double getAdjustedYearlySalary(){
        return (int)Math.round(salary/(double)costOfLiving);
    }

    public double getAdjustedYearlyBonus(){
        return (int)Math.round(bonus/(double)costOfLiving);
    }

    public void calculateScore(int bonusWeight,int  leaveWeight,int  salaryWeight,int  shareWeight,int  teleworkWeight){

        //AYS + AYB + CSO/4 + (LT * AYS / 260) - ((260 - 52 * RWT) * (AYS / 260) / 8)
        double totalWeights = bonusWeight + leaveWeight + salaryWeight
                + shareWeight + teleworkWeight;

        double adjustedYearlySalary = this.getSalary()/(double) this.getCostOfLiving();
        double adjustedYearlyBonus = this.getBonus()/(double)this.getCostOfLiving();

        double weightedSalary= adjustedYearlySalary * salaryWeight / totalWeights;
        double weightedBonus = adjustedYearlyBonus * bonusWeight / totalWeights;
        double weightedShare = this.getShare() * shareWeight / totalWeights;
        double weightedTelework = this.getTelework() * teleworkWeight / totalWeights;
        double weightedLeave = this.getLeave() * leaveWeight / totalWeights;

        double score = weightedSalary
                + weightedBonus
                + weightedShare/4
                + (weightedLeave * weightedSalary / 260.0)
                - ((260-52*weightedTelework) * (weightedSalary / 260.0)/8.0);

        this.setScore((int)Math.round(score));
    }
}

