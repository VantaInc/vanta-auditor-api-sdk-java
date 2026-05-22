# AuthDetails

The vendor's authentication details.


## Fields

| Field                                                                             | Type                                                                              | Required                                                                          | Description                                                                       |
| --------------------------------------------------------------------------------- | --------------------------------------------------------------------------------- | --------------------------------------------------------------------------------- | --------------------------------------------------------------------------------- |
| `passwordMinimumLength`                                                           | *Optional\<Double>*                                                               | :heavy_check_mark:                                                                | Minimum number for chacters required for passwords for this vendor.               |
| `passwordRequiresSymbol`                                                          | *Optional\<Boolean>*                                                              | :heavy_check_mark:                                                                | Whether or not the vendor requires passwords to have a symbol.                    |
| `passwordRequiresNumber`                                                          | *Optional\<Boolean>*                                                              | :heavy_check_mark:                                                                | Whether or not the vendor requires passwords to have a number.                    |
| `passwordMFA`                                                                     | *Optional\<Boolean>*                                                              | :heavy_check_mark:                                                                | Whether or not the vendor requires passwords to have multi factor authentication. |
| `method`                                                                          | [Optional\<Method>](../../models/components/Method.md)                            | :heavy_check_mark:                                                                | The vendor's authentication method.                                               |