import { ColDef } from 'ag-grid-community';
import * as moment from 'moment';

export namespace DepositsColDefs {
    export const COLUMN_DEFS: ColDef[] = [
        {
            headerName: 'Date',
            field: 'date',
            resizable: true,
            valueFormatter: dateFormatter
        },
        {
            headerName: 'Amount',
            field: 'amount',
            resizable: true
        }
    ];

    function dateFormatter(params): string {
        let date: moment.Moment = moment(params.data.date);
        return date.format('DD-MMM-YYYY');
    }
}