# The Documentation from Machine Learning Path

### Eval Metrics for each models
- 1 = custom_ssd_mobilenet_v2_fpnlite_320x320_43_classes_1      | BATCH_SIZE = 12 | min_depth = 16
- 2 = custom_ssd_mobilenet_v2_fpnlite_320x320_43_classes_2      | BATCH_SIZE = 12 | min_depth = 8
- 3 = custom_ssd_mobilenet_v2_fpnlite_320x320_V2                | BATCH_SIZE = 8
- 4 = custom_ssd_mobilenet_v2_fpnlite_320x320_V3                | BATCH_SIZE = 12
- 5 = custom_ssd_mobilenet_v2_fpnlite_640x640_1                 | BATCH_SIZE = 4
- 6* = custom_centernet_resnet101_v1_fpn_512x512                | BATCH_SIZE = 2


**Average Precision**

| No | mAP | mAP @.50IOU | mAP @.75IOU | mAP (small) | mAP (medium) | mAP (large) | steps | total_classes | 
| --- | --- | --- | --- | --- | --- | --- | --- | --- |
| 1 | 0.478 | 0.687 | 0.532 | 0.177 | 0.378 | 0.494 | 15000 | 43 |
| 2 | 0.460 | 0.675 | 0.516 | 0.101 | 0.281 | 0.482 | 15000 | 43 |
| 3 | 0.446 | 0.678 | 0.518 | -1 | 0.264 | 0.449 | 20000 | 21 |
| 4 | 0.453 | 0.677 | 0.498 | -1 | 0.209 | 0.459 | 15000 | 21 |
| 5 | 0.320 | 0.551 | 0.346 | -1 | 0.223 | 0.322 | 15000 | 21 |
| 6 | 0.355 | 0.524 | 0.385 | -1 | 0.148 | 0.358 | 4200 (initial steps 15000) | 21 |


**Average Recall**

| No | AR@1 | AR@10 | AR@100 | AR@100 (small) | AR@100 (medium) | AR@100 (large) | steps | total_classes |
| --- | --- | --- | --- | --- | --- | --- | --- | --- |
| 1 | 0.496 | 0.595 | 0.605 | 0.175 | 0.494 | 0.614 | 15000 | 43 |
| 2 | 0.481 | 0.567 | 0.580 | 0.100 | 0.428 | 0.595 | 15000 | 43 |
| 3 | 0.455 | 0.570 | 0.577 | -1| 0.390 | 0.574 | 20000 | 21 |
| 4 | 0.450 | 0.562 | 0.575 | -1| 0.300 | 0.578 | 15000 | 21 |
| 5 | 0.375 | 0.514 | 0.536 | -1| 0.313 | 0.536 | 15000 | 21 |
| 6 | 0.459 | 0.625 | 0.634 | -1| 0.295 | 0.640 | 4200 (initial steps 15000) | 21 |


**loss**

| No | localization_loss | classification_loss | regularization_loss | total_loss | steps | total_classes |
| --- | --- | --- | --- | --- | --- |  --- |
| 1 | 0.227 | 0.515 | 0.126 | 0.868 | 15000 | 43 |
| 2 | 0.244 | 0.567 | 0.124 | 0.936 | 15000 | 43 |
| 3 | 0.281 | 0.588 | 0.110 | 0.978 | 20000 | 21 |
| 4 | 0.290 | 0.602 | 0.110 | 1.001 | 15000 | 21 |
| 5 | 0.341 | 0.557 | 0.141 | 1.039 | 15000 | 21 |
| 6* | loss_box_offset = 0.506 | loss_box_scale = 1.168 | loss_object_center = 2.327 | 4.000 | 4200 (initial steps 15000) | 21 |
