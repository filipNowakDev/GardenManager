package pl.filipnowakdev.gardenmanager.rest.device;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.filipnowakdev.gardenmanager.exception.EntityAlreadyExistsException;
import pl.filipnowakdev.gardenmanager.exception.EntityNotFoundException;
import pl.filipnowakdev.gardenmanager.managers.device.DeviceConfigManager;
import pl.filipnowakdev.gardenmanager.model.device.DeviceConfig;

import java.util.List;

@RestController
@RequestMapping("/devices")
public class DeviceController {

    private final DeviceConfigManager manager;

    public DeviceController(DeviceConfigManager manager) {
        this.manager = manager;
    }

    @GetMapping
    public List<DeviceConfig> getAllDevices() {
        return manager.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DeviceConfig> getDevice(@PathVariable String id) {
        return manager.getById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    ResponseEntity<DeviceConfig> insertDevice(@RequestBody DeviceConfig device) {
        try {
            DeviceConfig insertedDevice = manager.insertDevice(device);
            return ResponseEntity.status(HttpStatus.CREATED).body(insertedDevice);
        } catch (EntityAlreadyExistsException ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @PutMapping()
    public ResponseEntity<DeviceConfig> updateDevice(@RequestBody DeviceConfig device) {
        try {
            return ResponseEntity.ok(manager.updateDevice(device));
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDevice(@PathVariable String id) {
        boolean deleted = manager.deleteDevice(id);
        return deleted ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
}
