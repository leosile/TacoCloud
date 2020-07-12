package tacos.data;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import tacos.Order;
import tacos.Taco;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class JdbcOrderRepository implements OrderRepository{
    private SimpleJdbcInsert orderInserter;
    private SimpleJdbcInsert orderTacoInserter;
    private ObjectMapper objectMapper;

    public JdbcOrderRepository(JdbcTemplate jdbc) {
        this.orderInserter = new SimpleJdbcInsert(jdbc)
                .withTableName("Taco_Order")
                .usingGeneratedKeyColumns("id");

        this.orderTacoInserter = new SimpleJdbcInsert(jdbc)
                .withTableName("Taco_Order_Tacos");
        this.objectMapper = objectMapper;
    }

    @Override
    public Order save(Order order) {
        order.setPlacedAt(new Date());
        long orderId=saveOrderDetails(order);
        order.setId(orderId);
        List<Taco> tacos=order.getTacos();

        for (Taco taco:tacos){
            saveOrderTacos(taco,orderId);
        }
        return null;
    }

    private void saveOrderTacos(Taco taco, long orderId) {
        System.out.println(taco);
        Map<String,Object> values=new HashMap<>();
        values.put("tacoOrder",orderId);
        values.put("taco",taco.getId());
        orderTacoInserter.execute(values);
    }

    private long saveOrderDetails(Order order) throws ConverterFailedExceptopn {

        Map<String,Object> values = order.convertToTableValues();
        long orderId =
                orderInserter
                        .executeAndReturnKey(values)
                        .longValue();
        return orderId;
    }

}

class ConverterFailedExceptopn extends NullPointerException {
    ConverterFailedExceptopn(String msg)
    {
        super(msg);
    }
}