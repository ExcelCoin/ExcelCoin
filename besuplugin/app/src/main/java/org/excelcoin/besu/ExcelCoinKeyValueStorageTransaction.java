package org.excelcoin.besu;

import org.apache.tuweni.bytes.Bytes;
import org.hyperledger.besu.plugin.services.storage.KeyValueStorageTransaction;
import org.hyperledger.besu.plugin.services.storage.SegmentIdentifier;

public class ExcelCoinKeyValueStorageTransaction implements KeyValueStorageTransaction {
  private final SegmentIdentifier segmentIdentifier;

  public ExcelCoinKeyValueStorageTransaction(SegmentIdentifier segmentIdentifier) {
    this.segmentIdentifier = segmentIdentifier;
  }

  @Override
  public void commit() {
    System.out.println(segmentIdentifier + " commit");
    Thread.dumpStack();
  }

  @Override
  public void put(byte[] key, byte[] value) {
    System.out.println(
        segmentIdentifier
            + " put: "
            + Bytes.wrap(key).toHexString()
            + " : "
            + Bytes.wrap(value).toHexString());
    Thread.dumpStack();
  }

  @Override
  public void remove(byte[] key) {
    System.out.println(segmentIdentifier + " remove: " + Bytes.wrap(key).toHexString());
    Thread.dumpStack();
  }

  @Override
  public void rollback() {
    System.out.println(segmentIdentifier + " rollback");
    Thread.dumpStack();
  }
}
