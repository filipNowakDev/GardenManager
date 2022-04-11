import React from 'react';
import { Button } from 'react-bootstrap';
import { LinkContainer } from 'react-router-bootstrap';
import { Link } from 'react-router-dom';

class Device extends React.Component {
    render() {
        return (
            <tr>
                <td>{this.props.device.name}</td>
                <td>{this.props.device.type}</td>
                <td>
                    <LinkContainer to={`/devices/${this.props.device.id}`}>
                        <Button variant="primary">View</Button>
                    </LinkContainer>
                    <LinkContainer to={`/devices/edit/${this.props.device.id}`}>
                        <Button variant="secondary">Edit</Button>
                    </LinkContainer>
                </td>
            </tr>
        )
    }
}

export default Device;