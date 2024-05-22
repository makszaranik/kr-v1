package src.java.kpi.fict.coursework.op.zaranik.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import kpi.fict.coursework.op.zaranik.services.passwordhashing.PasswordHashingService;
import kpi.fict.coursework.op.zaranik.services.passwordhashing.impl.PasswordHashingServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

public class PasswordHashingServiceTest {
  @InjectMocks
  PasswordHashingServiceImpl passwordHashingService;

  @BeforeEach
  void init() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void HashingTest(){
    //https://www.md5.cz/
    assertEquals(passwordHashingService.hashPassword("max123"), "d1696816bc1a7afe92f1c8787ac222c3");
    assertEquals(passwordHashingService.hashPassword("yana123"), "f120b1fbce5e71f228b8764c574455da");
    assertEquals(passwordHashingService.hashPassword("admin"), "21232f297a57a5a743894a0e4a801fc3");
    assertEquals(passwordHashingService.hashPassword("Max"), "6a061313d22e51e0f25b7cd4dc065233");
    assertEquals(passwordHashingService.hashPassword("Max11"), "6c02971b090cccfcec5e0f2a88225358");
  }

  @Test
  void DeHashingTest(){
    //https://www.md5.cz/
    assertTrue(passwordHashingService.checkPassword("max123", "d1696816bc1a7afe92f1c8787ac222c3"));
    assertTrue(passwordHashingService.checkPassword("yana123", "f120b1fbce5e71f228b8764c574455da"));
    assertTrue(passwordHashingService.checkPassword("admin", "21232f297a57a5a743894a0e4a801fc3"));
    assertTrue(passwordHashingService.checkPassword("Max", "6a061313d22e51e0f25b7cd4dc065233"));
    assertTrue(passwordHashingService.checkPassword("Max11", "6c02971b090cccfcec5e0f2a88225358"));
    assertFalse(passwordHashingService.checkPassword("Max11", "6c12971b090cccfcec5e0f2a88225358"));
    assertFalse(passwordHashingService.checkPassword("yana123", "f120b1fbce5e71f228b8764c574455yana"));

  }
}
