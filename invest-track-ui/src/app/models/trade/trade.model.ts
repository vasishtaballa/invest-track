export class Trade {
    id: number;
    equitySymbol: string;
    equityName: string;
    date: Date;
    exchange: string;
    mode: string;
    price: number;
    qty: number;
    target: number;
    brokerage: number;
    gross: number;
    net: number;
}
