package pl.filipnowakdev.gardenmanager.rest.device;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.filipnowakdev.gardenmanager.managers.device.DeviceManager;
import pl.filipnowakdev.gardenmanager.managers.device.DeviceNotFoundException;
import pl.filipnowakdev.gardenmanager.model.device.DeviceConfig;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/devices")
public class DeviceController {

    private final DeviceManager manager;

    public DeviceController(DeviceManager manager) {
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
    ResponseEntity<DeviceConfig> insertDevice(@RequestBody DeviceConfig device) throws URISyntaxException {
        DeviceConfig insertedDevice = manager.insertDevice(device);
        return ResponseEntity.created(new URI("/devices/" + insertedDevice.getId())).body(device);
    }

    @PutMapping()
    public ResponseEntity<DeviceConfig> updateDevice(@RequestBody DeviceConfig device) {
        try {
            return ResponseEntity.ok(manager.updateDevice(device));
        } catch (DeviceNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDevice(@PathVariable String id) {
        boolean deleted = manager.deleteDevice(id);
        return deleted ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }


}
