package org.excelcoin.besu;

import java.io.ByteArrayOutputStream;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;
import org.apache.tuweni.bytes.Bytes;
import org.bouncycastle.jcajce.provider.digest.Keccak;

public abstract class ExcelCoinWrappedDigest extends MessageDigest {
  protected final MessageDigest wrapped;
  private final ByteArrayOutputStream bos = new ByteArrayOutputStream();

  public ExcelCoinWrappedDigest(String algorithm, MessageDigest wrapped) {
    super(algorithm);
    this.wrapped = wrapped;
  }

  @Override
  public int engineGetDigestLength() {
    return wrapped.getDigestLength();
  }

  @Override
  public void engineUpdate(byte b) {
    wrapped.update(b);
    bos.write(b);
  }

  @Override
  public void engineUpdate(byte[] bytes, int off, int len) {
    wrapped.update(bytes, off, len);
    bos.write(bytes, off, len);
  }

  @Override
  public byte[] engineDigest() {
    byte[] hash = wrapped.digest();
    Map<Bytes, byte[]> hashToInput = getHashToInputMap();
    Bytes hashBytes = Bytes.wrap(hash);
    synchronized (hashToInput) {
      hashToInput.put(hashBytes, bos.toByteArray());
    }
    System.out.println("Engine digest! " + hashBytes);
    return hash;
  }

  @Override
  public void engineReset() {
    wrapped.reset();
    bos.reset();
  }

  protected abstract Map<Bytes, byte[]> getHashToInputMap();

  public static class WrappedKeccak256Digest extends ExcelCoinWrappedDigest {
    public static Map<Bytes, byte[]> hashToInput = new HashMap<>();

    public WrappedKeccak256Digest() {
      this(new Keccak.Digest256());
    }

    public WrappedKeccak256Digest(MessageDigest wrapped) {
      super("KECCAK-256", wrapped);
    }

    @Override
    protected Map<Bytes, byte[]> getHashToInputMap() {
      return hashToInput;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
      return new WrappedKeccak256Digest((MessageDigest) (wrapped.clone()));
    }
  }
}
