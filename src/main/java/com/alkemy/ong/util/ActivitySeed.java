package com.alkemy.ong.util;

import com.alkemy.ong.model.Activity;
import com.alkemy.ong.repository.ActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ActivitySeed implements CommandLineRunner {

    @Autowired
    private ActivityRepository activityRepository;

    @Override
    public void run(String... args) throws Exception {
        loadActivities();

    }

    private void loadActivities(){
        if (activityRepository.count() == 0) {
            createActivities();
        }
    }

    private void createActivities(){
        activityRepository.save(new Activity("Apoyo escolar nivel primario","El espacio de apoyo escolar es el corazon del area educativa. " +
                "Se realizan los talleres de lunes a jueves de 10 a 12 horas y de 14 a 16 horas en el contraturno.","https://images.unsplash.com/photo-1588072432836-e10032774350?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=872&q=80"));
        activityRepository.save(new Activity("Apoyo escolar nivel secundaria","Del mismo modo que en primaria, este taller es el corazon del area secundaria. " +
                "Se ralizan talleres de lunes a viernes de 10 a 12 horas y de 16 a 18 horas en el contraturno.","https://images.unsplash.com/photo-1531482615713-2afd69097998?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=870&q=80"));
        activityRepository.save(new Activity("Tutoria","Es un programa destinado a jovenes a partir del tercer año de secundaria, cuyo objetivo es garantizar su " +
                "permanencia en la escula y constuir un proyecto de vida que da sentida al colegio.","https://images.unsplash.com/photo-1523240795612-9a054b0db644?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=870&q=80"));

    }

}
