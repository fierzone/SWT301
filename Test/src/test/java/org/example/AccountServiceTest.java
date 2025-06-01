package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.*;

public class AccountServiceTest {
    
    private AccountService accountService;
    
    @BeforeEach
    void setUp() {
        accountService = new AccountService();
    }
    
    @ParameterizedTest(name = "Test case {index}: username={0}, password={1}, email={2}, expected={3}")
    @CsvFileSource(resources = "/test-data.csv", numLinesToSkip = 1)
    @DisplayName("Test register account with data from CSV file")
    void testRegisterAccountWithCsvData(String username, String password, String email, boolean expected) {
        boolean result = accountService.registerAccount(username, password, email);
        assertEquals(expected, result, 
            String.format("Registration should %s for username=%s, password=%s, email=%s",
                expected ? "succeed" : "fail", username, password, email));
    }
    
    @Test
    @DisplayName("Test null username")
    void testNullUsername() {
        assertFalse(accountService.registerAccount(null, "password123", "test@example.com"));
    }
    
    @Test
    @DisplayName("Test empty username")
    void testEmptyUsername() {
        assertFalse(accountService.registerAccount("", "password123", "test@example.com"));
    }
    
    @Test
    @DisplayName("Test short password")
    void testShortPassword() {
        assertFalse(accountService.registerAccount("testuser", "short", "test@example.com"));
    }
    
    @Test
    @DisplayName("Test null password")
    void testNullPassword() {
        assertFalse(accountService.registerAccount("testuser", null, "test@example.com"));
    }
    
    @Test
    @DisplayName("Test invalid email format")
    void testInvalidEmailFormat() {
        assertFalse(accountService.registerAccount("testuser", "password123", "invalid-email"));
    }
    
    @Test
    @DisplayName("Test null email")
    void testNullEmail() {
        assertFalse(accountService.registerAccount("testuser", "password123", null));
    }
    
    @Test
    @DisplayName("Test valid registration")
    void testValidRegistration() {
        assertTrue(accountService.registerAccount("testuser", "password123", "test@example.com"));
    }
    
    @Test
    @DisplayName("Test email validation")
    void testEmailValidation() {
        assertTrue(accountService.isValidEmail("test@example.com"));
        assertTrue(accountService.isValidEmail("user.name@domain.com"));
        assertTrue(accountService.isValidEmail("user+label@domain.com"));
        assertFalse(accountService.isValidEmail("invalid-email"));
        assertFalse(accountService.isValidEmail("missing@domain"));
        assertFalse(accountService.isValidEmail(""));
        assertFalse(accountService.isValidEmail(null));
    }
} 