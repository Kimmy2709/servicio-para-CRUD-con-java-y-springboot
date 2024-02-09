package pe.isil.cliente_1278.controller;

import pe.isil.cliente_1278.model.Curso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

@Controller
@RequestMapping("/cursos")
public class CursoController {

    @Autowired
    private RestTemplate restTemplate; //permite consumir api-rest

    //mapear las rutas del microservicio API-Rest
    //obtener todos los cursos : http://localhost:8080/intapps/cursos/getAll
    //obtener el curso por id:  http://localhost:8080/intapps/cursos/getById/{Id}
    //insertar un nuevo curso: http://localhost:8080/intapps/cursos/agregar
    //Actualizar un curso existente: http://localhost:8080/intapps/cursos/actualizar/{Id}
    //Eliminar un curso existente: http://localhost:8080/intapps/cursos/delete/{Id}

    private static final String GET_ALL_CURSOS_API= "http://localhost:8080/intapps/cursos/getAll";
    private static final String GET_BY_ID_CURSOS_API = "http://localhost:8080/intapps/cursos/getById/{Id}";
    private static final String STORE_CURSOS_API = "http://localhost:8080/intapps/cursos/agregar";
    private static final String UPDATE_CURSOS_API = "http://localhost:8080/intapps/cursos/actualizar/{Id}";
    private static final String DELETE_CURSOS_API = "http://localhost:8080/intapps/cursos/delete/{Id}";

    @GetMapping("")  //localhost:8080/cursos
    public String index(Model model){

        // primero se consume el api-rest
        ResponseEntity<Curso[]> listadoCursos = restTemplate.getForEntity(GET_ALL_CURSOS_API, Curso[].class);

        //se envia el listado a la vista(comunicacion desde el controller a la vista)
        model.addAttribute("cursos", listadoCursos.getBody());

        //derivamos a la vista
        return "/cursos/index";
    }


}
