package cl.awakelab.ensayo5.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.RowMapper;

import cl.awakelab.ensayo5.model.Comments;

public class CommentsDAO {

    JdbcTemplate template;

    public void setTemplate(JdbcTemplate template) {
        this.template = template;
    }

    static Logger log = Logger.getLogger(CommentsDAO.class);
    
    public Boolean insertarComments (final List<Comments> lista) {
        String sql= "INSERT ALL";
        int size = lista.size();
        for (int i = 0; i < size; i++) {
            sql += " into comments (id, postid, name, email, body) values (?,?,?,?,?) ";
        }
        sql = sql + "SELECT * FROM dual";
        
        return template.execute(sql, new PreparedStatementCallback<Boolean>() {
            @Override
            public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
                int contador = 0;
                for (Comments c : lista) {
                    ps.setInt((contador += 1), c.getId());
                    ps.setInt((contador += 1), c.getPostId());
                    ps.setString((contador += 1), c.getName());
                    ps.setString((contador += 1), c.getEmail());
                    ps.setString((contador += 1), c.getBody());                    
                }
                return ps.execute();
            }
        });
    }

    public int eliminarDatosComments() {
        String sql = "delete from comments";
        return template.update(sql);
    }

    public class CommentsMapper implements RowMapper<Comments> {
        public Comments mapRow(ResultSet rs, int rowNum) throws SQLException {
            Comments c = new Comments();
            c.setId(rs.getInt(1));
            c.setName(rs.getString(2));
            c.setEmail(rs.getString(3));
            c.setBody(rs.getString(4));
            return c;
        }
    }

    public List<Comments> listarComentariosbyId(int id_post) {
        String sql = "select id, name, email, SUBSTR (body,1,20) from comments where postid = " + id_post;
        return template.query(sql, new CommentsMapper());
    }

}
