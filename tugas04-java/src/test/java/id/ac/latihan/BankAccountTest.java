package id.ac.latihan;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.*;

class BankAccountTest {
    private final BankAccount account = new BankAccount();
    private static final double EPS = 1e-9;

    @Test void saldoAwalNol() {
        assertEquals(0.0, account.getBalance(), EPS);
    }
    @Test void depositMenambahSaldo() {
        account.deposit(100.0);
        assertEquals(100.0, account.getBalance(), EPS);
    }
    @Test void depositBerulangTerakumulasi() {
        account.deposit(100.0);
        account.deposit(50.5);
        assertEquals(150.5, account.getBalance(), EPS);
    }
    @Test void withdrawMengurangiSaldo() {
        account.deposit(100.0);
        account.withdraw(40.0);
        assertEquals(60.0, account.getBalance(), EPS);
    }
    @Test void withdrawSeluruhSaldo() {
        account.deposit(100.0);
        account.withdraw(100.0);
        assertEquals(0.0, account.getBalance(), EPS);
    }
    @Test void nominalNolTidakMengubahSaldo() {
        account.deposit(100.0);
        account.deposit(0.0);
        account.withdraw(0.0);
        assertEquals(100.0, account.getBalance(), EPS);
    }
    @Test void depositNegatifDitolak() {
        account.deposit(100.0);
        assertThrows(IllegalArgumentException.class,
            () -> account.deposit(-1.0));
        assertEquals(100.0, account.getBalance(), EPS);
    }
    @Test void withdrawNegatifDitolak() {
        account.deposit(100.0);
        assertThrows(IllegalArgumentException.class,
            () -> account.withdraw(-1.0));
        assertEquals(100.0, account.getBalance(), EPS);
    }
    @Test void withdrawMelebihiSaldoDitolak() {
        account.deposit(100.0);
        IllegalArgumentException ex = assertThrows(
            IllegalArgumentException.class,
            () -> account.withdraw(101.0));
        assertEquals("Insufficient balance", ex.getMessage());
        assertEquals(100.0, account.getBalance(), EPS);
    }
    @ParameterizedTest
    @ValueSource(doubles = {Double.NaN, Double.POSITIVE_INFINITY,
                           Double.NEGATIVE_INFINITY})
    void nominalNonFiniteDitolak(double amount) {
        account.deposit(100.0);
        assertThrows(IllegalArgumentException.class,
            () -> account.deposit(amount));
        assertThrows(IllegalArgumentException.class,
            () -> account.withdraw(amount));
        assertEquals(100.0, account.getBalance(), EPS);
    }
    @Test void depositOverflowDitolak() {
        account.deposit(Double.MAX_VALUE);
        assertThrows(IllegalArgumentException.class,
            () -> account.deposit(Double.MAX_VALUE));
        assertEquals(Double.MAX_VALUE, account.getBalance());
    }
}
