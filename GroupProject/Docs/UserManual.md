# Job Compare User Manual

- [Overview](#overview)
- [Home Screen](#home-screen)
- [Current Job](#current-job)
- [New Job Offers](#new-job-offers)
- [Settings](#settings)
- [Compare Jobs](#compare-jobs)


## Overview

The Job Compare app is designed to help you make a data-driven decision about your job selection.  You can store data about your current job and other job offers, and generate job comparisons tailored to your priorities.

## Home Screen

<img src="./images/homescreen.png" alt="Home Screen" width="250"/>

When you first launch the application, you'll have four options:

1. Current Job -- This allows you to enter data about your current job.  You'll be able to include your current job in your comparisons against job offers, so that you can also consider how your current postions compares with job offers.
2. New Job Offers - This will allow you to to add a new job offer.  
3. Settings - Here you will be able to configure your preference for how to weight various job attributes 
4. Compare Jobs - This will present you with a ranked list of jobs and allow you to compare jobs side-by-side.

*Note: the Compare Job button will be disabled if you do not have at least two job offers (or a current job and one job offer) to compare.*

## Current Job

<img src="./images/current_job.png" alt="Current Job" width="250"/>

For your current job, you should enter the following details: 

* Title
* Company
* Location
* Yearly salary adjusted for cost of living
* Yearly bonus adjusted for cost of living
* Allowed weekly telework days
* Leave time (days)
* Number of shares offered

## New Job Offers

<img src="./images/job_offer.png" alt="Job Offer" width="250"/>

*Note: you will not be able to edit or remove these job offers after adding them. Make sure you have your details correct.*

For each new job offer, you will add this information:

* Title
* Company
* Location
* Yearly salary adjusted for cost of living
* Yearly bonus adjusted for cost of living
* Allowed weekly telework days
* Leave time (days)
* Number of shares offered

## Settings

<img src="./images/settings.png" alt="Settings" width="250"/>

Our app provides an algorithm to rank job based on the data you enter -- but you have the option of weighting the various options.  Set non-negative integer weight for each of the fields below.  They will be weighted out of the sum of the integers you enter.

* Annual Salary
* Annual Bonus
* Telework Day
* Leave Time
* Company Shares

For example, if you wanted to weight the salary at twice the value of other factors, and you don't want to consider telework days at all, you might enter the following values:

| Attribute      | Weight |
|----------------|--------|
| Annual Salary  | 2      |
| Annual Bonus   | 1      |
| Telework Day   | 0      |
| Leave time     | 1      |
| Company shares | 1      |

## Compare Jobs

<img src="./images/job_comparison.png" alt="Settings" width="250"/>
<img src="./images/job_rank.png" alt="Settings" width="250" />


In this screen, you will see a ranked list of job offers.  This ranking can be custom tailored to your preferences in the *Settings* screen.  The top of the list is the most highly ranked job opportunity.

If you wish to compare the details of two jobs, simply select two jobs and tap *Compare*.




 