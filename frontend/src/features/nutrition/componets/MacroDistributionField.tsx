import { useState, useId } from "react";
import type {
  MacroDistributionPreset,
  MacroDistributionRequest,
  MacroDistribution,
} from "../../nutrition/type";

export interface MacroDistributionFieldProps {
  presets: MacroDistribution[] | undefined;
  value?: MacroDistributionRequest;
  onChange?: (value: MacroDistributionRequest) => void;
  disabled?: boolean;
  label?: string;
  showLabel?: boolean;
  error?: string;
  className?: string;
}

// ─── Internal constants ───────────────────────────────────────────────────────

const EMPTY_CUSTOM: MacroDistribution = {
  name: "CUSTOM",
  carbsPct: 0,
  proteinPct: 0,
  fatPct: 0,
};

const MACRO_META = [
  {
    key: "carbsPct" as const,
    label: "Carbs",
    barColor: "bg-green-500",
    borderColor: "border-green-400 focus:ring-green-400",
    labelColor: "text-green-700 dark:text-green-400",
    dotColor: "bg-green-500",
  },
  {
    key: "proteinPct" as const,
    label: "Protein",
    barColor: "bg-blue-500",
    borderColor: "border-blue-400 focus:ring-blue-400",
    labelColor: "text-blue-700 dark:text-blue-400",
    dotColor: "bg-blue-500",
  },
  {
    key: "fatPct" as const,
    label: "Fat",
    barColor: "bg-amber-400",
    borderColor: "border-amber-400 focus:ring-amber-400",
    labelColor: "text-amber-700 dark:text-amber-400",
    dotColor: "bg-amber-400",
  },
] as const;

// ─── Helpers ──────────────────────────────────────────────────────────────────

function distTotal(d: MacroDistribution): number {
  return d.carbsPct + d.proteinPct + d.fatPct;
}

function clamp(v: number): number {
  return Math.max(0, Math.min(100, v));
}

function parseIntSafe(v: string): number {
  const n = parseInt(v, 10);
  return isNaN(n) ? 0 : clamp(n);
}

function isPresetRequest(
  r: MacroDistributionRequest,
): r is { preset: MacroDistributionPreset } {
  return "preset" in r;
}

function isCustomRequest(
  r: MacroDistributionRequest,
): r is { customDistribution: MacroDistribution } {
  return "customDistribution" in r;
}

function resolveDisplayDist(
  current: MacroDistributionRequest,
  presets: MacroDistribution[],
): MacroDistribution {
  if (isCustomRequest(current)) return current.customDistribution;
  if (isPresetRequest(current)) {
    return presets.find((p) => p.name === current.preset) ?? EMPTY_CUSTOM;
  }
  return EMPTY_CUSTOM;
}

// ─── Stacked bar ──────────────────────────────────────────────────────────────

function StackedBar({ distribution }: { distribution: MacroDistribution }) {
  const t = distTotal(distribution);
  if (t === 0) {
    return (
      <div className="h-2 w-full rounded-full bg-gray-100 dark:bg-gray-800" />
    );
  }
  return (
    <div className="flex h-2 w-full overflow-hidden rounded-full">
      {MACRO_META.map(({ key, barColor }) => (
        <div
          key={key}
          className={`${barColor} h-full transition-[width] duration-300`}
          style={{ width: `${((distribution[key] / t) * 100).toFixed(2)}%` }}
        />
      ))}
    </div>
  );
}

// ─── Number input ─────────────────────────────────────────────────────────────

interface MacroInputProps {
  id: string;
  label: string;
  value: number;
  labelColor: string;
  borderColor: string;
  disabled?: boolean;
  onChange: (v: number) => void;
}

function MacroInput({
  id,
  label,
  value,
  labelColor,
  borderColor,
  disabled,
  onChange,
}: MacroInputProps) {
  const [raw, setRaw] = useState(String(value));
  const [focused, setFocused] = useState(false);

  return (
    <div className="flex flex-col gap-1.5">
      <label
        htmlFor={id}
        className={`text-[11px] font-semibold uppercase tracking-widest ${labelColor}`}
      >
        {label}
      </label>
      <input
        id={id}
        type="number"
        min={0}
        max={100}
        step={1}
        disabled={disabled}
        value={focused ? raw : String(value)}
        onFocus={() => {
          setRaw(String(value));
          setFocused(true);
        }}
        onChange={(e) => {
          setRaw(e.target.value);
          onChange(parseIntSafe(e.target.value));
        }}
        onBlur={() => {
          setFocused(false);
          setRaw(String(value));
        }}
        className={[
          "w-full rounded-lg border px-3 py-2.5 text-center text-lg font-semibold tabular-nums",
          "bg-white dark:bg-gray-900 text-gray-900 dark:text-gray-100",
          "transition-colors duration-150 focus:outline-none focus:ring-2 focus:ring-offset-1",
          "[appearance:textfield] [&::-webkit-inner-spin-button]:appearance-none [&::-webkit-outer-spin-button]:appearance-none",
          disabled ? "cursor-not-allowed opacity-40" : "",
          borderColor,
        ].join(" ")}
      />
    </div>
  );
}

// ─── Main component ───────────────────────────────────────────────────────────

export function MacroDistributionField({
  presets,
  value,
  onChange,
  disabled = false,
  label = "Macro Distribution",
  error,
  className = "",
}: MacroDistributionFieldProps) {
  const uid = useId();
  const resolvedPresets = presets ?? [];

  const [internal, setInternal] = useState<MacroDistributionRequest>(() =>
    resolvedPresets.length > 0
      ? { preset: resolvedPresets[0].name as MacroDistributionPreset }
      : { customDistribution: { ...EMPTY_CUSTOM } },
  );

  const current: MacroDistributionRequest = value ?? internal;

  const emit = (next: MacroDistributionRequest) => {
    if (value === undefined) setInternal(next);
    onChange?.(next);
  };

  const isPreset = isPresetRequest(current);
  const isCustom = isCustomRequest(current);
  const activePresetName = isPreset ? current.preset : null;

  const displayDist = resolveDisplayDist(current, resolvedPresets);
  const total = distTotal(displayDist);
  const isValid = total === 100;

  const handlePresetClick = (preset: MacroDistribution) => {
    if (disabled) return;
    emit({ preset: preset.name as MacroDistributionPreset });
  };

  const handleCustomClick = () => {
    if (disabled || isCustom) return;
    emit({ customDistribution: { ...displayDist } });
  };

  const handleMacroChange = (
    key: keyof Omit<MacroDistribution, "name">,
    v: number,
  ) => {
    if (disabled || !isCustom) return;
    emit({ customDistribution: { ...displayDist, [key]: v } });
  };

  return (
    <fieldset
      disabled={disabled}
      className={[
        "rounded-xl bg-white p-4 shadow-sm",
        "dark:bg-gray-900 dark:shadow-none",
        error
          ? "border-red-400 dark:border-red-500"
          : "border-gray-200 dark:border-gray-700",
        disabled ? "opacity-60" : "",
        className,
      ].join(" ")}
      aria-label={label}
    >
      {/* Preset pills + Custom toggle */}
      <div
        role="radiogroup"
        aria-label="Preset or custom distribution"
        className="mb-3 flex flex-wrap gap-1.5"
      >
        {resolvedPresets.map((preset) => {
          const selected = activePresetName === preset.name;
          return (
            <button
              key={preset.name}
              type="button"
              role="radio"
              aria-checked={selected}
              onClick={() => handlePresetClick(preset)}
              className={[
                "rounded-md border px-3 py-1.5 text-[13px] font-medium transition-all duration-150",
                "focus-visible:outline-none focus-visible:ring-2 focus-visible:ring-gray-400 focus-visible:ring-offset-1",
                selected
                  ? "border-gray-900 bg-gray-900 text-white dark:border-gray-100 dark:bg-gray-100 dark:text-gray-900"
                  : "border-gray-200 bg-white text-gray-600 hover:border-gray-300 hover:bg-gray-50 dark:border-gray-700 dark:bg-transparent dark:text-gray-400 dark:hover:border-gray-500 dark:hover:bg-gray-800",
              ].join(" ")}
            >
              {preset.name}
            </button>
          );
        })}

        {/* Custom mode button */}
        <button
          type="button"
          role="radio"
          aria-checked={isCustom}
          onClick={handleCustomClick}
          className={[
            "flex items-center gap-1.5 rounded-md border px-3 py-1.5 text-[13px] font-medium transition-all duration-150",
            "focus-visible:outline-none focus-visible:ring-2 focus-visible:ring-gray-400 focus-visible:ring-offset-1",
            isCustom
              ? "border-gray-900 bg-gray-900 text-white dark:border-gray-100 dark:bg-gray-100 dark:text-gray-900"
              : "border-dashed border-gray-300 bg-white text-gray-400 hover:border-gray-400 hover:text-gray-600 dark:border-gray-600 dark:bg-transparent dark:text-gray-500 dark:hover:border-gray-400",
          ].join(" ")}
        >
          <svg
            width="11"
            height="11"
            viewBox="0 0 12 12"
            fill="none"
            aria-hidden="true"
          >
            <path
              d="M6 1v10M1 6h10"
              stroke="currentColor"
              strokeWidth="1.75"
              strokeLinecap="round"
            />
          </svg>
          Custom
        </button>
      </div>

      {/* Macro inputs — editable only in custom mode */}
      <div className="mb-3 grid grid-cols-3 gap-2.5">
        {MACRO_META.map(
          ({ key, label: macroLabel, labelColor, borderColor }) => (
            <MacroInput
              key={key}
              id={`${uid}-${key}`}
              label={macroLabel}
              value={displayDist[key]}
              labelColor={labelColor}
              borderColor={borderColor}
              disabled={disabled || isPreset}
              onChange={(v) => handleMacroChange(key, v)}
            />
          ),
        )}
      </div>

      {/* Stacked bar */}
      <StackedBar distribution={displayDist} />

      {/* Legend + total */}
      <div className="mt-2.5 flex items-center justify-between">
        <div className="flex flex-wrap items-center gap-3">
          {MACRO_META.map(
            ({ key, label: macroLabel, dotColor, labelColor }) => (
              <span key={key} className="flex items-center gap-1.5">
                <span
                  className={`inline-block h-2 w-2 flex-shrink-0 rounded-full ${dotColor}`}
                />
                <span className={`text-[12px] font-medium ${labelColor}`}>
                  {macroLabel} {displayDist[key]}%
                </span>
              </span>
            ),
          )}
        </div>

        {isCustom && (
          <span
            className={[
              "text-[12px] font-semibold tabular-nums transition-colors",
              isValid
                ? "text-green-600 dark:text-green-400"
                : "text-red-500 dark:text-red-400",
            ].join(" ")}
            aria-live="polite"
            aria-label={`Total: ${total}%`}
          >
            = {total}%
          </span>
        )}
      </div>

      {isPreset && (
        <p className="mt-2 text-[11px] text-gray-400 dark:text-gray-500">
          Select <span className="font-medium">Custom</span> to set your own
          percentages.
        </p>
      )}

      {error && (
        <p
          className="mt-2 text-[12px] text-red-500 dark:text-red-400"
          role="alert"
        >
          {error}
        </p>
      )}
    </fieldset>
  );
}

export default MacroDistributionField;
