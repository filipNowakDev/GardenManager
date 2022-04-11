import React, { useState } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { useNavigate, useParams } from 'react-router-dom';
import { deviceUpdated, selectDeviceById } from './devicesSlice';

export const EditDeviceForm = () => {
    let params = useParams();

    const device = useSelector(state => selectDeviceById(state, params.deviceId))

    const [name, setName] = useState(device.name);
    const [type, setType] = useState(device.type);
    
    const dispatch = useDispatch();
    const navigate = useNavigate();

    const onNameChanged = e => setName(e.target.value)
    const onTypeChanged = e => setType(e.target.value)
    const onSubmit = () => {
        if (name && type) {
            dispatch(deviceUpdated({
                id: device.id,
                name,
                type
            }))
        }
        navigate(`/devices/${device.id}`)
    };

    return (
        <section>
            <h2>Add a new device</h2>
            <form>
                <label htmlFor="deviceName">Device name:</label>
                <input
                    type="text"
                    id="deviceName"
                    name="deviceName"
                    value={name}
                    onChange={onNameChanged}
                />
                <label htmlFor="deviceType">Device type:</label>
                <input
                    type="text"
                    id="deviceType"
                    name="deviceType"
                    value={type}
                    onChange={onTypeChanged}
                />
                <button type="button" onClick={onSubmit}>Save Device</button>
            </form>
        </section>
    );
}