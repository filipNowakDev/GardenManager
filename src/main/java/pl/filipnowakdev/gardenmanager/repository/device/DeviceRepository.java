package pl.filipnowakdev.gardenmanager.repository.device;

import org.springframework.data.mongodb.repository.MongoRepository;
import pl.filipnowakdev.gardenmanager.model.device.Device;

public interface DeviceRepository extends MongoRepository<Device, String> {
}
