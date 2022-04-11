import React from 'react';
import { Outlet } from 'react-router-dom';

import { NavigationBar } from './app/NavigationBar'


function App() {
	return (
		<div>
			<NavigationBar />
			<Outlet />
		</div>
	)
}

export default App;