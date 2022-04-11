import React from 'react';
import { useSelector } from 'react-redux';
import { useParams } from 'react-router-dom';
import { Link } from 'react-router-dom';
import { selectDeviceById } from './devicesSlice';

export const DeviceDetailsPage = () => {
    let params = useParams();

    const device = useSelector(state => selectDeviceById(state, params.deviceId))

    if (!device) {
        return (
          <section>
            <h2>Device not found!</h2>
          </section>
        )
      }
    
      return (
        <section>
          <div>
            <h2>{device.name}</h2>
            <Link to={`/devices/edit/${device.id}`}>Edit</Link>
            <p>Type: {device.type}</p>
          </div>
        </section>
      )
}