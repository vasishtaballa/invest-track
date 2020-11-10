import { ColDef } from 'ag-grid-community';
import * as moment from 'moment';

export namespace PortfolioColDefs {
    export const COLUMN_DEFS: ColDef[] = [
        {
            headerName: 'Equity Name',
            field: 'equityName',
            resizable: true
        },
        {
            headerName: 'Trade Date',
            field: 'date',
            valueFormatter: dateFormatter,
            resizable: true
        },
        {
            headerName: 'Exchange',
            field: 'exchange',
            resizable: true
        },
        {
            headerName: 'Trade Price',
            field: 'price',
            resizable: true
        },
        {
            headerName: 'Current Price',
            field: 'currentPrice',
            resizable: true
        },
        {
            headerName: 'Quantity',
            field: 'qty',
            resizable: true
        },
        {
            headerName: 'Brokerage (%)',
            field: 'brokerage',
            valueFormatter: percentageFormatter,
            resizable: true
        },
        {
            headerName: 'Target (%)',
            field: 'target',
            valueFormatter: percentageFormatter,
            resizable: true
        },
        {
            headerName: 'Margin (%)',
            field: 'margin',
            valueFormatter: percentageFormatter,
            resizable: true
        },
        {
            headerName: 'Price Before Tax',
            field: 'pbt',
            resizable: true
        },
        {
            headerName: 'Brokerage Amount',
            field: 'brokerageAmount',
            resizable: true
        },
        {
            headerName: 'Taxes',
            field: 'taxes',
            resizable: true
        },
        {
            headerName: 'Net',
            field: 'net',
            resizable: true
        },
        {
            headerName: '',
            cellRenderer: 'sellBtnRenderer'
        },
        {
            field: 'mcSymbol',
            hide: true
        }
    ];

    function dateFormatter(params): string {
        let date: moment.Moment = moment(params.data.date);
        return date.format('DD-MMM-YYYY');
    }
    
    function percentageFormatter(params): string {
        let key = params.colDef.field;
        return params.data[key] + ' %';
    }
}