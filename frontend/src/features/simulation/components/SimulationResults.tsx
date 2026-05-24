import { NutritionProgressBar } from "../../nutrition/componets/NutritionProgressBar";
import type { SimulationRequest, SimulationResponse } from "../type";
import type { UseMutationResult } from "@tanstack/react-query";

interface SimulationResultsProps {
  simulationMutation: UseMutationResult<
    SimulationResponse,
    Error,
    SimulationRequest,
    unknown
  >;
}

export function SimulationResults({
  simulationMutation,
}: SimulationResultsProps) {
  const totals = simulationMutation.data?.nutritionTotals?.totals;
  const targets = simulationMutation.data?.nutritionTotals?.targets;

  console.log(simulationMutation.data);

  return (
    <div className="m-2 p-3">
      {simulationMutation.error && <p>{simulationMutation.error.message}</p>}
      {simulationMutation.data && totals && targets && (
        <div className="w-full max-w-2xl px-4 py-6">
          <NutritionProgressBar
            label="Calories Produced"
            min={totals.CALORIES.yieldMin}
            max={totals.CALORIES.yieldMax}
            goal={targets.CALORIES}
            labelGrams={false}
          />
          <NutritionProgressBar
            label="Protein Produced"
            min={totals.PROTEIN.yieldMin}
            max={totals.PROTEIN.yieldMax}
            goal={targets.PROTEIN}
            color="#297fff"
          />
          <NutritionProgressBar
            label="Fat Produced"
            min={totals.FAT.yieldMin}
            max={totals.FAT.yieldMax}
            goal={targets.FAT}
            color="#fdbb00"
          />
          <NutritionProgressBar
            label="Carbs Produced"
            min={totals.CARBS.yieldMin}
            max={totals.CARBS.yieldMax}
            goal={targets.CARBS}
            color="#00c94f"
          />
          <p>
            You are{" "}
            {(simulationMutation.data.selfSufficiencyPercentage * 100).toFixed(
              2,
            )}
            % Self Sufficient
          </p>
        </div>
      )}
    </div>
  );
}
