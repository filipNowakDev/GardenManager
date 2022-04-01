package pl.filipnowakdev.gardenmanager.rest.broker;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import pl.filipnowakdev.gardenmanager.exception.EntityAlreadyExistsException;
import pl.filipnowakdev.gardenmanager.exception.EntityNotFoundException;
import pl.filipnowakdev.gardenmanager.managers.broker.MqttBrokerConfigManager;
import pl.filipnowakdev.gardenmanager.model.broker.mqtt.MqttBrokerConfig;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MqttBrokerControllerTest {

    public static final String TEST_ID = "test_id";
    @Mock
    private MqttBrokerConfigManager mqttBrokerConfigManager;

    @InjectMocks
    private MqttBrokerController mqttBrokerController;

    @Test
    void getAllBrokers() {
        when(mqttBrokerConfigManager.getAll()).thenReturn(List.of(
                mock(MqttBrokerConfig.class),
                mock(MqttBrokerConfig.class),
                mock(MqttBrokerConfig.class)
        ));

        List<MqttBrokerConfig> result = mqttBrokerController.getAll();

        assertThat(result).isNotNull();
        assertThat(result).hasSize(3);
    }

    @Test
    void getAllBrokersEmptyList() {
        when(mqttBrokerConfigManager.getAll()).thenReturn(List.of());
        List<MqttBrokerConfig> result = mqttBrokerController.getAll();

        assertThat(result).isNotNull();
        assertThat(result).isEmpty();
    }

    @Test
    void getBroker() {
        MqttBrokerConfig mockMqttBrokerConfig = mock(MqttBrokerConfig.class);
        when(mqttBrokerConfigManager.getById(any())).thenReturn(Optional.of(mockMqttBrokerConfig));

        ResponseEntity<MqttBrokerConfig> result = mqttBrokerController.getById(TEST_ID);

        assertThat(result).isNotNull();
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(result.getBody()).isSameAs(mockMqttBrokerConfig);
    }

    @Test
    void getBrokerNotExistingBroker() {
        when(mqttBrokerConfigManager.getById(any())).thenReturn(Optional.empty());

        ResponseEntity<MqttBrokerConfig> result = mqttBrokerController.getById(TEST_ID);

        assertThat(result).isNotNull();
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    void insertBroker() {
        MqttBrokerConfig mockMqttBrokerConfig = mock(MqttBrokerConfig.class);
        when(mqttBrokerConfigManager.insert(any())).thenReturn(mockMqttBrokerConfig);

        ResponseEntity<MqttBrokerConfig> result = mqttBrokerController.insert(mockMqttBrokerConfig);

        assertThat(result).isNotNull();
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(result.getBody()).isSameAs(mockMqttBrokerConfig);
    }

    @Test
    void insertBrokerBrokerAlreadyExists() {
        MqttBrokerConfig mockMqttBrokerConfig = mock(MqttBrokerConfig.class);
        when(mqttBrokerConfigManager.insert(any())).thenThrow(new EntityAlreadyExistsException());

        ResponseEntity<MqttBrokerConfig> result = mqttBrokerController.insert(mockMqttBrokerConfig);

        assertThat(result).isNotNull();
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.CONFLICT);
    }

    @Test
    void updateBroker() {
        MqttBrokerConfig mockMqttBrokerConfig = mock(MqttBrokerConfig.class);
        when(mqttBrokerConfigManager.update(any())).thenReturn(mockMqttBrokerConfig);

        ResponseEntity<MqttBrokerConfig> result = mqttBrokerController.update(mockMqttBrokerConfig);

        assertThat(result).isNotNull();
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(result.getBody()).isSameAs(mockMqttBrokerConfig);
    }

    @Test
    void updateBrokerNotExists() {
        MqttBrokerConfig mockMqttBrokerConfig = mock(MqttBrokerConfig.class);
        when(mqttBrokerConfigManager.update(any())).thenThrow(new EntityNotFoundException());

        ResponseEntity<MqttBrokerConfig> result = mqttBrokerController.update(mockMqttBrokerConfig);

        assertThat(result).isNotNull();
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    void deleteBroker() {
        when(mqttBrokerConfigManager.delete(any())).thenReturn(true);

        ResponseEntity<Void> result = mqttBrokerController.delete(TEST_ID);

        assertThat(result).isNotNull();
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void deleteBrokerNotExists() {
        when(mqttBrokerConfigManager.delete(any())).thenReturn(false);

        ResponseEntity<Void> result = mqttBrokerController.delete(TEST_ID);

        assertThat(result).isNotNull();
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }
}