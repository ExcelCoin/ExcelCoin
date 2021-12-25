package org.excelcoin.besu;

import java.util.Collections;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Stream;
import org.hyperledger.besu.plugin.services.exception.StorageException;
import org.hyperledger.besu.plugin.services.storage.KeyValueStorage;
import org.hyperledger.besu.plugin.services.storage.KeyValueStorageTransaction;

/** KeyValueStorage that doesn't actually store anything, because /dev/null is web scale */
public class ExcelCoinWebScaleKeyValueStorage implements KeyValueStorage {

  @Override
  public void clear() {}

  @Override
  public boolean containsKey(byte[] key) {
    return false;
  }

  @Override
  public Optional<byte[]> get(byte[] key) {
    return Optional.empty();
  }

  @Override
  public Stream<byte[]> streamKeys() throws StorageException {
    return Stream.empty();
  }

  @Override
  public boolean tryDelete(byte[] key) throws StorageException {
    return false;
  }

  @Override
  public Set<byte[]> getAllKeysThat(Predicate<byte[]> returnCondition) {
    return Collections.emptySet();
  }

  @Override
  public KeyValueStorageTransaction startTransaction() {
    return new Transaction();
  }

  @Override
  public void close() {}

  private static class Transaction implements KeyValueStorageTransaction {
    @Override
    public void commit() {}

    @Override
    public void put(byte[] key, byte[] value) {}

    @Override
    public void remove(byte[] key) {}

    @Override
    public void rollback() {}
  }
}
