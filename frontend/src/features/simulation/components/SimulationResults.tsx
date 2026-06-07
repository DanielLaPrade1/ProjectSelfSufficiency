import { NutritionProgressBar } from "../../nutrition";
import { NutritionProgressBarLegend } from "../../nutrition";
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
    <div className="w-full max-w-2xl p-3">
      {simulationMutation.data && totals && targets && (
        <div className="rounded-2xl border border-gray-200 bg-white p-6 shadow-sm">
          {/* Page header */}
          <h1 className="mb-1 text-3xl font-bold text-[#5c6ac4]">
            Simulation Results
          </h1>

          {/* Self sufficency percentage*/}
          <p className="mb-6 text-sm text-gray-500">
            You are{" "}
            <span className="text-lg font-bold text-[#5c6ac4]">
              {(
                simulationMutation.data.selfSufficiencyPercentage * 100
              ).toFixed(2)}
              %
            </span>{" "}
            self-sufficient
          </p>

          <section>
            <h2 className="mb-3 text-base font-semibold tracking-wide text-gray-500">
              Nutrient Production
            </h2>

            {/* Legend */}
            <div className="mb-5 rounded-xl border border-gray-200 bg-gray-50 p-4">
              <p className="mb-2 text-sm font-semibold tracking-wide text-gray-500">
                Key
              </p>
              <NutritionProgressBarLegend />
            </div>

            {/* Macro progress bars */}
            <div className="space-y-6">
              <NutritionProgressBar
                label="Calories Produced"
                min={totals.CALORIES.min}
                max={totals.CALORIES.max}
                goal={targets.CALORIES}
                labelGrams={false}
              />
              <NutritionProgressBar
                label="Protein Produced"
                min={totals.PROTEIN.min}
                max={totals.PROTEIN.max}
                goal={targets.PROTEIN}
                color="#297fff"
              />
              <NutritionProgressBar
                label="Fat Produced"
                min={totals.FAT.min}
                max={totals.FAT.max}
                goal={targets.FAT}
                color="#fdbb00"
              />
              <NutritionProgressBar
                label="Carbs Produced"
                min={totals.CARBS.min}
                max={totals.CARBS.max}
                goal={targets.CARBS}
                color="#00c94f"
              />
            </div>
          </section>
        </div>
      )}

      {simulationMutation.error && (
        <p className="mt-2 text-sm text-red-600">
          {simulationMutation.error.message}
        </p>
      )}
    </div>
  );
}
