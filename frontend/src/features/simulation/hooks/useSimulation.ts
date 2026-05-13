import { useMutation } from "@tanstack/react-query";
import { runSimulation } from "../api";

export function useSimulation() {
  const mutation = useMutation({mutationFn: runSimulation})
  
  return {
    runSimulation: mutation.mutateAsync,
    isSimulating: mutation.isPending,
    errorSimulating: mutation.isError
  }
}