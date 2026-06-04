# VantaTestSnapshotOutOfScopeResources

Resources excluded from the test at snapshot time, grouped by exclusion
reason.  `testLevel` contains resources the customer disabled for the
test; `frameworkLevel` contains resources scoped out by the framework's
segment configuration.


## Fields

| Field                                                                                                        | Type                                                                                                         | Required                                                                                                     | Description                                                                                                  |
| ------------------------------------------------------------------------------------------------------------ | ------------------------------------------------------------------------------------------------------------ | ------------------------------------------------------------------------------------------------------------ | ------------------------------------------------------------------------------------------------------------ |
| `testLevel`                                                                                                  | List\<[VantaTestSnapshotExcludedItemGroup](../../models/components/VantaTestSnapshotExcludedItemGroup.md)>   | :heavy_check_mark:                                                                                           | N/A                                                                                                          |
| `frameworkLevel`                                                                                             | List\<[VantaTestSnapshotFrameworkExclusion](../../models/components/VantaTestSnapshotFrameworkExclusion.md)> | :heavy_check_mark:                                                                                           | N/A                                                                                                          |