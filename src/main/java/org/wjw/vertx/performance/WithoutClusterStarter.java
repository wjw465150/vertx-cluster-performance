package org.wjw.vertx.performance;

import io.vertx.core.Vertx;

public class WithoutClusterStarter extends AbstractVertxStarter {

  public static void main(String... args) {
    System.out.println("Starting in non-clustered mode");
    Vertx vertx = Vertx.vertx();
    deployVerticles(vertx);
  }

}