package com.brainon.gestordeproyectos.usecases;

import com.brainon.gestordeproyectos.Service.SendMailService;
import com.brainon.gestordeproyectos.collections.Project;
import com.brainon.gestordeproyectos.collections.User;
import com.brainon.gestordeproyectos.model.ComentaryDTO;
import com.brainon.gestordeproyectos.model.ProjectDTO;
import com.brainon.gestordeproyectos.repositories.ComentaryRepository;
import com.brainon.gestordeproyectos.repositories.ProjectRepository;
import com.brainon.gestordeproyectos.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Objects;

@Service
@Validated
public class AddComentaryUseCase implements SaveComentary{

    private final ComentaryRepository comentaryRepository;
    private final UserRepository userRepository;
    private final MapperUtils mapperUtils;
    private final GetUseCase getUseCase;
    private final SendMailService sendMailService;
    private final ProjectRepository projectRepository;

    public AddComentaryUseCase(ComentaryRepository comentaryRepository, UserRepository userRepository, MapperUtils mapperUtils, GetUseCase getUseCase, SendMailService sendMailService, ProjectRepository projectRepository) {
        this.comentaryRepository = comentaryRepository;
        this.userRepository = userRepository;
        this.mapperUtils = mapperUtils;
        this.getUseCase = getUseCase;
        this.sendMailService = sendMailService;
        this.projectRepository = projectRepository;
    }

    /*@Override
    public Mono<Void> saveComentary(ComentaryDTO comentaryDTO) {
        return comentaryRepository.save(mapperUtils.mapperToComentary().apply(comentaryDTO))
                .and(userRepository.findByUid(comentaryDTO.getUserId()).flatMap(user -> { return projectRepository.findById(comentaryDTO.getProjectId()).flatMap(project -> {
                    return sendMailService.sendMail(user.getEmail(),
                            "Hay actualizaciones de tu proyecto: " + project.getProject() + " en Gestion brainon24",
                            "<table style=\"width:100%;margin: 10px 50px;\">" +
                                    "Hola, " + user.getName() +
                                    "<tr>" +
                                    "<td style=\"background:#0d6efd; color:#ffffff;\"><h2>Actualizaciones: " + comentaryDTO.getComentary() + "</h2></td>" +
                                    "</tr>" +
                                    "</table>");
                });
                }));
    }*/

    /*@Override
    public Mono<ProjectDTO> saveComentary(ComentaryDTO comentaryDTO){
        return comentaryRepository.save(mapperUtils.mapperToComentary().apply(projectDTO))
                .and(userRepository.findByUid(projectDTO.getUserId()).flatMap(user -> { return projectRepository.findById(projectDTO.getUserId()).flatMap(project -> {
                    return sendMailService.sendMail(user.getEmail(),
                            "Hay actualizaciones de tu proyecto: " + project.getProject() + " en Gestion brainon24",
                            "<table style=\"width:100%;margin: 10px 50px;\">" +
                                    "Hola, " + user.getName() +
                                    "<tr>" +
                                    "<td style=\"background:#0d6efd; color:#ffffff;\"><h2>Actualizaciones: " + comentaryDTO.getComentary() + "</h2></td>" +
                                    "</tr>" +
                                    "</table>");
                });
                }));
    }*/





    @Override
    public Mono<ComentaryDTO> saveComentary(ComentaryDTO comentaryDTO) {

        LocalDateTime fechaYHora = LocalDateTime.now();
        DateTimeFormatter isoHora = DateTimeFormatter.ISO_LOCAL_TIME;
        String horaConvertida = fechaYHora.format(isoHora).toString();
        String[] hora = horaConvertida.split("\\.");
        DateTimeFormatter isoFecha = DateTimeFormatter.ISO_LOCAL_DATE;
        String fechaConvertida = fechaYHora.format(isoFecha).toString();
        ArrayList<String> fechaHora = new ArrayList<String>();
        fechaHora.add(fechaConvertida);
        fechaHora.add(hora[0]);
        comentaryDTO.setFechaCreacio(fechaHora);

        projectRepository.findById(comentaryDTO.getProjectId()).flatMap(project -> userRepository.findByUid(project.getUserId()).flatMap(user ->
                sendMailService.sendMail(user.getEmail(),
                        "Hay actualizaciones de tu proyecto: " + project.getProject() + " en Gestion brainon24",
                        "<table style=\"width:100%;margin: 10px 50px;\">" +
                                "<tr>" +
                                "Hola, " + user.getName() +
                                "<br />" +
                                "<td style=\"background:#0d6efd; color:#ffffff;\"><h2>Actualizaciones: " + comentaryDTO.getComentary() + "</h2></td>" +
                                "</tr>" +
                                "</table>"))).subscribe();
        return comentaryRepository.save(mapperUtils.mapperToComentary().apply(comentaryDTO)).map(comentary -> mapperUtils.mapEntityToComentary().apply(comentary));
    }

}

