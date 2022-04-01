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

import static org.assertj.core.api.Assertions.assertThat;
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

        List<DeviceConfig> result = deviceController.getAll();

        assertThat(result).isNotNull();
        assertThat(result).hasSize(3);
    }

    @Test
    void getAllDevicesEmptyList() {
        when(deviceConfigManager.getAll()).thenReturn(List.of());
        List<DeviceConfig> result = deviceController.getAll();

        assertThat(result).isNotNull();
        assertThat(result).isEmpty();
    }

    @Test
    void getDevice() {
        DeviceConfig mockDeviceConfig = mock(DeviceConfig.class);
        when(deviceConfigManager.getById(any())).thenReturn(Optional.of(mockDeviceConfig));

        ResponseEntity<DeviceConfig> result = deviceController.getById(TEST_ID);

        assertThat(result).isNotNull();
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(result.getBody()).isSameAs(mockDeviceConfig);
    }

    @Test
    void getDeviceNotExistingDevice() {
        when(deviceConfigManager.getById(any())).thenReturn(Optional.empty());

        ResponseEntity<DeviceConfig> result = deviceController.getById(TEST_ID);

        assertThat(result).isNotNull();
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    void insertDevice() {
        DeviceConfig mockDeviceConfig = mock(DeviceConfig.class);
        when(deviceConfigManager.insert(any())).thenReturn(mockDeviceConfig);

        ResponseEntity<DeviceConfig> result = deviceController.insert(mockDeviceConfig);

        assertThat(result).isNotNull();
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(result.getBody()).isSameAs(mockDeviceConfig);
    }

    @Test
    void insertDeviceDeviceAlreadyExists() {
        DeviceConfig mockDeviceConfig = mock(DeviceConfig.class);
        when(deviceConfigManager.insert(any())).thenThrow(new EntityAlreadyExistsException());

        ResponseEntity<DeviceConfig> result = deviceController.insert(mockDeviceConfig);

        assertThat(result).isNotNull();
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.CONFLICT);
    }

    @Test
    void updateDevice() {
        DeviceConfig mockDeviceConfig = mock(DeviceConfig.class);
        when(deviceConfigManager.update(any())).thenReturn(mockDeviceConfig);

        ResponseEntity<DeviceConfig> result = deviceController.update(mockDeviceConfig);

        assertThat(result).isNotNull();
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(result.getBody()).isSameAs(mockDeviceConfig);
    }

    @Test
    void updateDeviceNotExists() {
        DeviceConfig mockDeviceConfig = mock(DeviceConfig.class);
        when(deviceConfigManager.update(any())).thenThrow(new EntityNotFoundException());

        ResponseEntity<DeviceConfig> result = deviceController.update(mockDeviceConfig);

        assertThat(result).isNotNull();
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    void deleteDevice() {
        when(deviceConfigManager.delete(any())).thenReturn(true);

        ResponseEntity<Void> result = deviceController.delete(TEST_ID);

        assertThat(result).isNotNull();
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void deleteDeviceNotExists() {
        when(deviceConfigManager.delete(any())).thenReturn(false);

        ResponseEntity<Void> result = deviceController.delete(TEST_ID);

        assertThat(result).isNotNull();
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }
}