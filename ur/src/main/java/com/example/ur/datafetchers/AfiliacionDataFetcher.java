package com.example.ur.datafetchers;

import com.example.ur.entities.Afiliacion;
import com.example.ur.entities.Sindicato;
import com.example.ur.services.AfiliacionService;
import com.example.ur.services.SindicatoService;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsMutation;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.InputArgument;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.Map;
import java.util.Optional;

@DgsComponent
public class AfiliacionDataFetcher {

    private final AfiliacionService afiliacionService;
    private final SindicatoService sindicatoService;

    @Autowired
    public AfiliacionDataFetcher(AfiliacionService afiliacionService, SindicatoService sindicatoService) {
        this.afiliacionService = afiliacionService;
        this.sindicatoService = sindicatoService;
    }

    @DgsQuery
    public Iterable<Afiliacion> afiliaciones() {
        return afiliacionService.findAll();
    }

    @DgsQuery
    public Afiliacion afiliacion(@InputArgument String id) {
        return afiliacionService.findById(Long.parseLong(id)).orElse(null);
    }

    @DgsMutation
    public Afiliacion createAfiliacion(@InputArgument Map<String, Object> input) {
        String fechaStr = (String) input.get("fechaAfiliacion");
        Long usuarioId = Long.parseLong(input.get("usuarioId").toString());
        Long sindicatoId = Long.parseLong(input.get("sindicatoId").toString());

        Optional<Sindicato> sindicatoOpt = sindicatoService.findById(sindicatoId);
        if (sindicatoOpt.isEmpty()) {
            throw new IllegalArgumentException("Sindicato not found with ID: " + sindicatoId);
        }

        Afiliacion afiliacion = new Afiliacion();
        afiliacion.setFechaAfiliacion(LocalDate.parse(fechaStr));
        afiliacion.setUsuarioId(usuarioId);
        afiliacion.setSindicato(sindicatoOpt.get());

        return afiliacionService.save(afiliacion);
    }

    @DgsMutation
    public boolean deleteAfiliacion(@InputArgument String id) {
        Long parsedId = Long.parseLong(id);
        if (afiliacionService.findById(parsedId).isPresent()) {
            afiliacionService.deleteById(parsedId);
            return true;
        }
        return false;
    }
}
