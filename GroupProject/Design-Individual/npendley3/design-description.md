# Assignment 5
## Design Description

*Nick Pendley*
*npendley3@gatech.edu*



```
1. When the app is started, the user is presented with the main menu, which allows the user to 
    1. enter or edit current job details, 
    2. enter job offers,
    3. adjust the comparison settings, or
    4. compare job offers (disabled if no job offers were entered yet).  
```

The `Application` class captures the overall application, and I have listed jobs and parameters as properties of this class.  They are each respectively broken out with their own separate classes, which will be used for entering or editing job details, entering job offers, or adjusting comparison settings.  Job comparison is captured in the compareJobs and rankJobs functions.  Each of these will depend upon the ScoreWeightingAlgorithm, which have separated as its own entity, though it should be a simple algorithm and does not require a complex class.

There will be a user interface to complete each of these operations, though that is not captured in this class UML diagram.

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

The job details are captured in the `Job` class as properties of the class.  The save and cancel operations will be present in the User Interface design.

```
3. When choosing to enter job offers, a user will:
Be shown a user interface to enter all of the details of the offer, which are the same ones listed above for the current job.
Be able to either save the job offer details or cancel.
Be able to 
1. enter another offer, 
2. return to the main menu, or 
3. compare the offer (if they saved it) with the current job details (if present).
```

The details about the job offer are captured in the `Job` class.  The options to navigate to other screen will be present in the user interface.

The action of adding a job offer or comparing offers are captured in the `Application` class.


```
4. When adjusting the comparison settings, the user can assign integer weights to:

* Yearly salary
* Yearly bonus
* Allowed weekly telework days
* Leave time
* Shares offered

If no weights are assigned, all factors are considered equal.
```

The comparison settings are captured in the `ComparisonParameters` class.  The actual calculation is represented as `ScoringWeightAlgorithm`. 

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

Comparisons are triggered in `Application.compareJobs()`  The job details which are shown come from the `Job` class.

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

The job ranking calculation is represented in `ScoreWeightingAlgorithm`, while the weightings themselves are stored in `ComparisonParameters`.

```
7. The user interface must be intuitive and responsive.

```

This requirement is not captured in the class design document but will be part of the user interface design.

```
8. For simplicity, you may assume there is a single system running the app (no communication or saving between devices is necessary).

```

This assumption about the architecture is not captured in the class UML documents but should be considered later in the design process.