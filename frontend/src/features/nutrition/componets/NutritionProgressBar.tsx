import { deriveScheme } from "../utils";

interface NutritionProgressBarProps {
  label: string;
  min: number;
  max: number;
  goal: number;
  color?: string;
  labelGrams?: boolean;
}

export function NutritionProgressBar({
  label,
  min,
  max,
  goal,
  color = "#29bfee",
  labelGrams = true,
}: NutritionProgressBarProps) {
  const scheme = deriveScheme(color);

  // Values
  const safeMin = Math.max(0, Math.min(min, goal));
  const safeMax = Math.max(safeMin, Math.min(max, goal));
  const mid = Math.round((min + max) / 2);

  // Percentages
  const safePMinDisplayMinimum = 1;
  const safePMaxDisplayMinimum = 3;
  const safePMidDisplayMinimum = 2;

  const pMin = Math.max(safePMinDisplayMinimum, (safeMin / goal) * 100);
  const pMax = Math.max(safePMaxDisplayMinimum, (safeMax / goal) * 100);
  const pMid = Math.max(
    safePMidDisplayMinimum,
    Math.min(100, ((min + max) / 2 / goal) * 100),
  );

  const showSafeMinAndMax = pMin > 10;

  return (
    <div className="w-full select-none">
      {/* Label */}
      <p className="text-[11px] font-extrabold uppercase tracking-widest text-slate-400 mb-2">
        {label}
      </p>

      {/* Caret + bar wrapper — padding-top reserves space for the caret */}
      <div className="relative" style={{ paddingTop: "32px" }}>
        {/* Caret */}
        <div
          className="absolute top-0 flex flex-col items-center pointer-events-none"
          style={{ left: `${pMid}%`, transform: "translateX(-50%)" }}
        >
          <div
            className="text-white text-[11px] font-extrabold rounded-[6px] px-2 py-[3px] whitespace-nowrap"
            style={{ background: scheme.caretBg }}
          >
            {mid.toLocaleString().split(".")[0]}
            {labelGrams && "g"}
          </div>
          <div
            className="mt-[2px]"
            style={{
              width: 0,
              height: 0,
              borderLeft: "6px solid transparent",
              borderRight: "6px solid transparent",
              borderTop: `8px solid ${scheme.caretBg}`,
            }}
          />
        </div>

        {/* Bar pill */}
        <div
          className="relative w-full h-7 rounded-full overflow-hidden"
          style={{
            background: scheme.trackBg,
            border: `2px solid ${scheme.trackBorder}`,
            boxSizing: "border-box",
          }}
        >
          {/* Solid fill — confirmed minimum */}
          <div
            className="absolute top-0 left-0 h-full rounded-l-full"
            style={{
              width: `${pMin}%`,
              background: scheme.fill,
            }}
          />

          {/* Striped fill — uncertainty range (min → max) */}
          <div
            className="absolute top-0 h-full"
            style={{
              left: `${pMin}%`,
              width: `${pMax - pMin}%`,
              background: `repeating-linear-gradient(
                45deg,
                ${scheme.stripeA} 0px,
                ${scheme.stripeA} 5px,
                ${scheme.stripeB} 5px,
                ${scheme.stripeB} 10px
              )`,
              borderRight: `2px dashed ${scheme.dashColor}`,
            }}
          />
        </div>

        {/* Min / max tick labels */}
        <div className="relative h-[18px] mt-1 mb-3">
          {showSafeMinAndMax && (
            <div>
              <span
                className="absolute text-[11px] font-bold text-slate-500 whitespace-nowrap"
                style={{ left: `${pMin}%`, transform: "translateX(-50%)" }}
              >
                {safeMin.toLocaleString().split(".")[0]}
                {labelGrams && "g"}
              </span>

              {safeMax > safeMin && (
                <span
                  className="absolute text-[11px] font-bold text-slate-500 whitespace-nowrap"
                  style={{
                    left: `${Math.min(96, pMax)}%`,
                    transform: "translateX(-50%)",
                  }}
                >
                  {safeMax.toLocaleString().split(".")[0]}
                  {labelGrams && "g"}
                </span>
              )}
            </div>
          )}
          <span className="absolute right-0 text-[11px] font-medium text-slate-400 whitespace-nowrap">
            / {goal.toLocaleString().split(".")[0]}
            {labelGrams && "g"}
          </span>
        </div>
      </div>
    </div>
  );
}
