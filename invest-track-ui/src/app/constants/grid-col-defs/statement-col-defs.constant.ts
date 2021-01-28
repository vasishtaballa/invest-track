import * as moment from "moment";

export namespace StatementColDefs {
    export const COLUMN_DEFS = [
        {
            headerName: 'Trade Date',
            field: 'date',
            resizable: true,
            valueFormatter: dateFormatter,
        },
        {
            headerName: 'Type',
            field: 'type',
            resizable: true,
            cellClass: getTypeClass
        },
        {
            headerName: 'Equity Name',
            field: 'equityName',
            resizable: true,
            filter: true
        },
        {
            headerName: 'Quantity',
            field: 'qty',
            resizable: true
        },
        {
            headerName: 'Price',
            field: 'price',
            valueFormatter: inrFormatter,
            resizable: true
        },
        {
            headerName: 'Net Price',
            field: 'net',
            valueFormatter: inrFormatter,
            resizable: true
        }
    ];

    function dateFormatter(params): string {
        let date: moment.Moment = moment(params.data.date);
        return date.format('DD-MMM-YYYY');
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

    function getTypeClass(params): string[] {
        let classes: string[] = [];
        let type = params.data.type;
        if (type === 'SELL')
            classes.push('loss');
        else
            classes.push('profit');
        return classes;
    }
}