// Macro Distibution
export type MacroDistributionRequest =
  | { preset: MacroDistributionPreset }
  | { customDistribution: MacroDistribution }

export type MacroDistributionPreset =
  | "STANDARD"
  | "MUSCLE_BUILDING"
  | "KETO"

export interface MacroDistribution {
  name: string
  proteinPct: number
  fatPct: number
  carbsPct: number
}