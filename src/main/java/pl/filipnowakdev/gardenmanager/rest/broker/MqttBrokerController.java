package pl.filipnowakdev.gardenmanager.rest.broker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.filipnowakdev.gardenmanager.exception.EntityAlreadyExistsException;
import pl.filipnowakdev.gardenmanager.exception.EntityNotFoundException;
import pl.filipnowakdev.gardenmanager.managers.broker.MqttBrokerConfigManager;
import pl.filipnowakdev.gardenmanager.model.broker.mqtt.MqttBrokerConfig;

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
    public List<MqttBrokerConfig> getAll() {
        return manager.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MqttBrokerConfig> getById(@PathVariable String id) {
        return manager.getById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    ResponseEntity<MqttBrokerConfig> insert(@RequestBody MqttBrokerConfig brokerConfig) {
        try {
            MqttBrokerConfig insertedConfig = manager.insert(brokerConfig);
            return ResponseEntity.status(HttpStatus.CREATED).body(insertedConfig);
        } catch (EntityAlreadyExistsException ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @PutMapping()
    public ResponseEntity<MqttBrokerConfig> update(@RequestBody MqttBrokerConfig device) {
        try {
            return ResponseEntity.ok(manager.update(device));
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        boolean deleted = manager.delete(id);
        return deleted ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
}
