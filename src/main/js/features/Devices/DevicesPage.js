import React from 'react';
import { DevicesList } from './DevicesList';
import { Navbar } from '../../app/Navbar';
import {AddDeviceForm} from './AddDeviceForm';

function DevicesPage() {
	return (
		<div>
            <h1>Manage Devices</h1>
			<div>
                <AddDeviceForm />
			    <DevicesList />
			</div>
		</div>
	)
}

export default DevicesPage;