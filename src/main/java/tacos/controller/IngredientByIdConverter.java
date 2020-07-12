package tacos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import tacos.Ingredient;
import tacos.data.JdbcIngredinetRepository;

@Component
public class IngredientByIdConverter implements Converter<String, Ingredient> {
    private JdbcIngredinetRepository ingredinetRepo;

    @Autowired
    public IngredientByIdConverter(JdbcIngredinetRepository ingredinetRepo) {
        this.ingredinetRepo = ingredinetRepo;
    }

    @Override
    public Ingredient convert(String id) {
        return ingredinetRepo.findOne(id);
    }
}
