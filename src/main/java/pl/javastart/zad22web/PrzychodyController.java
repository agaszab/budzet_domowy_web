package pl.javastart.zad22web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.SQLException;
import java.util.List;


@Controller
public class PrzychodyController {

    private Przychody przychody;
    private PrzychodyDao dao;

    public PrzychodyController(Przychody przychody, PrzychodyDao dao) {
        this.przychody = przychody;
        this.dao = dao;
    }

    public PrzychodyController() {
    }

    @RequestMapping("/")
    public static String main() {
        return "index";
    }


   /*
    @RequestMapping("/list")     // po wybraniu z menu nowych

    public String opcje(Model model, @RequestParam Opcje opcja) throws SQLException {

        switch (opcja) {
            case DODAJ_PRZYCHOD: {
                Przychody newPrzychody = new Przychody();
                newPrzychody.setType("przychody");
                model.addAttribute("newPrzychod", newPrzychody);
                return "formularz";
            }
            case DODAJ_WYDATEK: {
                Przychody newPrzychody = new Przychody();
                newPrzychody.setType("wydatki");
                model.addAttribute("newPrzychod", newPrzychody);
                return "formularz";
            }

        }
        return "index";

    }
    */

    @RequestMapping("/dodaj")
    public String dodajPrzychody(Model model, @RequestParam String opcja){
        String typ;
        if (opcja.equals("DODAJ_PRZYCHOD")) typ="przychody"; else typ="wydatki";
        Przychody newPrzychody = new Przychody();
        newPrzychody.setType(typ);
        model.addAttribute("newPrzychody", newPrzychody);
        return "formularz";
    }

    @RequestMapping("/add")
    public String add(Przychody przychody) {
        dao.save(przychody);
        return "redirect:/index";
    }




    @GetMapping("/listuj")
    public String listuj (Model model, @RequestParam String opcja) throws SQLException{
        String typ;
        List<Przychody> listaPrzychodow;
        if (opcja.equals("POKAZ_PRZYCHODY")) typ="przychody"; else typ="wydatki";
        model.addAttribute ("typ", typ);
        listaPrzychodow=dao.show(typ);
        model.addAttribute("listaPrzychodow", listaPrzychodow);
        return "lista";
    }
}