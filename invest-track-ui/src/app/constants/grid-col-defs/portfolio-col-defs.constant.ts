import { ColDef } from 'ag-grid-community';

export namespace PortfolioColDefs {
    export const COLUMN_DEFS: ColDef[] = [
        {
            headerName: 'Equity Name',
            field: 'equityName'
        },
        {
            headerName: 'Trade Date',
            field: 'date'
        },
        {
            headerName: 'Exchange',
            field: 'exchange'
        },
        {
            headerName: 'Price',
            field: 'price'
        },
        {
            headerName: 'Quantity',
            field: 'qty'
        },
        {
            headerName: 'Target',
            field: 'target'
        },
        {
            headerName: 'Gross',
            field: 'gross'
        },
        {
            headerName: 'Net',
            field: 'net'
        }
    ]
}