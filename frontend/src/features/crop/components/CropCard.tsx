type CropCardProps = {
  backgroundImage: string;
  cropImage: string;
  varietyName: string;
  cropName: string;
  maxWidth?: string;
};

export default function CropCard({
  backgroundImage,
  cropImage,
  varietyName,
  cropName,
  maxWidth = "",
}: CropCardProps) {
  return (
    <div
      className={`relative aspect-square w-full overflow-hidden rounded-[10%] ${maxWidth}`}
    >
      {/* Background */}
      <img
        src={backgroundImage}
        alt={`${cropName} background`}
        className="absolute inset-0 h-full w-full object-cover"
      />

      {/* Overlays */}
      <div className="absolute inset-0 bg-black/45" />
      <div className="absolute inset-0 bg-gradient-to-b from-transparent to-black/30" />

      {/* Content */}
      <div className="relative flex h-full flex-col items-center">
        {/* Crop Image */}
        <div className="flex justify-center w-full pt-[5%]">
          <img
            src={cropImage}
            alt={cropName}
            className="
              w-[100%]
              h-[100%]
              object-contain
              drop-shadow-[0_10px_20px_rgba(0,0,0,0.5)]
              transition-transform
              duration-300
              hover:scale-105
            "
          />
        </div>

        {/* Text */}
        <div className="text-center mt-auto pb-[8%]">
          <h2
            className="
              text-white
              font-black
              leading-none
              tracking-tight
              text-[clamp(1rem,15vw,12rem)]
            "
          >
            {varietyName}
          </h2>

          <p
            className="
              text-white/90
              font-bold
              text-[clamp(0.45rem,7vw,7rem)]
            "
          >
            {cropName}
          </p>
        </div>
      </div>
    </div>
  );
}
