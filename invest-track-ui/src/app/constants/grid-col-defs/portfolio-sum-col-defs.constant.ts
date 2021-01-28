import { ColDef } from "ag-grid-community";

export namespace PortfolioSummaryColDefs {
    export const COLUMN_DEFS: ColDef[] = [
        {
            headerName: 'Equity Symbol',
            field: 'equitySymbol',
            resizable: true
        },
        {
            headerName: 'Equity Name',
            field: 'equityName',
            resizable: true,
            filter: true
        },
        {
            headerName: 'Average Price',
            field: 'avgPrice',
            resizable: true,
            valueFormatter: inrFormatter
        },
        {
            headerName: 'Quantity',
            field: 'qty',
            resizable: true
        },
        {
            headerName: 'Current Price',
            field: 'currentPrice',
            valueFormatter: inrFormatter,
            resizable: true
        },
        {
            headerName: 'Net Price',
            field: 'netPrice',
            valueFormatter: inrFormatter,
            resizable: true
        },
        {
            headerName: 'Net Value',
            field: 'netValue',
            resizable: true,
            valueFormatter: inrFormatter
        },
        {
            headerName: 'Margin (%)',
            field: 'margin',
            valueFormatter: percentageFormatter,
            resizable: true,
        },
        {
            headerName: 'Net P/L',
            field: 'netPL',
            resizable: true,
            valueFormatter: inrFormatter
        }
    ];

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
}