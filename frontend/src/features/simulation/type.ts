import type { MacroDistributionRequest } from "../nutrition/type";

// ─── RESPONSE ──────────────────────────────────────────────────────

export interface SimulationResponse {
  nutritionTotals: {
    totals: Record<NutritionMetric, NutritionRange>
    targets: Record<NutritionMetric, number>
  }
  selfSufficiencyPercentage: number
}

// NutritionMetric Enum
const NUTRITION_METRIC = { 
  CALORIES: "CALORIES",
  PROTEIN: "PROTEIN",
  FAT: "FAT",
  CARBS: "CARBS"
} as const;
export type NutritionMetric = typeof NUTRITION_METRIC[keyof typeof NUTRITION_METRIC];

export interface NutritionRange {
  min: number
  max: number
}


// ─── REQUEST ──────────────────────────────────────────────────────

export interface SimulationRequest {
  calorieTarget: number
  simulationLength: number | undefined
  macroDistribution: MacroDistributionRequest
  cropRequests: CropRequest[]

}

// CropRequestDTO
export interface CropRequest {
  varietyId: string
  units: number
}