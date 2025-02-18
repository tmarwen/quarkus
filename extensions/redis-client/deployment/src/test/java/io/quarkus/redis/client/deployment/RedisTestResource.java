package io.quarkus.redis.client.deployment;

import java.util.Map;

import org.testcontainers.containers.GenericContainer;
import org.testcontainers.utility.DockerImageName;

import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;

public class RedisTestResource implements QuarkusTestResourceLifecycleManager {

    static GenericContainer<?> server = new GenericContainer<>(
            DockerImageName.parse("redis:7-alpine"))
            .withExposedPorts(6379);

    @Override
    public Map<String, String> start() {
        server.start();
        return Map.of("quarkus.redis.tr", "redis://" + server.getHost() + ":" + server.getMappedPort(6379));
    }

    @Override
    public void stop() {
        server.stop();
    }
}
