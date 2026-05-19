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

  return (
    <div className="m-2 p-3">
      {simulationMutation.data && (
        <div>
          <p>
            Calories Produced: {totals?.CALORIES.toFixed(0)} /{" "}
            {targets?.CALORIES.toFixed(0)}
          </p>
          <p>
            Protein Produced: {totals?.PROTEIN.toFixed(0)} /{" "}
            {targets?.PROTEIN.toFixed(0)}
          </p>
          <p>
            Fat Produced: {totals?.FAT.toFixed(0)} / {targets?.FAT.toFixed(0)}
          </p>
          <p>
            Carbs Produced: {totals?.CARBS.toFixed(0)} /{" "}
            {targets?.CARBS.toFixed(0)}
          </p>
          <p>
            You are{" "}
            {(simulationMutation.data.selfSufficiencyPercentage * 100).toFixed(
              2,
            )}
            % Self Sufficient
          </p>
        </div>
      )}
      {simulationMutation.error && <p>{simulationMutation.error.message}</p>}
    </div>
  );
}
