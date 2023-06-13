package com.stereo;

import static org.junit.jupiter.api.Assertions.*;

import net.bytebuddy.implementation.auxiliary.TypeProxy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import java.util.Arrays;
import java.util.List;
import static org.mockito.Mockito.*;

public class PalindromCheckTest {
    @Test
    public void testIsPalindrome() {
        String str = "madam";
        boolean expected = true;

        // Створюємо mock об'єкт PalindromCheckService
        PalindromCheckService palindromCheckService = Mockito.mock(PalindromCheckService.class);

        // Вказуємо, що метод isPalindrome повинен повертати очікуване значення
        Mockito.when(palindromCheckService.isPalindrome(str)).thenReturn(expected);

        // Створюємо об'єкт PalindromCheck з mock об'єктом PalindromCheckService
        PalindromCheck palindromCheck = new PalindromCheck(palindromCheckService);

        // Викликаємо метод isPalindrome з об'єкту PalindromCheck
        boolean actual = palindromCheck.isPalindrome(str);

        // Перевіряємо, що отримане значення співпадає з очікуваним
        assertEquals(expected, actual);
    }
    // Обробка виключень;
    @Test
    public void testIsPalindromeWithNullString() {
        String str = null;
        PalindromCheckService palindromCheckService = Mockito.mock(PalindromCheckService.class);
        Mockito.when(palindromCheckService.isPalindrome(str)).thenThrow(new NullPointerException());
        PalindromCheck palindromCheck = new PalindromCheck(palindromCheckService);
        assertThrows(NullPointerException.class, () -> {palindromCheck.isPalindrome(str);
        });
    }
    //перевірка що метод було викликано певну кількість разів;
    @Test
    public void testIsPalindromeInvocationCount() {
        String str = "pryvyt";
        boolean expected = true;

        PalindromCheckService palindromCheckService = Mockito.mock(PalindromCheckService.class);

        Mockito.when(palindromCheckService.isPalindrome(str)).thenReturn(expected);

        PalindromCheck palindromCheck = new PalindromCheck(palindromCheckService);

        // Викликаємо метод isPalindrome з об'єкту PalindromCheck кілька разів
        palindromCheck.isPalindrome(str);
        palindromCheck.isPalindrome(str);
        palindromCheck.isPalindrome(str);

        // Перевіряємо, що метод isPalindrome був викликаний три рази
        Mockito.verify(palindromCheckService, Mockito.times(3)).isPalindrome(str);
    }

    //сценарії за яких мок об’єкти згенерують виключення

    @Test
    public void testIsPalindromeWithRuntimeException() {
        String str = "pip";
        String errorMessage = "Runtime error: Palindrome check failed.";
        PalindromCheckService palindromCheckService = Mockito.mock(PalindromCheckService.class);
        Mockito.when(palindromCheckService.isPalindrome(str)).thenThrow(new RuntimeException(errorMessage));

        PalindromCheck palindromCheck = new PalindromCheck(palindromCheckService);

        assertThrows(RuntimeException.class, () -> {
            palindromCheck.isPalindrome(str);
        }, errorMessage);
    }

    // іціалізація за допомогою анотацій (@Mock, @InjectMocks);

    @Mock
    private PalindromCheckService palindromCheckService;

    @InjectMocks
    private PalindromCheck palindromCheck;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testIsPalindromeWithExistingMethod() {
        String str = "level";
        when(palindromCheckService.isPalindrome(str)).thenReturn(true);
        boolean result = palindromCheckService.isPalindrome(str);
        assertTrue(result);
    }
    //має бути використаний частковий мок об’єкт @Spy.

    //має бути використаний частковий мок об’єкт @Spy.
  /*  @Spy
    private PalindromCheck palindromCheckSpy;

    @Test
    void testWithSpy() {
        String word = "racecart";

        // Перевизначаємо метод isPalindrome() за допомогою мок-об'єкта, створеного з допомогою @Spy
        Mockito.when(palindromCheckSpy.isPalindrome(Mockito.anyString())).thenReturn(false);

        boolean result = palindromCheckSpy.isPalindrome(word);

        // Очікуємо, що результат буде `false`, оскільки перевизначили метод isPalindrome()
        assertFalse(result);
    }
   */
}