import React from 'react';
import { Button, Stack } from 'react-bootstrap';
import { LinkContainer } from 'react-router-bootstrap';

class Device extends React.Component {
    render() {
        return (
            <tr>
                <td>{this.props.device.name}</td>
                <td>{this.props.device.type}</td>
                <td>
                    <Stack direction="horizontal" gap={2}>
                        <LinkContainer to={`/devices/${this.props.device.id}`}>
                            <Button variant="primary">View</Button>
                        </LinkContainer>
                        <LinkContainer to={`/devices/edit/${this.props.device.id}`}>
                            <Button variant="secondary">Edit</Button>
                        </LinkContainer>
                    </Stack>
                </td>
            </tr>
        )
    }
}

export default Device;