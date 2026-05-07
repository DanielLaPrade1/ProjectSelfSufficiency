import { useQuery } from "@tanstack/react-query";
import { getCrops } from "../api";

export function useCrops() {
  return useQuery({
    queryKey: ["crops"],
    queryFn: getCrops,
  });
}