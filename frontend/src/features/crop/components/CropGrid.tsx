import type { ReactNode } from "react";

type CropGridProps = {
  children: ReactNode;
  minCardWidth?: string;
  gap?: string;
};

export function CropGrid({
  children,
  minCardWidth = "",
  gap = "",
}: CropGridProps) {
  return (
    <div
      className="grid w-full p-2"
      style={{
        gridTemplateColumns: `repeat(auto-fit, minmax(${minCardWidth}, 1fr))`,
        gap,
      }}
    >
      {children}
    </div>
  );
}
