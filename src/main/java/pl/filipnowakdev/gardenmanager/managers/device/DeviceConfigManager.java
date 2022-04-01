package pl.filipnowakdev.gardenmanager.managers.device;

import com.mongodb.client.result.DeleteResult;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;
import pl.filipnowakdev.gardenmanager.exception.EntityAlreadyExistsException;
import pl.filipnowakdev.gardenmanager.exception.EntityNotFoundException;
import pl.filipnowakdev.gardenmanager.model.device.DeviceConfig;

import java.util.List;
import java.util.Optional;

import static org.springframework.data.mongodb.core.query.Criteria.where;

@Component
public class DeviceConfigManager {

    private final MongoOperations mongoOps;

    public DeviceConfigManager(MongoOperations mongoOps) {
        this.mongoOps = mongoOps;
    }

    public List<DeviceConfig> getAll() {
        return mongoOps.findAll(DeviceConfig.class);
    }

    public Optional<DeviceConfig> getById(String id) {
        return Optional.ofNullable(mongoOps.findById(id, DeviceConfig.class));
    }

    public DeviceConfig insertDevice(DeviceConfig device) {
        try {
            return mongoOps.insert(device);
        } catch (DuplicateKeyException ex) {
            throw new EntityAlreadyExistsException();
        }
    }

    public DeviceConfig updateDevice(DeviceConfig device) {
        DeviceConfig currentDevice = Optional.ofNullable(mongoOps.findById(device.getId(), DeviceConfig.class)).orElseThrow(EntityNotFoundException::new);
        mongoOps.save(device);
        return currentDevice;
    }

    public boolean deleteDevice(String id) {
        DeleteResult result = mongoOps.remove(Query.query(where("_id").is(id)), DeviceConfig.class);
        return result.getDeletedCount() == 1;
    }



}
