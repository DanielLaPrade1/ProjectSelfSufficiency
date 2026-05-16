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

  return (
    <div className="m-2 p-3">
      {simulationMutation.data && (
        <div>
          <p>Calories Produced: {totals?.CALORIES}</p>
          <p>Protein Produced: {totals?.PROTEIN}</p>
          <p>Fat Produced: {totals?.FAT}</p>
          <p>Carbs Produced: {totals?.CARBS}</p>
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
