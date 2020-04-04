package at.schwarczflorian.coincollection.database;

import at.schwarczflorian.coincollection.model.Coin;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

@ApplicationScoped
@Transactional
public class CoinRepository implements PanacheRepository<Coin> {

}
