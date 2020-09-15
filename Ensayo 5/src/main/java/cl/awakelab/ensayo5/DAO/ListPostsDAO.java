package cl.awakelab.ensayo5.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import cl.awakelab.ensayo5.model.ListPosts;
import cl.awakelab.ensayo5.model.Posts;
import cl.awakelab.ensayo5.model.Users;



public class ListPostsDAO {

    JdbcTemplate template;

    public void setTemplate(JdbcTemplate template) {
        this.template = template;
    }
    
    static Logger log = Logger.getLogger(ListPostsDAO.class);
    
    public class ListPostsMapper implements RowMapper<ListPosts> {
        public ListPosts mapRow(ResultSet rs, int rowNum) throws SQLException {
            ListPosts l = new ListPosts();
            Posts p = new Posts();
            Users u = new Users();
            p.setId(rs.getInt(1));
            p.setTitle(rs.getString(2));
            u.setName(rs.getString(3));
            l.setN_comments(rs.getInt(4));
            l.setPost(p);
            l.setUser(u);            
            return l;
        }
    }
    
    public List<ListPosts> listarPublicaciones() {
        String sql = "select p.id, title, u.name, count(c.id) from users u inner join posts p on "
                + " (u.id = p.userid) left join comments c on (p.id = c.postid) group by p.id,"
                + " title, u.name order by 3";

        return template.query(sql, new ListPostsMapper());
    }
    
}
