package at.schwarczflorian.coincollection.business;

import at.schwarczflorian.coincollection.database.CollectorRepository;
import at.schwarczflorian.coincollection.database.CurrencyRepository;
import at.schwarczflorian.coincollection.database.SourceRepository;
import at.schwarczflorian.coincollection.model.Collector;
import at.schwarczflorian.coincollection.model.Currency;
import at.schwarczflorian.coincollection.model.Source;
import io.quarkus.runtime.StartupEvent;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.transaction.Transactional;

@ApplicationScoped
class InitBean {

    @Inject
    CollectorRepository collectorRepository;
    @Inject
    CurrencyRepository currencyRepository;
    @Inject
    SourceRepository sourceRepository;

    @Transactional
    public void init(@Observes StartupEvent event) {
        System.out.println("Service started!");

        if (collectorRepository.count() == 0) {
            collectorRepository.persistAndFlush(new Collector("Franz", "Schwarcz"));
            collectorRepository.persistAndFlush(new Collector("Florian", "Schwarcz"));
        }

        if (currencyRepository.count() == 0) {
            currencyRepository.persistAndFlush(new Currency("Groschen"));
            currencyRepository.persistAndFlush(new Currency("Schilling"));
        }

        if (sourceRepository.count() == 0) {
            sourceRepository.persistAndFlush(new Source("Kaffeedose"));
            sourceRepository.persistAndFlush(new Source("Münzheft"));
        }
    }

}
