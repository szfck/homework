## Vectorization

A lot of scaleable deep learning implementations are done on a GPU. Here we do in the jupyter notebook, where actually on the CPU. And it turns out that both GPU and CPU have parallelization instructions. They're sometimes called **SIMD** instructions. This stands for a single instruction multiple data. If you use build-in functions such as this **np.function** that don't require you explicitly implementation a for loop. **It enables Python numpy to take much better advantage of parallelism**. This is true both computations on CPUs and GPUs. **It's just that GPUs are remarkably good at these SIMD calculations**

### rule of thumb

**Whenever possible, avoid using explicit for loops**

```python
import numpy as np
import time

a = np.random.rand(1000000)
b = np.random.rand(1000000)

tic = time.time()
c = np.dot(a, b)

toc = time.time()

print(c)
print("vectorized version:" + str(1000 * (toc - tic)) + "ms")

c = 0
tic = time.time()

for i in range(1000000):
    c += a[i] * b[i]
toc = time.time()

print(c)
print("for loop:" + str(1000 * (toc - tic)) + "ms")
```

### result
```
249684.570579
vectorized version:0.9090900421142578ms
249684.570579
for loop:424.8027801513672ms
```

## Vectorizing Logistic Regression

**single iteration of gradient descent for logistic regression**

![](/assets/Snip20180130_23.png)

![](/assets/Snip20180130_26.png)

![](/assets/Snip20180130_27.png)