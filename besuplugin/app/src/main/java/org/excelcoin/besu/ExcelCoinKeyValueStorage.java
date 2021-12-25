package org.excelcoin.besu;

import java.util.Collections;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Stream;
import org.apache.tuweni.bytes.Bytes;
import org.hyperledger.besu.plugin.services.exception.StorageException;
import org.hyperledger.besu.plugin.services.storage.KeyValueStorage;
import org.hyperledger.besu.plugin.services.storage.KeyValueStorageTransaction;
import org.hyperledger.besu.plugin.services.storage.SegmentIdentifier;

public class ExcelCoinKeyValueStorage implements KeyValueStorage {
  private final SegmentIdentifier segmentIdentifier;

  public ExcelCoinKeyValueStorage(SegmentIdentifier segmentIdentifier) {
    this.segmentIdentifier = segmentIdentifier;
  }

  @Override
  public void clear() {
    System.out.println(segmentIdentifier + " clear!");
    Thread.dumpStack();
  }

  @Override
  public boolean containsKey(byte[] key) {
    System.out.println(segmentIdentifier + " containsKey Key: " + Bytes.wrap(key).toHexString());
    Thread.dumpStack();
    return false;
  }

  @Override
  public Optional<byte[]> get(byte[] key) {
    System.out.println(segmentIdentifier + " get Key: " + Bytes.wrap(key).toHexString());
    Thread.dumpStack();
    return Optional.empty();
  }

  @Override
  public Stream<byte[]> streamKeys() throws StorageException {
    System.out.println(segmentIdentifier + " stream keys");
    Thread.dumpStack();
    return Stream.empty();
  }

  @Override
  public boolean tryDelete(byte[] key) throws StorageException {
    System.out.println(segmentIdentifier + " try delete: " + Bytes.wrap(key).toHexString());
    Thread.dumpStack();
    return false;
  }

  @Override
  public Set<byte[]> getAllKeysThat(Predicate<byte[]> returnCondition) {
    System.out.println(segmentIdentifier + " get all keys that: " + returnCondition);
    Thread.dumpStack();
    return Collections.emptySet();
  }

  @Override
  public KeyValueStorageTransaction startTransaction() {
    System.out.println(segmentIdentifier + " start transaction");
    Thread.dumpStack();
    return new ExcelCoinKeyValueStorageTransaction(segmentIdentifier);
  }

  @Override
  public void close() {
    System.out.println(segmentIdentifier + " close");
    Thread.dumpStack();
  }
}
