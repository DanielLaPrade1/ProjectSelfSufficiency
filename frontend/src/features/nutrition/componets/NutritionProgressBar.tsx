// ─── Color derivation (color prop -> whole color scheme) ──────────────────────
function hexToRgb(hex: string): [number, number, number] {
  const clean = hex.replace("#", "");
  const full =
    clean.length === 3
      ? clean
          .split("")
          .map((c) => c + c)
          .join("")
      : clean;
  return [
    parseInt(full.slice(0, 2), 16),
    parseInt(full.slice(2, 4), 16),
    parseInt(full.slice(4, 6), 16),
  ];
}

function rgbToHsl(r: number, g: number, b: number): [number, number, number] {
  const rn = r / 255,
    gn = g / 255,
    bn = b / 255;
  const max = Math.max(rn, gn, bn);
  const min = Math.min(rn, gn, bn);
  const l = (max + min) / 2;
  if (max === min) return [0, 0, Math.round(l * 100)];
  const d = max - min;
  const s = l > 0.5 ? d / (2 - max - min) : d / (max + min);
  let h = 0;
  switch (max) {
    case rn:
      h = ((gn - bn) / d + (gn < bn ? 6 : 0)) / 6;
      break;
    case gn:
      h = ((bn - rn) / d + 2) / 6;
      break;
    case bn:
      h = ((rn - gn) / d + 4) / 6;
      break;
  }
  return [Math.round(h * 360), Math.round(s * 100), Math.round(l * 100)];
}

function hslToHex(h: number, s: number, l: number): string {
  const sn = s / 100,
    ln = l / 100;
  const a = sn * Math.min(ln, 1 - ln);
  const f = (n: number): string => {
    const k = (n + h / 30) % 12;
    const color = ln - a * Math.max(Math.min(k - 3, 9 - k, 1), -1);
    return Math.round(255 * color)
      .toString(16)
      .padStart(2, "0");
  };
  return `#${f(0)}${f(8)}${f(4)}`;
}

interface DerivedScheme {
  trackBg: string;
  trackBorder: string;
  fill: string;
  stripeA: string;
  stripeB: string;
  dashColor: string;
  caretBg: string;
}

function deriveScheme(hex: string): DerivedScheme {
  const [r, g, b] = hexToRgb(hex);
  const [h, s] = rgbToHsl(r, g, b);
  const sat = Math.min(s, 85);

  return {
    trackBg: hslToHex(h, Math.min(sat, 60), 93), // very light tint
    trackBorder: hslToHex(h, sat, 68), // medium border
    fill: hex, // solid base — no gradient
    stripeA: `rgba(${r},${g},${b},0.55)`, // stripe opaque band
    stripeB: `rgba(${r},${g},${b},0.20)`, // stripe gap band
    dashColor: hex, // dashed edge matches fill
    caretBg: hslToHex(h, Math.min(sat + 5, 90), 18), // dark shade for caret
  };
}

// ─── Component ────────────────────────────────────────────────────────────────

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
  const mid = Math.round((safeMin + safeMax) / 2);

  // Percentages
  const safePMinDisplayMinimum = 1;
  const safePMaxDisplayMinimum = 3;

  const pMin = Math.max(safePMinDisplayMinimum, (safeMin / goal) * 100);
  const pMax = Math.max(safePMaxDisplayMinimum, (safeMax / goal) * 100);
  const pMid = Math.max(
    2,
    Math.min(100, ((safeMin + safeMax) / 2 / goal) * 100),
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
        </div>
      </div>
    </div>
  );
}
