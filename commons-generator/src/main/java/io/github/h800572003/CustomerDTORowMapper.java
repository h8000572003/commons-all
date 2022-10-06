package io.github.h800572003;
import org.springframework.jdbc.core.RowMapper;
import java.sql.SQLException;
import java.sql.ResultSet;
import io.github.h800572003.CustomerDTO;

        /**
          *顧客
        */



public class CustomerDTORowMapper  implements RowMapper<CustomerDTO> {

    public CustomerDTO mapRow(ResultSet rs,int rowNum)throws SQLException {
   CustomerDTO target = new CustomerDTO();
target.setName(rs.getString("NAME"));
target.setStudentId(rs.getString("STUDENT_ID"));
target.setMail(rs.getString("MAIL"));
target.setTel(rs.getString("TEL"));
target.setAge(rs.getInt("AGE"));
return target;
    }
}
