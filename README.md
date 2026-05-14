# Project Self Sufficiency

A full-stack web application designed to model personal food production and estimate long-term food independence using crop yield, caloric, and nutrient data.

The goal of the project is to combine agricultural modeling with scalable backend systems to provide actionable sustainability insights for gardeners, homesteaders, and small-scale producers.

Users of the application should be able to answer questions such as:

- _Can I sustain myself with what I currently grow?_
- _Which crops contribute the most nutritional value?_
- _What nutrient or caloric gaps exist in my system?_
- _How can I improve my level of food self-sufficiency?_

---

# Tech Stack

## Backend
- Java
- Spring Boot
- Maven

## Frontend
- React

## Database
- PostgreSQL

---

## Example Workflow

A user inputs:
- Personal attributes:
  - Weight: 120 lbs
  - Height: 5'7"
  - Gender: Female
- Daily calorie requirement (optional):
  - 1700 calories
- Crops grown:
  - Potatoes (12)
  - Beans (5)
  - Corn (4)

The system processes inputs with agricultural data and returns:
> -**"You are 22% self-sufficient based on estimated yearly caloric production."**
> -**"Key Insights: High in Carbohydrates, Low in Protein"**
> -**"Crop Recomendations: Lentils, Carrots, Soybeans"**
> -**"Full Breakdown: ..."**

---

## Technical Challenges

This project explores several backend and data-modeling challenges, including:

- Translating agricultural production into caloric and nutrient metrics
- Balancing estimated agricultural data with real-world variability
- Supporting extensible calculations for crops, livestock, and environmental systems
- Generating meaningful recommendations from incomplete datasets

---

## Current Development Focus

The current milestone focuses on building the foundational calculation engine and full-stack workflow for food self-sufficiency analysis.

## Notes

- Early versions may use estimated or static agricultural datasets from a variety of sources
- Accuracy and simulation complexity will improve over time, as more features will be introduced
- The project is designed to support future expansion into larger sustainability and homesteading systems (animals, composting, etc.)
