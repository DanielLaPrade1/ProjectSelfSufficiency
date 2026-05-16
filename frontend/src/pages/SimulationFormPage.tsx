import {
  SimulationForm,
  useSimulation,
  SimulationResults,
} from "../features/simulation";

export default function SimulationFormPage() {
  const simulationMutation = useSimulation();

  return (
    <div>
      <SimulationForm simulationMutation={simulationMutation} />
      <SimulationResults simulationMutation={simulationMutation} />
    </div>
  );
}
