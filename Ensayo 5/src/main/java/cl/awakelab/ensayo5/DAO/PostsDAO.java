package cl.awakelab.ensayo5.DAO;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;

import cl.awakelab.ensayo5.model.Posts;

public class PostsDAO {

    JdbcTemplate template;

    public void setTemplate(JdbcTemplate template) {
        this.template = template;
    } 
    
    public Boolean insertarPosts(final List<Posts> lista) {
        String sql= "INSERT ALL";
        int size = lista.size();
        for (int i = 0; i < size; i++) {
            sql += " into posts (id, userid, title, body) values (?,?,?,?) ";
        }
        sql = sql + "SELECT * FROM dual";
        
        return template.execute(sql, new PreparedStatementCallback<Boolean>() {
            @Override
            public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
                int contador = 0;
                for (Posts posts : lista) {
                    ps.setInt((contador += 1), posts.getId());
                    ps.setInt((contador += 1), posts.getUserId());
                    ps.setString((contador += 1), posts.getTitle());
                    ps.setString((contador += 1), posts.getBody());
                }               
                return ps.execute();
            }
        });
    }
    
    public int eliminarDatosPosts () {
        String sql = "delete from posts";
        return template.update(sql);
    }

}
