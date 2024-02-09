package pe.isil.cliente_1278.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pe.isil.cliente_1278.model.Estudiante;

@Controller
@RequestMapping("/estudiante")
public class EstudianteController {

    @Autowired
    private RestTemplate restTemplate;

    private static final String GET_ALL_ESTUDIANTES_API= "http://localhost:8080/estudiante/getAll";
    private static final String STORE_ESTUDIANTES_API= "http://localhost:8080/estudiante/insert";
    private static final String GET_BY_ID_API= "http://localhost:8080/estudiante/getById/{id}";
    private static final String UPDATE_ESTUDIANTES_API= "http://localhost:8080/estudiante/update/{Id}";

    @GetMapping("")
    public String index(Model model){
        ResponseEntity<Estudiante[]> listarEstudiantes =  restTemplate.getForEntity(GET_ALL_ESTUDIANTES_API, Estudiante[].class);

        model.addAttribute("estudiante",listarEstudiantes.getBody());
        return "estudiante/index";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model){
        model.addAttribute("estudiantes", new Estudiante());
        return "/estudiante/nuevo";
    }

    @PostMapping("/store")
    public String store(Model model, Estudiante estudiante, RedirectAttributes redirectAttributes){
        restTemplate.postForEntity(STORE_ESTUDIANTES_API, estudiante, Estudiante.class);
        redirectAttributes.addFlashAttribute("msgExito", "Estudiante registrado");
        return "redirect:/estudiante";
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable("id") Integer id ,  Model  model){
          Estudiante estudiante =  restTemplate.getForObject(GET_BY_ID_API, Estudiante.class, id);
          model.addAttribute("estudiante", estudiante);
          return "estudiante/update";
    }
     @PostMapping("update/{id}")
    public String update(@PathVariable("id") Integer id, Model model, RedirectAttributes ra, Estudiante estudiante){
        restTemplate.put(UPDATE_ESTUDIANTES_API, estudiante, id);
        ra.addFlashAttribute("msgExito", "Estudiante actualizado");
        return "redirect:/estudiante";
     }


}
