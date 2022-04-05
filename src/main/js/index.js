import 'bootstrap/dist/css/bootstrap.min.css';
import React from 'react';
import * as ReactDOMClient from 'react-dom/client';
import App from './App';
import DevicesPage from './features/Devices/DevicesPage';
import { Provider } from 'react-redux';
import store from './app/store';
import {
  BrowserRouter,
  Routes,
  Route
} from 'react-router-dom';
import { DeviceDetailsPage } from './features/Devices/DeviceDetailsPage';
import WelcomePage from './features/WelcomePage/WelcomePage';
import { EditDeviceForm } from './features/Devices/EditDeviceForm';

const container = document.getElementById('react');
const root = ReactDOMClient.createRoot(container);

root.render(
  <React.StrictMode>
    <Provider store={store}>
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<App />}>
            <Route path="/" element={<WelcomePage />} />
            <Route path="devices" element={<DevicesPage />} />
            <Route path="devices/:deviceId" element={<DeviceDetailsPage />} />
            <Route path="devices/edit/:deviceId" element={<EditDeviceForm />} />
          </Route>
        </Routes>
      </BrowserRouter>
    </Provider>
  </React.StrictMode>
)