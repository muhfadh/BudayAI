# The Documentation from Machine Learning Path

### Eval Metrics for each models
- 1 = custom_ssd_mobilenet_v2_fpnlite_320x320_43_classes_1
- 2 = custom_ssd_mobilenet_v2_fpnlite_320x320_43_classes_2
- 3 = custom_ssd_mobilenet_v2_fpnlite_320x320_V2
- 4 = custom_ssd_mobilenet_v2_fpnlite_320x320_V3
- 5 = custom_ssd_mobilenet_v2_fpnlite_640x640_1


**Average Precision**

| No | mAP | mAP @.50IOU | mAP @.75IOU | mAP (small) | mAP (medium) | mAP (large) | steps |
| --- | --- | --- | --- | --- | --- | --- | --- |
| 1 | 0.478 | 0.687 | 0.532 | 0.177 | 0.378 | 0.494 | 15000 |
| 2 | 0.460 | 0.675 | 0.516 | 0.101 | 0.281 | 0.482 | 15000 |


**Average Recall**

| No | AR@1 | AR@10 | AR@100 | AR@100 (small) | AR@100 (medium) | AR@100 (large) | steps |
| --- | --- | --- | --- | --- | --- | --- | --- |
| 1 | 0.496 | 0.595 | 0.605 | 0.175 | 0.494 | 0.614 | 15000 |
| 2 | 0.481 | 0.567 | 0.580 | 0.100 | 0.428 | 0.595 | 15000 |


**loss**

| No | localization_loss | classification_loss | regularization_loss | total_loss | steps |
| --- | --- | --- | --- | --- | --- |
| 1 | 0.227 | 0.515 | 0.126 | 0.868 | 15000 |
| 2 | 0.244 | 0.567 | 0.124 | 0.936 | 15000 |
