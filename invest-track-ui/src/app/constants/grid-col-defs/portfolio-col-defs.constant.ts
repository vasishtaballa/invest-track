import { ColDef } from 'ag-grid-community';
import * as moment from 'moment';

export namespace PortfolioColDefs {
    export const COLUMN_DEFS: ColDef[] = [
        {
            headerName: 'Equity Name',
            field: 'equityName',
            resizable: true,
            filter: true
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
            resizable: true,
            valueFormatter: inrFormatter
        },
        {
            headerName: 'Current Price',
            field: 'currentPrice',
            resizable: true,
            valueFormatter: inrFormatter
        },
        {
            headerName: 'Quantity',
            field: 'balQty',
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
            resizable: true,
            cellClass: getMarginCellClass
        },
        {
            headerName: 'Price Before Tax',
            field: 'pbt',
            resizable: true,
            valueFormatter: inrFormatter
        },
        {
            headerName: 'Brokerage Amount',
            field: 'brokerageAmount',
            resizable: true,
            valueFormatter: inrFormatter
        },
        {
            headerName: 'Taxes',
            field: 'taxes',
            resizable: true,
            valueFormatter: inrFormatter
        },
        {
            headerName: 'Net',
            field: 'net',
            resizable: true,
            valueFormatter: inrFormatter
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
        return params.data[key] + '%';
    }

    function inrFormatter(params): string {
        let key = params.colDef.field;
        let value = params.data[key];
        if (value != null) {
            return Number(value).toLocaleString('en-IN', {
                style: 'currency',
                currency: 'INR',
                maximumFractionDigits: 2
            });
        }
        return Number(0).toLocaleString('en-IN', {
            style: 'currency',
            currency: 'INR',
            maximumFractionDigits: 2
        });
    }

    function getMarginCellClass(params): string[] {
        let classes: string[] = [];
        let margin = params.data.margin;
        if (margin < 0)
            classes.push('loss');
        else
            classes.push('profit');
        return classes;
    }
}