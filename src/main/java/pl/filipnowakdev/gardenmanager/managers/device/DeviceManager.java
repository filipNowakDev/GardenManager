package pl.filipnowakdev.gardenmanager.managers.device;

import org.springframework.stereotype.Service;
import pl.filipnowakdev.gardenmanager.model.device.Device;
import pl.filipnowakdev.gardenmanager.repository.device.DeviceRepository;

import java.util.List;
import java.util.Optional;

@Service
public class DeviceManager {

    private final DeviceRepository repository;

    public DeviceManager(DeviceRepository repository) {
        this.repository = repository;
    }

    public List<Device> getAll() {
        return repository.findAll();
    }

    public Optional<Device> getById(String id) {
        return repository.findById(id);
    }

    public Device insertDevice(Device device) {
        return repository.save(device);
    }

    public Device updateDevice(Device device) {
        Device currentDevice = repository.findById(device.getId()).orElseThrow(DeviceNotFoundException::new);
        currentDevice.setName(device.getName());
        currentDevice.setType(device.getType());
        repository.save(currentDevice);
        return currentDevice;
    }

    public void deleteDevice(String id) {
        repository.deleteById(id);
    }



}
