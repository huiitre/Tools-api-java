package fr.huiitre.tools.tools_core.test.repository;

import fr.huiitre.tools.tools_core.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

@Repository
public class TestRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<User> getAllUsers() {
        String sql = "SELECT * FROM tools_core.\"user\""; // Utilisation des guillemets si nécessaire pour le nom 'user'
        return jdbcTemplate.query(sql, new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                User user = new User();
                user.setId(rs.getInt("iduser"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setRememberToken(rs.getString("remember_token"));
                user.setRole(rs.getInt("role"));
                user.setIsActive(rs.getInt("is_active"));
                
                Timestamp tokenTs = rs.getTimestamp("token_date");
                if (tokenTs != null) {
                    user.setTokenDate(tokenTs.toLocalDateTime());
                }
                
                Timestamp createdTs = rs.getTimestamp("created_at");
                if (createdTs != null) {
                    user.setCreatedAt(createdTs.toLocalDateTime());
                }
                
                Timestamp updatedTs = rs.getTimestamp("updated_at");
                if (updatedTs != null) {
                    user.setUpdatedAt(updatedTs.toLocalDateTime());
                }
                
                user.setGoogleId(rs.getString("google_id"));
                user.setGoogleEmail(rs.getString("google_email"));
                user.setGoogleName(rs.getString("google_name"));
                user.setGoogleGivenName(rs.getString("google_given_name"));
                user.setGoogleFamilyName(rs.getString("google_family_name"));
                user.setGooglePicture(rs.getString("google_picture"));

                return user;
            }
        });
    }
}
