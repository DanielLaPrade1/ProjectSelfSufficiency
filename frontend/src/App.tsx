import { Routes, Route } from "react-router-dom";
import HomePage from "./pages/LandingPage";
import SimulationFormPage from "./pages/SimulationFormPage";
import SimulationResultPage from "./pages/SimulationResultPage";

function App() {
  return (
    <Routes>
      <Route path="/" element={<HomePage />} />
      <Route path="/simulate" element={<SimulationFormPage />} />
      <Route path="/simulation-results" element={<SimulationResultPage />} />
    </Routes>
  );
}

export default App;
