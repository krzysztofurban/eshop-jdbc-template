package pl.krzysztofurban.eshopjdbctemplate;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import pl.krzysztofurban.eshopjdbctemplate.model.dto.CustomerOrder;

import java.util.List;

@SpringBootTest
@Slf4j
class EshopJdbcTemplateApplicationTests {

  @Autowired
  JdbcTemplate jdbcTemplate;

  @Test
  void contextLoads() {
    String sql = "Select " +
        "c.customerId, o.orderId, p.productId," +
        "c.name as customerName, c.email as customerEmail, p.name as productName, " +
        "p.quantity as quantity, p.price as price " +
        "from " +
        "Customer c inner join `Order` o on c.customerId = o.customerId inner join " +
        "Product p on o.productId = p.productId";

    List<CustomerOrder> customerWithOrdersList = jdbcTemplate.
        query(sql, new BeanPropertyRowMapper<>(CustomerOrder.class));

    log.info("Order Details: " + customerWithOrdersList.toString());
  }
}
