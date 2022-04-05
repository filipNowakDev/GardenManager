import React from 'react';
import { Link } from 'react-router-dom';

class Device extends React.Component{
	render() {
		return (
			<tr>
				<td>{this.props.device.name}</td>
				<td>{this.props.device.type}</td>
                <td><Link to={`/devices/${this.props.device.id}`}>View</Link></td>
                <td><Link to={`/devices/edit/${this.props.device.id}`}>Edit</Link></td>
			</tr>
		)
	}
}

export default Device;