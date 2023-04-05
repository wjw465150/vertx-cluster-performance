package org.wjw.vertx.performance;

import java.io.IOException;

import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.spi.cluster.ClusterManager;
import io.vertx.spi.cluster.redis.RedisClusterManager;

public class WithClusterStarter extends AbstractVertxStarter {

  public static void main(String... args) throws Exception {
    System.out.println("Starting in clustered mode");
    ClusterManager mgr = new RedisClusterManager();

    VertxOptions options = new VertxOptions().setClusterManager(mgr);
    Vertx.clusteredVertx(options, resultHandler -> {
      Vertx vertx = resultHandler.result();
      deployVerticles(vertx);
    });
  }
}