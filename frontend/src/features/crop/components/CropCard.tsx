import { useState, useRef, useEffect } from "react";

type CropCardProps = {
  backgroundImage: string;
  cropImage: string;
  varietyName: string;
  cropName: string;
  defaultSelected?: boolean;
  onSelectedChange?: (selected: boolean) => void;
  onQuantityChange?: (quantity: number) => void;
};

type CardState = "default" | "entering-quantity" | "selected";

export function CropCard({
  backgroundImage,
  cropImage,
  varietyName,
  cropName,
  defaultSelected = false,
  onSelectedChange,
  onQuantityChange,
}: CropCardProps) {
  const [cardState, setCardState] = useState<CardState>(
    defaultSelected ? "selected" : "default",
  );
  const [quantity, setQuantity] = useState<string>("");
  const [savedQuantity, setSavedQuantity] = useState<number | null>(null);
  const inputRef = useRef<HTMLInputElement>(null);

  useEffect(() => {
    if (cardState === "entering-quantity") {
      inputRef.current?.focus();
    }
  }, [cardState]);

  const handleCardClick = () => {
    if (cardState === "default") {
      setCardState("entering-quantity");
    } else if (cardState === "selected") {
      setCardState("default");
      setSavedQuantity(null);
    }
  };

  const handleSave = () => {
    const parsed = parseFloat(quantity);
    if (!quantity.trim() || isNaN(parsed)) return;
    if (parsed === 0) {
      setCardState("default");
      setSavedQuantity(null);
    } else {
      setCardState("selected");
      setSavedQuantity(parsed);
    }
    onSelectedChange?.(true);
    onQuantityChange?.(parsed);
  };

  const handleKeyDown = (e: React.KeyboardEvent<HTMLInputElement>) => {
    if (e.key === "Enter") handleSave();
    if (e.key === "Escape") {
      if (savedQuantity !== null) {
        setCardState("selected");
      } else {
        setCardState("default");
      }
    }
  };

  const isEnteringQuantity = cardState === "entering-quantity";
  const isSelected = cardState === "selected";

  return (
    <div
      onClick={!isEnteringQuantity ? handleCardClick : undefined}
      className={`
        @container
        relative
        aspect-square
        w-full
        overflow-hidden
        rounded-[10%]
        transition-all
        duration-300
        select-none
        ${!isEnteringQuantity ? "cursor-pointer" : "cursor-default"}
        ${
          isSelected
            ? "ring-4 ring-white ring-offset-4 ring-offset-green-500 brightness-110"
            : "ring-0"
        }
        ${!isEnteringQuantity && !isSelected ? "hover:brightness-105" : ""}
      `}
    >
      {/* Background */}
      <img
        src={backgroundImage}
        alt={`${cropName} background`}
        className="absolute inset-0 h-full w-full object-cover"
      />

      {/* Dim overlay when entering quantity */}
      <div
        className={`
          absolute inset-0 z-10
          bg-black
          transition-opacity duration-300
          ${isEnteringQuantity ? "opacity-60" : "opacity-0 pointer-events-none"}
        `}
      />

      {/* Quantity input overlay */}
      {isEnteringQuantity && (
        <div
          className="absolute inset-0 z-20 flex flex-col items-center justify-center gap-[5%] px-[8%]"
          onClick={(e) => e.stopPropagation()}
        >
          <label
            className="text-white font-bold tracking-widest uppercase"
            style={{ fontSize: "clamp(0.5rem, 10cqw, 1.25rem)" }}
          >
            Quantity
          </label>

          <input
            ref={inputRef}
            type="number"
            min="0"
            value={quantity}
            onChange={(e) => setQuantity(e.target.value)}
            onKeyDown={handleKeyDown}
            placeholder="0"
            className="
              w-full
              rounded-lg
              border-2 border-white/60
              bg-white/15
              text-center text-white
              placeholder-white/40
              backdrop-blur-sm
              outline-none
              focus:border-white focus:bg-white/25
              transition-all duration-200
              [appearance:textfield]
              [&::-webkit-outer-spin-button]:appearance-none
              [&::-webkit-inner-spin-button]:appearance-none
            "
            style={{
              fontSize: "clamp(0.6rem, 12cqw, 1.5rem)",
              padding: "4% 6%",
            }}
          />

          <button
            onClick={handleSave}
            className="
              w-full
              rounded-lg
              bg-green-500
              font-bold
              tracking-wide
              text-white
              uppercase
              transition-all duration-200
              hover:bg-green-400
              active:scale-95
              disabled:opacity-40
              disabled:cursor-not-allowed
            "
            style={{
              fontSize: "clamp(0.5rem, 9cqw, 1.1rem)",
              padding: "4% 6%",
            }}
            disabled={!quantity.trim() || isNaN(parseFloat(quantity))}
          >
            Save
          </button>
        </div>
      )}

      {/* Selected checkmark + quantity badge */}
      {isSelected && (
        <div className="absolute top-[4%] right-[4%] z-10 flex flex-col items-center gap-[4%]">
          <div className="flex h-[14cqw] w-[14cqw] max-h-16 max-w-16 items-center justify-center rounded-full bg-green-500 shadow-lg">
            <svg
              viewBox="0 0 24 24"
              fill="none"
              stroke="white"
              strokeWidth="3"
              strokeLinecap="round"
              strokeLinejoin="round"
              className="h-[60%] w-[60%]"
            >
              <polyline points="20 6 9 17 4 12" />
            </svg>
          </div>
          {savedQuantity !== null && (
            <div
              className="rounded-full bg-black/50 text-white font-bold text-center backdrop-blur-sm px-[3cqw] py-[1cqw]"
              style={{ fontSize: "clamp(0.4rem, 8cqw, 1rem)" }}
            >
              ×{savedQuantity}
            </div>
          )}
        </div>
      )}

      {/* Content */}
      <div className="relative flex h-full flex-col items-center">
        <div className="flex w-full justify-center pt-[2%]">
          <img
            src={cropImage}
            alt={`${cropName} ${varietyName}`}
            className={`
              h-full w-full object-contain
              transition-all duration-300
              ${isEnteringQuantity ? "scale-90 opacity-40" : ""}
            `}
          />
        </div>

        <div className="mt-auto pb-[2%] text-center">
          <h2
            className="text-white font-black leading-none tracking-tight text-shadow-2xl"
            style={{ fontSize: "clamp(0.6rem, 21cqw, 3.75rem)" }}
          >
            {varietyName}
          </h2>
          <p
            className="text-white/90 font-bold text-shadow-2xl"
            style={{ fontSize: "clamp(0.45rem, 12cqw, 2.25rem)" }}
          >
            {cropName}
          </p>
        </div>
      </div>
    </div>
  );
}
