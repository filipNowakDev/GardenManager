package pl.filipnowakdev.gardenmanager.managers.broker;

import com.mongodb.client.result.DeleteResult;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;
import pl.filipnowakdev.gardenmanager.model.broker.mqtt.MqttBrokerConfig;

import java.util.List;
import java.util.Optional;

import static org.springframework.data.mongodb.core.query.Criteria.where;

@Component
//TODO encrypt broker credentials
public class MqttBrokerManager {

    private final MongoOperations mongoOps;

    public MqttBrokerManager(MongoOperations mongoOps) {
        this.mongoOps = mongoOps;
    }

    public List<MqttBrokerConfig> getAll() {
        return mongoOps.findAll(MqttBrokerConfig.class);
    }

    public Optional<MqttBrokerConfig> getById(String id) {
        return Optional.ofNullable(mongoOps.findById(id, MqttBrokerConfig.class));
    }

    public MqttBrokerConfig insertBroker(MqttBrokerConfig broker) {
        return mongoOps.insert(broker);
    }

    public MqttBrokerConfig updateBroker(MqttBrokerConfig broker) {
        MqttBrokerConfig currentBroker = Optional.ofNullable(mongoOps.findById(broker.getId(), MqttBrokerConfig.class)).orElseThrow(BrokerNotFoundException::new);
        mongoOps.save(broker);
        return currentBroker;
    }

    public boolean deleteBroker(String id) {
        DeleteResult result = mongoOps.remove(Query.query(where("_id").is(id)), MqttBrokerConfig.class);
        return result.getDeletedCount() == 1;    }
}
