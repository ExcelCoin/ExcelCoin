package org.excelcoin.besu;

import java.security.Provider;

public final class ExcelCoinHashProvider extends Provider {
  private static final String info = "Excel Coin Hash Security Provider v1.0";

  public static final String PROVIDER_NAME = "ExcelCoinHash";

  public ExcelCoinHashProvider() {
    super(PROVIDER_NAME, "1.0", info);
    put("MessageDigest.KECCAK-256", ExcelCoinWrappedDigest.WrappedKeccak256Digest.class.getName());
  }
}
