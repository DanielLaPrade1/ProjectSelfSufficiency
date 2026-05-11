import type { CropInput, SimulationRequest } from "../type";
import { runSimulation } from "../api";

export function SimulationForm() {
  const handleSimulation = () => {
    const potato: CropInput = {
      varietyId: "561b474a-25f4-4f94-bc90-4efc7e04c2ea",
      units: 3,
    };
    const tomato: CropInput = {
      varietyId: "31e36b77-1b1e-4527-94b2-296dffbff314",
      units: 1,
    };

    const exampleSimReq: SimulationRequest = {
      calorieTarget: 2000,
      cropInputs: [potato, tomato],
    };

    const simulationCalProd = document.getElementById("simulation-cal-prod");
    const simulationSSS = document.getElementById("simulation-sss");

    runSimulation(exampleSimReq).then((result) => {
      if (simulationCalProd && simulationSSS) {
        simulationCalProd.innerHTML = `Calories Produced: ${result.caloriesProduced}`;
        simulationSSS.innerHTML = `Percentage: ${result.selfSufficiencyPercentage}`;
      }
    });
  };
  return (
    <div>
      <button onClick={handleSimulation} className="button">
        Simulation Example
      </button>
      <p id="simulation-cal-prod"></p>
      <p id="simulation-sss"></p>
    </div>
  );
}
