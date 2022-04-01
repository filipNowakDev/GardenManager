package pl.filipnowakdev.gardenmanager.rest.broker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.filipnowakdev.gardenmanager.exception.EntityAlreadyExistsException;
import pl.filipnowakdev.gardenmanager.exception.EntityNotFoundException;
import pl.filipnowakdev.gardenmanager.managers.broker.MqttBrokerConfigManager;
import pl.filipnowakdev.gardenmanager.model.broker.mqtt.MqttBrokerConfig;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/mqtt-brokers")
public class MqttBrokerController {

    private final MqttBrokerConfigManager manager;

    @Autowired
    public MqttBrokerController(MqttBrokerConfigManager manager) {
        this.manager = manager;
    }

    @GetMapping
    public List<MqttBrokerConfig> getAllDevices() {
        return manager.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MqttBrokerConfig> getDevice(@PathVariable String id) {
        return manager.getById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    ResponseEntity<MqttBrokerConfig> insertDevice(@RequestBody MqttBrokerConfig brokerConfig) throws URISyntaxException {
        try {
            MqttBrokerConfig insertedDevice = manager.insertBroker(brokerConfig);
            return ResponseEntity.created(new URI("/devices/" + insertedDevice.getId())).body(brokerConfig);
        } catch (EntityAlreadyExistsException ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @PutMapping()
    public ResponseEntity<MqttBrokerConfig> updateDevice(@RequestBody MqttBrokerConfig device) {
        try {
            return ResponseEntity.ok(manager.updateBroker(device));
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDevice(@PathVariable String id) {
        boolean deleted = manager.deleteBroker(id);
        return deleted ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
}
