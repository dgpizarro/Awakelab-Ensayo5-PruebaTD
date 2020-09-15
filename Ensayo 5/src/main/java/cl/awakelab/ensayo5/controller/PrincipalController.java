package cl.awakelab.ensayo5.controller;

import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import cl.awakelab.ensayo5.DAO.CommentsDAO;
import cl.awakelab.ensayo5.DAO.ListPostsDAO;
import cl.awakelab.ensayo5.DAO.PostsDAO;
import cl.awakelab.ensayo5.model.Comments;
import cl.awakelab.ensayo5.model.ListPosts;
import cl.awakelab.ensayo5.model.Posts;

@Controller
public class PrincipalController {

    @Autowired
    CommentsDAO cd;

    @Autowired
    ListPostsDAO ld;

    @Autowired
    PostsDAO pd;
    
    private RestTemplate restTemplate = new RestTemplate();

    static Logger log = Logger.getLogger(PrincipalController.class);

    public List<Posts> getPosts() {
        String uri = "https://jsonplaceholder.typicode.com/posts";

        try {
            ResponseEntity<Posts[]> respuesta = restTemplate.getForEntity(uri, Posts[].class);
            log.log(Level.INFO, "Traspaso Json Posts a List<Posts> exitoso");
            return Arrays.asList(respuesta.getBody());
        } catch (Exception e) {
            log.log(Level.ERROR, e);
            return null;
        }
        
    }

    public List<Comments> getComments() {
        String uri = "https://jsonplaceholder.typicode.com/comments";

        try {
            ResponseEntity<Comments[]> respuesta = restTemplate.getForEntity(uri, Comments[].class);
            log.log(Level.INFO, "Traspaso Json Comments a Lista<Comments>");
            return Arrays.asList(respuesta.getBody());
        } catch (Exception e) {
            log.log(Level.ERROR, e);
           return null;
        }
        
    }

    @RequestMapping("/cargarDatos")
    public String irCargaDatos() {

        try {

            cd.eliminarDatosComments();
            log.log(Level.INFO, "Datos eliminados de tabla Comments en BD");

            pd.eliminarDatosPosts();
            log.log(Level.INFO, "Datos eliminados de tabla Posts en BD");

        } catch (Exception e) {
            log.log(Level.ERROR, e);
        }
        
        List<Posts> listaP = getPosts();
        if (listaP != null) {
            try {
                pd.insertarPosts(listaP);
                log.log(Level.INFO, "Se insertan " + listaP.size() + " registros de Posts a BD");
            } catch (Exception e) {
                log.log(Level.ERROR, e);
            }
        }
        
        List<Comments> listaC = getComments();
        if (listaC != null) {
            try {
                cd.insertarComments(listaC);
                  log.log(Level.INFO, "Se insertan " + listaC.size() + " registros de Comments a BD");
             } catch (Exception e) {
                 log.log(Level.ERROR, e);
              }
        }       

        return "redirect:/index";
    }

    @RequestMapping({ "/index", "/" })
    public String irPortal(Model m) {
        
        try {
            
            List<ListPosts> publicaciones = ld.listarPublicaciones();
            
            m.addAttribute("posts", publicaciones);
            
            log.log(Level.INFO, "Búsqueda de todas las publicaciones  -> SQL");
            
        } catch (Exception e) {
            log.log(Level.ERROR, e);
        }
               
        log.log(Level.INFO, "Ingreso a index");
        
        return "PortalPublicaciones";
    }

    @RequestMapping("/comentarios/{idpost}")
    public String irComentarios(@PathVariable int idpost, Model m) {
        
        try {
            
            List<Comments> comentarios = cd.listarComentariosbyId(idpost);
            
            m.addAttribute("com", comentarios);
            
            log.log(Level.INFO, "Búsqueda de comentarios de publiación ID " + idpost + " -> SQL");
            
        } catch (Exception e) {
            log.log(Level.ERROR, e);
        }
        
        log.log(Level.INFO, "Ingreso a modal con detalle de comentarios de publicacion ID: " + idpost);
        
        return "DetalleComentarios";
    }

}
