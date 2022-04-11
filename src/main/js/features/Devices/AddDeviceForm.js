import { nanoid } from '@reduxjs/toolkit';
import React, { useState } from 'react';
import { Button, Form } from 'react-bootstrap';
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
            <Form>
                <Form.Group className="mb-3">
                    <Form.Label htmlFor="deviceName">Device name:</Form.Label>
                    <Form.Control
                        type="text"
                        id="deviceName"
                        name="deviceName"
                        value={name}
                        onChange={onNameChanged}
                    />
                </Form.Group>
                <Form.Group className="mb-3">
                    <Form.Label htmlFor="deviceType">Device type:</Form.Label>
                    <Form.Control
                        type="text"
                        id="deviceType"
                        name="deviceType"
                        value={type}
                        onChange={onTypeChanged}
                    />
                </Form.Group>

                <Button variant="primary" type="button" onClick={onSubmit}>Save Device</Button>
            </Form>
        </section>
    );
}