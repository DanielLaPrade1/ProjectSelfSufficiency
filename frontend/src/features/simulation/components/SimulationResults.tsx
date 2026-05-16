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
  return (
    <div className="m-2 p-3">
      {simulationMutation.data && (
        <div>
          <p>Calories Produced: {simulationMutation.data.caloriesProduced}</p>
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
