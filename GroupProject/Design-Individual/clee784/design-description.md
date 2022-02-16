# Design Description Document
This is a design description document for "Job Comparison" app. This app will save the current job and new job offers and will compare based on comparison settings. 

## Requirements
1. When the app is started, the user is presented with the main menu,
which allows the user to (1) enter current job details, (2) enter job offers, (3) adjust the comparison settings, or (4) compare job offers (disabled if no job offers were entered yet).
> In order to satisfy this requirement, I have created a MainMenu class that has different menus.

2. When choosing to enter current job details, a user will:
a. Be shown a user interface to enter (if it is the first time) or edit all of the details of their current job, which consist of:
i. Title
ii. Company
iii. Location (entered as city and state)
iv. Cost of living in the location (expressed as an index)
v. Yearly salary
vi. Yearly bonus
vii. Allowed weekly telework days (expressed as the number of days per week allowed for remote work, inclusively between 0 and 5)
viii, Leave time (vacation days and holiday and/or sick leave, as a single overall number of days)
ix. Number of company shares offered at hiring (valued at $1 per share and expressed as a number >= 0)
b. Be able to either save the job details or cancel and exit without saving, returning in both cases to the main menu.
> For this requirement, I have created CurrentJob class which can be invoked by MainMenu class. This class can edit existing job and create a new job and save it. Also, return to main menu method is implemented.  

3. When choosing to enter job offers, a user will:
a. Be shown a user interface to enter all of the details of the offer, which are the same ones listed above for the current job.
b. Be able to either save the job offer details or cancel.
c. Be able to (1) enter another offer, (2) return to the main menu, or (3) compare the offer with the current job details (if present).
> For this requirements, I have created JobOfferDetails class. This class has a method called save and it will save a job offer with detailed informations. Also, delete and edit methods are implemented. Finally, compareOffer method is implemented and it uses CompareJobOffer class. 

4. When adjusting the comparison settings, the user can assign integer weights to:
  a. Yearly salary
  b. Yearly bonus
  c. Allowed weekly telework days
  d. Leave time
  e. Shares offered
 If no weights are assigned, all factors are considered equal.
> Salary, Bonus, WFH, VacationDays and Stocks classes are implemented individually and an interface called ComparisonSettingInterface is created to interconnect each individual classes stated above. Also, a weight can be assigned and stored. When the weight is set to 1, all factors will be considered equal. 

5. When choosing to compare job offers, a user will:
a. Be shown a list of job offers, displayed as Title and Company, ranked from best to worst (see below for details), and including the current job (if present), clearly indicated.
b. Select two jobs to compare and trigger the comparison.
c. Be shown a table comparing the two jobs, displaying, for each job:
i. Title
ii. Company
iii. Location
iv. Yearly salary adjusted for cost of living
v. Yearly bonus adjusted for cost of living
vi. Allowed weekly telework days
vii. Leave time
viii. Number of shares offered
d. Be offered to perform another comparison or go back to the main menu.
> CompareJobOffer class has SelectJobList method that shows the list of the jobs offeres entered by the users. Also, this class connects to ComparisonTable in order to compare the job offers.
> The job list shown from SelectJobList is selectable and the user will be able to  pick a specific job offers to compare.
> Also, DoOtherComparison method is implemented to do more comparisons or MainMenu method can be called in order to return to the main menu.

6. When ranking jobs, a job’s score is computed as the weighted sum of: AYS + AYB + (RBP * AYS) + (LT * AYS / 260) - (CT * AYS / 8)
where:
AYS = yearly salary adjusted for cost of living
AYB = yearly bonus adjusted for cost of living
CSO = Company shares offered (assuming a 4-year vesting schedule and a price-per-share of $1)
LT = leave time
RWT = telework days per week
The rationale for the RWT subformula is:
value of an employee hour = (AYS / 260) / 8
commute hours per year (assuming a 1-hour/day commute) =
1 * (260 - 52 * RWT)
therefore travel-time cost = (260 - 52 * RWT) * (AYS / 260) / 8
For example, if the weights are 2 for the yearly salary, 2 for the shares offered, and 1 for all other factors, the score would be computed as:
2/7 * AYS + 1/7 * AYB + 2/7 * (CSO/4) + 1/7 * (LT * AYS / 260) - 1/7 * ((260 - 52 * RWT) * (AYS / 260) / 8)
> In order to satisfy this requirement, I have implemented a method called RankingJobOffers method is created from CompareJobOffer class. The algorith to obtain the result is well defined from the above section. 

7. The user interface must be intuitive and responsive.
8. For simplicity, you may assume there is a single system running the app (no communication or saving between devices is necessary).
