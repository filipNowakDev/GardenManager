import React from 'react';
import { DevicesList } from './DevicesList';
import { AddDeviceForm } from './AddDeviceForm';
import { Col, Container, Row } from 'react-bootstrap';

function DevicesPage() {
    return (

        <Container fluid='md'>
            <Row>
                <Col sm={8}>
                    <DevicesList />
                </Col>
                <Col sm={4}>
                    <AddDeviceForm />
                </Col>
            </Row>
        </Container>
    )
}

export default DevicesPage;