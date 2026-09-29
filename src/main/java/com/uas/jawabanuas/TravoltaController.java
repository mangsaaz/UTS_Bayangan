package com.uas.jawabanuas;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TravoltaController {

    // Menampilkan halaman form
    @GetMapping("/travolta")
    public String tampilkanForm() {
        return "input_travolta"; // Mengarah ke file input_travolta.html
    }

    // Memproses data dari form
    @PostMapping("/travolta/hitung")
    public String hitungGaji(
            @RequestParam("jamKerja") int jamKerja,
            @RequestParam("pengeluaran") double pengeluaran,
            Model model) {
        
        double rateNormal = 15000;
        double rateLembur = rateNormal * 1.5;
        double gajiTotal = 0;

        if (jamKerja <= 40) {
            gajiTotal = jamKerja * rateNormal;
        } else {
            double gajiNormal = 40 * rateNormal;
            double gajiLembur = (jamKerja - 40) * rateLembur;
            gajiTotal = gajiNormal + gajiLembur;
        }

        String status;
        double tabungan = 0;

        if (gajiTotal > pengeluaran) {
            status = "Bisa menabung";
            tabungan = gajiTotal - pengeluaran;
        } else if (gajiTotal == pengeluaran) {
            status = "Tidak bisa menabung";
        } else {
            status = "Cari tambahan";
        }

        // Menyimpan data untuk dikirim ke HTML (seperti request.setAttribute di Servlet)
        model.addAttribute("gajiTotal", gajiTotal);
        model.addAttribute("pengeluaran", pengeluaran);
        model.addAttribute("status", status);
        model.addAttribute("tabungan", tabungan);

        return "hasil_travolta"; // Mengarah ke file hasil_travolta.html
    }
}