export interface SimulationResponse {
  caloriesProduced: number,
  selfSufficiencyPercentage: number
}

export interface SimulationRequest {
  calorieTarget: number
  cropInputs: CropInput[]
}

export interface CropInput {
  varietyId: string
  units: number
}