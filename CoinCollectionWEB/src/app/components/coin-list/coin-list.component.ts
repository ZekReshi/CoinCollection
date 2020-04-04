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
  showForm = false;
  newCoin: Coin = {
    id: undefined,
    value: undefined,
    preservation: undefined,
    yearOfCoinage: undefined,
    source: undefined,
    currency: undefined,
    collector: undefined
  };
  newFront: File = null;
  newBack: File = null;
  newFrontUrl = '';
  newBackUrl = '';
  validValues: number[] = [1, 2, 5, 10, 20, 50, 100, 200, 500, 1000];

  constructor(private coinService: CoinService, private currencyService: CurrencyService, private sourceService: SourceService,
              private collectorService: CollectorService) { }

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
        console.log(error.message);
      }
    );
  }

  refreshCurrencies(): void {
    this.currencyService.getAll().subscribe(
      (data) => {
        this.currencies = data;
      },
      (error) => {
        console.log(error.message);
      }
    );
  }

  refreshSources(): void {
    this.sourceService.getAll().subscribe(
      (data) => {
        this.sources = data;
      },
      (error) => {
        console.log(error.message);
      }
    );
  }

  refreshCollectors(): void {
    this.collectorService.getAll().subscribe(
      (data) => {
        this.collectors = data;
      },
      (error) => {
        console.log(error.message);
      }
    );
  }

  getFrontImageUrl(coin: Coin) {
    this.coinService.getFrontImageUrl(coin);
  }

  getBackImageUrl(coin: Coin) {
    this.coinService.getBackImageUrl(coin);
  }

  setFrontImage(files: FileList): void {
    if (files.length === 0) {
      return;
    }
    this.newFront = files[0];
    const reader = new FileReader();
    reader.readAsDataURL(this.newFront);
    reader.onload = (event) => {
      this.newFrontUrl = reader.result as string;
    };
  }

  setBackImage(files: FileList): void {
    if (files.length === 0) {
      return;
    }
    this.newBack = files[0];
    const reader = new FileReader();
    reader.readAsDataURL(this.newBack);
    reader.onload = (event) => {
      this.newBackUrl = reader.result as string;
    };
  }

  saveNewCoin(): void {
    if (!this.isNewCoinValid()) {
      return;
    }
    this.coinService.postCoin(this.newCoin).subscribe (
      (coinData) => {
        this.newCoin = coinData;
        this.coinService.postFront(this.newCoin, this.newFront).subscribe (
          (frontData) => {
            this.refreshCoins();
          },
          (error) => {
            console.log(error.message);
          }
        );
        this.coinService.postBack(this.newCoin, this.newBack).subscribe (
          (backData) => {
            this.refreshCoins();
          },
          (error) => {
            console.log(error.message);
          }
        );
        this.newCoin = {
          id: undefined,
          value: undefined,
          preservation: undefined,
          yearOfCoinage: undefined,
          source: undefined,
          currency: undefined,
          collector: undefined
        };
        this.newFront = null;
        this.newFrontUrl = '';
        this.newBack = null;
        this.newBackUrl = '';
        this.showForm = false;
      },
      (error) => {
        console.log(error.message);
      }
    );
  }

  isNewCoinValid(): boolean {
    return this.newCoin.value !== undefined &&
    this.validValues.includes(this.newCoin.value) &&
    this.newCoin.preservation !== undefined &&
    this.newCoin.preservation > 0 &&
    this.newCoin.preservation <= 10 &&
    this.newCoin.yearOfCoinage !== undefined &&
    this.newCoin.yearOfCoinage < 2019 &&
    this.newCoin.currency !== undefined &&
    this.newCoin.source !== undefined &&
    this.newCoin.collector !== undefined &&
    this.newFront != null &&
    this.newBack != null;
  }

}
