import { createSlice, nanoid } from '@reduxjs/toolkit';

const initialState = [
    {
        id: nanoid(),
        name: "device1",
        type: "mqtt"
    },
    {
        id: nanoid(),
        name: "device2",
        type: "http"
    }
];

const devicesSlice = createSlice({
    name: "devices",
    initialState,
    reducers: {
        deviceAdded(state, action) {
            state.push(action.payload);
        },
        deviceUpdated(state, action) {
            const {id, name, type} = action.payload;
            const existingDevice = state.find(device => device.id === id)
            if (existingDevice) {
                existingDevice.name = name;
                existingDevice.type = type;
            }
        }
    }
});

export const {deviceAdded, deviceUpdated} = devicesSlice.actions;

export default devicesSlice.reducer;