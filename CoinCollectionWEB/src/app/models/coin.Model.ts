import { Source } from './source.Model';
import { Currency } from './currency.Model';
import { Collector } from './collector.Model';

export interface Coin {
    id: number,
    value: number,
    preservation: number,
    yearOfCoinage: number,
    front: File,
    back: File,
    source: Source,
    currency: Currency,
    collector: Collector
}
