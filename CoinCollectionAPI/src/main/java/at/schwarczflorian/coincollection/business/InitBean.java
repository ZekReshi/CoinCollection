package at.schwarczflorian.coincollection.business;

import io.quarkus.runtime.StartupEvent;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;

@ApplicationScoped
class InitBean {

    public void init(@Observes StartupEvent event) {
        System.out.println("Service started!");
    }

}
