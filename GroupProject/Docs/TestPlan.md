# Test Plan

**Author**: Team 1

## 1 Testing Strategy

### 1.1 Overall strategy

We will use JUnit to do unit tests and manual testing to do system-level testing.  Emulators and/or devices will be used for system testing.  

### 1.2 Test Selection

We will select test cases by testing expected behavior and also boundary conditions or unexpected behavior.  We will write some automated tests which test the application code, while also doing manual testing at the system level. 

### 1.3 Adequacy Criterion

We will check to make sure that our test cases cover 100% of our classes. We plan to do manual testing at the system-level and use unit-tests to cover 100% of calculation-oriented functions.  

### 1.4 Bug Tracking

We will use Trello for tracking features and bugs.

### 1.5 Technology

We plan to use JUnit in our tests.

## 2 Test Cases

| Category        | Test                                                                  | Expected Result                                 | Actual Result                    | Pass/Fail | Comments |
|-----------------|-----------------------------------------------------------------------|-------------------------------------------------|----------------------------------|-----------|----------|
| Settings        | Positive integer weights                                              | Succesfully saved                               | Saved                            | Pass      |          |
| Settings        | Zero value weights                                                    | Successfully saved                              | Saved                            | Pass      |          |
| Settings        | Negative values                                                       | Rejected                                        | Entry not allowed                | Pass      |          |
| Settings        | Check score of a job, then change settings and check the score again. | Scores accurately reflect weights from settings | Scores accurate                  | Pass      |          |
| Comparison      | Current job and no job offers                                         | Disallowed                                      | Disallowed                       | Pass      |          |
| Comparison      | No current job and a single job offer                                 | Disallowed                                      | Disallowed                       | Pass      |          |
| Comparison      | No current job and two job offers                                     | Offer to compare                                | Allows access to ranking         | Pass      |          |
| Comparison      | Two job offers and current job                                        | Accurately scored and ranked                    | Compared and ranked successfully | Pass      |          |
| Job Offer Entry | Missing some fields data                                              | Does not save, notifies                         | Did not save, notifies           | Pass      |          |
| Job Offer Entry | Enter all data                                                        | Successfully saved                              | Saved                            | Pass      |          |
| Current Job     | Enter data                                                            | Successfully saved                              | Saved                            | Pass      |          |
| Current Job     | Missing some fields of data                                           | Does not save; notifies user                    | Does not save, notifies user     | Pass      |          |








