## Design Description

*Team 1*

```
1. When the app is started, the user is presented with the main menu, which allows the user to 
    1. enter or edit current job details, 
    2. enter job offers,
    3. adjust the comparison settings, or
    4. compare job offers (disabled if no job offers were entered yet).  
```

The `Application` class captures the above requirement,  the class has 4 methods, compareJobOffers, enterJobdetails(to enter or edit job details), enter jobOffers and adjustSettings. Each Method navigates the user to different user interfaces.


```
2. When choosing to enter current job details, a user will:
    a. Be shown a user interface to enter (if it is the first time) or edit all of the details of their current job, which consist of:
      1. Title
      2. Company
      3. Location (entered as city and state)
      4. Cost of living in the location (expressed as an index)
      5. Yearly salary
      6. Yearly bonus
      7. Allowed weekly telework days (expressed as the number of days per week allowed for remote work, inclusively between 0 and 5)
      8. Leave time (vacation days and holiday and/or sick leave, as a single overall number of days)
      9. Number of company shares offered at hiring (valued at $1 per share and expressed as a number >= 0)
    b. Be able to either save the job details or cancel and exit without saving, returning in both cases to the main menu.
```
The JobOfferEditor module is used to enter and edit job  details as well as job offers. This class uses a common model class JobDetails to capture the data model. It has methods to edit existing job and create a new job and save it. Also a method to return to main menu upon cancel or save options

```
3. When choosing to enter job offers, a user will:
Be shown a user interface to enter all of the details of the offer, which are the same ones listed above for the current job.
Be able to either save the job offer details or cancel.
Be able to 
1. enter another offer, 
2. return to the main menu, or 
3. compare the offer (if they saved it) with the current job details (if present).
```


The details about the job offer are captured in the `JobOfferEditor` class. This class has a method called save and it will save a job offer with detailed informations. Also, delete and edit methods are implemented. Finally, compareOffer method is will be used to compare offer with current job.

```
4. When adjusting the comparison settings, the user can assign integer weights to:

* Yearly salary
* Yearly bonus
* Allowed weekly telework days
* Leave time
* Shares offered

If no weights are assigned, all factors are considered equal.
```

This SettingEditor class captures the weight of salary, bonus, telework and number of shares, it extends the Editor class. The default value for each weight is 1.


```
5. When choosing to compare job offers, a user will:
    a. Be shown a list of job offers, displayed as Title and Company, ranked from best to worst (see below for details), and including the current job (if present), clearly indicated.
    b. Select two jobs to compare and trigger the comparison.
    c. Be shown a table comparing the two jobs, displaying, for each job:
      1. Title
      2. Company
      3. Location
      4. Yearly salary adjusted for cost of living
      5. Yearly bonus adjusted for cost of living
      6. Allowed weekly telework days
      7. Leave time
      8. Number of shares offered
    d. Be offered to perform another comparison or go back to the main menu.
```

JobOfferComparer class has a property that ranks list of JobDetails from best to worst. The job list shown from SelectJobList is selectable and the user will be able to  pick a specific job offers to compare. Also, makeAnotherComparison method is implemented to do more comparisons. 

```
6. When ranking jobs, a job’s score is computed as the weighted sum of:

AYS + AYB + CSO/4 + (LT * AYS / 260) - ((260 - 52 * RWT) * (AYS / 260) / 8)

where:
AYS = yearly salary adjusted for cost of living
AYB = yearly bonus adjusted for cost of living
CSO = Company shares offered (assuming a 4-year vesting schedule and a price-per-share of $1)
LT = leave time
RWT = telework days per week
The rationale for the RWT subformula is:

    a. value of an employee hour = (AYS / 260) / 8
    b. commute hours per year (assuming a 1-hour/day commute) = 1 * (260 - 52 * RWT)
    c. therefore travel-time cost = (260 - 52 * RWT) * (AYS / 260) / 8

For example, if the weights are 2 for the yearly salary, 2 for the shares offered, and 1 for all other factors, the score would be computed as:


2/7 * AYS + 1/7 * AYB + 2/7 * (CSO/4) + 1/7 * (LT * AYS / 260) - 1/7 * ((260 - 52 * RWT) * (AYS / 260) / 8)

```

Ranking is included in the job offer as a property, therefore it’s not captured separately.


```
7. The user interface must be intuitive and responsive.

```

This requirement is not captured in the class design document but will be part of the user interface design.

```
8. For simplicity, you may assume there is a single system running the app (no communication or saving between devices is necessary).

```
This assumption about the architecture is not captured in the class UML documents but should be considered later in the design process.
