package id.ac.latihan;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class PendaftaranTest {
    private final Pendaftaran p = new Pendaftaran();

    @Test void namaKosongDitolak() {
        assertEquals("Nama harus diisi",
            p.validasiPendaftaran("", 20, "andi@mail.com", true));
    }
    @Test void umurDiBawah17Ditolak() {
        assertEquals("Umur minimal 17 tahun",
            p.validasiPendaftaran("Andi", 16, "andi@mail.com", true));
    }
    @Test void emailTanpaAtDitolak() {
        assertEquals("Email tidak valid",
            p.validasiPendaftaran("Andi", 17, "andimail.com", true));
    }
    @Test void tidakSetujuSyaratDitolak() {
        assertEquals("Harus menyetujui syarat dan ketentuan",
            p.validasiPendaftaran("Andi", 17, "andi@mail.com", false));
    }
    @Test void dataValidBerhasil() {
        assertEquals("Pendaftaran berhasil",
            p.validasiPendaftaran("Andi", 17, "andi@mail.com", true));
    }
    @Test void namaNullDitolak() {
        assertEquals("Nama harus diisi",
            p.validasiPendaftaran(null, 20, "andi@mail.com", true));
    }
    @Test void emailNullDitolak() {
        assertEquals("Email tidak valid",
            p.validasiPendaftaran("Andi", 20, null, true));
    }
}
