// RESPONSE

export interface SimulationResponse {
  nutritionTotals: {
    totals: Record<NutritionMetric, number>
  }
  selfSufficiencyPercentage: number
}

export type NutritionTotals = Record<NutritionMetric, number>;

// erasablesyntaxonly Safe Enum
const NUTRITION_METRIC = { 
  CALORIES: "CALORIES",
  PROTEIN: "PROTEIN",
  FAT: "FAT",
  CARBS: "CARBS"
} as const;
export type NutritionMetric = typeof NUTRITION_METRIC[keyof typeof NUTRITION_METRIC];


// REQUEST

export interface SimulationRequest {
  calorieTarget: number
  cropInputs: CropInput[]
}

export interface CropInput {
  varietyId: string
  units: number
}