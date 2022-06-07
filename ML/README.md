# The Documentation from Machine Learning Path

### Eval Metrics for each models
- 1 = custom_ssd_mobilenet_v2_fpnlite_320x320_42_classes_1      | BATCH_SIZE = 12 | training time = 2h 29m 41s
- 2 = custom_ssd_mobilenet_v2_fpnlite_320x320_42_classes_2      | BATCH_SIZE = 14 | training time = 10h 34m 37s (error in tensorboard, actually 3-4h)
- 3 = custom_ssd_mobilenet_v2_fpnlite_320x320_42_classes_3      | BATCH_SIZE = 12 | gamma: 2.25 | alpha: 0.5 | training time = 2h 43m 46s
- 4 = custom_ssd_mobilenet_v2_fpnlite_320x320_42_classes_4      | BATCH_SIZE = 12 | min_depth = 8 | training time = 2h 41m 0s
- 5 = custom_ssd_mobilenet_v2_fpnlite_320x320_V2                | BATCH_SIZE = 8
- 6 = custom_ssd_mobilenet_v2_fpnlite_320x320_V3                | BATCH_SIZE = 12
- 7 = custom_ssd_mobilenet_v2_fpnlite_640x640_1                 | BATCH_SIZE = 4
- 8* = custom_centernet_resnet101_v1_fpn_512x512                | BATCH_SIZE = 2


**Average Precision**

| No | mAP | mAP @.50IOU | mAP @.75IOU | mAP (small) | mAP (medium) | mAP (large) | steps | total_classes | 
| --- | --- | --- | --- | --- | --- | --- | --- | --- |
| 1 | 0.410 | 0.621 | 0.454 | 0.101 | 0.294 | 0.425 | 15000 | 42 | 
| 2 | 0.459 | 0.660 | 0.526 | 0.151 | 0.303 | 0.473 | 15000 | 42 | 
| 3 | 0.427 | 0.623 | 0.471 | 0.101 | 0.294 | 0.442 | 15000 | 42 | 
| 4 | 0.457 | 0.667 | 0.547 | 0.050 | 0.321 | 0.480 | 15000 | 42 | 2h 41m
| 5 | 0.446 | 0.678 | 0.518 | -1 | 0.264 | 0.449 | 20000 | 21 |
| 6 | 0.453 | 0.677 | 0.498 | -1 | 0.209 | 0.459 | 15000 | 21 |
| 7 | 0.320 | 0.551 | 0.346 | -1 | 0.223 | 0.322 | 15000 | 21 |
| 8 | 0.355 | 0.524 | 0.385 | -1 | 0.148 | 0.358 | 4200 (initial steps 15000) | 21 |


**Average Recall**

| No | AR@1 | AR@10 | AR@100 | AR@100 (small) | AR@100 (medium) | AR@100 (large) | steps | total_classes |
| --- | --- | --- | --- | --- | --- | --- | --- | --- |
| 1 | 0.446 | 0.540 | 0.550 | 0.100 | 0.433 | 0.549 | 15000 | 42 |
| 2 | 0.467 | 0.584 | 0.591 | 0.150 | 0.443 | 0.600 | 15000 | 42 |
| 3 | 0.473 | 0.562 | 0.568 | 0.100 | 0.456 | 0.574 | 15000 | 42 |
| 4 | 0.481 | 0.593 | 0.604 | 0.050 | 0.441 | 0.628 | 15000 | 42 |
| 5 | 0.455 | 0.570 | 0.577 | -1| 0.390 | 0.574 | 20000 | 21 |
| 6 | 0.450 | 0.562 | 0.575 | -1| 0.300 | 0.578 | 15000 | 21 |
| 7 | 0.375 | 0.514 | 0.536 | -1| 0.313 | 0.536 | 15000 | 21 |
| 8 | 0.459 | 0.625 | 0.634 | -1| 0.295 | 0.640 | 4200 (initial steps 15000) | 21 |


**loss**

| No | localization_loss | classification_loss | regularization_loss | total_loss | steps | total_classes |
| --- | --- | --- | --- | --- | --- |  --- |
| 1 | 0.257 | 0.577 | 0.123 | 0.958 | 15000 | 42 |
| 2 | 0.239 | 0.581 | 0.117 | 0.938 | 15000 | 42 |
| 3 | 0.258 | 0.800 | 0.138 | 1.196 | 15000 | 42 |
| 4 | 0.233 | 0.520 | 0.125 | 0.878 | 15000 | 42 |
| 5 | 0.281 | 0.588 | 0.110 | 0.978 | 20000 | 21 |
| 6 | 0.290 | 0.602 | 0.110 | 1.001 | 15000 | 21 |
| 7 | 0.341 | 0.557 | 0.141 | 1.039 | 15000 | 21 |
| 8* | loss_box_offset = 0.506 | loss_box_scale = 1.168 | loss_object_center = 2.327 | 4.000 | 4200 (initial steps 15000) | 21 |
