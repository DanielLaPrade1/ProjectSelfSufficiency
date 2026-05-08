type CropCardProps = {
  backgroundImage: string;
  cropImage: string;
  varietyName: string;
  cropName: string;
};

export function CropCard({
  backgroundImage,
  cropImage,
  varietyName,
  cropName,
}: CropCardProps) {
  return (
    <div
      className="
        @container
        relative
        aspect-square
        w-full
        overflow-hidden
        rounded-[10%]
      "
    >
      {/* Background */}
      <img
        src={backgroundImage}
        alt={`${cropName} background`}
        className="absolute inset-0 h-full w-full object-cover"
      />

      {/* Content */}
      <div className="relative flex h-full flex-col items-center">
        {/* Crop Image */}
        <div className="flex w-full justify-center pt-[2%]">
          <img
            src={cropImage}
            alt={`${cropName} ${varietyName}`}
            className="
              h-full
              w-full
              object-contain
              transition-transform
              duration-300
              hover:scale-105
            "
          />
        </div>

        {/* Text */}
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
