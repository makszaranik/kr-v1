package kpi.fict.coursework.op.zaranik.services.passwordhashing.impl;
import kpi.fict.coursework.op.zaranik.services.passwordhashing.PasswordHashingService;
import org.apache.commons.codec.digest.DigestUtils;

public class PasswordHashingServiceImpl implements PasswordHashingService {
  public String hashPassword(String plainPassword) {
    return DigestUtils.md5Hex(plainPassword);
  }

  public boolean checkPassword(String plainPassword, String hashedPassword) {
    return DigestUtils.md5Hex(plainPassword).equals(hashedPassword);
  }
}
