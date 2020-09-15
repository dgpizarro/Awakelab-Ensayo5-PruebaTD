package cl.awakelab.ensayo5.test;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isA;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml",
        "file:src/main/webapp/WEB-INF/spring/root-context.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PrincipalControllerTest {

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private JdbcTemplate template;

    private MockMvc mock;

    @Before
    public void setUp() throws Exception {
        this.mock = MockMvcBuilders.webAppContextSetup(wac).build();
    }
    
    @Test
    public void t1_testIrCargaDatos() throws Exception {
        this.mock
        .perform(get("/cargarDatos"))
        .andExpect(status().isFound())
        .andExpect(view().name("redirect:/index"))
        .andDo(print());
        
        String sql1 = "select count(id) from posts";
        int n_posts = template.queryForObject(sql1, Integer.class);
        assertEquals(100, n_posts);
        
        String sql2 = "select count(id) from comments";
        int n_comments = template.queryForObject(sql2, Integer.class);
        assertEquals(500, n_comments);
    }
    
    

    @Test
    public void t2_testIrPortal() throws Exception {
        this.mock
        .perform(get("/index"))
        .andExpect(status().isOk())
        .andExpect(view().name("PortalPublicaciones"))
        .andExpect(model().attributeExists("posts"))
        .andExpect(model().attribute("posts", isA(Iterable.class)))
        .andExpect(model().attribute("posts",
                        hasItem(allOf(
                            hasProperty("post", hasProperty("id",is(46))),
                            hasProperty("post", hasProperty("title",is("aut quo modi neque nostrum ducimus"))),
                            hasProperty("user", hasProperty("name",is("Chelsey Dietrich"))),
                            hasProperty("n_comments", is(5))))))
        .andDo(print());
    }
    
    @Test
    public void t3_testIrComentarios () throws Exception {
        this.mock
        .perform(get("/comentarios/1"))
        .andExpect(status().isOk())
        .andExpect(view().name("DetalleComentarios"))
        .andExpect(model().attributeExists("com"))
        .andExpect(model().attribute("com", isA(Iterable.class)))
        .andExpect(model().attribute("com",
                        hasItem(allOf(
                            hasProperty("id", is(1)),
                            hasProperty("name", is("id labore ex et quam laborum")),
                            hasProperty("email", is("Eliseo@gardner.biz")),
                            hasProperty("body", is("laudantium enim quas"))))))
        .andDo(print());
    }

}
