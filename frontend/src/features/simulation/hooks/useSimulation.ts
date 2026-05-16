import { useMutation } from "@tanstack/react-query";
import { runSimulation } from "../api";

export function useSimulation() {
  return useMutation({
    mutationFn: runSimulation,
  });
}