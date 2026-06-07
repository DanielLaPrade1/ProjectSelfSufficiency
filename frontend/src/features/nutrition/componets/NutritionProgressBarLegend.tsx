import { deriveScheme } from "../utils";

function LegendItem({
  swatch,
  label,
}: {
  swatch: React.ReactNode;
  label: string;
}) {
  return (
    <span className="flex items-center gap-1.5">
      {swatch}
      <span className="text-[11px] font-semibold text-slate-500">{label}</span>
    </span>
  );
}

export function NutritionProgressBarLegend({
  color = "#00613d",
}: {
  color?: string;
}) {
  const scheme = deriveScheme(color);

  return (
    <div className="flex flex-wrap items-center gap-x-5 gap-y-2 mb-5">
      <LegendItem
        label="Confident minimum"
        swatch={
          <span
            className="inline-block h-3 w-5 rounded-l-full"
            style={{ background: scheme.fill }}
          />
        }
      />
      <LegendItem
        label="Range (min–max)"
        swatch={
          <span
            className="inline-block h-3 w-5 rounded-[3px]"
            style={{
              background: `repeating-linear-gradient(45deg, ${scheme.stripeA} 0px, ${scheme.stripeA} 3px, ${scheme.stripeB} 3px, ${scheme.stripeB} 6px)`,
            }}
          />
        }
      />
      <LegendItem
        label="Midpoint estimate"
        swatch={
          <span className="flex flex-col items-center leading-none">
            <span
              className="text-white text-[9px] font-extrabold rounded-[4px] px-1.5 py-[1px]"
              style={{ background: scheme.caretBg }}
            >
              123
            </span>
            <span
              style={{
                width: 0,
                height: 0,
                marginTop: "1px",
                borderLeft: "4px solid transparent",
                borderRight: "4px solid transparent",
                borderTop: `5px solid ${scheme.caretBg}`,
              }}
            />
          </span>
        }
      />
      <LegendItem
        label="Goal (full bar)"
        swatch={
          <span
            className="inline-block h-3 w-10 rounded-full"
            style={{
              background: scheme.trackBg,
              border: `1.5px solid ${scheme.trackBorder}`,
            }}
          />
        }
      />
    </div>
  );
}
