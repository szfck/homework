## Activation functions

If you're using binary classification, then the **sigmoid** function is very natural for the output layer. And the for all other units on **ReLU** (rectified linear unit), is increasingly the default choice of activation function.

The derivative of the activation function, the slope of the activation function is very different from zero. So in practice using the **ReLU** activation function, your neural network will often learn much faster than using the tanh or the sigmoid activation function. The main reason is that there is less of this effect of the slope of the function going to zero, which slows down learning.

$$
\sigma(z) = \frac{1}{1 + e^{-z}}
$$

$$
tanh(z) = \frac{e^z - e^{-z}}{e^z + e^{-z}}
$$


$$
ReLU(z) = max(0, z)
$$

$$
Leaky ReLU = max(0.01z, z)
$$

![](/assets/Snip20180131_35.png)


## why need non linear activation function

It turns out that if you use a linear activation function, then no matter how many layers your neural network has, always doing is just a **linear activation function**. So you might as well not have any hidden layers. 


## hyperparameters

### Parameters

$$ W^{[1]}, b^{[1]}, W^{[2]}, b^{[2]} ... $$

### Hyperparameters

- Learning rate $$\alpha$$
- \#iterations
- \#hidden layer $$L$$
- \#hidden units $$n^{[1]}$$
- choice of activation 







