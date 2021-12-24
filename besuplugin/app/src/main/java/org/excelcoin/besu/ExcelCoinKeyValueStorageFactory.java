package org.excelcoin.besu;

import org.hyperledger.besu.plugin.services.*;
import org.hyperledger.besu.plugin.services.storage.*;

public class ExcelCoinKeyValueStorageFactory implements KeyValueStorageFactory {
  public KeyValueStorage create(
      SegmentIdentifier segment, BesuConfiguration configuration, MetricsSystem metricsSystem) {
    System.out.println(segment);
    throw new RuntimeException("hahaha");
  }

  public String getName() {
    return "excel";
  }

  public boolean isSegmentIsolationSupported() {
    return true;
  }

  public void close() {}
}
