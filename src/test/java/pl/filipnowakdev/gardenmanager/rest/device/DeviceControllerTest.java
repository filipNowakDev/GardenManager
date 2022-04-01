package pl.filipnowakdev.gardenmanager.rest.device;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import pl.filipnowakdev.gardenmanager.exception.EntityAlreadyExistsException;
import pl.filipnowakdev.gardenmanager.exception.EntityNotFoundException;
import pl.filipnowakdev.gardenmanager.managers.device.DeviceConfigManager;
import pl.filipnowakdev.gardenmanager.model.device.DeviceConfig;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DeviceControllerTest {

    public static final String TEST_ID = "test_id";
    @Mock
    private DeviceConfigManager deviceConfigManager;

    @InjectMocks
    private DeviceController deviceController;


    @Test
    void getAllDevices() {
        when(deviceConfigManager.getAll()).thenReturn(List.of(
                mock(DeviceConfig.class),
                mock(DeviceConfig.class),
                mock(DeviceConfig.class)
        ));

        List<DeviceConfig> result = deviceController.getAllDevices();

        assertNotNull(result);
        assertEquals(3, result.size());
    }

    @Test
    void getAllDevicesEmptyList() {
        when(deviceConfigManager.getAll()).thenReturn(List.of());
        List<DeviceConfig> result = deviceController.getAllDevices();

        assertNotNull(result);
        assertEquals(0, result.size());
    }

    @Test
    void getDevice() {
        DeviceConfig mockDeviceConfig = mock(DeviceConfig.class);
        when(deviceConfigManager.getById(any())).thenReturn(Optional.of(mockDeviceConfig));

        ResponseEntity<DeviceConfig> result = deviceController.getDevice(TEST_ID);

        assertNotNull(result);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertSame(mockDeviceConfig, result.getBody());
    }

    @Test
    void getDeviceNotExistingDevice() {
        when(deviceConfigManager.getById(any())).thenReturn(Optional.empty());

        ResponseEntity<DeviceConfig> result = deviceController.getDevice(TEST_ID);

        assertNotNull(result);
        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
    }

    @Test
    void insertDevice() {
        DeviceConfig mockDeviceConfig = mock(DeviceConfig.class);
        when(deviceConfigManager.insertDevice(any())).thenReturn(mockDeviceConfig);

        ResponseEntity<DeviceConfig> result = deviceController.insertDevice(mockDeviceConfig);

        assertNotNull(result);
        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertSame(mockDeviceConfig, result.getBody());
    }

    @Test
    void insertDeviceDeviceAlreadyExists() {
        DeviceConfig mockDeviceConfig = mock(DeviceConfig.class);
        when(deviceConfigManager.insertDevice(any())).thenThrow(new EntityAlreadyExistsException());

        ResponseEntity<DeviceConfig> result = deviceController.insertDevice(mockDeviceConfig);

        assertNotNull(result);
        assertEquals(HttpStatus.CONFLICT, result.getStatusCode());
    }

    @Test
    void updateDevice() {
        DeviceConfig mockDeviceConfig = mock(DeviceConfig.class);
        when(deviceConfigManager.updateDevice(any())).thenReturn(mockDeviceConfig);

        ResponseEntity<DeviceConfig> result = deviceController.updateDevice(mockDeviceConfig);

        assertNotNull(result);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(mockDeviceConfig, result.getBody());
    }

    @Test
    void updateDeviceNotExists() {
        DeviceConfig mockDeviceConfig = mock(DeviceConfig.class);
        when(deviceConfigManager.updateDevice(any())).thenThrow(new EntityNotFoundException());

        ResponseEntity<DeviceConfig> result = deviceController.updateDevice(mockDeviceConfig);

        assertNotNull(result);
        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
    }

    @Test
    void deleteDevice() {
        when(deviceConfigManager.deleteDevice(any())).thenReturn(true);

        ResponseEntity<Void> result = deviceController.deleteDevice(TEST_ID);

        assertNotNull(result);
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    void deleteDeviceNotExists() {
        when(deviceConfigManager.deleteDevice(any())).thenReturn(false);

        ResponseEntity<Void> result = deviceController.deleteDevice(TEST_ID);

        assertNotNull(result);
        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
    }
}