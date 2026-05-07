import { Routes, Route } from "react-router-dom";
import HomePage from "./pages/LandingPage";
import SimulationResultPage from "./pages/SimulationResultPage";

function App() {
  return (
    <Routes>
      <Route path="/" element={<HomePage />} />
      <Route path="/simulation-results" element={<SimulationResultPage />} />
    </Routes>
  );
}

export default App;
