package org.excelcoin.besu;

import org.hyperledger.besu.plugin.services.*;
import org.hyperledger.besu.plugin.services.storage.*;

public class ExcelCoinKeyValueStorageFactory implements KeyValueStorageFactory {
  private final KeyValueStorageFactory inMemoryFactory;

  public ExcelCoinKeyValueStorageFactory(KeyValueStorageFactory inMemoryFactory) {
    this.inMemoryFactory = inMemoryFactory;
  }

  public KeyValueStorage create(
      SegmentIdentifier segment, BesuConfiguration configuration, MetricsSystem metricsSystem) {
    System.out.println(segment);
    if (segment.getName().equals("BLOCKCHAIN")) {
      // TODO(zhuowei): build a fake blockchain
      return inMemoryFactory.create(segment, configuration, metricsSystem);
    }
    return new ExcelCoinKeyValueStorage(segment);
  }

  public String getName() {
    return "excel";
  }

  public boolean isSegmentIsolationSupported() {
    return true;
  }

  public void close() {}
}
