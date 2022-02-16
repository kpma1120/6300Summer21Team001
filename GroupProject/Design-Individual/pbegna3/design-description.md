## Design Description
The requirement is captured in the UML design document attached.
1. When the app is started, the user is presented with the main menu, which allows the user to (1) enter or edit current job details, (2) enter job offers, (3) adjust the comparison settings, or (4) compare job offers (disabled if no job offers were entered yet).

When the Main Menu class has four methods , that helps the user navigate  to the corresponding four classes. JobDetails, job offers, setting editor and offer comparer classes have many to one relationships with the Main Menu class.

2. When choosing to enter current job details, a user will:
Be shown a user interface to enter (if it is the first time) or edit all of the details.
Be able to either save the job details or cancel and exit without saving, returning in both cases to the main menu.

The JobDetails class captures all the details in this requirement and it extends the editor class for cancel and save operations.

3. Job offers

The JobOfferEditor class uses the jobdetails class to capture job details , it also extends the Editor class for cancel and save operations. 

4. Adjusting the comparison settings.
This SettingEditor class captures the weight of salary, bonus, telework and number of shares, it extends the Editor class. The default value for each weight is 1.

5. Compare job offers
JobOfferComparer class properties such as ranked list of JobDetails class, selected jobs and operations such as compare jobs and select jobs for comparison.
6. Ranking Jobs.

Ranking is included in the job offer as a property , therefore it’s not captured separately.

7. The user interface must be intuitive and responsive

 This is purely a GUI class and not captured in the UML design.
 
8. Assumptions

Single app,  the user is already logged in using a single sign on or another user management system.
