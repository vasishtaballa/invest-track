import { ColDef } from "ag-grid-community";

export namespace EquityPLColumnDefs {
    export const COLUMN_DEFS: ColDef[] = [
        {
            headerName: 'Equity Symbol',
            field: 'equitySymbol',
            resizable: true,
            filter: true
        },
        {
            headerName: 'Equity Name',
            field: 'equityName',
            resizable: true,
            filter: true
        },
        {
            headerName: 'Net PL',
            field: 'netPL',
            resizable: true,
            valueFormatter: inrFormatter,
        }
    ];

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