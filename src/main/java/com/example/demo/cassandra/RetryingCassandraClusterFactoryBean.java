package com.example.demo.cassandra;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.cassandra.config.CassandraClusterFactoryBean;

public class RetryingCassandraClusterFactoryBean extends CassandraClusterFactoryBean {

  private static final Logger LOG =
      LoggerFactory.getLogger(RetryingCassandraClusterFactoryBean.class);

  @Override
  public void afterPropertiesSet() {
    connect();
  }

  private void connect() {
    try {
      super.afterPropertiesSet();
    } catch (Exception e) {
      LOG.warn(e.getMessage());
      LOG.warn("Retrying connection in 10 seconds");
      sleep();
      connect();
    }
  }

  private void sleep() {
    try {
      Thread.sleep(10000);
    } catch (InterruptedException ignored) {
    }
  }
}