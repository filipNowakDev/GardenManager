package pl.filipnowakdev.gardenmanager.rest.device;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.filipnowakdev.gardenmanager.managers.device.DeviceManager;
import pl.filipnowakdev.gardenmanager.managers.device.DeviceNotFoundException;
import pl.filipnowakdev.gardenmanager.model.device.Device;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/devices")
public class DeviceController {

    private final DeviceManager manager;

    public DeviceController(DeviceManager manager) {
        this.manager = manager;
    }

    @GetMapping
    public List<Device> getAllDevices() {
        return manager.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Device> getDevice(@PathVariable String id) {
        return manager.getById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    ResponseEntity<Device> insertDevice(@RequestBody Device device) throws URISyntaxException {
        Device insertedDevice = manager.insertDevice(device);
        return ResponseEntity.created(new URI("/clients/" + insertedDevice.getId())).body(device);
    }

    @PutMapping()
    public ResponseEntity updateDevice(@RequestBody Device device) {
        try {
            return ResponseEntity.ok(manager.updateDevice(device));
        } catch (DeviceNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteDevice(@PathVariable String id) {
        manager.deleteDevice(id);
        return ResponseEntity.ok().build();
    }


}
