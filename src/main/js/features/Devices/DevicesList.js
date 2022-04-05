import React from 'react';
import { useSelector } from 'react-redux';
import Device from './Device';

export const DevicesList = () => {
    const devices = useSelector(state => state.devices);

    const renderedDevices = devices.map(device =>
        <Device key={device.id} device={device} />
    );

    return (
        <div>
            <h2>Devices</h2>
            <table>
                <tbody>
                    <tr>
                        <th>Name</th>
                        <th>Type</th>
                        <th>Details</th>
                        <th>Edit</th>
                    </tr>
                    {renderedDevices}
                </tbody>
            </table>
        </div>
    );
}