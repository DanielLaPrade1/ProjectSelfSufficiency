import type { CropInput, SimulationRequest, SimulationResponse } from "../type";
import { CropCard, CropGrid, useCrops } from "../../crop";
import React, { useState } from "react";
import { useSimulation } from "../hooks/useSimulation";
import axios from "axios";

export function SimulationForm() {
  // Form Inputs
  const [calorieTarget, setCalorieTarget] = useState("");
  const [selectedCrops, setSelectedCrops] = useState<CropInput[]>([]);

  // Form Submit
  const { runSimulation, isSimulating, simulationError, resetSimulation } =
    useSimulation();
  const [simulationResponse, setSimulationResponse] =
    useState<SimulationResponse | null>(null);
  const [formError, setFormError] = useState<string | null>(null);

  // Card Loading
  const {
    data: cropCardData,
    isLoading: cropCardsLoading,
    error: cropCardError,
  } = useCrops();
  if (cropCardsLoading) {
    return <p>Loading Crop Cards...</p>;
  }
  if (cropCardError) {
    return <p>Error loading crops.</p>;
  }

  const handleSubmit = async (e: React.SubmitEvent) => {
    e.preventDefault();

    resetSimulation();
    setSimulationResponse(null);
    setFormError(null);

    const ct: number = Number(calorieTarget);

    const req: SimulationRequest = {
      calorieTarget: ct,
      cropInputs: selectedCrops,
    };

    try {
      setSimulationResponse(await runSimulation(req));
    } catch (err) {
      if (axios.isAxiosError(err)) {
        setFormError(err.response?.data?.message ?? "Simulation failed");
      }
    }
  };

  const handleCropQuantityChange = (varietyId: string, quantity: number) => {
    setSelectedCrops((sc) => {
      if (quantity === 0) {
        return sc.filter((c) => c.varietyId != varietyId);
      }
      const newCrop: CropInput = { varietyId, units: quantity };
      return [...sc, newCrop];
    });
  };

  return (
    <div className="h-[20%], w-[50%] m-2 p-3">
      <form onSubmit={handleSubmit}>
        <input
          value={calorieTarget}
          onChange={(e) => setCalorieTarget(e.target.value)}
          type="text"
          placeholder="Daily Calorie Target"
          className="
          w-half
          rounded-2xl
          border
          border-gray-300
          bg-white
          px-4
          py-3
          text-gray-900
          shadow-sm
          outline-none
          transition
          duration-200
          placeholder:text-gray-400
          focus:border-green-500
          focus:ring-4
          focus:ring-blue-200
        "
        />
        <div className="my-2">
          <CropGrid minCardWidth="150px" gap="1em">
            {cropCardData?.map((crop) => (
              <CropCard
                key={crop.varietyID}
                backgroundImage={"/images/crop/farm-bg-1.webp"}
                cropImage={crop.varietyImageUrl}
                varietyName={crop.varietyName}
                cropName={crop.cropName}
                onQuantityChange={(q) =>
                  handleCropQuantityChange(crop.varietyID, q)
                }
              />
            ))}
          </CropGrid>
        </div>
        <button
          type="submit"
          disabled={isSimulating}
          className="
          rounded-2xl
          bg-blue-600
          px-6
          py-3
          text-lg
          font-semibold
          text-white
          shadow-md
          transition
          duration-200
          hover:bg-blue-700
          hover:shadow-lg
          active:scale-[0.98]
        "
        >
          Run Simulation
        </button>
      </form>
      {simulationResponse && (
        <div>
          <p>Calories Produced: {simulationResponse.caloriesProduced}</p>
          <p>
            You are{" "}
            {(simulationResponse.selfSufficiencyPercentage * 100).toFixed(2)}%
            Self Sufficient
          </p>
        </div>
      )}
      {formError && <p>{formError}</p>}
    </div>
  );
}
