import React, { useState, useEffect } from 'react';
import { Box, CircularProgress, Typography } from '@mui/material';
import { MapContainer, TileLayer, Marker, Popup, useMap } from 'react-leaflet';
import L from 'leaflet';
import 'leaflet/dist/leaflet.css';
import carImage from "../../assets/images/car.png";
import './Map.scss';
import location from '../../assets/images/location.png';

// Fix Leaflet icon issue
delete L.Icon.Default.prototype._getIconUrl;
L.Icon.Default.mergeOptions({
  iconRetinaUrl: 'https://unpkg.com/leaflet@1.7.1/dist/images/marker-icon-2x.png',
  iconUrl: 'https://unpkg.com/leaflet@1.7.1/dist/images/marker-icon.png',
  shadowUrl: 'https://unpkg.com/leaflet@1.7.1/dist/images/marker-shadow.png',
});

// Custom icons
const userIcon = new L.Icon({
  iconUrl: location,
  iconSize: [38, 38],
  iconAnchor: [19, 38],
  popupAnchor: [0, -38],
});



// Component to recenter map when location changes
function SetViewOnChange({ coords }) {
  const map = useMap();
  map.setView(coords, map.getZoom());
  return null;
}

// Create a custom car icon
const carIcon = new L.Icon({
  iconUrl: carImage,
  iconSize: [40, 40],
  iconAnchor: [20, 20],
  popupAnchor: [0, -20],
});

const Map = () => {
  const [loading, setLoading] = useState(true);
  const [drivers, setDrivers] = useState([]);
  const [userLocation, setUserLocation] = useState({ lat: 40.7128, lng: -74.0060 }); // Default NYC
  const [mapCenter, setMapCenter] = useState({ lat: 40.7128, lng: -74.0060 });
  const [zoom, setZoom] = useState(13);

  // Mock data for available drivers
  const mockDrivers = [
    { 
      id: 1, 
      name: 'John D.', 
      rating: 4.8, 
      car: 'Toyota Camry', 
      plate: 'ABC123', 
      eta: '3 min',
      location: { lat: 40.7138, lng: -74.0070 },
    },
    { 
      id: 2, 
      name: 'Sarah M.', 
      rating: 4.9, 
      car: 'Honda Civic', 
      plate: 'XYZ789', 
      eta: '5 min',
      location: { lat: 40.7148, lng: -74.0050 },
    },
    { 
      id: 3, 
      name: 'Robert K.', 
      rating: 4.7, 
      car: 'Tesla Model 3', 
      plate: 'TES321', 
      eta: '7 min',
      location: { lat: 40.7118, lng: -74.0080 },
    },
  ];

  useEffect(() => {
    // Simulate loading map and fetching drivers
    const timer = setTimeout(() => {
      setLoading(false);
      setDrivers(mockDrivers);
    }, 1500);

    // Get user's current location if available
    if (navigator.geolocation) {
      navigator.geolocation.getCurrentPosition(
        (position) => {
          const newLocation = {
            lat: position.coords.latitude,
            lng: position.coords.longitude
          };
          setUserLocation(newLocation);
          setMapCenter(newLocation);
        },
        (error) => {
          console.error("Error getting location:", error);
        }
      );
    }

    return () => clearTimeout(timer);
  }, []);

  // Updated car positions
  const carPositions = [
    { id: 1, lat: 31.334586, lng:  75.571922, heading: 45 },
  ];

  return (
    <div className="map-page">
      {loading ? (
        <Box className="map-loading">
          <CircularProgress />
          <Typography variant="body1" mt={2}>Loading map...</Typography>
        </Box>
      ) : (
        <div className="map-view">
          <MapContainer 
            center={[mapCenter.lat, mapCenter.lng]} 
            zoom={zoom} 
            style={{ height: '100vh', width: '100%' }}
          >
            <TileLayer
              attribution='&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
              url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
            />
            <SetViewOnChange coords={[mapCenter.lat, mapCenter.lng]} />
            
            {/* User marker */}
            <Marker 
              position={[userLocation.lat, userLocation.lng]}
              icon={userIcon}
            >
              <Popup>Your location</Popup>
            </Marker>
            
            {/* Car markers */}
            {carPositions.map((car) => (
              <Marker
                key={car.id}
                position={[car.lat, car.lng]}
                icon={carIcon}
                rotationAngle={car.heading}
              >
                <Popup>
                  <Typography variant="body2">Car #{car.id}</Typography>
                </Popup>
              </Marker>
            ))}
          </MapContainer>
        </div>
      )}
    </div>
  );
};

export default Map;