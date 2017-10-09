# Multi CNN

Execute the inference phase on multiple mobile devices.

## Android File Structure

```
- /storage/emulated/0/
    - multicnn/netfiles/cifar/
        - Cifar10_def.txt
        - labels.txt
        - *.msg
    - tmpImages/
        - 1507518149234.png
```

## Project Structure

```
- /app/src/main
    - java/
        - cn.alexchao.multicnn/
        - layers/
        - messagepack/
        - network/
        - numdroid/
        - util/
    - res/
        - layout/
        - mipmap/
        - values/
    - rs/
        - *.rs
    - AndroidManifest.xml
```