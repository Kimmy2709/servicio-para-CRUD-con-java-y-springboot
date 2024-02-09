package pe.isil.cliente_1278.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import pe.isil.cliente_1278.model.Carrera;

@Controller
@RequestMapping("/carrera")
public class CarreraController {

    @Autowired
    private RestTemplate restTemplate;

    private static final String GET_ALL_CARRERA_API = "http://localhost:8080/carrera/getAll";

    @GetMapping("")
    private String index(Model model){
        ResponseEntity<Carrera[]> listarCarreras = restTemplate.getForEntity(GET_ALL_CARRERA_API, Carrera[].class);
        model.addAttribute("carrera", listarCarreras.getBody());
        return "/carrera/index";
    }
}
