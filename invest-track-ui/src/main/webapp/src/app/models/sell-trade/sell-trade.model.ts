import { BuyTrade } from '../buy-trade/buy-trade.model';

export class SellTrade {
    id: number;
    buyTrade: BuyTrade;
    date: Date;
    price: number;
    qty: number;
    margin: number;
    brokerage: number;
    pbt: number;
    brokerageAmount: number;
    taxes: number;
    net: number;
}
