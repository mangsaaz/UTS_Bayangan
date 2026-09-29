package com.uas.jawabanuas;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class KuadratController {

    @GetMapping("/kuadrat")
    public String tampilkanForm() {
        return "input_kuadrat";
    }

    @PostMapping("/kuadrat/hitung")
    public String hitungKuadrat(
            @RequestParam("a") double a,
            @RequestParam("b") double b,
            @RequestParam("c") double c,
            Model model) {
        
        model.addAttribute("a", a);
        model.addAttribute("b", b);
        model.addAttribute("c", c);

        if (a == 0) {
            model.addAttribute("error", "Nilai 'a' tidak boleh nol!");
            return "hasil_kuadrat";
        }

        double d = (b * b) - (4 * a * c);
        model.addAttribute("d", d);

        if (d > 0) {
            model.addAttribute("jenis", "Akar Real Berbeda");
            model.addAttribute("x1", (-b + Math.sqrt(d)) / (2 * a));
            model.addAttribute("x2", (-b - Math.sqrt(d)) / (2 * a));
        } else if (d == 0) {
            model.addAttribute("jenis", "Akar Kembar");
            model.addAttribute("x1", -b / (2 * a));
        } else {
            model.addAttribute("jenis", "Akar Imajiner (Tidak ada akar real)");
        }

        return "hasil_kuadrat";
    }
}