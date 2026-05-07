import CropCard from "../features/crop/components/CropCard";
import farmBG from "../assets/crop/farm-bg1.png";
import romaImg from "../assets/crop/tomato/roma-logo.png";

export default function HomePage() {
  return (
    <div className="p-6">
      <CropCard
        backgroundImage={farmBG}
        cropImage={romaImg}
        varietyName="Roma"
        cropName="Tomato"
        maxWidth="250px"
      />
    </div>
  );
}
