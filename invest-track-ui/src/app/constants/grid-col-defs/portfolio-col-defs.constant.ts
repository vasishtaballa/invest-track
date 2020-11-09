import { ColDef } from 'ag-grid-community';
import * as moment from 'moment';

export namespace PortfolioColDefs {
    export const COLUMN_DEFS: ColDef[] = [
        {
            headerName: 'Equity Name',
            field: 'equityName',
            suppressSizeToFit: false
        },
        {
            headerName: 'Trade Date',
            field: 'date',
            valueFormatter: dateFormatter,
            suppressSizeToFit: false
        },
        {
            headerName: 'Exchange',
            field: 'exchange',
            suppressSizeToFit: false
        },
        {
            headerName: 'Trade Price',
            field: 'price',
            suppressSizeToFit: false
        },
        {
            headerName: 'Current Price',
            field: 'currentPrice',
            suppressSizeToFit: false
        },
        {
            headerName: 'Quantity',
            field: 'qty',
            suppressSizeToFit: false
        },
        {
            headerName: 'Brokerage (%)',
            field: 'brokerage',
            suppressSizeToFit: false
        },
        {
            headerName: 'Target (%)',
            field: 'target',
            suppressSizeToFit: false
        },
        {
            headerName: 'Margin (%)',
            field: 'margin',
            suppressSizeToFit: false
        },
        {
            headerName: 'Gross',
            field: 'gross',
            suppressSizeToFit: false
        },
        {
            headerName: 'Net',
            field: 'net',
            suppressSizeToFit: false
        },
        {
            field: 'mcSymbol',
            suppressSizeToFit: false,
            hide: true
        }
    ];

    function dateFormatter(params): string {
        let date: moment.Moment = moment(params.data.date);
        return date.format('DD-MMM-YYYY');
    }
}