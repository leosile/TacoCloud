package tacos.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import tacos.Ingredient;
import tacos.Taco;

import java.sql.Timestamp;
import java.sql.Types;
import java.util.Arrays;
import java.util.Date;

@Repository
public class JdbcTacoRepository implements TacoRepository{
    private JdbcTemplate jdbc;

    @Autowired
    public JdbcTacoRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public Taco save(Taco taco) {
        long tacoId= saveTacoInfo(taco);
        taco.setId(tacoId);
        for (Ingredient ingredient:taco.getIngredients()){
            saveIngredientToTaco(ingredient,taco.getId());
        }
        return taco;
    }

    private void saveIngredientToTaco(Ingredient ingredient, Long id) {
        jdbc.update(
                "INSERT INTO Taco_Ingredients (taco,ingredient) "+
                    " VALUES (? , ?)",
                id,ingredient.getId());
    }

    private long saveTacoInfo(Taco taco) {

        taco.setCreatedAt(new Date());
        PreparedStatementCreatorFactory pscFactory= new PreparedStatementCreatorFactory(
                        "INSERT INTO Taco (name,createdAt) VALUES (?,?)",
                        Types.VARCHAR,Types.TIMESTAMP);
        pscFactory.setReturnGeneratedKeys(true);

        PreparedStatementCreator psc=  pscFactory.newPreparedStatementCreator(
                        Arrays.asList(taco.getName(),new Timestamp(taco.getCreatedAt().getTime())));

        KeyHolder keyHolder= new GeneratedKeyHolder();
        jdbc.update(psc,keyHolder);
        if (keyHolder==null)
        {
            return 100;
        }
        return keyHolder.getKey().longValue();
    }
}
