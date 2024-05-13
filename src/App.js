import React, { useEffect } from 'react';
import { Entity, Scene } from 'aframe-react';
import 'aframe';
import 'aframe-ar';

function App() {
  useEffect(() => {
    // Your GPS coordinates
    const latitude = 50.64321886997472;
    const longitude = 3.059177874980513;

    // Set the GPS coordinates to the entity
    const entity = document.querySelector('[gps-new-entity-place]');
    entity.setAttribute('gps-new-entity-place', `latitude: ${latitude}; longitude: ${longitude}`);
  }, []);

  return (
    <div className="App">
      <Scene vr-mode-ui="enabled: false" arjs="sourceType: webcam; videoTexture: true; debugUIEnabled: false" renderer="antialias: true; alpha: true">
        <Entity camera gps-new-camera="gpsMinDistance: 5"></Entity>
        <Entity material="color: red" geometry="primitive: box" gps-new-entity-place="" scale="10 10 10"></Entity>
      </Scene>
    </div>
  );
}

export default App;
