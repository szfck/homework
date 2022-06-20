## One hidden layer neural network
![](/assets/Snip20180131_28.png)

![](/assets/Snip20180131_29.png)

![](/assets/Snip20180131_31.png)


## Vectorizing across multiple examples

![](/assets/Snip20180131_33.png)

## Justification for vectorized implementation

![](/assets/Snip20180131_34.png)

----
## Forward Propagation and Back Progagation

### 1. proof of derivative for sigmoid function

$$
\sigma(z) = \frac{1}{1 + e^{-z}}
$$

$$
\sigma'(z)=\frac{e^{-z}}{(1 + e^{-z})^2}=\frac{1 + e^{-z} - 1}{(1 + e^{-z})^2} =
\frac{1}{1 + e^{-z}} - \frac{1}{(1 + e^{-z})^2}
=\sigma(z)(1 - \sigma(z))
$$



### 2. forward propagation

$$
z^{[1]} = w^{[1]}X + b^{[1]}
$$

$$
A^{[1]} = g^{[1]}(z^{[1]})
$$

$$
z^{[2]} = w^{[2]}A^{[1]} + b^{[2]}
$$

$$
A^{[2]} = g^{[2]}(z^{[2]}) = \sigma(z^{[2]})
$$

### 3. cost function of m examples
$$
J(w^{[1]}, b^{[1]}, w^{[2]}, b^{[2]}) = \frac{1}{m}\sum_{i = 1}^{m}L(\hat{y}^{(i)}, y^{(0)}) = -\frac{1}{m}\sum_{i = 1}^{m}[y^{(i)}log(\hat{y}^{(i)}) + (1 - y^{(i)})log(1 - \hat{y}^{(i)})]
$$

----
### 4. back propagation

**one step**

![](/assets/Snip20180201_38.png)

$$
L(a, y) = -ylog(a)-log(y)log(1 - a)
$$

$$
da = -\frac{y}{a} +  \frac{1 - y}{1 - a}
$$

$$
\sigma(z)' = \sigma(z)(1 - \sigma(z)) = a(1 - a) 
$$

$$
dz = da \times \sigma(z)'=a - y
$$

$$ 
dw = dz \times x 
$$

$$
db = dz 
$$

----
**two steps**

![](/assets/Snip20180201_39.png)

![](/assets/Snip20180201_40.png)
