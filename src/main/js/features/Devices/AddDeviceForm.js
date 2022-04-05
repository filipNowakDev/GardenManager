import { nanoid } from '@reduxjs/toolkit';
import React, { useState } from 'react';
import { useDispatch } from 'react-redux';
import { deviceAdded } from './devicesSlice';

export const AddDeviceForm = () => {
    const [name, setName] = useState('');
    const [type, setType] = useState('');
    const dispatch = useDispatch();

    const onNameChanged = e => setName(e.target.value)
    const onTypeChanged = e => setType(e.target.value)
    const onSubmit = () => {
        if (name && type) {
            dispatch(deviceAdded({
                id: nanoid(),
                name,
                type
            }))
        }
        setName('')
        setType('')
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