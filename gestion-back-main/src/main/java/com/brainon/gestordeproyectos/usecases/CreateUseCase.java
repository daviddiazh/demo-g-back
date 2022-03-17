package com.brainon.gestordeproyectos.usecases;

import com.brainon.gestordeproyectos.collections.Project;
import com.brainon.gestordeproyectos.model.ProjectDTO;
import com.brainon.gestordeproyectos.repositories.ProjectRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

@Service
@Validated
public class CreateUseCase implements SaveProject{

    private final ProjectRepository projectRepository;
    private final MapperUtils mapperUtils;

    public CreateUseCase(ProjectRepository projectRepository, MapperUtils mapperUtils) {
        this.projectRepository = projectRepository;
        this.mapperUtils = mapperUtils;
    }

    @Override
    public Mono<String> apply(ProjectDTO newProject){

        LocalDateTime fechaYHora = LocalDateTime.now();
        DateTimeFormatter isoHora = DateTimeFormatter.ISO_LOCAL_TIME;
        String horaConvertida = fechaYHora.format(isoHora).toString();
        String[] hora = horaConvertida.split("\\.");
        DateTimeFormatter isoFecha = DateTimeFormatter.ISO_LOCAL_DATE;
        String fechaConvertida = fechaYHora.format(isoFecha).toString();
        ArrayList<String> fechaHora = new ArrayList<String>();
        fechaHora.add(fechaConvertida);
        fechaHora.add(hora[0]);
        newProject.setFechaCreacion(fechaHora);

        return projectRepository
                .save(mapperUtils.mapperToProject(null).apply(newProject))
                .map(Project::getId);

    }

}
