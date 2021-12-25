package org.excelcoin.besu;

import org.hyperledger.besu.plugin.BesuContext;
import org.hyperledger.besu.plugin.BesuPlugin;
import org.hyperledger.besu.plugin.services.StorageService;
import org.hyperledger.besu.plugin.services.storage.KeyValueStorageFactory;

public class ExcelCoinPlugin implements BesuPlugin {
  @Override
  public void register(BesuContext context) {
    StorageService storageService = context.getService(StorageService.class).get();
    KeyValueStorageFactory inMemoryFactory = storageService.getByName("memory").get();
    storageService.registerKeyValueStorage(new ExcelCoinKeyValueStorageFactory(inMemoryFactory));
    System.out.println("im in");
  }

  public void start() {}

  public void stop() {}
}
