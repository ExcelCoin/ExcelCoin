package org.excelcoin.besu;

import java.util.ArrayList;
import java.util.List;
import org.apache.tuweni.bytes.Bytes;
import org.hyperledger.besu.plugin.services.storage.KeyValueStorageTransaction;
import org.hyperledger.besu.plugin.services.storage.SegmentIdentifier;

public class ExcelCoinKeyValueStorageTransaction implements KeyValueStorageTransaction {
  private final ExcelCoinKeyValueStorage storage;
  private final SegmentIdentifier segmentIdentifier;
  private List<Runnable> operations = new ArrayList<>();

  public ExcelCoinKeyValueStorageTransaction(
      ExcelCoinKeyValueStorage storage, SegmentIdentifier segmentIdentifier) {
    this.storage = storage;
    this.segmentIdentifier = segmentIdentifier;
  }

  @Override
  public void commit() {
    System.out.println(segmentIdentifier + " commit");
    Thread.dumpStack();
    for (Runnable r : operations) {
      r.run();
    }
  }

  @Override
  public void put(byte[] key, byte[] value) {
    Bytes keyBytes = Bytes.wrap(key);
    System.out.println(
        segmentIdentifier
            + " put: "
            + keyBytes.toHexString()
            + " : "
            + Bytes.wrap(value).toHexString());
    byte[] keyPreimage = ExcelCoinWrappedDigest.WrappedKeccak256Digest.hashToInput.get(keyBytes);
    if (keyPreimage != null) {
      System.out.println(" key = keccak256(" + Bytes.wrap(keyPreimage).toHexString() + ")");
    }
    Thread.dumpStack();
    operations.add(() -> storage.put(key, value));
  }

  @Override
  public void remove(byte[] key) {
    System.out.println(segmentIdentifier + " remove: " + Bytes.wrap(key).toHexString());
    Thread.dumpStack();
    operations.add(() -> storage.remove(key));
  }

  @Override
  public void rollback() {
    System.out.println(segmentIdentifier + " rollback");
    Thread.dumpStack();
  }
}
