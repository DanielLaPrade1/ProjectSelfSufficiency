import type {
  CropRequest,
  SimulationRequest,
  SimulationResponse,
} from "../type";
import { CropCard, CropGrid, useCrops } from "../../crop";
import React, { useState } from "react";
import type { UseMutationResult } from "@tanstack/react-query";
import { useMacroPresets, MacroDistributionField } from "../../nutrition";
import type { MacroDistributionRequest } from "../../nutrition/type";

interface SimulationFormProps {
  simulationMutation: UseMutationResult<
    SimulationResponse,
    Error,
    SimulationRequest,
    unknown
  >;
}

export function SimulationForm({ simulationMutation }: SimulationFormProps) {
  // Form Inputs
  const [calorieTarget, setCalorieTarget] = useState("");
  const [selectedCrops, setSelectedCrops] = useState<CropRequest[]>([]);
  const [macroDistribution, setMacroDistribution] =
    useState<MacroDistributionRequest>({ preset: "STANDARD" });

  // Api Fetching for Fields
  const {
    data: cropCardData,
    isLoading: cropCardsLoading,
    error: cropCardError,
  } = useCrops();

  const {
    data: macroPresetData,
    isLoading: macroPresetsLoading,
    error: macroPresetError,
  } = useMacroPresets();

  if (cropCardsLoading) return <p>Loading Crop Cards...</p>;
  if (cropCardError || !cropCardData) return <p>Error loading crops.</p>;

  if (macroPresetsLoading) return <p>Loading Macro Presets...</p>;
  if (macroPresetError || !macroPresetData)
    return <p>Error loading macro presets.</p>;

  const handleSubmit = async (e: React.SubmitEvent) => {
    e.preventDefault();

    const ct: number = Number(calorieTarget);

    const req: SimulationRequest = {
      calorieTarget: ct,
      cropRequests: selectedCrops,
      macroDistribution: macroDistribution,
    };
    console.log(macroDistribution);
    console.log(req);

    await simulationMutation.mutateAsync(req);
  };

  const handleCropQuantityChange = (varietyId: string, quantity: number) => {
    setSelectedCrops((sc) => {
      if (quantity === 0) {
        return sc.filter((c) => c.varietyId != varietyId);
      }
      const newCrop: CropRequest = { varietyId, units: quantity };
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
        <MacroDistributionField
          presets={macroPresetData}
          onChange={(m) => setMacroDistribution(m)}
        />
        <button
          type="submit"
          disabled={simulationMutation.isPending}
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
    </div>
  );
}
