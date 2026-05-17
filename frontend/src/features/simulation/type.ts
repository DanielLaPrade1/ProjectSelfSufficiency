// RESPONSE

export interface SimulationResponse {
  nutritionTotals: {
    totals: Record<NutritionMetric, number>
  }
  selfSufficiencyPercentage: number
}

// Nutrition Totals
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
  cropRequests: CropRequest[]
  macroDistribution: MacroDistributionRequest
}

// Macro Distibution
type MacroDistributionRequest =
  | { preset: MacroDistributionPreset }
  | { customDistribution: MacroDistribution }

export type MacroDistributionPreset =
  | "STANDARD"
  | "MUSCLE_BUILDING"
  | "KETO"

export interface MacroDistribution {
  proteinPercent: number
  farPercent: number
  carbsPercent: number
}

// CropInputs
export interface CropRequest {
  varietyId: string
  units: number
}