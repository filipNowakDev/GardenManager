import React from 'react';
import { Table } from 'react-bootstrap';
import { useSelector } from 'react-redux';
import Device from './Device';
import { selectAllDevices } from './devicesSlice';

export const DevicesList = () => {
    const devices = useSelector(selectAllDevices);

    const renderedDevices = devices.map(device =>
        <Device key={device.id} device={device} />
    );

    return (
        <div>
            <h2>Devices</h2>
            <Table striped bordered hover>
                <tbody>
                    <tr>
                        <th>Name</th>
                        <th>Type</th>
                        <th>Actions</th>
                    </tr>
                    {renderedDevices}
                </tbody>
            </Table>
        </div>
    );
}