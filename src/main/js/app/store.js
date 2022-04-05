import { configureStore } from '@reduxjs/toolkit';
import devicesReducer from '../features/Devices/devicesSlice';

export default configureStore({
    reducer: {
      devices: devicesReducer
    }
  })