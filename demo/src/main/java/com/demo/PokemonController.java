package com.demo;
import com.opencsv.exceptions.CsvValidationException;

import com.opencsv.CSVReader;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


@Controller
public class PokemonController {

    @GetMapping("/pokemon")
    public String getPokemonData(Model model) {
        ArrayList<Object> pokemonList = new ArrayList<>();
        String filePath = "C://Users//User5//Desktop//pokemon.csv";

        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            String[] nextLine;
            while ((nextLine = reader.readNext()) != null) {

                Pokemon pokemon = new Pokemon();

                pokemon.setNumber(Integer.parseInt(nextLine[0]));
                pokemon.setName(nextLine[1]);
                pokemon.setType1(nextLine[2]);
                pokemon.setType2(nextLine[3]);
                pokemon.setTotal(Integer.parseInt(nextLine[4]));
                pokemon.setHP(Integer.parseInt(nextLine[5]));
                pokemon.setAttack(Integer.parseInt(nextLine[6]));
                pokemon.setDefense(Integer.parseInt(nextLine[7]));
                pokemon.setSpAtk(Integer.parseInt(nextLine[8]));
                pokemon.setSpDef(Integer.parseInt(nextLine[9]));
                pokemon.setSpeed(Integer.parseInt(nextLine[10]));
                pokemon.setGeneration(Integer.parseInt(nextLine[11]));
                pokemon.setLegendary(nextLine[12]);
            }
        }catch (IOException | CsvValidationException | NumberFormatException e) {
            e.printStackTrace();
            model.addAttribute("errorMessage", "Error reading CSV file: " + e.getMessage());
            return "error";
        }

        model.addAttribute("pokemonList", pokemonList);
        return "pokemon";
    }
}
