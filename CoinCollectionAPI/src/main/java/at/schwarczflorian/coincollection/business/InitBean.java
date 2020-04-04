package at.schwarczflorian.coincollection.business;

import io.quarkus.runtime.StartupEvent;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.transaction.Transactional;

@ApplicationScoped
public class InitBean {

    @Transactional
    public void init(@Observes StartupEvent event) {
        System.out.println("Service started!");
    }

}
