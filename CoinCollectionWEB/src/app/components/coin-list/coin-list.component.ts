import { Component, OnInit } from '@angular/core';
import { Coin } from 'src/app/models/coin.Model';
import { CoinService } from 'src/app/services/coin.service';
import { CurrencyService } from 'src/app/services/currency.service';
import { SourceService } from 'src/app/services/source.service';
import { CollectorService } from 'src/app/services/collector.service';
import { Currency } from 'src/app/models/currency.Model';
import { Source } from 'src/app/models/source.Model';
import { Collector } from 'src/app/models/collector.Model';

@Component({
  selector: 'app-coin-list',
  templateUrl: './coin-list.component.html',
  styleUrls: ['./coin-list.component.css']
})
export class CoinListComponent implements OnInit {
  coins: Coin[];
  currencies: Currency[];
  sources: Source[];
  collectors: Collector[];
  showForm: boolean = false;
  newCoin: Coin = {
    id: undefined,
    value: undefined,
    preservation: undefined,
    yearOfCoinage: undefined,
    front: undefined,
    back: undefined,
    source: undefined,
    currency: undefined,
    collector: undefined
  };

  constructor(private coinService: CoinService, private currencyService: CurrencyService, private sourceService: SourceService, private collectorService: CollectorService) { }

  ngOnInit() {
    this.refreshCoins();
    this.refreshCurrencies();
    this.refreshSources();
    this.refreshCollectors();
  }

  refreshCoins(): void {
    this.coinService.getAll().subscribe(
      (data) => {
        this.coins = data;
      },
      (error) => {
        alert(error.message);
      }
    )
  }

  refreshCurrencies(): void {
    this.currencyService.getAll().subscribe(
      (data) => {
        this.currencies = data;
      },
      (error) => {
        alert(error.message);
      }
    )
  }

  refreshSources(): void {
    this.sourceService.getAll().subscribe(
      (data) => {
        this.sources = data;
      },
      (error) => {
        alert(error.message);
      }
    )
  }

  refreshCollectors(): void {
    this.collectorService.getAll().subscribe(
      (data) => {
        this.collectors = data;
      },
      (error) => {
        alert(error.message);
      }
    )
  }

  getFrontImageUrl(id: number) {
    this.coinService.getFrontImageUrl(id);
  }

  getBackImageUrl(id: number) {
    this.coinService.getBackImageUrl(id);
  }

}
