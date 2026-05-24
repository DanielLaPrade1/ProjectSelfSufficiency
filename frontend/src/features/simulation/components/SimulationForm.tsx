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

    simulationMutation.reset();

    const ct: number = Number(calorieTarget);

    const req: SimulationRequest = {
      calorieTarget: ct,
      cropRequests: selectedCrops,
      macroDistribution: macroDistribution,
    };

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
    <div className="w-full max-w-2xl px-4 py-6">
      {/* ── Page header ──────────────────────────────────────────────────── */}
      <div className="mb-6">
        <h1 className="text-5xl font-bold text-gray-900">Crop Simulation</h1>
        <p className="mt-1 text-md text-gray-500">
          Configure your harvest inputs and run a nutritional yield simulation.
        </p>
      </div>

      <form onSubmit={handleSubmit} className="flex flex-col gap-6">
        {/* ── Section 1: Calorie target ────────────────────────────────────── */}
        <section className="rounded-2xl border border-gray-200 bg-white p-5 shadow-sm">
          <label
            htmlFor="calorie-target"
            className="mb-2 block text-sm font-semibold uppercase tracking-wide text-gray-500"
          >
            Daily Calorie Target
          </label>
          <div className="relative">
            <input
              id="calorie-target"
              value={calorieTarget}
              onChange={(e) => setCalorieTarget(e.target.value)}
              type="number"
              min={800}
              max={10000}
              placeholder="e.g. 2000"
              className="
                w-full rounded-xl border border-gray-300 bg-white
                px-4 py-3 pr-16 text-gray-900 shadow-sm outline-none
                transition duration-200 placeholder:text-gray-400
                focus:border-green-500 focus:ring-4 focus:ring-green-100
              "
            />
            {/* Inline unit suffix */}
            <span className="pointer-events-none absolute right-4 top-1/2 -translate-y-1/2 text-sm font-medium text-gray-400">
              kcal
            </span>
          </div>
        </section>

        {/* ── Section 2: Macro distribution ───────────────────────────────── */}
        <section className="rounded-2xl border border-gray-200 bg-white p-5 shadow-sm">
          <label className="mb-2 block text-sm font-semibold uppercase tracking-wide text-gray-500">
            Select Macro Split
          </label>
          <MacroDistributionField
            presets={macroPresetData}
            onChange={(m) => setMacroDistribution(m)}
          />
        </section>

        {/* ── Section 3: Crop selection ────────────────────────────────────── */}
        <section className="rounded-2xl border border-gray-200 bg-white p-5 shadow-sm">
          <h2 className="mb-3 text-sm font-semibold uppercase tracking-wide text-gray-500">
            Which Crops Are You Growing?
          </h2>
          {cropCardData.length === 0 ? (
            <p className="text-sm text-gray-400">No crops available.</p>
          ) : (
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
          )}
        </section>

        {/* ── CTA ──────────────────────────────────────────────────────────── */}
        <button
          type="submit"
          disabled={simulationMutation.isPending}
          className="
            flex w-full items-center justify-center gap-3
            rounded-2xl bg-green-600 px-6 py-4
            text-lg font-semibold text-white shadow-md
            transition duration-200
            hover:bg-green-700 hover:shadow-lg
            active:scale-[0.98]
            disabled:cursor-not-allowed disabled:opacity-60
          "
        >
          {simulationMutation.isPending ? (
            <>
              {/* Spinner */}
              <span
                className="h-5 w-5 animate-spin rounded-full border-2 border-white border-t-transparent"
                aria-hidden="true"
              />
              Running simulation…
            </>
          ) : (
            "Run Simulation"
          )}
        </button>
      </form>
    </div>
  );
}
