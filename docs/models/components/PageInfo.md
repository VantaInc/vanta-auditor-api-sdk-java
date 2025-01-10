# PageInfo

Provides information about the pagination of a dataset.


## Fields

| Field                                                                                        | Type                                                                                         | Required                                                                                     | Description                                                                                  |
| -------------------------------------------------------------------------------------------- | -------------------------------------------------------------------------------------------- | -------------------------------------------------------------------------------------------- | -------------------------------------------------------------------------------------------- |
| `endCursor`                                                                                  | *Optional\<String>*                                                                          | :heavy_check_mark:                                                                           | The cursor that points to the end of the current page, or null if there is no such cursor.   |
| `hasNextPage`                                                                                | *boolean*                                                                                    | :heavy_check_mark:                                                                           | Indicates if there is another page after the current page.                                   |
| `hasPreviousPage`                                                                            | *boolean*                                                                                    | :heavy_check_mark:                                                                           | Indicates if there is a page before the current page.                                        |
| `startCursor`                                                                                | *Optional\<String>*                                                                          | :heavy_check_mark:                                                                           | The cursor that points to the start of the current page, or null if there is no such cursor. |