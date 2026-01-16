02_modern_java/AccountServiceTest.java

import org.junit.jupiter.api.Test;
import org.springframework.http.*;
import java.math.BigDecimal;

public class AccountServiceTest {
    
    // Simulate your Java service
    AccountService service = new AccountService();
    
    @Test
    public void testOverdraftLogic() {
        // Test COBOL "IF NEW-BALANCE < 0" logic
        BalanceUpdateRequest request = new BalanceUpdateRequest();
        request.setAccountNumber("ACC001");
        request.setAmount(new BigDecimal("-100.00"));
        
        ResponseEntity<Account> response = service.updateBalance(request);
        Account account = response.getBody();
        
        // PROOF: Exact COBOL logic works in Java!
        assert account.getStatus().equals("OVERDRAWN");
        assert account.getBalance().equals(new BigDecimal("-100.00"));
    }
    
    @Test
    public void testNormalTransaction() {
        // Test COBOL "ACTIVE" status logic
        BalanceUpdateRequest request = new BalanceUpdateRequest();
        request.setAccountNumber("ACC002");
        request.setAmount(new BigDecimal("500.00"));
        
        ResponseEntity<Account> response = service.updateBalance(request);
        Account account = response.getBody();
        
        // PROOF: Matches COBOL "ACTIVE" logic
        assert account.getStatus().equals("ACTIVE");
        assert account.getBalance().equals(new BigDecimal("500.00"));
    }
}
