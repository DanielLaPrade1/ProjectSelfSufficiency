# Project Self Sufficiency: A tool for calculating food security

## Purpose

A web application designed to help individuals understand how close they are to being self-sufficient in food production.

The application calculates a **self-sufficiency percentage** based on a variety of components related to gardening and homesteading. It gives users a clear, quantitative view of their food independence.

Users of this app should be able to answer:

- _Can I sustain myself with what I grow?_
- _What crops / animals give me the most impact?_
- _What am I missing?_

---

## Tech Stack

- **Backend**: Java, Spring Boot
- **Frontend**: React
- **Database**: PostgreSQL
- **Build Tools**: Maven

---

## Current Status

This project is in **early development (Phase 1)**.

## Phase 1 Goals

Phase 1 focuses on building a functional full-stack application with the following features:

### Frontend

- Simple UI for entering:
  - Daily calorie needs
  - Crops grown
  - Growing area
- Display:
  - Total calories produced
  - Percentage of self-sufficiency (caloric)
  - Breakdown by crop
  - Suggestions

### Backend

- Crop data modeling
- User input handling
- Calorie production calculations

---

## Example

A user inputs:

- Personal attributes: **120 lbs, 5'7", Female**
- Daily calorie requirement: **1700**
- Available growing space: **128 sqft**
- Crops grown: **Potatoes, Beans, Corn**

System output:

> **"You are 42% self-sufficient."**

---

## Future

Project Self Sufficiency aims to become more than just a calculator:

- A **decision-making tool**
- A **simulation platform**
- A **personal sustainability dashboard**

---

## Notes

- Early versions may use static or estimated agricultural data
- Accuracy will improve over time as more variables are introduced
