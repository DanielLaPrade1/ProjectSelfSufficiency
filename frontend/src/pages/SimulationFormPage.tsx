import { CropCard, CropGrid, useCrops } from "../features/crop";

export default function HomePage() {
  const { data: crops, isLoading, error } = useCrops();

  if (isLoading) {
    return <p>Loading Crop Cards...</p>;
  }

  if (error) {
    return <p>Error loading crops.</p>;
  }

  return (
    <div className="h-[100%], w-[100%]">
      <CropGrid minCardWidth="150px" gap="1em">
        {crops?.map((crop) => (
          <CropCard
            key={crop.varietyID}
            backgroundImage={"/images/crop/farm-bg-1.webp"}
            cropImage={crop.varietyImageUrl}
            varietyName={crop.varietyName}
            cropName={crop.cropName}
          />
        ))}
      </CropGrid>
    </div>
  );
}
