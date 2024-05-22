package kpi.fict.coursework.op.zaranik.services.passwordhashing;

public interface PasswordHashingService {
  void hashPassword(String plainPassword);
  boolean checkPassword(String plainPassword, String hashedPassword);
}
