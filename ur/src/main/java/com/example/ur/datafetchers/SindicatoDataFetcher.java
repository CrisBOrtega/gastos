package com.example.ur.datafetchers;

import com.example.ur.entities.Sindicato;
import com.example.ur.services.SindicatoService;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsMutation;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.InputArgument;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;
import java.util.Optional;

@DgsComponent
public class SindicatoDataFetcher {

    private final SindicatoService sindicatoService;

    @Autowired
    public SindicatoDataFetcher(SindicatoService sindicatoService) {
        this.sindicatoService = sindicatoService;
    }

    @DgsQuery
    public Iterable<Sindicato> sindicatos() {
        return sindicatoService.findAll();
    }

    @DgsQuery
    public Sindicato sindicato(@InputArgument String id) {
        return sindicatoService.findById(Long.parseLong(id)).orElse(null);
    }

    @DgsMutation
    public Sindicato createSindicato(@InputArgument Map<String, String> input) {
        Sindicato sindicato = new Sindicato();
        sindicato.setNombre(input.get("nombre"));
        sindicato.setCodigo(input.get("codigo"));
        return sindicatoService.save(sindicato);
    }

    @DgsMutation
    public Sindicato updateSindicato(@InputArgument String id, @InputArgument Map<String, String> input) {
        Optional<Sindicato> optionalSindicato = sindicatoService.findById(Long.parseLong(id));
        if (optionalSindicato.isPresent()) {
            Sindicato sindicato = optionalSindicato.get();
            sindicato.setNombre(input.get("nombre"));
            sindicato.setCodigo(input.get("codigo"));
            return sindicatoService.save(sindicato);
        }
        return null;
    }

    @DgsMutation
    public boolean deleteSindicato(@InputArgument String id) {
        Long parsedId = Long.parseLong(id);
        if (sindicatoService.findById(parsedId).isPresent()) {
            sindicatoService.deleteById(parsedId);
            return true;
        }
        return false;
    }
}
