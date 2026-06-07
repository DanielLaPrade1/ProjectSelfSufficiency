// ─── Color Derivation ───────────────────────────────────────────

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

// Color scheme deriver for NutritionProgressBar / legend
export function deriveScheme(hex: string): DerivedScheme {
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